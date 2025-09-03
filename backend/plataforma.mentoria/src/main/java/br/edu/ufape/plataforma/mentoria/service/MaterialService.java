package br.edu.ufape.plataforma.mentoria.service;

import br.edu.ufape.plataforma.mentoria.dto.MaterialDTO;
import br.edu.ufape.plataforma.mentoria.enums.MaterialType;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MaterialMapper;
import br.edu.ufape.plataforma.mentoria.model.Material;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.MaterialRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final UserRepository userRepository;
    private final MaterialMapper materialMapper;
    private final Path uploadDir = Paths.get("upload");

    @Autowired
    public MaterialService(MaterialRepository materialRepository,
                           UserRepository userRepository,
                           MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.userRepository = userRepository;
        this.materialMapper = materialMapper;

        // Criar diretório de upload se não existir
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar o diretório de upload", e);
        }
    }

    /**
     * Persiste um novo material de apoio no banco de dados.
     * Para vídeos e documentos, o arquivo é salvo na pasta "upload".
     *
     * @param materialDTO DTO com dados do material
     * @param arquivo Arquivo a ser salvo (quando for documento ou vídeo)
     * @param userID ID do user que está fazendo o upload
     * @return Material persistido com ID gerado
     * @throws IOException Se houver erro ao salvar o arquivo
     */
    public MaterialDTO createMaterial(MaterialDTO materialDTO, MultipartFile arquivo, Long userID) throws IOException {
        // Buscar mentor pelo ID
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException(Mentor.class, userID));

        // Converter DTO para entidade
        Material material = materialMapper.toEntity(materialDTO);

        // Tratamento específico para LINK
        if (material.getMaterialType() == MaterialType.LINK) {
            // Garantir que filePath seja null para LINK
            material.setFilePath(null);
            // A URL já deve estar definida no objeto material
        }
        // Verificar se é um tipo que requer arquivo (vídeo ou documento)
        else if ((material.getMaterialType() == MaterialType.VIDEO ||
                material.getMaterialType() == MaterialType.DOCUMENTO) &&
                arquivo != null && !arquivo.isEmpty()) {

            // Gerar nome único para o arquivo
            String nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
            Path caminhoCompleto = uploadDir.resolve(nomeArquivo);

            // Salvar arquivo no sistema de arquivos
            Files.copy(arquivo.getInputStream(), caminhoCompleto);

            // Atualizar o caminho do arquivo no material
            material.setFilePath(caminhoCompleto.toString());
        }

        // Associar o material ao mentor
        material.setUserUploader(user);

        // Persistir o material no banco de dados
        Material materialSalvo = materialRepository.save(material);

        // Retornar DTO do material salvo
        return materialMapper.toDTO(materialSalvo);
    }

    /**
     * Busca um material pelo ID
     *
     * @param id ID do material a ser buscado
     * @return DTO do material encontrado
     * @throws EntityNotFoundException se o material não for encontrado
     */
    public MaterialDTO getMaterialById(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Material.class, id));

        return materialMapper.toDTO(material);
    }

    public List<MaterialDTO> listarTodos() {
        List<Material> materiais = materialRepository.findAll();
        return materiais.stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }
}

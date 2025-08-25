package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;

import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import br.edu.ufape.plataforma.mentoria.service.contract.MentoredServiceInterface;
import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.dto.UpdateMentoredDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.repository.MentoredRepository;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MentoredService implements MentoredServiceInterface {

    private final MentoredRepository mentoredRepository;
    private final MentoredSearchService mentoredSearchService;
    private final MentoredMapper mentoredMapper;
    private final UserRepository userRepository;

    public MentoredService(MentoredRepository mentoredRepository,
                           MentoredSearchService mentoredSearchService,
                           MentoredMapper mentoredMapper,
                           UserRepository userRepository) {
        this.mentoredRepository = mentoredRepository;
        this.mentoredSearchService = mentoredSearchService;
        this.mentoredMapper = mentoredMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<Mentored> getAllMentored() {
        return mentoredRepository.findAll();
    }

    @Override
    public MentoredDTO createMentored(MentoredDTO mentoredDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail);
        Mentored mentored = mentoredMapper.toEntity(mentoredDTO);

        if (mentoredRepository.existsByCpf(mentored.getCpf())) {
            throw new AttributeAlreadyInUseException("CPF", mentored.getCpf(), Mentored.class);
        }

        mentored.setUser(user);
        Mentored savedMentored = mentoredRepository.save(mentored); // Salva o objeto já configurado
        return mentoredMapper.toDTO(savedMentored);
    }
    @Override
    public Mentored updateMentored(Long id, Mentored mentored) {
        if (mentoredRepository.existsById(id)) {
            mentored.setId(id);
            return mentoredRepository.save(mentored);
        }
        throw new EntityNotFoundException(Mentored.class, id);
    }
    @Override
    public MentoredDTO updateMentored(Long id, MentoredDTO mentoredDTO) {
        Mentored existingMentored = mentoredSearchService.getMentoredById(id);
        
        Mentored mentoredToUpdate = mentoredMapper.toEntity(mentoredDTO);
        
        mentoredToUpdate.setId(id);
        mentoredToUpdate.setUser(existingMentored.getUser());
        
        Mentored updatedMentored = mentoredRepository.save(mentoredToUpdate);
        
        return mentoredMapper.toDTO(updatedMentored);
    }
    @Override
    public Mentored updateMentored(Long id, UpdateMentoredDTO dto) {
        Mentored mentored = mentoredSearchService.getMentoredById(id);

        if (dto.getFullName() != null) {
            mentored.setFullName(dto.getFullName());
        }

        if (dto.getBirthDate() != null) {
            mentored.setBirthDate(dto.getBirthDate());
        }

        if (dto.getCourse() != null) {
            mentored.setCourse(dto.getCourse());
        }

        if (dto.getAcademicSummary() != null) {
            mentored.setAcademicSummary(dto.getAcademicSummary());
        }

        if (dto.getInterestArea() != null) {
            mentored.setInterestArea(dto.getInterestArea());
        }

        return mentoredRepository.save(mentored);
    }
    @Override
    public void deleteById(Long id) {
        if (!mentoredRepository.existsById(id)) {
            throw new EntityNotFoundException(Mentored.class, id);
        }
        mentoredRepository.deleteById(id);
    }


}
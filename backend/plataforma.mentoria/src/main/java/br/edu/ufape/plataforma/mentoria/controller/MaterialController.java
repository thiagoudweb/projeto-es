package br.edu.ufape.plataforma.mentoria.controller;

import br.edu.ufape.plataforma.mentoria.dto.MaterialDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.service.AuthService;
import br.edu.ufape.plataforma.mentoria.service.MaterialService;
import br.edu.ufape.plataforma.mentoria.service.MentorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/materiais")
public class MaterialController {

    private final AuthService authService;
    private final MaterialService materialService;


    @Autowired
    public MaterialController(MaterialService materialService, AuthService authService) {
        this.materialService = materialService;

        this.authService = authService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MaterialDTO> createMaterial(
            @RequestPart("material") MaterialDTO materialDTO,
            @RequestPart(name = "arquivo", required = false) MultipartFile arquivo
            ) {

        try {
            Long userId = authService.getCurrentUser().getId();

            MaterialDTO savedMaterialDTO = materialService.createMaterial(materialDTO, arquivo, userId);
            return new ResponseEntity<>(savedMaterialDTO, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDTO> getMaterialById(@PathVariable Long id) {
        try {
            MaterialDTO material = materialService.getMaterialById(id);
            return ResponseEntity.ok(material);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listarTodos() {
        List<MaterialDTO> materiais = materialService.listarTodos();
        return ResponseEntity.ok(materiais);
    }
}
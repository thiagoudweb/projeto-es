package br.edu.ufape.plataforma.mentoria.controller;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;
import jakarta.validation.Valid;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/mentored")
public class MentoredController {

    @Autowired
    private MentoredService mentoredService;

    @Autowired
    private MentorService mentorService;

    @GetMapping("/{idMentored}")
    public ResponseEntity<MentoredDTO> getMentoredDetails(@PathVariable Long idMentored) {
        MentoredDTO mentoredDTO = mentoredService.getMentoredDetailsDTO(idMentored);
        return ResponseEntity.ok(mentoredDTO);
    }

    @PostMapping
    public ResponseEntity<MentoredDTO> createMentored(@Valid @RequestBody MentoredDTO mentoredDTO) {
        MentoredDTO savedMentoredDTO = mentoredService.createMentored(mentoredDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMentoredDTO);
    }

    @PutMapping("/{idMentored}")
    public ResponseEntity<MentoredDTO> updateMentored(@PathVariable Long idMentored,
            @Valid @RequestBody MentoredDTO mentoredDTO) {
        MentoredDTO updatedMentoredDTO = mentoredService.updateMentored(idMentored, mentoredDTO);
        if (updatedMentoredDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMentoredDTO);
    }

    @DeleteMapping("/{idMentored}")
    public ResponseEntity<String> deleteMentored(@PathVariable Long idMentored) {
        try {
            mentoredService.deleteById(idMentored);
            return ResponseEntity.ok("Mentored(a) removido(a) com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/mentors/search")
    public ResponseEntity<List<MentorDTO>> searchMentor(
            @RequestParam(required = false) String interestArea,
            @RequestParam(required = false) String specializations) {

        if (interestArea == null && (specializations == null || specializations.isEmpty())) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        InterestArea interestAreaEnum = null;
        if (interestArea != null && !interestArea.isBlank()) {
            try {
                interestAreaEnum = InterestArea.valueOf(interestArea);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(Collections.emptyList());
            }
        }

        List<MentorDTO> results = mentorService.findByInterestAreaAndSpecializations(interestAreaEnum, specializations);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/me")
    public ResponseEntity<MentoredDTO> getCurrentMentored() {
        MentoredDTO mentored = mentoredService.getCurrentMentored();
        return ResponseEntity.ok(mentored);
    }
}
package br.edu.ufape.plataforma.mentoria.controller;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.service.MentorService;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @Autowired
    private MentoredService mentoredService;

    @Autowired
    private MentoredMapper mentoredMapper;

    @GetMapping("/{idMentor}")
    public ResponseEntity<MentorDTO> getMentorDetails(@PathVariable Long idMentor) throws Exception {
        MentorDTO mentorDTO = mentorService.getMentorDetailsDTO(idMentor);
        if (mentorDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentorDTO);
    }

    @PostMapping
    public ResponseEntity<MentorDTO> createMentor(@Valid @RequestBody MentorDTO mentorDTO) {
        MentorDTO savedMentorDTO = mentorService.createMentor(mentorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMentorDTO);
    }

    @PutMapping("/{idMentor}")
    public ResponseEntity<MentorDTO> updateMentor(@PathVariable Long idMentor,
            @Valid @RequestBody MentorDTO mentorDTO) throws Exception {
        MentorDTO updatedMentorDTO = mentorService.updateMentor(idMentor, mentorDTO);
        if (updatedMentorDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMentorDTO);
    }

    @DeleteMapping("/{idMentor}")
    public ResponseEntity<String> deleteMentor(@PathVariable Long idMentor) {
        try {
            mentorService.deleteMentor(idMentor);
            return ResponseEntity.ok("Mentor(a) removido(a) com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/mentoreds/search")
    public ResponseEntity<List<MentoredDTO>> searchMentored(
            @RequestParam(required = false) String interestArea){

        if (interestArea == null || interestArea.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        InterestArea interestAreaEnum = null;
        try {
            interestAreaEnum = InterestArea.valueOf(interestArea);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<MentoredDTO> results = mentoredService.findByInterestArea(interestAreaEnum);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/me")
    public ResponseEntity<MentorDTO> getCurrentMentor() throws EntityNotFoundException {
        MentorDTO mentor = mentorService.getCurrentMentor();
        if (mentor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentor);
    }
}

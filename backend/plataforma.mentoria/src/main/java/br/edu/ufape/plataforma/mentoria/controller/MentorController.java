package br.edu.ufape.plataforma.mentoria.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestAreas;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.service.MentorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private MentorService mentorService;

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

    @GetMapping("/search")
    public ResponseEntity<List<MentorDTO>> searchMentor(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) InterestAreas interestArea) {

        if (fullName == null && interestArea == null) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<MentorDTO> results = mentorService.findByNameAndInterestArea(fullName, interestArea);
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

package br.edu.ufape.plataforma.mentoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mentored")
public class MentoredController {

    @Autowired
    private MentoredService mentoredService;

    @Autowired
    private MentoredMapper mentoredMapper;

    @GetMapping("/{idMentored}")
    public ResponseEntity<MentoredDTO> getMentoredDetails(@PathVariable Long idMentored) throws Exception {
        MentoredDTO mentoredDTO = mentoredService.getMentoredDetailsDTO(idMentored);
        if (mentoredDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentoredDTO);
    }

    @PostMapping
    public ResponseEntity<MentoredDTO> createMentored(@Valid @RequestBody MentoredDTO mentoredDTO) {
        MentoredDTO savedMentoredDTO = mentoredService.createMentored(mentoredDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMentoredDTO);
    }

    @PutMapping("/{idMentored}")
    public ResponseEntity<MentoredDTO> updateMentored(@PathVariable Long idMentored,
            @Valid @RequestBody MentoredDTO mentoredDTO) throws Exception {
        MentoredDTO updatedMentoredDTO = mentoredService.updateMentored(idMentored, mentoredDTO);
        if (updatedMentoredDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMentoredDTO);
    }

    @DeleteMapping("/{idMentored}")
    public ResponseEntity<String> deleteMentored(@PathVariable Long idMentored) throws Exception {
        try {
            mentoredService.deleteById(idMentored);
            return ResponseEntity.ok("Mentored(a) removido(a) com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<MentoredDTO>> searchMentored(
//            @RequestParam(required = false) String fullName,
//            @RequestParam(required = false) InterestArea interestArea) {
//
//        if (fullName == null && interestArea == null) {
//            return ResponseEntity.ok(Collections.emptyList());
//        }
//
//        List<MentoredDTO> results = mentoredService.findByNameAndInterestArea(fullName, interestArea);
//        return ResponseEntity.ok(results);
//    }

//    @GetMapping("/interests/mentored/{interestName}")
//    public ResponseEntity<List<MentorededDTO>> searchMentorededByInterest(@PathVariable String interestName) {
//        List<Mentoreded> results = mentoredService.searchMentoredByInterest(interestName);
//        List<MentoredDTO> resultsDTO = results.stream()
//                .map(mentoredMapper::toDto)
//                .toList();
//        return ResponseEntity.ok(resultsDTO);
//    }

    @GetMapping("/me")
    public ResponseEntity<MentoredDTO> getCurrentMentored() throws EntityNotFoundException {
        MentoredDTO mentored = mentoredService.getCurrentMentored();
        if (mentored == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentored);
    }
}
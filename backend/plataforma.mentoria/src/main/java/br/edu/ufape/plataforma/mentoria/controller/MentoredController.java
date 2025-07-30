package br.edu.ufape.plataforma.mentoria.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestAreas;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;

@RestController
@RequestMapping("/mentored")
public class MentoredController {

    private final MentoredService mentoredService;
    private final MentoredMapper mentoredMapper;

    private final MentorService mentorService;

    @Autowired
    public MentoredController(MentoredService mentoredService, MentoredMapper mentoredMapper, MentorService mentorService) {
        this.mentoredService = mentoredService;
        this.mentoredMapper = mentoredMapper;
        this.mentorService = mentorService;
    }

    @GetMapping
    public ResponseEntity<List<MentoredDTO>> getAllMentored() {
        List<MentoredDTO> mentoreds = mentoredService.getAllMentored()
                .stream()
                .map(mentoredMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(mentoreds);
    }

    @GetMapping("/{idMentored}")
    public ResponseEntity<MentoredDTO> getMentoredById(@PathVariable Long idMentored) throws Exception {
        Mentored mentored = mentoredService.getMentoredById(idMentored);
        return ResponseEntity.ok(mentoredMapper.toDto(mentored));
    }

    @PostMapping
    public ResponseEntity<MentoredDTO> createMentored(@RequestBody MentoredDTO mentoredDTO) {
        MentoredDTO savedMentored = mentoredService.createMentored(mentoredDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMentored);
    }

    @PutMapping("/{idMentored}")
    public ResponseEntity<MentoredDTO> updateMentored(@PathVariable Long id, @RequestBody MentoredDTO mentoredDTO) throws Exception {
        MentoredDTO updatedMentored = mentoredService.updateMentored(id, mentoredDTO);
        if (updatedMentored == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMentored);
    }

    @DeleteMapping("/{idMentored}")
    public ResponseEntity<Void> deleteMentored(@PathVariable Long idMentored) throws Exception {
        mentoredService.deleteById(idMentored);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mentors/search")
    public ResponseEntity<List<MentorDTO>> searchMentor(
            @RequestParam(required = false) InterestAreas interestArea,
            @RequestParam(required = false) List<String> specializations) {

        if (interestArea == null && (specializations == null || specializations.isEmpty())) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<MentorDTO> results = mentorService.findMentors(interestArea, specializations);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/me")
    public ResponseEntity<MentoredDTO> getCurrentMentored() throws EntityNotFoundException {
        MentoredDTO mentored = mentoredService.getCurrentMentored();
        if (mentored == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mentored);
    }
}
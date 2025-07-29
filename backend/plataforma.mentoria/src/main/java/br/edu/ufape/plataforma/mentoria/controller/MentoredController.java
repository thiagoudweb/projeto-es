package br.edu.ufape.plataforma.mentoria.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestAreas;
import br.edu.ufape.plataforma.mentoria.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;

@RestController
@RequestMapping("/api/mentored")
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

    @GetMapping("/{id}")
    public ResponseEntity<MentoredDTO> getMentoredById(@PathVariable Long id) throws Exception {
        Mentored mentored = mentoredService.getMentoredById(id);
        return ResponseEntity.ok(mentoredMapper.toDto(mentored));
    }

    @PostMapping
    public ResponseEntity<MentoredDTO> createMentored(@RequestBody MentoredDTO mentoredDTO) {
        Mentored savedMentored = mentoredService.createMentored(mentoredDTO);
        return ResponseEntity.ok(mentoredMapper.toDto(savedMentored));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentoredDTO> updateMentored(@PathVariable Long id, @RequestBody MentoredDTO mentoredDTO) throws Exception {
        Mentored updatedMentored = mentoredService.updateMentored(id, mentoredDTO);
        return ResponseEntity.ok(mentoredMapper.toDto(updatedMentored));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentored(@PathVariable Long id) throws Exception {
        mentoredService.deleteById(id);
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

}
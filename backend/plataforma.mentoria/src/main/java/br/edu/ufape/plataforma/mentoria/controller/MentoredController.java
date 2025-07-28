package br.edu.ufape.plataforma.mentoria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.service.MentoredService;

@RestController
@RequestMapping("/api/mentored")
@RequiredArgsConstructor
public class MentoredController {

    private final MentoredService mentoredService;

    @GetMapping
    public ResponseEntity<List<MentoredDTO>> getAllMentoreds() {
        List<MentoredDTO> mentoreds = mentoredService.findAllMentoreds();
        return ResponseEntity.ok(mentoreds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentoredDTO> getMentoredById(@PathVariable Long id) {
        MentoredDTO mentored = mentoredService.findMentoredById(id);
        return ResponseEntity.ok(mentored);
    }

    @PostMapping
    public ResponseEntity<MentoredDTO> createMentored(@RequestBody MentoredDTO mentoredDTO) {
        MentoredDTO savedMentored = mentoredService.saveMentored(mentoredDTO);
        return ResponseEntity.ok(savedMentored);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentoredDTO> updateMentored(@PathVariable Long id, @RequestBody MentoredDTO mentoredDTO) {
        MentoredDTO updatedMentored = mentoredService.updateMentored(id, mentoredDTO);
        return ResponseEntity.ok(updatedMentored);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentored(@PathVariable Long id) {
        mentoredService.deleteMentoredById(id);
        return ResponseEntity.noContent().build();
    }
}
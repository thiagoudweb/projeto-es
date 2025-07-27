package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.MentoredNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.repository.MentoredRepository;

public class MentoredService {

    @Autowired
    private MentoredRepository mentoredRepository;

    @Autowired
    private MentoredMapper mentoredMapper;

    public Mentored getMentoredById(Long id) throws Exception {
        return mentoredRepository.findById(id)
                .orElseThrow(() -> new MentoredNotFoundException(id));
    }

    public List<Mentored> getAllMentored() {
        return mentoredRepository.findAll();
    }

    public Mentored createMentored(Mentored mentored) {
        return mentoredRepository.save(mentored);
    }

    public Mentored createMentored(MentoredDTO mentoredDTO) {
        Mentored mentored = mentoredMapper.toEntity(mentoredDTO);
        return mentoredRepository.save(mentored);
    }

    public Mentored updateMentored(Long id, Mentored mentored) throws Exception {
        if (mentoredRepository.existsById(id)) {
            mentored.setId(id);
            return mentoredRepository.save(mentored);
        }
        throw new MentoredNotFoundException(id);
    }

    public Mentored updateMentored(Long id, MentoredDTO mentoredDTO) throws Exception {
        if (mentoredRepository.existsById(id)) {
            Mentored mentored = mentoredMapper.toEntity(mentoredDTO);
            mentored.setId(id);
            return mentoredRepository.save(mentored);
        }
        throw new MentoredNotFoundException(id);
    }

    public void deleteById(Long id) throws Exception {
        if (!mentoredRepository.existsById(id)) {
            throw new MentoredNotFoundException(id);
        }
        mentoredRepository.deleteById(id);
    }
}

package br.edu.ufape.plataforma.mentoria.mapper;

import org.springframework.stereotype.Component;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

@Component
public class MentoredMapper {
    public Mentored toEntity(MentoredDTO mentoredDTO) {
        Mentored mentored = new Mentored();
        mentored.setFullName(mentoredDTO.getFullName());
        mentored.setEmail(mentoredDTO.getEmail());
        mentored.setCpf(mentoredDTO.getCpf());
        mentored.setBirthDate(mentoredDTO.getBirthDate());
        mentored.setCourse(mentoredDTO.getCourse());
        mentored.setAcademicSummary(mentoredDTO.getAcademicSummary());
        return mentored;
    }

    public MentoredDTO toDto(Mentored mentored) {
        MentoredDTO mentoredDTO = new MentoredDTO();
        mentoredDTO.setFullName(mentored.getFullName());
        mentoredDTO.setEmail(mentored.getEmail());
        mentoredDTO.setCpf(mentored.getCpf());
        mentoredDTO.setBirthDate(mentored.getBirthDate());
        mentoredDTO.setCourse(mentored.getCourse());
        mentoredDTO.setAcademicSummary(mentored.getAcademicSummary());
        return mentoredDTO;
    }
}

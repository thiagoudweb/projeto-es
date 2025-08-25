package br.edu.ufape.plataforma.mentoria.mapper;

import org.springframework.stereotype.Component;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

@Component
public class MentoredMapper {
    public Mentored toEntity(MentoredDTO mentoredDTO) {
        if (mentoredDTO == null) {
            return null;
        }
        Mentored mentored = new Mentored();
        mentored.setFullName(mentoredDTO.getFullName());
        mentored.setCpf(mentoredDTO.getCpf());
        mentored.setBirthDate(mentoredDTO.getBirthDate());
        mentored.setCourse(mentoredDTO.getCourse());
        mentored.setAcademicSummary(mentoredDTO.getAcademicSummary());
        mentored.setInterestArea(mentoredDTO.getInterestArea());
        return mentored;
    }

    public MentoredDTO toDTO(Mentored mentored) {
        if (mentored == null) {
            return null;
        }
        MentoredDTO mentoredDTO = new MentoredDTO();
        mentoredDTO.setId(mentored.getId());
        mentoredDTO.setFullName(mentored.getFullName());
        mentoredDTO.setCpf(mentored.getCpf());
        mentoredDTO.setBirthDate(mentored.getBirthDate());
        mentoredDTO.setCourse(mentored.getCourse());
        mentoredDTO.setAcademicSummary(mentored.getAcademicSummary());
        mentoredDTO.setInterestArea(mentored.getInterestArea());
        return mentoredDTO;
    }
}
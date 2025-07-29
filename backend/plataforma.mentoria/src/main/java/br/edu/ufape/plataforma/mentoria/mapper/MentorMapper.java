package br.edu.ufape.plataforma.mentoria.mapper;

import org.springframework.stereotype.Component;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

@Component
public class MentorMapper {
    public Mentor toEntity(MentorDTO mentorDTO) {
        if (mentorDTO == null) {
            return null;
        }

        Mentor mentor = new Mentor();
        mentor.setFullName(mentorDTO.getFullName());
        mentor.setCpf(mentorDTO.getCpf());
        mentor.setBirthDate(mentorDTO.getBirthDate());
        mentor.setCourse(mentorDTO.getCourse());
        mentor.setProfessionalSummary(mentorDTO.getProfessionalSummary());
        mentor.setAffiliationType(mentorDTO.getAffiliationType());
        mentor.setSpecializations(mentorDTO.getSpecializations());

        return mentor;
    }

    public MentorDTO toDto(Mentor mentor) {
        if (mentor == null) {
            return null;
        }

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setFullName(mentor.getFullName());
        mentorDTO.setCpf(mentor.getCpf());
        mentorDTO.setBirthDate(mentor.getBirthDate());
        mentorDTO.setCourse(mentor.getCourse());
        mentorDTO.setProfessionalSummary(mentor.getProfessionalSummary());
        mentorDTO.setAffiliationType(mentor.getAffiliationType());
        mentorDTO.setSpecializations(mentor.getSpecializations());
        return mentorDTO;
    }
}
package br.edu.ufape.plataforma.mentoria.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

class MentorMapperTest {

    private MentorMapper mentorMapper;

    @BeforeEach
    void setUp() {
        mentorMapper = new MentorMapper();
    }

    @Test
    void testMentorToDto() {
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setProfessionalSummary("Experienced software engineer");
        AffiliationType affiliationType = AffiliationType.GESTOR;
        mentor.setAffiliationType(affiliationType);

        MentorDTO mentorDTO = mentorMapper.toDTO(mentor);

        assertEquals(mentor.getId(), mentorDTO.getId());
        assertEquals(mentor.getProfessionalSummary(), mentorDTO.getProfessionalSummary());
        assertEquals(mentor.getAffiliationType(), mentorDTO.getAffiliationType());
    }

    @Test
    void testDtoToMentor() {
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setProfessionalSummary("Experienced software engineer");
        mentorDTO.setAffiliationType(AffiliationType.GESTOR);

        Mentor mentor = mentorMapper.toEntity(mentorDTO);

        assertEquals(mentorDTO.getProfessionalSummary(), mentor.getProfessionalSummary());
        assertEquals(mentorDTO.getAffiliationType(), mentor.getAffiliationType());
    }

    @Test
    void testDtoToMentorWithNull() {
        Mentor mentor = mentorMapper.toEntity(null);
        assertNull(mentor);
    }

    @Test
    void testMentorToDtoWithNull() {
        MentorDTO mentorDTO = mentorMapper.toDTO(null);
        assertNull(mentorDTO);
    }
}

package br.edu.ufape.plataforma.mentoria.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

public class MentorMapperTest {

    private MentorMapper mentorMapper;

    @BeforeEach
    public void setUp() {
        mentorMapper = new MentorMapper();
    }

    @Test
    public void testMentorToDto() {
        Mentor mentor = new Mentor.Builder().build();
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
    public void testDtoToMentor() {
        MentorDTO mentorDTO = new MentorDTO.Builder().build();
        mentorDTO.setProfessionalSummary("Experienced software engineer");
        mentorDTO.setAffiliationType(AffiliationType.GESTOR);

        Mentor mentor = mentorMapper.toEntity(mentorDTO);

        assertEquals(mentorDTO.getProfessionalSummary(), mentor.getProfessionalSummary());
        assertEquals(mentorDTO.getAffiliationType(), mentor.getAffiliationType());
    }

    @Test
    public void testDtoToMentorWithNull() {
        Mentor mentor = mentorMapper.toEntity(null);
        assertNull(mentor);
    }

    @Test
    public void testMentorToDtoWithNull() {
        MentorDTO mentorDTO = mentorMapper.toDTO(null);
        assertNull(mentorDTO);
    }
}

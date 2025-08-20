package br.edu.ufape.plataforma.mentoria.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

public class MentoredMapperTest {

    private MentoredMapper mentoredMapper;

    @BeforeEach
    public void setUp() {
        mentoredMapper = new MentoredMapper();
    }

    @Test
    public void testMentoredToDto() {
        Mentored mentored = new Mentored();
        mentored.setId(1L);
        mentored.setCpf("123.456.789-00");
        mentored.setAcademicSummary("Bachelor's in Computer Science");

        MentoredDTO mentoredDTO = mentoredMapper.toDTO(mentored);

        assertEquals(mentored.getId(), mentoredDTO.getId());
        assertEquals(mentored.getCpf(), mentoredDTO.getCpf());
        assertEquals(mentored.getAcademicSummary(), mentoredDTO.getAcademicSummary());
    }

    @Test
    public void testDtoToMentored() {
        MentoredDTO mentoredDTO = new MentoredDTO();
        mentoredDTO.setId(1L);
        mentoredDTO.setCpf("123.456.789-00");
        mentoredDTO.setAcademicSummary("Bachelor's in Computer Science");

        Mentored mentored = mentoredMapper.toEntity(mentoredDTO);

        assertEquals(mentoredDTO.getCpf(), mentored.getCpf());
        assertEquals(mentoredDTO.getAcademicSummary(), mentored.getAcademicSummary());
    }

    @Test
    public void testDtoToMentoredWithNull() {
        Mentored mentored = mentoredMapper.toEntity(null);
        assertNull(mentored);
    }

    @Test
    public void testMentoredToDtoWithNull() {
        MentoredDTO mentoredDTO = mentoredMapper.toDTO(null);
        assertNull(mentoredDTO);
    }
}

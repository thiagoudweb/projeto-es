package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;

public class MentorDTOTest {

    @Test
    void testNoArgsConstructor() {
        MentorDTO dto = new MentorDTO();

        assertNull(dto.getId());
        assertNull(dto.getFullName());
        assertNull(dto.getCpf());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getProfessionalSummary());
        assertNull(dto.getAffiliationType());
        assertNull(dto.getSpecializations());
        assertNull(dto.getInterestArea());
    }

    @Test
    void testAllArgsConstructor() {
        String fullName = "John Mentor";
        String cpf = "12345678901";
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        Course course = Course.CIENCIA_DA_COMPUTACAO;
        String professionalSummary = "Experienced software engineer";
        AffiliationType affiliationType = AffiliationType.PESQUISADOR;
        List<String> specializations = Arrays.asList("Java", "Spring Boot");
        List<InterestArea> interestAreas = Arrays.asList(InterestArea.TECNOLOGIA_DA_INFORMACAO);

        MentorDTO dto = new MentorDTO(fullName, cpf, birthDate, course,
                professionalSummary, affiliationType, specializations, interestAreas);

        assertEquals(fullName, dto.getFullName());
        assertEquals(cpf, dto.getCpf());
        assertEquals(birthDate, dto.getBirthDate());
        assertEquals(course, dto.getCourse());
        assertEquals(professionalSummary, dto.getProfessionalSummary());
        assertEquals(affiliationType, dto.getAffiliationType());
        assertEquals(specializations, dto.getSpecializations());
        assertEquals(interestAreas, dto.getInterestArea());
    }

    @Test
    void testSetAndGetId() {
        MentorDTO dto = new MentorDTO();
        Long id = 1L;

        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    void testSetAndGetFullName() {
        MentorDTO dto = new MentorDTO();
        String fullName = "Jane Mentor";

        dto.setFullName(fullName);

        assertEquals(fullName, dto.getFullName());
    }

    @Test
    void testSetAndGetCpf() {
        MentorDTO dto = new MentorDTO();
        String cpf = "98765432100";

        dto.setCpf(cpf);

        assertEquals(cpf, dto.getCpf());
    }

    @Test
    void testSetAndGetBirthDate() {
        MentorDTO dto = new MentorDTO();
        LocalDate birthDate = LocalDate.of(1985, 12, 25);

        dto.setBirthDate(birthDate);

        assertEquals(birthDate, dto.getBirthDate());
    }

    @Test
    void testSetAndGetCourse() {
        MentorDTO dto = new MentorDTO();
        Course course = Course.ADMINISTRACAO;

        dto.setCourse(course);

        assertEquals(course, dto.getCourse());
    }

    @Test
    void testSetAndGetProfessionalSummary() {
        MentorDTO dto = new MentorDTO();
        String summary = "Senior developer with 10 years experience";

        dto.setProfessionalSummary(summary);

        assertEquals(summary, dto.getProfessionalSummary());
    }

    @Test
    void testSetAndGetAffiliationType() {
        MentorDTO dto = new MentorDTO();
        AffiliationType type = AffiliationType.GESTOR;

        dto.setAffiliationType(type);

        assertEquals(type, dto.getAffiliationType());
    }

    @Test
    void testSetAndGetSpecializations() {
        MentorDTO dto = new MentorDTO();
        List<String> specializations = Arrays.asList("Python", "Machine Learning", "AI");

        dto.setSpecializations(specializations);

        assertEquals(specializations, dto.getSpecializations());
        assertEquals(3, dto.getSpecializations().size());
        assertTrue(dto.getSpecializations().contains("Python"));
    }

    @Test
    void testSetAndGetInterestArea() {
        MentorDTO dto = new MentorDTO();
        List<InterestArea> interestAreas = Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.EDUCACAO);

        dto.setInterestArea(interestAreas);

        assertEquals(interestAreas, dto.getInterestArea());
        assertEquals(2, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.TECNOLOGIA_DA_INFORMACAO));
    }

    @Test
    void testSetEmptyCollections() {
        MentorDTO dto = new MentorDTO();
        List<String> emptySpecializations = Arrays.asList();
        List<InterestArea> emptyInterestAreas = Arrays.asList();

        dto.setSpecializations(emptySpecializations);
        dto.setInterestArea(emptyInterestAreas);

        assertEquals(emptySpecializations, dto.getSpecializations());
        assertEquals(emptyInterestAreas, dto.getInterestArea());
        assertTrue(dto.getSpecializations().isEmpty());
        assertTrue(dto.getInterestArea().isEmpty());
    }

    @Test
    void testCompleteObjectCreation() {
        MentorDTO dto = new MentorDTO();

        // Set all fields
        dto.setId(1L);
        dto.setFullName("Complete Mentor");
        dto.setCpf("11122233344");
        dto.setBirthDate(LocalDate.of(1988, 3, 10));
        dto.setCourse(Course.ADMINISTRACAO);
        dto.setProfessionalSummary("Complete professional summary");
        dto.setAffiliationType(AffiliationType.DOCENTE);
        dto.setSpecializations(Arrays.asList("Leadership", "Management"));
        dto.setInterestArea(Arrays.asList(InterestArea.DESENVOLVIMENTO_DE_SOFTWARE));

        // Verify all fields
        assertEquals(1L, dto.getId());
        assertEquals("Complete Mentor", dto.getFullName());
        assertEquals("11122233344", dto.getCpf());
        assertEquals(LocalDate.of(1988, 3, 10), dto.getBirthDate());
        assertEquals(Course.ADMINISTRACAO, dto.getCourse());
        assertEquals("Complete professional summary", dto.getProfessionalSummary());
        assertEquals(AffiliationType.DOCENTE, dto.getAffiliationType());
        assertEquals(Arrays.asList("Leadership", "Management"), dto.getSpecializations());
        assertEquals(Arrays.asList(InterestArea.DESENVOLVIMENTO_DE_SOFTWARE), dto.getInterestArea());
    }
}
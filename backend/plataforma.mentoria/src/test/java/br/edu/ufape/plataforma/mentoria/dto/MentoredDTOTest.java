package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;

public class MentoredDTOTest {

    @Test
    void testNoArgsConstructor() {
        MentoredDTO dto = new MentoredDTO();

        assertNull(dto.getId());
        assertNull(dto.getFullName());
        assertNull(dto.getCpf());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getAcademicSummary());
        assertNull(dto.getInterestArea());
    }

    @Test
    void testAllArgsConstructor() {
        String fullName = "Jane Student";
        String cpf = "12345678901";
        LocalDate birthDate = LocalDate.of(2000, 8, 15);
        Course course = Course.CIENCIA_DA_COMPUTACAO;
        String academicSummary = "Computer Science undergraduate student";
        List<InterestArea> interestAreas = Arrays.asList(InterestArea.TECNOLOGIA_DA_INFORMACAO, InterestArea.EDUCACAO);

        MentoredDTO dto = new MentoredDTO(fullName, cpf, birthDate, course, academicSummary, interestAreas);

        assertEquals(fullName, dto.getFullName());
        assertEquals(cpf, dto.getCpf());
        assertEquals(birthDate, dto.getBirthDate());
        assertEquals(course, dto.getCourse());
        assertEquals(academicSummary, dto.getAcademicSummary());
        assertEquals(interestAreas, dto.getInterestArea());
    }

    @Test
    void testSetAndGetId() {
        MentoredDTO dto = new MentoredDTO();
        Long id = 1L;

        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    void testSetAndGetFullName() {
        MentoredDTO dto = new MentoredDTO();
        String fullName = "John Student";

        dto.setFullName(fullName);

        assertEquals(fullName, dto.getFullName());
    }

    @Test
    void testSetAndGetCpf() {
        MentoredDTO dto = new MentoredDTO();
        String cpf = "98765432100";

        dto.setCpf(cpf);

        assertEquals(cpf, dto.getCpf());
    }

    @Test
    void testSetAndGetBirthDate() {
        MentoredDTO dto = new MentoredDTO();
        LocalDate birthDate = LocalDate.of(1999, 3, 20);

        dto.setBirthDate(birthDate);

        assertEquals(birthDate, dto.getBirthDate());
    }

    @Test
    void testSetAndGetCourse() {
        MentoredDTO dto = new MentoredDTO();
        Course course = Course.CONTABILIDADE;

        dto.setCourse(course);

        assertEquals(course, dto.getCourse());
    }

    @Test
    void testSetAndGetAcademicSummary() {
        MentoredDTO dto = new MentoredDTO();
        String summary = "Passionate student interested in software development";

        dto.setAcademicSummary(summary);

        assertEquals(summary, dto.getAcademicSummary());
    }

    @Test
    void testSetAndGetInterestArea() {
        MentoredDTO dto = new MentoredDTO();
        List<InterestArea> interestAreas = Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.DESENVOLVIMENTO_DE_SOFTWARE,
                InterestArea.BIOTECNOLOGIA);

        dto.setInterestArea(interestAreas);

        assertEquals(interestAreas, dto.getInterestArea());
        assertEquals(3, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.TECNOLOGIA_DA_INFORMACAO));
        assertTrue(dto.getInterestArea().contains(InterestArea.DESENVOLVIMENTO_DE_SOFTWARE));
        assertTrue(dto.getInterestArea().contains(InterestArea.BIOTECNOLOGIA));
    }

    @Test
    void testSetNullValues() {
        MentoredDTO dto = new MentoredDTO();

        dto.setId(null);
        dto.setFullName(null);
        dto.setCpf(null);
        dto.setBirthDate(null);
        dto.setCourse(null);
        dto.setAcademicSummary(null);
        dto.setInterestArea(null);

        assertNull(dto.getId());
        assertNull(dto.getFullName());
        assertNull(dto.getCpf());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getAcademicSummary());
        assertNull(dto.getInterestArea());
    }

    @Test
    void testSetEmptyAcademicSummary() {
        MentoredDTO dto = new MentoredDTO();
        String emptyString = "";

        dto.setAcademicSummary(emptyString);

        assertEquals("", dto.getAcademicSummary());
    }

    @Test
    void testSetEmptyInterestAreaList() {
        MentoredDTO dto = new MentoredDTO();
        List<InterestArea> emptyList = Arrays.asList();

        dto.setInterestArea(emptyList);

        assertEquals(emptyList, dto.getInterestArea());
        assertTrue(dto.getInterestArea().isEmpty());
    }

    @Test
    void testSetSingleInterestArea() {
        MentoredDTO dto = new MentoredDTO();
        List<InterestArea> singleArea = Arrays.asList(InterestArea.TECNOLOGIA_DA_INFORMACAO);

        dto.setInterestArea(singleArea);

        assertEquals(1, dto.getInterestArea().size());
        assertEquals(InterestArea.TECNOLOGIA_DA_INFORMACAO, dto.getInterestArea().get(0));
    }

    @Test
    void testCompleteObjectCreation() {
        MentoredDTO dto = new MentoredDTO();

        // Set all fields
        dto.setId(2L);
        dto.setFullName("Complete Student");
        dto.setCpf("11122233344");
        dto.setBirthDate(LocalDate.of(2001, 7, 5));
        dto.setCourse(Course.CONTABILIDADE);
        dto.setAcademicSummary("Business administration student with focus on entrepreneurship");
        dto.setInterestArea(Arrays.asList(InterestArea.ARTES_E_DESIGN, InterestArea.DESENVOLVIMENTO_DE_SOFTWARE));

        // Verify all fields
        assertEquals(2L, dto.getId());
        assertEquals("Complete Student", dto.getFullName());
        assertEquals("11122233344", dto.getCpf());
        assertEquals(LocalDate.of(2001, 7, 5), dto.getBirthDate());
        assertEquals(Course.CONTABILIDADE, dto.getCourse());
        assertEquals("Business administration student with focus on entrepreneurship", dto.getAcademicSummary());
        assertEquals(2, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.ARTES_E_DESIGN));
        assertTrue(dto.getInterestArea().contains(InterestArea.DESENVOLVIMENTO_DE_SOFTWARE));
    }

    @Test
    void testLongAcademicSummary() {
        MentoredDTO dto = new MentoredDTO();
        String longSummary = "This is a very long academic summary that contains detailed information about the student's background, interests, achievements, and goals. It describes their academic journey, extracurricular activities, research interests, career aspirations, and personal motivations for pursuing their chosen field of study.";

        dto.setAcademicSummary(longSummary);

        assertEquals(longSummary, dto.getAcademicSummary());
        assertTrue(dto.getAcademicSummary().length() > 100);
    }

    @Test
    void testMultipleInterestAreas() {
        MentoredDTO dto = new MentoredDTO();
        List<InterestArea> multipleAreas = Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.CIENCIAS_HUMANAS_E_SOCIAIS,
                InterestArea.DIREITO_DIGITAL,
                InterestArea.COMUNICACAO_INSTITUCIONAL,
                InterestArea.EDUCACAO);

        dto.setInterestArea(multipleAreas);

        assertEquals(5, dto.getInterestArea().size());
        for (InterestArea area : multipleAreas) {
            assertTrue(dto.getInterestArea().contains(area));
        }
    }
}
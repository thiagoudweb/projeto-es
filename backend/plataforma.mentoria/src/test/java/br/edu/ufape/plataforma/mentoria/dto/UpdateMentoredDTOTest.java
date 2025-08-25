package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;

class UpdateMentoredDTOTest {

    @Test
    void testNoArgsConstructor() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        assertNull(dto.getFullName());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getAcademicSummary());
        assertNull(dto.getInterestArea());
    }

    @Test
    void testSetAndGetFullName() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        String fullName = "Updated Student Name";

        dto.setFullName(fullName);

        assertEquals(fullName, dto.getFullName());
    }

    @Test
    void testSetAndGetBirthDate() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        LocalDate birthDate = LocalDate.of(2001, 4, 20);

        dto.setBirthDate(birthDate);

        assertEquals(birthDate, dto.getBirthDate());
    }

    @Test
    void testSetAndGetCourse() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        Course course = Course.CIENCIA_DA_COMPUTACAO;

        dto.setCourse(course);

        assertEquals(course, dto.getCourse());
    }

    @Test
    void testSetAndGetAcademicSummary() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        String summary = "Updated academic summary with new achievements";

        dto.setAcademicSummary(summary);

        assertEquals(summary, dto.getAcademicSummary());
    }

    @Test
    void testSetAndGetInterestArea() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        List<InterestArea> interestAreas = Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.ADMINISTRACAO_E_GESTAO,
                InterestArea.CIENCIAS_BIOLOGICAS_E_SAUDE);

        dto.setInterestArea(interestAreas);

        assertEquals(interestAreas, dto.getInterestArea());
        assertEquals(3, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.TECNOLOGIA_DA_INFORMACAO));
        assertTrue(dto.getInterestArea().contains(InterestArea.ADMINISTRACAO_E_GESTAO));
        assertTrue(dto.getInterestArea().contains(InterestArea.CIENCIAS_BIOLOGICAS_E_SAUDE));
    }

    @Test
    void testSetEmptyStrings() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        dto.setFullName("");
        dto.setAcademicSummary("");

        assertEquals("", dto.getFullName());
        assertEquals("", dto.getAcademicSummary());
    }

    @Test
    void testSetEmptyInterestAreaList() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        List<InterestArea> emptyList = Arrays.asList();

        dto.setInterestArea(emptyList);

        assertEquals(emptyList, dto.getInterestArea());
        assertTrue(dto.getInterestArea().isEmpty());
    }

    @Test
    void testCompleteUpdate() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        // Set all fields for a complete update
        dto.setFullName("Jane Updated Student");
        dto.setBirthDate(LocalDate.of(2000, 11, 15));
        dto.setCourse(Course.CIENCIA_DA_COMPUTACAO);
        dto.setAcademicSummary("Engineering student with focus on software development and AI research");
        dto.setInterestArea(Arrays.asList(
                InterestArea.ARTES_E_DESIGN,
                InterestArea.ADMINISTRACAO_E_GESTAO,
                InterestArea.CIENCIAS_BIOLOGICAS_E_SAUDE,
                InterestArea.COMUNICACAO_INSTITUCIONAL));

        assertEquals("Jane Updated Student", dto.getFullName());
        assertEquals(LocalDate.of(2000, 11, 15), dto.getBirthDate());
        assertEquals(Course.CIENCIA_DA_COMPUTACAO, dto.getCourse());
        assertEquals("Engineering student with focus on software development and AI research",
                dto.getAcademicSummary());
        assertEquals(4, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.ARTES_E_DESIGN));
        assertTrue(dto.getInterestArea().contains(InterestArea.ADMINISTRACAO_E_GESTAO));
    }

    @Test
    void testPartialUpdate() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        dto.setFullName("Partially Updated Name");
        dto.setAcademicSummary("Updated academic summary only");

        assertEquals("Partially Updated Name", dto.getFullName());
        assertEquals("Updated academic summary only", dto.getAcademicSummary());

        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getInterestArea());
    }

    @Test
    void testAllCourseTypes() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        Course[] courses = Course.values();

        for (Course course : courses) {
            dto.setCourse(course);
            assertEquals(course, dto.getCourse());
        }
    }

    @Test
    void testAllInterestAreaTypes() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        InterestArea[] areas = InterestArea.values();

        for (InterestArea area : areas) {
            List<InterestArea> singleArea = Arrays.asList(area);
            dto.setInterestArea(singleArea);
            assertEquals(1, dto.getInterestArea().size());
            assertTrue(dto.getInterestArea().contains(area));
        }
    }

    @Test
    void testSingleInterestArea() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        List<InterestArea> singleArea = Arrays.asList(InterestArea.ENGENHARIA_DE_PRODUCAO);

        dto.setInterestArea(singleArea);

        assertEquals(1, dto.getInterestArea().size());
        assertEquals(InterestArea.ENGENHARIA_DE_PRODUCAO, dto.getInterestArea().get(0));
    }

    @Test
    void testMultipleInterestAreas() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        List<InterestArea> multipleAreas = Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.ENGENHARIA_DE_PRODUCAO,
                InterestArea.EDUCACAO,
                InterestArea.ADMINISTRACAO_E_GESTAO,
                InterestArea.CIENCIAS_BIOLOGICAS_E_SAUDE);

        dto.setInterestArea(multipleAreas);

        assertEquals(5, dto.getInterestArea().size());
        for (InterestArea area : multipleAreas) {
            assertTrue(dto.getInterestArea().contains(area));
        }
    }

    @Test
    void testLongAcademicSummary() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        String longSummary = "This is a very detailed academic summary that contains extensive information about the student's educational background, research interests, academic achievements, extracurricular activities, leadership roles, volunteer work, internship experiences, and future academic and career aspirations in their chosen field of study.";

        dto.setAcademicSummary(longSummary);

        assertEquals(longSummary, dto.getAcademicSummary());
        assertTrue(dto.getAcademicSummary().length() > 200);
    }

    @Test
    void testDateEdgeCases() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        LocalDate[] testDates = {
                LocalDate.of(1995, 1, 1),
                LocalDate.of(2005, 6, 15),
                LocalDate.of(2000, 12, 31),
                LocalDate.now().minusYears(18)
        };

        for (LocalDate date : testDates) {
            dto.setBirthDate(date);
            assertEquals(date, dto.getBirthDate());
        }
    }

    @Test
    void testShortAcademicSummary() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        String shortSummary = "Brief summary";

        dto.setAcademicSummary(shortSummary);

        assertEquals(shortSummary, dto.getAcademicSummary());
        assertTrue(dto.getAcademicSummary().length() < 20);
    }

    @Test
    void testInterestAreaModification() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        List<InterestArea> initialAreas = Arrays.asList(InterestArea.TECNOLOGIA_DA_INFORMACAO);
        dto.setInterestArea(initialAreas);
        assertEquals(1, dto.getInterestArea().size());

        List<InterestArea> updatedAreas = Arrays.asList(
                InterestArea.ENGENHARIA_DE_PRODUCAO,
                InterestArea.EDUCACAO);
        dto.setInterestArea(updatedAreas);
        assertEquals(2, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.ENGENHARIA_DE_PRODUCAO));
        assertTrue(dto.getInterestArea().contains(InterestArea.EDUCACAO));
        assertFalse(dto.getInterestArea().contains(InterestArea.TECNOLOGIA_DA_INFORMACAO));
    }

    @Test
    void testFieldsIndependence() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();

        dto.setFullName("Test Name");
        assertEquals("Test Name", dto.getFullName());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getAcademicSummary());
        assertNull(dto.getInterestArea());

        dto.setCourse(Course.ARQUITETURA_E_URBANISMO);
        assertEquals("Test Name", dto.getFullName());
        assertEquals(Course.ARQUITETURA_E_URBANISMO, dto.getCourse());
        assertNull(dto.getBirthDate());
        assertNull(dto.getAcademicSummary());
        assertNull(dto.getInterestArea());
    }
}
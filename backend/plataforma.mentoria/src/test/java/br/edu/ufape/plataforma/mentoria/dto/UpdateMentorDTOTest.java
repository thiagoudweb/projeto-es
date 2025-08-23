package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;

public class UpdateMentorDTOTest {

    @Test
    void testNoArgsConstructor() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        assertNull(dto.getFullName());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getInterestArea());
        assertNull(dto.getProfessionalSummary());
        assertNull(dto.getAffiliationType());
        assertNull(dto.getSpecializations());
    }

    @Test
    void testSetAndGetFullName() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        String fullName = "Updated Mentor Name";

        dto.setFullName(fullName);

        assertEquals(fullName, dto.getFullName());
    }

    @Test
    void testSetAndGetBirthDate() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        LocalDate birthDate = LocalDate.of(1985, 6, 15);

        dto.setBirthDate(birthDate);

        assertEquals(birthDate, dto.getBirthDate());
    }

    @Test
    void testSetAndGetCourse() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        Course course = Course.CIENCIA_DA_COMPUTACAO;

        dto.setCourse(course);

        assertEquals(course, dto.getCourse());
    }

    @Test
    void testSetAndGetInterestArea() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        List<InterestArea> interestAreas = Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.EDUCACAO);

        dto.setInterestArea(interestAreas);

        assertEquals(interestAreas, dto.getInterestArea());
        assertEquals(2, dto.getInterestArea().size());
        assertTrue(dto.getInterestArea().contains(InterestArea.TECNOLOGIA_DA_INFORMACAO));
        assertTrue(dto.getInterestArea().contains(InterestArea.EDUCACAO));
    }

    @Test
    void testSetAndGetProfessionalSummary() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        String summary = "Updated professional summary with new achievements";

        dto.setProfessionalSummary(summary);

        assertEquals(summary, dto.getProfessionalSummary());
    }

    @Test
    void testSetAndGetAffiliationType() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        AffiliationType type = AffiliationType.DOCENTE;

        dto.setAffiliationType(type);

        assertEquals(type, dto.getAffiliationType());
    }

    @Test
    void testSetAndGetSpecializations() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        List<String> specializations = Arrays.asList(
                "Spring Boot",
                "Microservices",
                "Cloud Computing");

        dto.setSpecializations(specializations);

        assertEquals(specializations, dto.getSpecializations());
        assertEquals(3, dto.getSpecializations().size());
        assertTrue(dto.getSpecializations().contains("Spring Boot"));
        assertTrue(dto.getSpecializations().contains("Microservices"));
    }

    @Test
    void testSetNullValues() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        dto.setFullName(null);
        dto.setBirthDate(null);
        dto.setCourse(null);
        dto.setInterestArea(null);
        dto.setProfessionalSummary(null);
        dto.setAffiliationType(null);
        dto.setSpecializations(null);

        assertNull(dto.getFullName());
        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getInterestArea());
        assertNull(dto.getProfessionalSummary());
        assertNull(dto.getAffiliationType());
        assertNull(dto.getSpecializations());
    }

    @Test
    void testSetEmptyStrings() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        dto.setFullName("");
        dto.setProfessionalSummary("");

        assertEquals("", dto.getFullName());
        assertEquals("", dto.getProfessionalSummary());
    }

    @Test
    void testSetEmptyCollections() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        List<InterestArea> emptyInterestAreas = Arrays.asList();
        List<String> emptySpecializations = Arrays.asList();

        dto.setInterestArea(emptyInterestAreas);
        dto.setSpecializations(emptySpecializations);

        assertEquals(emptyInterestAreas, dto.getInterestArea());
        assertEquals(emptySpecializations, dto.getSpecializations());
        assertTrue(dto.getInterestArea().isEmpty());
        assertTrue(dto.getSpecializations().isEmpty());
    }

    @Test
    void testCompleteUpdate() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        dto.setFullName("John Updated Mentor");
        dto.setBirthDate(LocalDate.of(1980, 3, 25));
        dto.setCourse(Course.CIENCIA_DA_COMPUTACAO);
        dto.setInterestArea(Arrays.asList(
                InterestArea.TECNOLOGIA_DA_INFORMACAO,
                InterestArea.CIBERSEGURANCA,
                InterestArea.CIENCIAS_HUMANAS_E_SOCIAIS));
        dto.setProfessionalSummary("Senior software engineer with expertise in cloud technologies");
        dto.setAffiliationType(AffiliationType.GESTOR);
        dto.setSpecializations(Arrays.asList(
                "AWS",
                "Docker",
                "Kubernetes",
                "Java",
                "Python"));

        assertEquals("John Updated Mentor", dto.getFullName());
        assertEquals(LocalDate.of(1980, 3, 25), dto.getBirthDate());
        assertEquals(Course.CIENCIA_DA_COMPUTACAO, dto.getCourse());
        assertEquals(3, dto.getInterestArea().size());
        assertEquals("Senior software engineer with expertise in cloud technologies", dto.getProfessionalSummary());
        assertEquals(AffiliationType.GESTOR, dto.getAffiliationType());
        assertEquals(5, dto.getSpecializations().size());
    }

    @Test
    void testPartialUpdate() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        dto.setFullName("Partially Updated Name");
        dto.setProfessionalSummary("Updated summary only");
        dto.setSpecializations(Arrays.asList("New Skill"));

        assertEquals("Partially Updated Name", dto.getFullName());
        assertEquals("Updated summary only", dto.getProfessionalSummary());
        assertEquals(1, dto.getSpecializations().size());

        assertNull(dto.getBirthDate());
        assertNull(dto.getCourse());
        assertNull(dto.getInterestArea());
        assertNull(dto.getAffiliationType());
    }

    @Test
    void testAllAffiliationTypes() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        AffiliationType[] types = AffiliationType.values();

        for (AffiliationType type : types) {
            dto.setAffiliationType(type);
            assertEquals(type, dto.getAffiliationType());
        }
    }

    @Test
    void testAllCourseTypes() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        Course[] courses = Course.values();

        for (Course course : courses) {
            dto.setCourse(course);
            assertEquals(course, dto.getCourse());
        }
    }

    @Test
    void testSingleInterestArea() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        List<InterestArea> singleArea = Arrays.asList(InterestArea.NUTRICAO);

        dto.setInterestArea(singleArea);

        assertEquals(1, dto.getInterestArea().size());
        assertEquals(InterestArea.NUTRICAO, dto.getInterestArea().get(0));
    }

    @Test
    void testSingleSpecialization() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        List<String> singleSpec = Arrays.asList("Machine Learning");

        dto.setSpecializations(singleSpec);

        assertEquals(1, dto.getSpecializations().size());
        assertEquals("Machine Learning", dto.getSpecializations().get(0));
    }

    @Test
    void testLongProfessionalSummary() {
        UpdateMentorDTO dto = new UpdateMentorDTO();
        String longSummary = "This is a very detailed professional summary that contains extensive information about the mentor's career progression, technical skills, leadership experience, project management capabilities, team collaboration skills, and future career goals. It provides comprehensive insights into their professional journey and expertise areas.";

        dto.setProfessionalSummary(longSummary);

        assertEquals(longSummary, dto.getProfessionalSummary());
        assertTrue(dto.getProfessionalSummary().length() > 200);
    }

    @Test
    void testDateEdgeCases() {
        UpdateMentorDTO dto = new UpdateMentorDTO();

        LocalDate[] testDates = {
                LocalDate.of(1950, 1, 1),
                LocalDate.of(2000, 12, 31),
                LocalDate.now(),
                LocalDate.of(1990, 2, 14)
        };

        for (LocalDate date : testDates) {
            dto.setBirthDate(date);
            assertEquals(date, dto.getBirthDate());
        }
    }
}
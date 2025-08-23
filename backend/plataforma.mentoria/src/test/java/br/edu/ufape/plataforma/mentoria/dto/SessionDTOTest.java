package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ufape.plataforma.mentoria.enums.Status;

public class SessionDTOTest {

    @Test
    void testNoArgsConstructor() {
        SessionDTO dto = new SessionDTO();

        assertNull(dto.getMentorId());
        assertNull(dto.getDate());
        assertNull(dto.getTime());
        assertNull(dto.getMeetingTopic());
        assertNull(dto.getStatus());
        assertNull(dto.getLocation());
    }

    @Test
    void testAllArgsConstructor() {
        Long mentorId = 1L;
        Long mentoredId = 2L;
        LocalDate date = LocalDate.of(2024, 8, 22);
        LocalTime time = LocalTime.of(14, 30);
        String meetingTopic = "Career Development Session";
        String location = "Online";

        SessionDTO dto = new SessionDTO(mentorId, mentoredId, date, time, meetingTopic, location);

        assertEquals(mentorId, dto.getMentorId());
        assertEquals(date, dto.getDate());
        assertEquals(time, dto.getTime());
        assertEquals(meetingTopic, dto.getMeetingTopic());
        assertEquals(Status.PENDING, dto.getStatus());
        assertEquals(location, dto.getLocation());
    }

    @Test
    void testSetAndGetMentorId() {
        SessionDTO dto = new SessionDTO();
        Long mentorId = 5L;

        dto.setMentorId(mentorId);

        assertEquals(mentorId, dto.getMentorId());
    }

    @Test
    void testSetAndGetDate() {
        SessionDTO dto = new SessionDTO();
        LocalDate date = LocalDate.of(2024, 12, 15);

        dto.setDate(date);

        assertEquals(date, dto.getDate());
    }

    @Test
    void testSetAndGetTime() {
        SessionDTO dto = new SessionDTO();
        LocalTime time = LocalTime.of(16, 45);

        dto.setTime(time);

        assertEquals(time, dto.getTime());
    }

    @Test
    void testSetAndGetMeetingTopic() {
        SessionDTO dto = new SessionDTO();
        String topic = "Java Programming Fundamentals";

        dto.setMeetingTopic(topic);

        assertEquals(topic, dto.getMeetingTopic());
    }

    @Test
    void testSetAndGetStatus() {
        SessionDTO dto = new SessionDTO();
        Status status = Status.ACCEPTED;

        dto.setStatus(status);

        assertEquals(status, dto.getStatus());
    }

    @Test
    void testSetAndGetLocation() {
        SessionDTO dto = new SessionDTO();
        String location = "Conference Room A";

        dto.setLocation(location);

        assertEquals(location, dto.getLocation());
    }

    @Test
    void testAllStatusValues() {
        SessionDTO dto = new SessionDTO();

        // Test all possible status values
        dto.setStatus(Status.PENDING);
        assertEquals(Status.PENDING, dto.getStatus());

        dto.setStatus(Status.ACCEPTED);
        assertEquals(Status.ACCEPTED, dto.getStatus());

        dto.setStatus(Status.REJECTED);
        assertEquals(Status.REJECTED, dto.getStatus());

        dto.setStatus(Status.COMPLETED);
        assertEquals(Status.COMPLETED, dto.getStatus());

        dto.setStatus(Status.CANCELLED);
        assertEquals(Status.CANCELLED, dto.getStatus());
    }

    @Test
    void testSetNullValues() {
        SessionDTO dto = new SessionDTO();

        dto.setMentorId(null);
        dto.setMentoredId(null);
        dto.setDate(null);
        dto.setTime(null);
        dto.setMeetingTopic(null);
        dto.setStatus(null);
        dto.setLocation(null);

        assertNull(dto.getMentorId());
        assertNull(dto.getDate());
        assertNull(dto.getTime());
        assertNull(dto.getMeetingTopic());
        assertNull(dto.getStatus());
        assertNull(dto.getLocation());
    }

    @Test
    void testSetEmptyStrings() {
        SessionDTO dto = new SessionDTO();

        dto.setMeetingTopic("");
        dto.setLocation("");

        assertEquals("", dto.getMeetingTopic());
        assertEquals("", dto.getLocation());
    }

    @Test
    void testCompleteSessionCreation() {
        SessionDTO dto = new SessionDTO();

        // Set all fields
        dto.setMentorId(10L);
        dto.setMentoredId(20L);
        dto.setDate(LocalDate.of(2024, 9, 10));
        dto.setTime(LocalTime.of(10, 0));
        dto.setMeetingTopic("Spring Boot Tutorial");
        dto.setStatus(Status.ACCEPTED);
        dto.setLocation("Online - Zoom");

        // Verify all fields
        assertEquals(10L, dto.getMentorId());
        assertEquals(LocalDate.of(2024, 9, 10), dto.getDate());
        assertEquals(LocalTime.of(10, 0), dto.getTime());
        assertEquals("Spring Boot Tutorial", dto.getMeetingTopic());
        assertEquals(Status.ACCEPTED, dto.getStatus());
        assertEquals("Online - Zoom", dto.getLocation());
    }

    @Test
    void testDateTimeEdgeCases() {
        SessionDTO dto = new SessionDTO();

        LocalDate pastDate = LocalDate.of(2020, 1, 1);
        LocalDate futureDate = LocalDate.of(2030, 12, 31);

        dto.setDate(pastDate);
        assertEquals(pastDate, dto.getDate());

        dto.setDate(futureDate);
        assertEquals(futureDate, dto.getDate());

        LocalTime earlyTime = LocalTime.of(0, 0);
        LocalTime lateTime = LocalTime.of(23, 59);

        dto.setTime(earlyTime);
        assertEquals(earlyTime, dto.getTime());

        dto.setTime(lateTime);
        assertEquals(lateTime, dto.getTime());
    }

    @Test
    void testLongMeetingTopic() {
        SessionDTO dto = new SessionDTO();
        String longTopic = "This is a very long meeting topic that describes in detail what will be covered during the mentoring session including various aspects of software development, best practices, career advice, and technical discussions";

        dto.setMeetingTopic(longTopic);

        assertEquals(longTopic, dto.getMeetingTopic());
        assertTrue(dto.getMeetingTopic().length() > 100);
    }

    @Test
    void testVariousLocationTypes() {
        SessionDTO dto = new SessionDTO();

        String[] locations = {
                "Online",
                "Conference Room B",
                "Coffee Shop Downtown",
                "University Library",
                "Remote - Google Meet",
                "Office Building - Floor 3"
        };

        for (String location : locations) {
            dto.setLocation(location);
            assertEquals(location, dto.getLocation());
        }
    }

    @Test
    void testConstructorDefaultStatus() {
        SessionDTO dto = new SessionDTO(1L, 2L, LocalDate.now(), LocalTime.now(), "Test Topic", "Test Location");

        assertEquals(Status.PENDING, dto.getStatus());
    }
}
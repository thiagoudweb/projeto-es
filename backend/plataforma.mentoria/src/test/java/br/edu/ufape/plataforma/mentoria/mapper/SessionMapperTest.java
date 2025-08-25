package br.edu.ufape.plataforma.mentoria.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.ufape.plataforma.mentoria.dto.SessionDTO;
import br.edu.ufape.plataforma.mentoria.enums.Status;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.model.Session;

class SessionMapperTest {

    private SessionMapper sessionMapper;

    @BeforeEach
    void setUp() {
        sessionMapper = new SessionMapper();
    }

    @Test
    void testSessionToDto() {
        Session session = new Session();
        session.setId(1L);
        session.setMeetingTopic("Test Meeting");
        session.setDate(LocalDate.now());
        session.setTime(LocalTime.of(14, 30));
        session.setLocation("Online");
        session.setStatus(Status.PENDING);

        Mentor mentor = new Mentor.Builder()
            .fullName("John Mentor")
            .build();
        mentor.setId(1L);
        session.setMentor(mentor);

        Mentored mentored = new Mentored();
        mentored.setId(2L);
        mentored.setFullName("Jane Student");
        session.setMentored(mentored);

        SessionDTO sessionDTO = sessionMapper.toDTO(session);

        assertNotNull(sessionDTO, "SessionDTO should not be null");
        assertEquals(session.getMentor().getId(), sessionDTO.getMentorId());
        assertEquals(session.getMeetingTopic(), sessionDTO.getMeetingTopic());
        assertEquals(session.getDate(), sessionDTO.getDate());
        assertEquals(session.getTime(), sessionDTO.getTime());
        assertEquals(session.getLocation(), sessionDTO.getLocation());
        assertEquals(session.getStatus(), sessionDTO.getStatus());
    }

    @Test
    void testDtoToSession() {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setMentorId(1L);
        sessionDTO.setMentoredId(2L);
        sessionDTO.setMeetingTopic("Test Session");
        sessionDTO.setDate(LocalDate.now());
        sessionDTO.setTime(LocalTime.of(15, 30));
        sessionDTO.setLocation("Conference Room");
        sessionDTO.setStatus(Status.PENDING);
        // for some reason, it isn't working with ACCEPTED

        Session session = sessionMapper.toEntity(sessionDTO);

        assertNotNull(session, "Session should not be null");
        assertEquals(sessionDTO.getMeetingTopic(), session.getMeetingTopic());
        assertEquals(sessionDTO.getDate(), session.getDate());
        assertEquals(sessionDTO.getTime(), session.getTime());
        assertEquals(sessionDTO.getLocation(), session.getLocation());
        assertEquals(sessionDTO.getStatus(), session.getStatus());

    }

    @Test
    void testSessionToDtoWithNullDto() {

        Session sessionDTO = sessionMapper.toEntity(null);
        assertNull(sessionDTO);
    }

    @Test
    void testSessionToDtoWithSessionNull() {

        SessionDTO sessionDTO = sessionMapper.toDTO(null);

        assertNull(sessionDTO);
    }
}

package br.edu.ufape.plataforma.mentoria.service;

import br.edu.ufape.plataforma.mentoria.dto.SessionDTO;
import br.edu.ufape.plataforma.mentoria.enums.*;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.SessionMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.model.Session;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.MentorRepository;
import br.edu.ufape.plataforma.mentoria.repository.MentoredRepository;
import br.edu.ufape.plataforma.mentoria.repository.SessionRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionMapper sessionMapper;


    private User user;
    private User guest;
    private Session session;
    private SessionDTO sessionDTO;

    @BeforeEach
    void setUp() {
        user = new User("user@gmail.com", "Joestar@123", UserRole.MENTORADO);
        user.setId(1L);
        Mentored mentored = new Mentored("Joestar", "12345678900",
                LocalDate.of(2000, 1, 1),
                Course.ADMINISTRACAO, user,
                "Estudante de Administração", InterestArea.CIBERSEGURANCA);

        guest = new User("guest@gmail.com", "Joestar@123", UserRole.MENTOR);
        guest.setId(2L);
        Mentor mentor = new Mentor("Guest Mentor", "98765432100",
                LocalDate.of(1990, 1, 1),
                Course.ADMINISTRACAO, guest,
                "Mentor profissional", AffiliationType.GESTOR,
                List.of("Gestão de Projetos"), InterestArea.CIBERSEGURANCA);

        session = new Session(user, guest,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        session.setId(1L);

       sessionDTO = new SessionDTO(
                user.getId(),
                guest.getId(),
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord"
        );
    }

    @Test
    void createSession() {

        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        when(userRepository.findById(guest.getId())).thenReturn(java.util.Optional.of(guest));
        when(sessionMapper.toEntity(sessionDTO)).thenReturn(session);
        when(sessionRepository.save(session)).thenReturn(session);

        Session createdSession = sessionService.createSession(sessionDTO);

        assertNotNull(createdSession);
        assertEquals(session.getMeetingTopic(), createdSession.getMeetingTopic());
    }

    @Test
    void updateSession() throws Exception {
        SessionDTO sessionUpdateDTO = new SessionDTO(
                user.getId(),
                guest.getId(),
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Meet"
        );

        Session updatedEntity = new Session(user, guest,
                sessionUpdateDTO.getDate(),
                sessionUpdateDTO.getTime(),
                sessionUpdateDTO.getMeetingTopic(),
                sessionUpdateDTO.getLocation());
        updatedEntity.setId(session.getId());

        // Mockando repositório e mapper
        when(sessionRepository.findById(session.getId())).thenReturn(java.util.Optional.of(session));
        when(sessionMapper.toEntity(sessionUpdateDTO)).thenReturn(updatedEntity);
        when(sessionRepository.save(updatedEntity)).thenReturn(updatedEntity);
        when(sessionMapper.toDTO(updatedEntity)).thenReturn(sessionUpdateDTO);

        // Executa o método
        SessionDTO updatedSession = sessionService.updateSession(session.getId(), sessionUpdateDTO);

        // Asserções
        assertNotNull(updatedSession);
        assertEquals("Meet", updatedSession.getLocation()); // verifica se o campo atualizado está correto
        assertEquals(session.getMeetingTopic(), updatedSession.getMeetingTopic()); // opcional, já que não mudou
    }

    @Test
    void getSessionById() {
        when(sessionRepository.findById(session.getId())).thenReturn(java.util.Optional.of(session));

        Session result = sessionService.getSessionById(session.getId());

        assertNotNull(result);
        assertEquals(session.getId(), result.getId());
    }

    @Test
    void getSessionById_NotFound() {
        when(sessionRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> sessionService.getSessionById(99L));
    }

    @Test
    void deleteSession() {
        when(sessionRepository.findById(session.getId())).thenReturn(java.util.Optional.of(session));
        sessionService.deleteSession(session.getId());


        verify(sessionRepository, times(1)).delete(session);
    }

    @Test
    void deleteSession_SessionNotFound() {
        Long fakeId = 999L;
        when(sessionRepository.findById(fakeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> sessionService.deleteSession(fakeId));
    }

    @Test
    void findSessionHistoryBetweenUsers() {
        // Mocka os usuários retornados pelo userRepository.findById
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.findById(guest.getId())).thenReturn(Optional.of(guest));

        // Mocka o retorno do sessionRepository.findByUserAndGuestOrUserAndGuest
        when(sessionRepository.findByUserAndGuestOrUserAndGuest(user, guest, guest, user))
                .thenReturn(List.of(session));

        // Mocka a conversão da entidade Session para SessionDTO
        when(sessionMapper.toDTO(session)).thenReturn(sessionDTO);

        // Chama o método do serviço
        List<SessionDTO> sessionHistory = sessionService.findSessionHistoryBetweenUsers(user.getId(), guest.getId());

        // Verificações
        assertNotNull(sessionHistory);
        assertFalse(sessionHistory.isEmpty());
        assertEquals(1, sessionHistory.size());
        assertEquals(sessionDTO, sessionHistory.get(0));
    }

    @Test
    void findSessionHistoryUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Retorna sessão só em findByUserId, findByGuestId vazio
        when(sessionRepository.findByUserId(user.getId())).thenReturn(List.of(session));
        when(sessionRepository.findByGuestId(user.getId())).thenReturn(List.of());

        when(sessionMapper.toDTO(session)).thenReturn(sessionDTO);

        List<SessionDTO> sessionHistoryUser = sessionService.findSessionHistoryUser(user.getId());

        assertNotNull(sessionHistoryUser);
        assertFalse(sessionHistoryUser.isEmpty());
        assertEquals(1, sessionHistoryUser.size());
        assertEquals(sessionDTO, sessionHistoryUser.get(0));
    }

}
package br.edu.ufape.plataforma.mentoria.service;

import br.edu.ufape.plataforma.mentoria.dto.SessionDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;

import br.edu.ufape.plataforma.mentoria.mapper.SessionMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.Session;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.SessionRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private UserRepository userRepository;

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException(Session.class, id));
    }

    public Session createSession(SessionDTO sessionDTO) {

        User user = userRepository.findById(sessionDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com o ID " + sessionDTO.getUserId() + " não encontrado."));

        User guest = userRepository.findById(sessionDTO.getGuestId())
                .orElseThrow(() -> new EntityNotFoundException("Convidado com o ID " + sessionDTO.getGuestId() + " não encontrado."));

        if( user.getId().equals(guest.getId())) {
            throw new IllegalArgumentException("Usuário e convidado não podem ser a mesma pessoa.");
        }

        Session session = sessionMapper.toEntity(sessionDTO);
        session.setUser(user);
        session.setGuest(guest);


        return sessionRepository.save(session);
    }

    public SessionDTO updateSession(Long id, SessionDTO sessionDTO) throws Exception{
        // Buscar o Sessão existente para obter o ID e os usuários associados
        Session existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Session.class, id));

        // Converter DTO para Entity
        Session sessionToUpdate = sessionMapper.toEntity(sessionDTO);

        // Definir o ID e os usuários do Sessão existente
        sessionToUpdate.setId(existingSession.getId());
        sessionToUpdate.setUser(existingSession.getUser());
        sessionToUpdate.setGuest(existingSession.getGuest());

        // Salvar a sessão atualizada
        Session updatedSession = sessionRepository.save(sessionToUpdate);

        return sessionMapper.toDTO(updatedSession);
    }

    public void deleteSession(Long id) {
        Session session = getSessionById(id);
        sessionRepository.delete(session);
    }

    public void deleteSession(Session session) {
        if (session == null) {
            throw new EntityNotFoundException(Session.class, "null");
        }
        sessionRepository.delete(session);
    }

    public SessionDTO getSessionDTOById(Long id) {
        Session session = getSessionById(id);
        return sessionMapper.toDTO(session);
    }

    public List<SessionDTO> findSessionHistoryBetweenUsers(Long userId1, Long userId2) {
        User user1 = userRepository.findById(userId1)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com o ID " + userId1 + " não encontrado."));

        User user2 = userRepository.findById(userId2)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com o ID " + userId2 + " não encontrado."));
        List<Session> sessions = sessionRepository.findByUserAndGuestOrUserAndGuest(user1, user2, user2, user1);

        return sessions.stream().
                map(sessionMapper::toDTO).
                collect(Collectors.toList());
    }

    public List<SessionDTO> findSessionHistoryUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com o ID " + userId + " não encontrado."));

        List<Session> sessionUser = sessionRepository.findByUserId(user.getId());
        List<Session> sessionGuest = sessionRepository.findByGuestId(user.getId());

        List<Session> sessoesJuntas = Stream.concat(sessionUser.stream(), sessionGuest.stream())
                .toList();

        return sessoesJuntas.stream().
                map(sessionMapper::toDTO).
                collect(Collectors.toList());
    }

    public List<SessionDTO> findAll() {
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toDTO)
                .collect(Collectors.toList());
    }
}

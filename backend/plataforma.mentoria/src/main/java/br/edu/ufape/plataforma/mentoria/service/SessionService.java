package br.edu.ufape.plataforma.mentoria.service;

import br.edu.ufape.plataforma.mentoria.dto.SessionDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;

import br.edu.ufape.plataforma.mentoria.mapper.SessionMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.model.Session;
import br.edu.ufape.plataforma.mentoria.repository.MentorRepository;
import br.edu.ufape.plataforma.mentoria.repository.MentoredRepository;
import br.edu.ufape.plataforma.mentoria.repository.SessionRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentoredRepository mentoredRepository;

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException(Session.class, id));
    }

    public Session createSession(SessionDTO sessionDTO) {

        Mentor mentor = mentorRepository.findById(sessionDTO.getMentorId())
                .orElseThrow(() -> new EntityNotFoundException("Mentor com o ID " + sessionDTO.getMentorId() + " não encontrado."));

        Mentored mentored = mentoredRepository.findById(sessionDTO.getMentoredID())
                .orElseThrow(() -> new EntityNotFoundException("Mentorado com o ID " + sessionDTO.getMentoredID() + " não encontrado."));

        if(mentor.getId().equals(mentored.getId())) {
            throw new IllegalArgumentException("Mentor e Mentorado não podem ser a mesma pessoa.");
        }
        Session session = sessionMapper.toEntity(sessionDTO);
        session.setMentor(mentor);
        session.setMentored(mentored);

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
        sessionToUpdate.setMentor(existingSession.getMentor());
        sessionToUpdate.setMentored(existingSession.getMentored());

        // Salvar a sessão atualizada
        Session updatedSession = sessionRepository.save(sessionToUpdate);

        return sessionMapper.toDTO(updatedSession);
    }

    public void deleteSession(Long id) {
        Session session = getSessionById(id);
        sessionRepository.delete(session);
    }

    public SessionDTO getSessionDTOById(Long id) {
        Session session = getSessionById(id);
        return sessionMapper.toDTO(session);
    }

    public List<SessionDTO> findSessionHistoryBetweenUsers(Long mentorId, Long mentoredId) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new EntityNotFoundException("Mentor com o ID " + mentorId + " não encontrado."));

        Mentored mentored = mentoredRepository.findById(mentoredId)
                .orElseThrow(() -> new EntityNotFoundException("Mentorado com o ID " + mentoredId + " não encontrado."));

        List<Session> sessions = sessionRepository.findByMentorIdAndMentoredId(mentorId, mentoredId);

        return sessions.stream().
                map(sessionMapper::toDTO).
                collect(Collectors.toList());
    }

    public List<SessionDTO> findSessionHistoryMentor(Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new EntityNotFoundException("Mentor com o ID " + mentorId + " não encontrado."));

        List<Session> sessions = sessionRepository.findByMentorId(mentorId);

        return sessions.stream().
                map(sessionMapper::toDTO).
                collect(Collectors.toList());
    }

    public List<SessionDTO> findSessionHistoryMentored(Long mentoredId) {
        Mentored mentored = mentoredRepository.findById(mentoredId)
                .orElseThrow(() -> new EntityNotFoundException("Mentorado com o ID " + mentoredId + " não encontrado."));

        List<Session> sessions = sessionRepository.findByMentoredId(mentoredId);

        return sessions.stream().
                map(sessionMapper::toDTO).
                collect(Collectors.toList());
    }

    public List<SessionDTO> findAll() {
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toDTO)
                .collect(Collectors.toList());
    }
}

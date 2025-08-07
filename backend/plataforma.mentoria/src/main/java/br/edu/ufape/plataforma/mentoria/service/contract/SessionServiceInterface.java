package br.edu.ufape.plataforma.mentoria.service.contract;

import br.edu.ufape.plataforma.mentoria.dto.SessionDTO;
import br.edu.ufape.plataforma.mentoria.enums.Status;
import br.edu.ufape.plataforma.mentoria.model.Session;

import java.util.List;

public interface SessionServiceInterface {


    public Session getSessionById(Long id);
    public Session createSession(SessionDTO sessionDTO);
    public SessionDTO updateSession(Long id, SessionDTO sessionDTO);
    public void deleteSession(Long id);
    public SessionDTO getSessionDTOById(Long id);
    public SessionDTO updateSessionStatus(Long id, Status newStatus);
    public List<SessionDTO> findSessionHistoryBetweenUsers(Long mentorId, Long mentoredId);
    public List<SessionDTO> findSessionHistoryMentor(Long mentorId);
    public List<SessionDTO> findSessionHistoryMentored(Long mentoredId);
    public List<SessionDTO> findAll();
}

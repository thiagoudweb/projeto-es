package br.edu.ufape.plataforma.mentoria.repository;

import br.edu.ufape.plataforma.mentoria.model.Session;
import br.edu.ufape.plataforma.mentoria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
    List<Session> findByUserId(Long userId);
    List<Session> findByGuestId(Long guestId);
    List<Session> findByUserIdAndGuestId(Long userId, Long guestId);
    List<Session> findByUserAndGuestOrUserAndGuest(User user1, User guest1,
                                                   User user, User guest);
}

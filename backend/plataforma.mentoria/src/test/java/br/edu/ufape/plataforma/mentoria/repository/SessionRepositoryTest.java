package br.edu.ufape.plataforma.mentoria.repository;

import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.enums.UserRole;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.model.Session;
import br.edu.ufape.plataforma.mentoria.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

@DataJpaTest
class SessionRepositoryTest {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentoredRepository mentoredRepository;

    private User user;
    private User guest;
    private User userFake;
    private User guestFake;

    @BeforeEach
    void setUp() {
        user = new User("user@gmail.com", "Joestar@123", UserRole.MENTORADO);
        userRepository.save(user);
        Mentored mentored = new Mentored("Joestar", "12345678900",
                LocalDate.of(2000, 1, 1),
                Course.ADMINISTRACAO, user,
                "Estudante de Administração", InterestArea.CIBERSEGURANCA);
        mentoredRepository.save(mentored);

        guest = new User("guest@gmail.com", "Joestar@123", UserRole.MENTOR);
        userRepository.save(guest);
        Mentor mentor = new Mentor("Guest Mentor", "98765432100",
                LocalDate.of(1990, 1, 1),
                Course.ADMINISTRACAO, guest,
                "Mentor profissional", AffiliationType.GESTOR,
                List.of("Gestão de Projetos"), InterestArea.CIBERSEGURANCA);
        mentorRepository.save(mentor);

        userFake = new User("userFake@gmail.com", "Joestar@123", UserRole.MENTORADO);
        userRepository.save(userFake);
        Mentored mentoredFake = new Mentored("Joestar", "12345678911",
                LocalDate.of(2000, 1, 1),
                Course.ADMINISTRACAO, userFake,
                "Estudante de Administração", InterestArea.CIBERSEGURANCA);
        mentoredRepository.save(mentoredFake);

        guestFake = new User("guestFake@gmail.com", "Joestar@123", UserRole.MENTOR);
        userRepository.save(guestFake);
        Mentor mentorFake = new Mentor("Guest Mentor", "98765432122",
                LocalDate.of(1990, 1, 1),
                Course.ADMINISTRACAO, guestFake,
                "Mentor profissional", AffiliationType.GESTOR,
                List.of("Gestão de Projetos"), InterestArea.CIBERSEGURANCA);
        mentorRepository.save(mentorFake);
    }

    @Test
    void findByUser() {
        Session session = new Session(user, guest,
                                      LocalDate.of(2023, 10, 1),
                                      LocalTime.of(10, 0),
                                      "Discussão sobre o projeto",
                                      "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByUserId(user.getId());

        assert !sessions.isEmpty();
        assert sessions.getFirst().getUser().equals(user);
    }

    @Test
    void findByUserErro() {
        Session session = new Session(userFake, guest,
                                      LocalDate.of(2023, 10, 1),
                                      LocalTime.of(10, 0),
                                      "Discussão sobre o projeto",
                                      "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByUserId(user.getId());

        assert sessions.isEmpty();
    }

    @Test
    void findByGuest() {
        Session session = new Session(user, guest,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByGuestId(guest.getId());

        assert !sessions.isEmpty();
        assert sessions.getFirst().getGuest().equals(guest);
    }

    @Test
    void findByGuestErro() {
        Session session = new Session(user, guestFake,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByGuestId(guest.getId());

        assert sessions.isEmpty();
    }

    @Test
    void findByUserAndGuest() {
        Session session = new Session(user, guest,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByUserIdAndGuestId(user.getId(), guest.getId());

        assert !sessions.isEmpty();
        assert sessions.getFirst().getGuest().equals(guest);
    }

    @Test
    void findByUserAndGuestErro() {
        Session session = new Session(userFake, guestFake,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByUserIdAndGuestId(user.getId(), guest.getId());

        assert sessions.isEmpty();
    }

    @Test
    void findByUserAndGuestSameId() {
        Session session = new Session(user, guest,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        Session session2 = new Session(guest, user,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session2);

        Session session3 = new Session(user, guestFake,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session3);

        List<Session> sessionUser = sessionRepository.findByUserId(user.getId());
        List<Session> sessionGuest = sessionRepository.findByGuestId(user.getId());

        List<Session> sessoesJuntas = Stream.concat(sessionUser.stream(), sessionGuest.stream())
                .toList();


        assert !sessoesJuntas.isEmpty();
        assert sessoesJuntas.size() == 3;
    }


    @Test
    void findByUserAndGuestOrUserAndGuest() {
        Session session = new Session(user, guest,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        Session session2 = new Session(guest, user,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session2);

        List<Session> sessions = sessionRepository.findByUserAndGuestOrUserAndGuest(user, guest, guest, user);

        assert !sessions.isEmpty();
        assert sessions.getFirst().getUser().equals(user);
        assert sessions.getLast().getGuest().equals(user);
    }

    @Test
    void findByUserAndGuestOrUserAndGuestErro() {
        Session session = new Session(user, guest,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        Session session2 = new Session(user, guestFake,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session2);

        List<Session> sessions = sessionRepository.findByUserAndGuestOrUserAndGuest(user, guest, guest, user);

        assert  sessions.size() == 1;
    }
}
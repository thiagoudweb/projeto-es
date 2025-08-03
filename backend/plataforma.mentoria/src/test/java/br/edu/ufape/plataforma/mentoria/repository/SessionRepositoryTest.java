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

    private Mentored mentored;
    private Mentor mentor;
    private Mentored mentoredFake;
    private Mentor mentorFake;

    @BeforeEach
    void setUp() {
        User user = new User("user@gmail.com", "Joestar@123", UserRole.MENTORADO);
        userRepository.save(user);
        mentored = new Mentored("Joestar", "12345678900",
                LocalDate.of(2000, 1, 1),
                Course.ADMINISTRACAO, user,
                "Estudante de Administração", InterestArea.CIBERSEGURANCA);
        mentoredRepository.save(mentored);

        User guest = new User("guest@gmail.com", "Joestar@123", UserRole.MENTOR);
        userRepository.save(guest);
        mentor = new Mentor("Guest Mentor", "98765432100",
                LocalDate.of(1990, 1, 1),
                Course.ADMINISTRACAO, guest,
                "Mentor profissional", AffiliationType.GESTOR,
                List.of("Gestão de Projetos"), List.of(InterestArea.CIBERSEGURANCA));
                mentorRepository.save(mentor);

        User userFake = new User("userFake@gmail.com", "Joestar@123", UserRole.MENTORADO);
        userRepository.save(userFake);
        mentoredFake = new Mentored("Joestar", "12345678911",
                LocalDate.of(2000, 1, 1),
                Course.ADMINISTRACAO, userFake,
                "Estudante de Administração", InterestArea.CIBERSEGURANCA);
        mentoredRepository.save(mentoredFake);

        User guestFake = new User("guestFake@gmail.com", "Joestar@123", UserRole.MENTOR);
        userRepository.save(guestFake);
        mentorFake = new Mentor("Guest Mentor", "98765432122",
                LocalDate.of(1990, 1, 1),
                Course.ADMINISTRACAO, guestFake,
                "Mentor profissional", AffiliationType.GESTOR,
                List.of("Gestão de Projetos"), List.of(InterestArea.CIBERSEGURANCA));
        mentorRepository.save(mentorFake);
    }

    @Test
    void findByMentor() {
        Session session = new Session(mentor, mentored,
                                      LocalDate.of(2023, 10, 1),
                                      LocalTime.of(10, 0),
                                      "Discussão sobre o projeto",
                                      "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByMentorId(mentor.getId());

        assert !sessions.isEmpty();
        assert sessions.getFirst().getDate().equals(LocalDate.of(2023, 10, 1));
    }

    @Test
    void findByMentorErro() {
        Session session = new Session(mentorFake, mentored,
                                      LocalDate.of(2023, 10, 1),
                                      LocalTime.of(10, 0),
                                      "Discussão sobre o projeto",
                                      "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByMentorId(mentor.getId());

        assert sessions.isEmpty();
    }

    @Test
    void findByMentored() {
        Session session = new Session(mentor, mentored,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByMentoredId(mentored.getId());

        assert !sessions.isEmpty();
        assert sessions.getFirst().getMentored().equals(mentored);
    }

    @Test
    void findByMentoredErro() {
        Session session = new Session(mentor, mentoredFake,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByMentoredId(mentored.getId());

        assert sessions.isEmpty();
    }

    @Test
    void findByMentorAndMentored() {
        Session session = new Session(mentor, mentored,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByMentorIdAndMentoredId(mentor.getId(), mentored.getId());

        assert !sessions.isEmpty();
        assert sessions.getFirst().getMentor().equals(mentor);
        assert sessions.getFirst().getMentored().equals(mentored);
    }

    @Test
    void findByMentorAndMentoredErro() {
        Session session = new Session(mentorFake, mentoredFake,
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 0),
                "Discussão sobre o projeto",
                "Discord");
        sessionRepository.save(session);

        List<Session> sessions = sessionRepository.findByMentorIdAndMentoredId(mentor.getId(), mentored.getId());

        assert sessions.isEmpty();
    }
}
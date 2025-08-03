package br.edu.ufape.plataforma.mentoria.repository;
import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.enums.UserRole;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class MentorRepositoryTest {

    @Autowired
    private MentorRepository mentorRepository;

    @Test
    void testFindByInterestAreaAndSpecializations() {
        User user = new User();
        user.setEmail("maria@teste.com");
        user.setPassword("senhaSegura123");
        user.setRole(UserRole.MENTOR);

        List<String> lista = new ArrayList<String>();
        lista.add("Engenharia de Software");

        // Dado: criar e salvar um Mentor com interesse em ENGENHARIA
        Mentor mentor = new Mentor();
        mentor.setFullName("Maria Souza");
        mentor.setCpf("12345678900");
        mentor.setSpecializations(lista);
        mentor.setAffiliationType(AffiliationType.GESTOR);
        mentor.setBirthDate(LocalDate.of(1990, 1, 1));
        mentor.setProfessionalSummary("Professional");
        mentor.setUser(user);
        mentor.setInterestArea(List.of(InterestArea.CIBERSEGURANCA));
        mentor.setCourse(Course.ADMINISTRACAO);
        mentorRepository.save(mentor);

        /* --------------------------------------------------------------------------------------- */
        User user2 = new User();
        user2.setEmail("maria123@teste.com");
        user2.setPassword("senhaSegura123");
        user2.setRole(UserRole.MENTOR);

        // Dado: criar e salvar um Mentor com interesse em ENGENHARIA
        Mentor mentor2 = new Mentor();
        mentor2.setFullName("Maria Souza");
        mentor2.setCpf("12345678901");
        mentor2.setSpecializations(lista);
        mentor2.setAffiliationType(AffiliationType.GESTOR);
        mentor2.setBirthDate(LocalDate.of(1990, 1, 1));
        mentor2.setProfessionalSummary("Professional");
        mentor2.setUser(user2);
        mentor2.setInterestArea(List.of(InterestArea.CIBERSEGURANCA));
        mentor2.setCourse(Course.ADMINISTRACAO);

        mentorRepository.save(mentor2);

        List<Mentor> encontrados = mentorRepository.findByInterestAreaAndSpecializationsContaining(InterestArea.CIBERSEGURANCA,"Engenharia de Software");

        // Então: deve conter o mentor criado
        assertFalse(encontrados.isEmpty());
        assertEquals(2, encontrados.size());
    }
}
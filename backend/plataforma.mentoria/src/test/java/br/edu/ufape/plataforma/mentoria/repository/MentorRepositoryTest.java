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
        Mentor mentor = new Mentor.Builder()
            .fullName("Maria Souza")
            .cpf("12345678900")
            .specializations(lista)
            .affiliationType(AffiliationType.GESTOR)
            .birthDate(LocalDate.of(1990, 1, 1))
            .professionalSummary("Professional")
            .user(user)
            .interestArea(List.of(InterestArea.CIBERSEGURANCA))
            .course(Course.ADMINISTRACAO)
            .build();
        mentorRepository.save(mentor);

        /* --------------------------------------------------------------------------------------- */
        User user2 = new User();
        user2.setEmail("maria123@teste.com");
        user2.setPassword("senhaSegura123");
        user2.setRole(UserRole.MENTOR);

        // Dado: criar e salvar um Mentor com interesse em ENGENHARIA
        Mentor mentor2 = new Mentor.Builder()
            .fullName("Maria Souza")
            .cpf("12345678901")
            .specializations(lista)
            .affiliationType(AffiliationType.GESTOR)
            .birthDate(LocalDate.of(1990, 1, 1))
            .professionalSummary("Professional")
            .user(user2)
            .interestArea(List.of(InterestArea.CIBERSEGURANCA))
            .course(Course.ADMINISTRACAO)
            .build();

        mentorRepository.save(mentor2);

        List<Mentor> encontrados = mentorRepository.findByInterestAreaAndSpecializationsContaining(InterestArea.CIBERSEGURANCA,"Engenharia de Software");

        // Então: deve conter o mentor criado
        assertFalse(encontrados.isEmpty());
        assertEquals(2, encontrados.size());
    }
}
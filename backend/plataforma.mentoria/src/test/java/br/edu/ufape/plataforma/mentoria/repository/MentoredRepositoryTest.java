package br.edu.ufape.plataforma.mentoria.repository;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.enums.UserRole;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class MentoredRepositoryTest {

    @Autowired
    private MentoredRepository mentoredRepository;

    @Test
    void testFindByInterestArea() {
        User user = new User();
        user.setEmail("maria@teste.com");
        user.setPassword("senhaSegura123");
        user.setRole(UserRole.MENTORADO);

        // Dado: criar e salvar um Mentor com interesse em CIBERSEGURANCA
        Mentored mentored = new Mentored();
        mentored.setFullName("Maria Souza");
        mentored.setCpf("12345678900");
        mentored.setBirthDate(LocalDate.of(1990, 1, 1));
        mentored.setUser(user);
        mentored.setInterestArea(InterestArea.CIBERSEGURANCA);
        mentored.setCourse(Course.ADMINISTRACAO);
        mentored.setAcademicSummary("Academic");
        mentoredRepository.save(mentored);
        /*-----------------------------------------------------------------------------------------------------------------*/
        User user2 = new User();
        user2.setEmail("joao@teste.com");
        user2.setPassword("senhaSegura123");
        user2.setRole(UserRole.MENTORADO);

        // Dado: criar e salvar um Mentor com interesse em CIBERSEGURANCA
        Mentored mentored2 = new Mentored();
        mentored2.setFullName("Joao Souza");
        mentored2.setCpf("12345678901");
        mentored2.setBirthDate(LocalDate.of(1990, 1, 1));
        mentored2.setUser(user2);
        mentored2.setInterestArea(InterestArea.CIBERSEGURANCA);
        mentored2.setCourse(Course.ADMINISTRACAO);
        mentored2.setAcademicSummary("Academic");
        mentoredRepository.save(mentored2);

        List<Mentored> encontrados = mentoredRepository.findByInterestArea(InterestArea.CIBERSEGURANCA);

        // Então: deve conter o mentor criado
        assertFalse(encontrados.isEmpty());
        assertEquals(2, encontrados.size());
    }
}
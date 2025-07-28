package br.edu.ufape.plataforma.mentoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

}
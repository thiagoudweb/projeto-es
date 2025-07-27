package br.edu.ufape.plataforma.mentoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

@Repository
public interface MentoredRepository extends JpaRepository<Mentored, Long> {

}

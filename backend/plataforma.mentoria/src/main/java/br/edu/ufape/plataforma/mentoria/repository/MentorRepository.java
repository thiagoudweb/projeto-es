package br.edu.ufape.plataforma.mentoria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.plataforma.mentoria.enums.InterestAreas;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    boolean existsByCpf(String cpf);
    Optional<Mentor> findByUserEmail(String email);
    List<Mentor> findByInterestAreas(InterestAreas interestArea);
    List<Mentor> findByFullNameContainingIgnoreCaseAndInterestAreas(String fullName, InterestAreas interestArea);
    List<Mentor> findByFullNameContainingIgnoreCase(String fullName);
}
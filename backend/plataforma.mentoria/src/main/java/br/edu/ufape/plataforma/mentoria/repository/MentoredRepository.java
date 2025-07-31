package br.edu.ufape.plataforma.mentoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

import java.util.List;
import java.util.Optional;

@Repository
public interface MentoredRepository extends JpaRepository<Mentored, Long> {
    public boolean existsByCpf(String cpf);

    public Mentored findByCpf(String cpf);

    public Optional<Mentored> findByUserEmail(String email);

//    @Query("SELECT m FROM Mentored m JOIN m.interestAreas ia WHERE UPPER(CAST(ia AS string)) LIKE UPPER(CONCAT('%', :interest, '%'))")
//    List<Mentored> findByInterestAreaContaining(@Param("interest") String interest);
}

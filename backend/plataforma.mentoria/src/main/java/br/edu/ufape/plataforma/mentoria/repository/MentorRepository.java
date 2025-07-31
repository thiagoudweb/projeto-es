package br.edu.ufape.plataforma.mentoria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    boolean existsByCpf(String cpf);
    Optional<Mentor> findByUserEmail(String email);
//    List<Mentor> findByInterestAreas(InterestArea interestArea);
//    List<Mentor> findByFullNameContainingIgnoreCaseAndInterestAreas(String fullName, InterestArea interestArea);
//    List<Mentor> findByFullNameContainingIgnoreCase(String fullName);

//    @Query("SELECT m FROM Mentor m LEFT JOIN m.specializations s WHERE (:interestArea IS NULL OR :interestArea MEMBER OF m.interestAreas) AND (:specializations IS NULL OR s IN :specializations)")
//    List<Mentor> findByInterestAreaAndSpecializations(@Param("interestArea") InterestArea area, @Param("specializations") List<String> specializations);

}
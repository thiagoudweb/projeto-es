package br.edu.ufape.plataforma.mentoria.model;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.CascadeType;

@Entity
@AssociationOverrides({
    @AssociationOverride(name = "interestArea",
        joinTable = @JoinTable(name = "mentored_interest_areas",
            joinColumns = @JoinColumn(name = "mentored_id")))
})
public class Mentored extends Person {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @PrimaryKeyJoinColumn
    private User user;

    private String academicSummary;

    public Mentored(String fullName, String cpf, LocalDate birthDate, Course course, User user,
                    String academicSummary, List<InterestArea> interestArea) {
        super(fullName, cpf, birthDate, course, interestArea);
        this.user = user;
        this.academicSummary = academicSummary;
    }

    public Mentored() {

    }

    public Mentored(String fullName, String cpf, LocalDate of, Course administracao, User user2,
            String academicSummary2, InterestArea ciberseguranca) {
        //TODO Auto-generated constructor stub
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcademicSummary() {
        return academicSummary;
    }

    public void setAcademicSummary(String academicSummary) {
        this.academicSummary = academicSummary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

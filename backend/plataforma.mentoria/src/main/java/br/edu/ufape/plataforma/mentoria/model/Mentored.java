package br.edu.ufape.plataforma.mentoria.model;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import jakarta.persistence.*;

@Entity
@AssociationOverride(name = "interestArea",
        joinTable = @JoinTable(name = "mentored_interest_areas",
                joinColumns = @JoinColumn(name = "mentored_id")))
public class Mentored extends Person {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
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
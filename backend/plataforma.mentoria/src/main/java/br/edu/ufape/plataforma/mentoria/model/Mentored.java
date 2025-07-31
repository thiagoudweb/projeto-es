package br.edu.ufape.plataforma.mentoria.model;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Mentored extends Person {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    private User user;

    private String academicSummary;

    public Mentored(String fullName, String cpf, LocalDate birthDate, Course course, User user,
                    String academicSummary, InterestArea interestArea) {
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

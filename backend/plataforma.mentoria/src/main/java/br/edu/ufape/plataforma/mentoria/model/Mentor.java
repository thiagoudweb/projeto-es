package br.edu.ufape.plataforma.mentoria.model;

import java.time.LocalDate;
import java.util.List;

import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import jakarta.persistence.*;

@Entity
@AssociationOverride(name = "interestArea",
        joinTable = @JoinTable(name = "mentor_interest_areas",
                joinColumns = @JoinColumn(name = "mentor_id")))
public class Mentor extends Person {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    private String professionalSummary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AffiliationType affiliationType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mentor_specializations", joinColumns = @JoinColumn(name = "mentor_id"))
    @Column(name = "specialization")
    private List<String> specializations;

    public Mentor(String fullName, String cpf, LocalDate birthDate, Course course, User user, String professionalSummary, AffiliationType affiliationType, List<String> specializations, List<InterestArea> interestArea) {
        super(fullName, cpf, birthDate, course, interestArea);
        this.user = user;
        this.professionalSummary = professionalSummary;
        this.affiliationType = affiliationType;
        this.specializations = specializations;
    }

    public Mentor(){

    }
    public void setId(Long id) { this.id = id;}

    public Long getId() {
        return this.id;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

    public AffiliationType getAffiliationType() {
        return affiliationType;
    }

    public void setAffiliationType(AffiliationType affiliationType) {
        this.affiliationType = affiliationType;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
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
    public static class Builder {
        private String fullName;
        private String cpf;
        private LocalDate birthDate;
        private Course course;
        private User user;
        private String professionalSummary;
        private AffiliationType affiliationType;
        private List<String> specializations;
        private List<InterestArea> interestArea;

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }
        public Builder course(Course course) {
            this.course = course;
            return this;
        }
        public Builder user(User user) {
            this.user = user;
            return this;
        }
        public Builder professionalSummary(String professionalSummary) {
            this.professionalSummary = professionalSummary;
            return this;
        }
        public Builder affiliationType(AffiliationType affiliationType) {
            this.affiliationType = affiliationType;
            return this;
        }
        public Builder specializations(List<String> specializations) {
            this.specializations = specializations;
            return this;
        }
        public Builder interestArea(List<InterestArea> interestArea) {
            this.interestArea = interestArea;
            return this;
        }
        public Mentor build() {
            return new Mentor(fullName, cpf, birthDate, course, user, professionalSummary, affiliationType, specializations, interestArea);
        }
    }

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
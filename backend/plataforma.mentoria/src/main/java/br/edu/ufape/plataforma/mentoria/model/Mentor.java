package br.edu.ufape.plataforma.mentoria.model;

import java.util.List;
import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Mentor extends Person {

    @Id
    @Column(name = "user_id")
    private Long userId;

    private String professionalSummary;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AffiliationType affiliationType;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mentor_specializations", joinColumns = @JoinColumn(name = "mentor_id"))
    @Column(name = "specialization")
    private List<String> specializations;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
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
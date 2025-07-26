package br.edu.ufape.plataforma.mentoria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Mentored extends Person {

    @Id
    @Column(name = "user_id")
    private Long userId;
    
    private String academicSummary;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
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

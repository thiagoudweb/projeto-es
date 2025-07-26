package br.edu.ufape.plataforma.mentoria.model;

import java.time.LocalDate;

import br.edu.ufape.plataforma.mentoria.enums.Course;
// import java.util.List;
// import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
// import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ElementCollection;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    
    @Column(nullable = false)
    private String fullName;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Course course;
    
    // @ElementCollection(fetch = FetchType.EAGER)
    // @Enumerated(EnumType.STRING)
    // @CollectionTable(name = "person_interest_areas", joinColumns = @JoinColumn(name = "person_id"))
    // @Column(name = "interest_area")
    // private List<InterestArea> interestAreas;

    public Person(){

    }

    public Person(String fullName, String cpf, LocalDate birthDate, Course course) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.course = course;
    }
    
    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    // public List<InterestArea> getInterestAreas() {
    //     return interestAreas;
    // }
    
    // public void setInterestAreas(List<InterestArea> interestAreas) {
    //     this.interestAreas = interestAreas;
    // }
}
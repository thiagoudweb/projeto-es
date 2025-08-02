package br.edu.ufape.plataforma.mentoria.dto;

import java.time.LocalDate;

import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;

public class UpdateMentoredDTO {
    private String fullName;
    private LocalDate birthDate;
    private Course course;
    private String academicSummary;
    private InterestArea interestArea;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public String getAcademicSummary() {
        return academicSummary;
    }
    public void setAcademicSummary(String academicSummary) {
        this.academicSummary = academicSummary;
    }
    public InterestArea getInterestArea() {
        return interestArea;
    }
    public void setInterestArea(InterestArea interestArea) {
        this.interestArea = interestArea;
    }

}

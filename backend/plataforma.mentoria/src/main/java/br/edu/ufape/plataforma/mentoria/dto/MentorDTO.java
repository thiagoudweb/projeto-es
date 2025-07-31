package br.edu.ufape.plataforma.mentoria.dto;

import java.time.LocalDate;
import java.util.List;
import br.edu.ufape.plataforma.mentoria.enums.AffiliationType;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class MentorDTO {

    private Long id;

    @NotBlank(message = "O nome completo é obrigatório")
    private String fullName;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento deve ser no passado ou presente")
    private LocalDate birthDate;

    @NotNull(message = "O curso é obrigatório")
    private Course course;

    @Size(max = 1000, message = "O resumo profissional deve ter no máximo 1000 caracteres")
    private String professionalSummary;

    @NotNull(message = "O tipo de afiliação é obrigatório")
    private AffiliationType affiliationType;

    @NotEmpty(message = "Pelo menos uma especialização deve ser informada")
    private List<@NotBlank(message = "Especializações não podem estar em branco") String> specializations;

    @NotNull(message = "A área de interesse é obrigatória")
    private InterestArea interestArea;

    public MentorDTO() {
    }

    public MentorDTO(String fullName, String cpf, LocalDate birthDate, Course course, String professionalSummary,
            AffiliationType affiliationType, List<String> specializations, InterestArea interestArea) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.course = course;
        this.professionalSummary = professionalSummary;
        this.affiliationType = affiliationType;
        this.specializations = specializations;
        this.interestArea = interestArea;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public InterestArea getInterestArea() {
        return interestArea;
    }

    public void setInterestArea(InterestArea interestArea) {
        this.interestArea = interestArea;
    }

}

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
    private List<InterestArea> interestArea;

    private MentorDTO(String fullName, String cpf, LocalDate birthDate, Course course, String professionalSummary,
            AffiliationType affiliationType, List<String> specializations, List<InterestArea> interestArea) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.course = course;
        this.professionalSummary = professionalSummary;
        this.affiliationType = affiliationType;
        this.specializations = specializations;
        this.interestArea = interestArea;
    }

    public MentorDTO() {
    }

    public static class Builder {
        private String fullName;
        private String cpf;
        private LocalDate birthDate;
        private Course course;
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
        public MentorDTO build() {
            return new MentorDTO(fullName, cpf, birthDate, course, professionalSummary, affiliationType, specializations, interestArea);
        }
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

    public List<InterestArea> getInterestArea() {
        return interestArea;
    }

    public void setInterestArea(List<InterestArea> interestArea) {
        this.interestArea = interestArea;
    }

}

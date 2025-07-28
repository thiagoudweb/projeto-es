package br.edu.ufape.plataforma.mentoria.dto;

import java.time.LocalDate;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class MentoredDTO {

    @NotBlank(message = "O nome completo é obrigatório")
    private String fullName;

    @NotBlank(message = "O email é obrigatório")
    // @Size(max = 255, message = "O email deve ter no máximo 255 caracteres")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento deve ser no passado ou presente")
    private LocalDate birthDate;

    @NotNull(message = "O curso é obrigatório")
    private Course course;

    @Size(max = 1000, message = "O resumo acadêmico deve ter no máximo 1000 caracteres")
    private String academicSummary;

    public MentoredDTO() {
    }

    public MentoredDTO(String fullName, String cpf, LocalDate birthDate, Course course,
            String academicSummary) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.course = course;
        this.academicSummary = academicSummary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAcademicSummary() {
        return academicSummary;
    }

    public void setAcademicSummary(String academicSummary) {
        this.academicSummary = academicSummary;
    }
}

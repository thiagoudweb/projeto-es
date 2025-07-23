package br.edu.ufape.plataforma.mentoria.enums;

public enum Course {
    ADMINISTRACAO("Administração"),
    DIREITO("Direito"),
    MEDICINA("Medicina"),
    ENGENHARIA_CIVIL("Engenharia Civil"),
    CIENCIA_DA_COMPUTACAO("Ciência da Computação"),
    PSICOLOGIA("Psicologia"),
    ENFERMAGEM("Enfermagem"),
    ARQUITETURA_E_URBANISMO("Arquitetura e Urbanismo"),
    CONTABILIDADE("Ciências Contábeis"),
    ODONTOLOGIA("Odontologia"),
    PEDAGOGIA("Pedagogia"),
    FISIOTERAPIA("Fisioterapia"),
    NUTRICIONISMO("Nutrição"),
    EDUCACAO_FISICA("Educação Física"),
    VETERINARIA("Medicina Veterinária"),
    ZOOTECNIA("Zootecnia"),
    LETRAS("Letras");

    private final String courseName;

    Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}

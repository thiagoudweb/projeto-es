package br.edu.ufape.plataforma.mentoria.enums;

public enum AffiliationType {
    DOCENTE("Docente"),
    TECNICO_ADMINISTRATIVO("Técnico Administrativo"),
    ALUNO_POS_GRADUACAO("Aluno de Pós-Graduação"),
    PESQUISADOR("Pesquisador"),
    GESTOR("Gestor"),
    TERCEIRIZADO("Terceirizado");

    private final String affiliationName;

    AffiliationType(String affiliationName) {
        this.affiliationName = affiliationName;
    }

    public String getAffiliationName() {
        return affiliationName;
    }
}
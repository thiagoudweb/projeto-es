package br.edu.ufape.plataforma.mentoria.enums;


public enum InterestArea {

    TECNOLOGIA_DA_INFORMACAO("Tecnologia da Informação"),
    ENGENHARIA("Engenharia"),
    ADMINISTRACAO_E_GESTAO("Administração e Gestão"),
    CIENCIAS_EXATAS("Ciências Exatas"),
    CIENCIAS_HUMANAS("Ciências Humanas"),
    CIENCIAS_BIOLOGICAS_E_SAUDE("Ciências Biológicas e Saúde"),
    MARKETING_E_COMUNICACAO("Marketing e Comunicação"),
    FINANCAS("Finanças"),
    EDUCACAO("Educação"),
    JURIDICO("Jurídico");

    private final String nome;


    InterestArea(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
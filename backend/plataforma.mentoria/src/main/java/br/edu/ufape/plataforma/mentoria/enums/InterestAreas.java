package br.edu.ufape.plataforma.mentoria.enums;
import java.util.List;


public enum InterestAreas {

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


    InterestAreas(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
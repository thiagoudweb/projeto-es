package br.edu.ufape.plataforma.mentoria.enums;
import java.util.List;


public enum InterestAreas {

    TECNOLOGIA_DA_INFORMACAO("Tecnologia da Informação", List.of(
            "Desenvolvimento de Software",
            "Segurança da Informação",
            "Inteligência Artificial",
            "Machine Learning",
            "Cloud Computing"
    )),

    ENGENHARIA("Engenharia", List.of(
            "Engenharia Civil",
            "Engenharia Elétrica",
            "Engenharia Mecânica"
    )),

    ADMINISTRACAO_E_GESTAO("Administração e Gestão", List.of(
            "Gestão de Projetos",
            "Administração Geral",
            "Marketing e Vendas"
    )),

    CIENCIAS_EXATAS("Ciências Exatas", List.of(
            "Matemática",
            "Física",
            "Estatística"
    )),

    CIENCIAS_HUMANAS("Ciências Humanas", List.of(
            "Psicologia",
            "Sociologia",
            "Filosofia"
    )),

    CIENCIAS_BIOLOGICAS_E_SAUDE("Ciências Biológicas e Saúde", List.of(
            "Biologia",
            "Medicina",
            "Enfermagem"
    )),

    MARKETING_E_COMUNICACAO("Marketing e Comunicação", List.of(
            "Marketing Digital",
            "Comunicação Corporativa"
    )),

    FINANCAS("Finanças", List.of(
            "Análise Financeira",
            "Investimentos"
    )),

    EDUCACAO("Educação", List.of(
            "Pedagogia",
            "Tecnologias Educacionais"
    )),

    JURIDICO("Jurídico", List.of(
            "Direito Civil",
            "Direito Penal"
    ));

    private final String nome;
    private final List<String> subareas;

    InterestAreas(String nome, List<String> subareas) {
        this.nome = nome;
        this.subareas = subareas;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getSubareas() {
        return subareas;
    }
}
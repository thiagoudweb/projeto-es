package br.edu.ufape.plataforma.mentoria.enums;


public enum InterestArea {


    // Tecnologia da Informação e afins
    TECNOLOGIA_DA_INFORMACAO("Tecnologia da Informação"),
    DESENVOLVIMENTO_DE_SOFTWARE("Desenvolvimento de Software"),
    CIENCIA_DE_DADOS_E_IA("Ciência de Dados e Inteligência Artificial"),
    CIBERSEGURANCA("Cibersegurança"),
    UX_UI_DESIGN("UX/UI Design"),

    // Engenharia e Ciências Exatas
    ENGENHARIA_GERAL("Engenharia"),
    ENGENHARIA_CIVIL("Engenharia Civil"),
    ENGENHARIA_DE_PRODUCAO("Engenharia de Produção"),
    MATEMATICA_E_ESTATISTICA("Matemática e Estatística"),
    FISICA("Física"),

    // Administração, Gestão e Negócios
    ADMINISTRACAO_E_GESTAO("Administração e Gestão"),
    EMPREENDEDORISMO_E_INOVACAO("Empreendedorismo e Inovação"),
    FINANCAS_E_CONTABILIDADE("Finanças e Contabilidade"),
    RECURSOS_HUMANOS("Recursos Humanos"),
    LOGISTICA_E_CADEIA_DE_SUPRIMENTOS("Logística e Cadeia de Suprimentos"),

    // Marketing e Comunicação
    MARKETING_E_COMUNICACAO("Marketing e Comunicação"),
    MARKETING_DIGITAL("Marketing Digital"),
    JORNALISMO("Jornalismo"),
    PUBLICIDADE_E_PROPAGANDA("Publicidade e Propaganda"),
    COMUNICACAO_INSTITUCIONAL("Comunicação Institucional"),

    // Saúde e Ciências Biológicas
    CIENCIAS_BIOLOGICAS_E_SAUDE("Ciências Biológicas e Saúde"),
    MEDICINA("Medicina"),
    PSICOLOGIA("Psicologia"),
    NUTRICAO("Nutrição"),
    BIOTECNOLOGIA("Biotecnologia"),

    // Educação, Artes e Ciências Humanas
    EDUCACAO("Educação"),
    ARTES_E_DESIGN("Artes e Design"),
    CIENCIAS_HUMANAS_E_SOCIAIS("Ciências Humanas e Sociais"),
    LETRAS("Letras"),
    HISTORIA("História"),
    GEOGRAFIA("Geografia"),
    SOCIOLOGIA("Sociologia"),

    // Jurídico e Meio Ambiente
    JURIDICO("Jurídico"),
    DIREITO_DIGITAL("Direito Digital"),
    MEIO_AMBIENTE_E_SUSTENTABILIDADE("Meio Ambiente e Sustentabilidade");

    private final String nome;


    InterestArea(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
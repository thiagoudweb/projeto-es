package br.edu.ufape.plataforma.mentoria.enums;

public enum Status {
    PENDING("Pendente"),
    ACCEPTED("Aceito"),
    REJECTED("Rejeitado"),
    COMPLETED("Concluído"),
    CANCELLED("Cancelado");

    private final String statusSessao;

    Status(String statusSessao) {
        this.statusSessao = statusSessao;
    }

    public String getStatus() {
        return statusSessao;
    }

}

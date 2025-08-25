package br.edu.ufape.plataforma.mentoria.enums;

public enum Status {
    PENDING("Pendente"),
    ACCEPTED("Aceito"),
    REJECTED("Rejeitado"),
    COMPLETED("Concluído"),
    CANCELLED("Cancelado");

    private final String status_sessao;

    Status(String status_sessao) {
        this.status_sessao = status_sessao;
    }

    public String getStatus() {
        return status_sessao;
    }

}

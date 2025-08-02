package br.edu.ufape.plataforma.mentoria.enums;

public enum Status {
    PENDING("Pendente"),
    ACCEPTED("Aceito"),
    REJECTED("Rejeitado"),
    COMPLETED("Concluído"),
    CANCELLED("Cancelado");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}

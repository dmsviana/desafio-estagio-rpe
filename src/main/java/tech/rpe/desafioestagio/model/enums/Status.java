package tech.rpe.desafioestagio.model.enums;

public enum Status {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String status;

    Status(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

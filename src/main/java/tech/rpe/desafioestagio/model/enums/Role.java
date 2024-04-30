package tech.rpe.desafioestagio.model.enums;

public enum Role {

    DESENVOLVEDOR("Desenvolvedor"),
    QA("QA"),
    GERENTE("Gerente"),
    RH("RH");

    private final String role;

    Role(final String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

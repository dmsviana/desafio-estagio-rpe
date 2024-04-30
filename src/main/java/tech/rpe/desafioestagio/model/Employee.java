package tech.rpe.desafioestagio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import tech.rpe.desafioestagio.model.enums.Role;
import tech.rpe.desafioestagio.model.enums.Status;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "tb_employees")
public class Employee extends Person {


    @Enumerated(STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "date_of_hire", nullable = false)
    private LocalDate dateOfHire;

    public Employee() {
    }

    public Employee(final String id, final String nome, final String cpf, final String phoneNumber, final Address address, final Role role, final Status status, final LocalDate dateOfHire) {
        super(id, nome, cpf, phoneNumber, address);
        this.role = role;
        this.status = status;
        this.dateOfHire = dateOfHire;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(role, employee.role)
                .append(status, employee.status)
                .append(dateOfHire, employee.dateOfHire).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(role)
                .append(status)
                .append(dateOfHire).toHashCode();
    }
}

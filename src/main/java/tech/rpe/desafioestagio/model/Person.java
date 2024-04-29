package tech.rpe.desafioestagio.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.UUID;

@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = UUID)
    private String id;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 100)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true, updatable = false)
    @CPF
    private String cpf;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @OneToOne(cascade = ALL)
    private Address address;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Person() {

    }

    public Person(String id, String name, String cpf, String phoneNumber, Address address) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.validate();
    }

    private void validate() {
        if (this.name.length() < 3) {
            throw new IllegalArgumentException("O campo nome deve ter pelo menos 3 caracteres.");
        }
        if (!isValidCpf(this.cpf)) {
            throw new IllegalArgumentException("O campo cpf deve estar no formato 000.000.000-00.");
        }
    }

    private boolean isValidCpf(final String cpf) {
        return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return new EqualsBuilder()
                .append(id, person.id)
                .append(name, person.name)
                .append(cpf, person.cpf)
                .append(phoneNumber, person.phoneNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(cpf)
                .append(phoneNumber).toHashCode();
    }
}

package tech.rpe.desafioestagio.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tb_customers")
public class Customer extends Person {


    @Column(name = "last_service_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate lastServiceDate;

    public Customer() {

    }

    public Customer(String id, String name, String cpf, String phoneNumber, LocalDate lastServiceDate, Address address) {
        super(id, name, cpf, phoneNumber, address);
    }

    public LocalDate getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(LocalDate lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }



}

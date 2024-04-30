package tech.rpe.desafioestagio.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.annotation.Testable;
import tech.rpe.desafioestagio.model.enums.Role;
import tech.rpe.desafioestagio.model.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testable
@DisplayName("Customer tests")
public class CustomerTest {


    @Test
    public void createCustomer_WithValidName_ShouldReturnCustomer() {
        Customer customer = new Customer(UUID.randomUUID().toString(), "Diogo", "103.800.364-44", "12345678901", LocalDate.now(), new Address());
        assertEquals("Diogo", customer.getName());
    }

    @Test
    public void createCustomer_WithInvalidName_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(UUID.randomUUID().toString(), "Di", "12345678901", "12345678901", LocalDate.now(), new Address());
        }, "Nome deve ter mais de 3 caracteres");
    }

    @Test
    public void createCustomer_WithValidCpf_ShouldReturnCustomer() {
        Customer customer = new Customer(UUID.randomUUID().toString(), "Diogo Marcelo", "103.800.364-44", "12345678901", LocalDate.now(), new Address());
        assertEquals("103.800.364-44", customer.getCpf());

    }

    @Test
    public void createCustomer_WithInvalidCpf_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Customer(UUID.randomUUID().toString(), "Diogo Marcelo", "12345678901", "12345678901", LocalDate.now(), new Address());
        }, "O campo cpf deve estar no formato 000.000.000-00.");
    }

    @Test
    public void createCustomer_WithInvalidPhoneNumber_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee(UUID.randomUUID().toString(), "Diogo Marcelo", "103.800.364-44", "123", new Address(), Role.DESENVOLVEDOR, Status.ATIVO, LocalDate.now());
        }, "O número de telefone deve ter pelo menos 10 dígitos.");
    }

}

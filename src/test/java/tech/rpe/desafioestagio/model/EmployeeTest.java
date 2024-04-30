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
@DisplayName("Employee tests")
public class EmployeeTest {

    @Test
    public void createEmployee_WithValidName_ShouldReturnEmployee() {
        Employee employee = new Employee(UUID.randomUUID().toString(), "Diogo", "103.800.364-44", "12345678901", new Address(), Role.QA, Status.INATIVO, LocalDate.now());
        assertEquals("Diogo", employee.getName());
    }

    @Test
    public void createEmployee_WithInvalidName_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee(UUID.randomUUID().toString(), "Di", "12345678901", "12345678901", new Address(), Role.DESENVOLVEDOR, Status.ATIVO, LocalDate.now());
        }, "Nome deve ter mais de 3 caracteres");
    }

    @Test
    public void createEmployee_WithValidCpf_ShouldReturnEmployee() {
        Employee employee = new Employee(UUID.randomUUID().toString(), "Diogo Marcelo", "103.800.364-44", "12345678901", new Address(), Role.GERENTE, Status.ATIVO, LocalDate.now());
        assertEquals("103.800.364-44", employee.getCpf());
    }

    @Test
    public void createEmployee_WithInvalidCpf_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee(UUID.randomUUID().toString(), "Diogo Marcelo", "12345678901", "12345678901", new Address(), Role.DESENVOLVEDOR, Status.ATIVO, LocalDate.now());
        }, "O campo cpf deve estar no formato 000.000.000-00.");
    }

    @Test
    public void createEmployee_WithValidPhoneNumber_ShouldReturnEmployee() {
        Employee employee = new Employee(UUID.randomUUID().toString(), "Diogo Marcelo", "103.800.364-44", "12345678901", new Address(), Role.RH, Status.INATIVO, LocalDate.now());
        assertEquals("12345678901", employee.getPhoneNumber());
    }

    @Test
    public void createEmployee_WithInvalidPhoneNumber_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee(UUID.randomUUID().toString(), "Diogo Marcelo", "103.800.364-44", "123", new Address(), Role.DESENVOLVEDOR, Status.ATIVO, LocalDate.now());
        }, "O número de telefone deve ter pelo menos 10 dígitos.");
    }
}
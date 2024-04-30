package tech.rpe.desafioestagio.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import tech.rpe.desafioestagio.model.enums.Role;
import tech.rpe.desafioestagio.model.enums.Status;

import java.time.LocalDate;

public record EmployeeDto(
        String id,
        @NotBlank String name,
        @NotBlank @CPF String cpf,
        @NotBlank String phoneNumber,
        @NotNull AddressDto address,
        @NotNull Role role,
        @NotNull Status status,
        @NotNull LocalDate dateOfHire
) {
}

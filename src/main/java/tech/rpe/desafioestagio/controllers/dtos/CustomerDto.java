package tech.rpe.desafioestagio.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDto(
        String id,
        @NotBlank String name,
        @NotBlank String cpf,
        @NotBlank String phoneNumber,
        LocalDate lastServiceDate,
        @NotNull AddressDto address
) {
}

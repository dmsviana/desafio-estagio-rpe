package tech.rpe.desafioestagio.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(
        String id,
        @NotBlank String name,
        @NotBlank String cpf,
        @NotBlank String phoneNumber,
        @NotNull AddressDto address
) {
}

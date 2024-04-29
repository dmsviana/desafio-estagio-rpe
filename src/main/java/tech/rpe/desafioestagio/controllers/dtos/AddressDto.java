package tech.rpe.desafioestagio.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDto(
        String id,
        @NotBlank String street,
        @NotNull Integer number,
        @NotBlank String district,
        @NotBlank String city,
        @NotBlank String state,
        @NotBlank String zipCode,
        @NotBlank String complement
) {
}

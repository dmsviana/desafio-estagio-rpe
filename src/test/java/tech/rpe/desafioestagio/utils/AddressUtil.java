package tech.rpe.desafioestagio.utils;

import tech.rpe.desafioestagio.controllers.dtos.AddressDto;
import tech.rpe.desafioestagio.model.Address;

import java.util.UUID;

public class AddressUtil {

    public static final String ID = UUID.randomUUID().toString();

    public static Address createAddressDefault() {
        return new Address(
                ID,
                "Rua da paz",
                0,
                "Centro",
                "Monteiro",
                "PB",
                "01001-000",
                "Apto 101"
        );
    }

    public static AddressDto createAddressExpectedResponseDefault() {
        return new AddressDto(
                ID,
                "Rua da paz",
                0,
                "Centro",
                "Monteiro",
                "PB",
                "01001-000",
                "Apto 101"
        );
    }
}

package tech.rpe.desafioestagio.utils;

import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.model.Customer;

import java.time.LocalDate;
import java.util.UUID;

public class CustomerUtil {

    private static final String ID = UUID.randomUUID().toString();

    public static Customer createCustomerDefault() {
        Customer customer = new Customer(
                ID,
                "Diogo",
                "103.800.364-44",
                "83996586204",
                LocalDate.now(),
                AddressUtil.createAddressDefault()

        );
        return customer;
    }

    public static CustomerDto createExpectedResponseDefault() {
        return new CustomerDto(
                ID,
                "Diogo",
                "103.800.364-44",
                "83996586204",
                LocalDate.now(),
                AddressUtil.createAddressExpectedResponseDefault()
        );
    }
}

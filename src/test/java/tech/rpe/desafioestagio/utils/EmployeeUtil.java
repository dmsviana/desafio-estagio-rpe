package tech.rpe.desafioestagio.utils;

import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;
import tech.rpe.desafioestagio.model.Employee;
import tech.rpe.desafioestagio.model.enums.Role;
import tech.rpe.desafioestagio.model.enums.Status;

import java.time.LocalDate;
import java.util.UUID;

public class EmployeeUtil {


    private static final String ID = UUID.randomUUID().toString();

    public static Employee createEmployeeDefault() {
        Employee employee = new Employee(
                ID,
                "Diogo",
                "103.800.364-44",
                "83996586204",
                AddressUtil.createAddressDefault(),
                Role.DESENVOLVEDOR,
                Status.ATIVO,
                LocalDate.now()
        );
        return employee;
    }

    public static EmployeeDto createExpectedResponseDefault() {
        return new EmployeeDto(
                ID,
                "Diogo",
                "103.800.364-44",
                "83996586204",
                AddressUtil.createAddressExpectedResponseDefault(),
                Role.DESENVOLVEDOR,
                Status.ATIVO,
                LocalDate.now()
        );
    }
}

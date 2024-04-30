package tech.rpe.desafioestagio.services;

import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;

import java.util.List;

public interface EmployeeService {


    EmployeeDto create(final EmployeeDto employeeRequest);

    EmployeeDto update(final String id, final EmployeeDto employeeRequest);

    void delete(final String id);

    EmployeeDto findById(final String id);

    EmployeeDto findByCpf(final String cpf);

    List<EmployeeDto> findAll();


}

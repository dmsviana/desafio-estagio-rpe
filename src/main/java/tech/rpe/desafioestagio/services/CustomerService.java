package tech.rpe.desafioestagio.services;

import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto create(final CustomerDto customerRequest);

    CustomerDto update(final String id, final CustomerDto customerRequest);

    void delete(final String id);

    CustomerDto findById(final String id);

    CustomerDto findByCpf(final String cpf);

    List<CustomerDto> findAll();

}

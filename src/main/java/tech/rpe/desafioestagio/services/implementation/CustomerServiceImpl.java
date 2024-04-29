package tech.rpe.desafioestagio.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.exceptions.CustomerAlreadyExistsException;
import tech.rpe.desafioestagio.exceptions.CustomerNotFoundException;
import tech.rpe.desafioestagio.model.Customer;
import tech.rpe.desafioestagio.model.mapper.CustomerMapper;
import tech.rpe.desafioestagio.repositories.CustomerRepository;
import tech.rpe.desafioestagio.services.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public CustomerDto create(CustomerDto customerRequest) {
        if(customerRepository.findByCpf(customerRequest.cpf()) != null) {
            log.error("[CustomerServiceImpl]: Cliente com o CPF informado já existe:{}", customerRequest.cpf());
            throw new CustomerAlreadyExistsException("Cliente com o CPF informado já existe: " + customerRequest.cpf());
        }
        var customer = CustomerMapper.toEntity(customerRequest);
        var createdCustomer = customerRepository.save(customer);

        log.info("[CustomerServiceImpl]: Cliente com o seguinte ID foi criado: {}", createdCustomer.getId());
        return CustomerMapper.toDTO(createdCustomer);
    }

    @Override
    public CustomerDto update(String id, CustomerDto customerRequest) {
        var customer = findCustomerById(id);

        customer.setName(customerRequest.name());
        customer.setCpf(customerRequest.cpf());
        customer.setPhoneNumber(customerRequest.phoneNumber());
        customer.setLastServiceDate(customerRequest.lastServiceDate());
        customer.getAddress().setStreet(customerRequest.address().street());
        customer.getAddress().setNumber(customerRequest.address().number());
        customer.getAddress().setDistrict(customerRequest.address().district());
        customer.getAddress().setCity(customerRequest.address().city());
        customer.getAddress().setState(customerRequest.address().state());
        customer.getAddress().setZipCode(customerRequest.address().zipCode());
        customer.getAddress().setComplement(customerRequest.address().complement());

        var updatedCustomer = customerRepository.save(customer);
        log.info("[CustomerServiceImpl]: Cliente com o seguinte ID foi atualizado: {}", updatedCustomer.getId());
        return CustomerMapper.toDTO(updatedCustomer);
    }

    @Override
    public void delete(String id) {
        var customer = findCustomerById(id);

        customerRepository.delete(customer);
        log.info("[CustomerServiceImpl]: Cliente com o seguinte ID foi deletado: {}", id);
    }

    @Override
    public CustomerDto findById(String id) {
        var customer = findCustomerById(id);

        log.info("[CustomerServiceImpl]: Cliente com o seguinte ID foi encontrado: {}", id);
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public CustomerDto findByCpf(String cpf) {
        var customer = findCustomerByCpf(cpf);

        log.info("[CustomerServiceImpl]: Cliente com o seguinte CPF foi encontrado: {}", cpf);
        return CustomerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDto> findAll() {
        var customers = customerRepository.findAll();
        log.info("[CustomerServiceImpl]: Listando todos os clientes");
        return CustomerMapper.toDtoList(customers);
    }

    private Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[CustomerServiceImpl]: Cliente com o ID informado não foi encontrado: {}", id);
                    return new CustomerNotFoundException("Cliente com o ID informado não foi encontrado: " + id);
                });
    }

    private Customer findCustomerByCpf(String cpf) {
        return Optional.ofNullable(customerRepository.findByCpf(cpf))
                .orElseThrow(() -> {
                    log.error("[CustomerServiceImpl]: Cliente com o CPF informado não foi encontrado: {}", cpf);
                    return new CustomerNotFoundException("Cliente com o CPF informado não foi encontrado: " + cpf);
                });
    }
}
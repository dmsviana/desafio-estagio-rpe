package tech.rpe.desafioestagio.controllers;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.services.CustomerService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerDto create(@RequestBody @Valid final CustomerDto customerRequest) {
        var createdCustomer = customerService.create(customerRequest);
        return createdCustomer;
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(OK)
    public CustomerDto findById(@PathVariable final String customerId) {
        var customer = customerService.findById(customerId);
        return customer;
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<CustomerDto> findAll() {
        var customers = customerService.findAll();
        return customers;
    }


    @PutMapping("/{customerId}")
    @ResponseStatus(OK)
    public CustomerDto update(@PathVariable final String customerId, @RequestBody @Valid final CustomerDto customerRequest) {
        var updatedCustomer = customerService.update(customerId, customerRequest);
        return updatedCustomer;
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final String customerId) {
        customerService.delete(customerId);
    }

}

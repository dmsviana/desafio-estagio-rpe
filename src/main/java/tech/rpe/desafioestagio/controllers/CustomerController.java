package tech.rpe.desafioestagio.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "This method creates a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerDto create(@RequestBody @Valid final CustomerDto customerRequest) {
        var createdCustomer = customerService.create(customerRequest);
        return createdCustomer;
    }

    @Operation(description = "This method updates an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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


    @Operation(description = "This method updates a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{customerId}")
    @ResponseStatus(OK)
    public CustomerDto update(@PathVariable final String customerId, @RequestBody @Valid final CustomerDto customerRequest) {
        var updatedCustomer = customerService.update(customerId, customerRequest);
        return updatedCustomer;
    }

    @Operation(description = "This method deletes a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{customerId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final String customerId) {
        customerService.delete(customerId);
    }

}

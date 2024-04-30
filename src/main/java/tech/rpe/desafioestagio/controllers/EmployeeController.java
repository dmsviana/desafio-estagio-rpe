package tech.rpe.desafioestagio.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;
import tech.rpe.desafioestagio.services.EmployeeService;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(description = "This method creates a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeDto create(@RequestBody @Valid final EmployeeDto employeeRequest) {
        var createdEmployee = employeeService.create(employeeRequest);
        return createdEmployee;
    }

    @Operation(description = "This method creates a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{employeeId}")
    @ResponseStatus(OK)
    public EmployeeDto findById(@PathVariable final String employeeId) {
        var employee = employeeService.findById(employeeId);
        return employee;
    }

    @Operation(description = "This method returns all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    @ResponseStatus(OK)
    public List<EmployeeDto> findAll() {
        var employees = employeeService.findAll();
        return employees;
    }

    @Operation(description = "This method updates an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{employeeId}")
    @ResponseStatus(OK)
    public EmployeeDto update(@PathVariable final String employeeId, @RequestBody @Valid final EmployeeDto employeeRequest) {
        var updatedEmployee = employeeService.update(employeeId, employeeRequest);
        return updatedEmployee;
    }

    @Operation(description = "This method deletes an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{employeeId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final String employeeId) {
        employeeService.delete(employeeId);
    }

}
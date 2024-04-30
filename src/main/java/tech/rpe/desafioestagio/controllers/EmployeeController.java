package tech.rpe.desafioestagio.controllers;

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

    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeDto create(@RequestBody @Valid final EmployeeDto employeeRequest) {
        var createdEmployee = employeeService.create(employeeRequest);
        return createdEmployee;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(OK)
    public EmployeeDto findById(@PathVariable final String employeeId) {
        var employee = employeeService.findById(employeeId);
        return employee;
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<EmployeeDto> findAll() {
        var employees = employeeService.findAll();
        return employees;
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(OK)
    public EmployeeDto update(@PathVariable final String employeeId, @RequestBody @Valid final EmployeeDto employeeRequest) {
        var updatedEmployee = employeeService.update(employeeId, employeeRequest);
        return updatedEmployee;
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable final String employeeId) {
        employeeService.delete(employeeId);
    }

}
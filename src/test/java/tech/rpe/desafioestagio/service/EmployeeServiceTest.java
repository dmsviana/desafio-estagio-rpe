package tech.rpe.desafioestagio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;
import tech.rpe.desafioestagio.exceptions.EmployeeAlreadyExistsException;
import tech.rpe.desafioestagio.exceptions.EmployeeNotFoundException;
import tech.rpe.desafioestagio.model.Employee;
import tech.rpe.desafioestagio.model.mapper.EmployeeMapper;
import tech.rpe.desafioestagio.repositories.EmployeeRepository;
import tech.rpe.desafioestagio.services.implementation.EmployeeServiceImpl;
import tech.rpe.desafioestagio.utils.EmployeeUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void findEmployee_WithValidId_ShouldReturnEmployee() {

        Employee employee = EmployeeUtil.createEmployeeDefault();

        EmployeeDto employeeResponseDto = EmployeeUtil.createExpectedResponseDefault();

        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        EmployeeDto response = employeeService.findById(employee.getId());

        assertAll("response",
                () -> assertEquals(employeeResponseDto.id(), response.id()),
                () -> assertEquals(employeeResponseDto.name(), response.name()),
                () -> assertEquals(employeeResponseDto.cpf(), response.cpf()),
                () -> assertEquals(employeeResponseDto.phoneNumber(), response.phoneNumber()),
                () -> assertEquals(employeeResponseDto.address(), response.address())
        );

        verify(employeeRepository).findById(employee.getId());
    }

    @Test
    void findEmployee_WithInvalidId_ShouldReturnEmployeeNotFoundException() {

        when(employeeRepository.findById("123")).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findById("123"));
        verify(employeeRepository).findById("123");
    }

    @Test
    void createEmployee_WithValidData_ShouldReturnEmployee() {
        Employee employee = EmployeeUtil.createEmployeeDefault();

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto expectedResponse = EmployeeUtil.createExpectedResponseDefault();
        EmployeeDto response = employeeService.create(EmployeeMapper.toDTO(employee));

        assertAll("response",
                () -> assertEquals(expectedResponse.id(), response.id()),
                () -> assertEquals(expectedResponse.name(), response.name()),
                () -> assertEquals(expectedResponse.cpf(), response.cpf()),
                () -> assertEquals(expectedResponse.phoneNumber(), response.phoneNumber()),
                () -> assertEquals(expectedResponse.address(), response.address())
        );

        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void createEmployee_WithExistingCpf_ShouldReturnEmployeeAlreadyExistsException() {
        Employee employee = EmployeeUtil.createEmployeeDefault();

        when(employeeRepository.findByCpf(any())).thenReturn(employee);

        assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.create(EmployeeMapper.toDTO(employee)));
        verify(employeeRepository, times(0)).save(any(Employee.class));
    }

    @Test
    void updateEmployee_WithValidData_ShouldReturnEmployee() {
        Employee employee = EmployeeUtil.createEmployeeDefault();

        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeDto expectedResponse = EmployeeUtil.createExpectedResponseDefault();
        EmployeeDto response = employeeService.update(employee.getId(), EmployeeMapper.toDTO(employee));

        assertAll("response",
                () -> assertEquals(expectedResponse.id(), response.id()),
                () -> assertEquals(expectedResponse.name(), response.name()),
                () -> assertEquals(expectedResponse.cpf(), response.cpf()),
                () -> assertEquals(expectedResponse.phoneNumber(), response.phoneNumber()),
                () -> assertEquals(expectedResponse.address(), response.address())
        );

        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void updateEmployee_WithInvalidId_ShouldReturnEmployeeNotFoundException() {
        Employee employee = EmployeeUtil.createEmployeeDefault();

        when(employeeRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.update(employee.getId(), EmployeeMapper.toDTO(employee)));
        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository, times(0)).save(any(Employee.class));
    }

    @Test
    void deleteEmployee_WithValidId_ShouldNotThrowAnyException() {
        Employee employee = EmployeeUtil.createEmployeeDefault();

        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(any(Employee.class));

        assertDoesNotThrow(() -> employeeService.delete(employee.getId()));
        verify(employeeRepository).findById(employee.getId());
        verify(employeeRepository).delete(any(Employee.class));
    }

    @Test
    void deleteEmployee_WithInvalidId_ShouldThrowEmployeeNotFoundException() {
        when(employeeRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete("123"));
        verify(employeeRepository).findById("123");
        verify(employeeRepository, times(0)).delete(any(Employee.class));
    }

    @Test
    void findAllEmployees_ShouldReturnListOfEmployees() {
        List<Employee> employees = List.of(EmployeeUtil.createEmployeeDefault());

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDto> response = employeeService.findAll();

        assertEquals(1, response.size());
        verify(employeeRepository).findAll();
    }

    @Test
    void findByCpf_WithValidCpf_ShouldReturnEmployee() {
        Employee employee = EmployeeUtil.createEmployeeDefault();

        when(employeeRepository.findByCpf(any())).thenReturn(employee);

        EmployeeDto response = employeeService.findByCpf(employee.getCpf());

        assertEquals(employee.getCpf(), response.cpf());
        verify(employeeRepository).findByCpf(employee.getCpf());
    }

    @Test
    void findByCpf_WithInvalidCpf_ShouldThrowEmployeeNotFoundException() {
        when(employeeRepository.findByCpf(any())).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findByCpf("123"));
        verify(employeeRepository).findByCpf("123");
    }

}
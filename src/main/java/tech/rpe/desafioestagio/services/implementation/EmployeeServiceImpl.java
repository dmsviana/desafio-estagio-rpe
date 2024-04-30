package tech.rpe.desafioestagio.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;
import tech.rpe.desafioestagio.exceptions.EmployeeAlreadyExistsException;
import tech.rpe.desafioestagio.exceptions.EmployeeNotFoundException;
import tech.rpe.desafioestagio.model.Employee;
import tech.rpe.desafioestagio.model.mapper.EmployeeMapper;
import tech.rpe.desafioestagio.repositories.EmployeeRepository;
import tech.rpe.desafioestagio.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeRequest) {
        if(employeeRepository.findByCpf(employeeRequest.cpf()) != null) {
            log.error("[EmployeeServiceImpl]: Funcionário com o CPF informado já existe:{}", employeeRequest.cpf());
            throw new EmployeeAlreadyExistsException("Funcionário com o CPF informado já existe: " + employeeRequest.cpf());
        }
        var employee = EmployeeMapper.toEntity(employeeRequest);
        var createdEmployee = employeeRepository.save(employee);

        log.info("[EmployeeServiceImpl]: Funcionário com o seguinte ID foi criado: {}", createdEmployee.getId());
        return EmployeeMapper.toDTO(createdEmployee);
    }

    @Override
    public EmployeeDto update(String id, EmployeeDto employeeRequest) {
        var employee = findEmployeeById(id);

        employee.setName(employeeRequest.name());
        employee.setCpf(employeeRequest.cpf());
        employee.setPhoneNumber(employeeRequest.phoneNumber());
        employee.setDateOfHire(employeeRequest.dateOfHire());
        employee.setRole(employeeRequest.role());
        employee.setStatus(employeeRequest.status());
        employee.getAddress().setStreet(employeeRequest.address().street());
        employee.getAddress().setNumber(employeeRequest.address().number());
        employee.getAddress().setDistrict(employeeRequest.address().district());
        employee.getAddress().setCity(employeeRequest.address().city());
        employee.getAddress().setState(employeeRequest.address().state());
        employee.getAddress().setZipCode(employeeRequest.address().zipCode());
        employee.getAddress().setComplement(employeeRequest.address().complement());

        var updatedEmployee = employeeRepository.save(employee);
        log.info("[EmployeeServiceImpl]: Funcionário com o seguinte ID foi atualizado: {}", updatedEmployee.getId());
        return EmployeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void delete(String id) {
        var employee = findEmployeeById(id);

        employeeRepository.delete(employee);
        log.info("[EmployeeServiceImpl]: Funcionário com o seguinte ID foi deletado: {}", id);
    }

    @Override
    public EmployeeDto findById(String id) {
        var employee = findEmployeeById(id);

        log.info("[EmployeeServiceImpl]: Funcionário com o seguinte ID foi encontrado: {}", id);
        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDto findByCpf(String cpf) {
        var employee = findEmployeeByCpf(cpf);

        log.info("[EmployeeServiceImpl]: Funcionário com o seguinte CPF foi encontrado: {}", cpf);
        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDto> findAll() {
        var employees = employeeRepository.findAll();
        log.info("[EmployeeServiceImpl]: Listando todos os funcionários");
        return EmployeeMapper.toDtoList(employees);
    }

    private Employee findEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[EmployeeServiceImpl]: Funcionário com o ID informado não foi encontrado: {}", id);
                    return new EmployeeNotFoundException("Funcionário com o ID informado não foi encontrado: " + id);
                });
    }

    private Employee findEmployeeByCpf(String cpf) {
        return Optional.ofNullable(employeeRepository.findByCpf(cpf))
                .orElseThrow(() -> {
                    log.error("[EmployeeServiceImpl]: Funcionário com o CPF informado não foi encontrado: {}", cpf);
                    return new EmployeeNotFoundException("Funcionário com o CPF informado não foi encontrado: " + cpf);
                });
    }
}
package tech.rpe.desafioestagio.model.mapper;

import tech.rpe.desafioestagio.controllers.dtos.EmployeeDto;
import tech.rpe.desafioestagio.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDto toDTO(final Employee entity) {
        return new EmployeeDto(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getPhoneNumber(),
                AddressMapper.toDTO(entity.getAddress()),
                entity.getRole(),
                entity.getStatus(),
                entity.getDateOfHire()
        );
    }

    public static Employee toEntity(final EmployeeDto dto) {
        return new Employee(
                dto.id(),
                dto.name(),
                dto.cpf(),
                dto.phoneNumber(),
                AddressMapper.toEntity(dto.address()),
                dto.role(),
                dto.status(),
                dto.dateOfHire()
        );
    }

    public static List<EmployeeDto> toDtoList(final List<Employee> entities) {
        return entities.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}

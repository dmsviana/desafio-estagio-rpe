package tech.rpe.desafioestagio.model.mapper;

import tech.rpe.desafioestagio.controllers.dtos.CustomerDto;
import tech.rpe.desafioestagio.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDto toDTO(final Customer entity) {
        return new CustomerDto(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getPhoneNumber(),
                AddressMapper.toDTO(entity.getAddress())
        );
    }

    public static Customer toEntity(final CustomerDto dto) {
        return new Customer(
                dto.id(),
                dto.name(),
                dto.cpf(),
                dto.phoneNumber(),
                AddressMapper.toEntity(dto.address())
        );
    }

    public static List<CustomerDto> toDtoList(final List<Customer> entities) {
        return entities.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

}

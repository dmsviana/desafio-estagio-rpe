package tech.rpe.desafioestagio.model.mapper;

import tech.rpe.desafioestagio.controllers.dtos.AddressDto;
import tech.rpe.desafioestagio.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {

    public static AddressDto toDTO(final Address entity) {
        return new AddressDto(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getDistrict(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode(),
                entity.getComplement()
        );
    }

    public static Address toEntity(final AddressDto dto) {
        return new Address(
                dto.id(),
                dto.street(),
                dto.number(),
                dto.district(),
                dto.city(),
                dto.state(),
                dto.zipCode(),
                dto.complement()
        );
    }

    public static List<AddressDto> toDTOList(final List<Address> entities) {
        return entities.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

}

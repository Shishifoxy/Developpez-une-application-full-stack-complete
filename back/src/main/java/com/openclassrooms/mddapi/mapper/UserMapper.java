package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper pour convertir entre l'entité User et le DTO UserDto.
 * Utilise MapStruct pour le mappage automatique des champs entre les entités et les DTOs.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Convertit une entité User en un DTO UserDto.
     *
     * @param user L'entité User à convertir.
     * @return UserDto Le DTO User converti.
     */
    UserDto toDto(User user);

    /**
     * Convertit un DTO UserDto en une entité User.
     *
     * @param dto Le DTO UserDto à convertir.
     * @return User L'entité User convertie.
     */
    @Mapping(target = "subscriptions", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto dto);
}

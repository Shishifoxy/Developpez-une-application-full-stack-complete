package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.Entity.Theme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper pour convertir entre l'entité Theme et le DTO ThemeDto.
 * Utilise MapStruct pour le mappage automatique des champs entre les entités et les DTOs.
 */
@Mapper(componentModel = "spring")
public interface ThemeMapper {

    /**
     * Convertit une entité Theme en un DTO ThemeDto.
     *
     * @param theme L'entité Theme à convertir.
     * @return ThemeDto Le DTO Theme converti.
     */
    @Mapping(target = "subscribed", ignore = true)
    ThemeDto toDto(Theme theme);

    /**
     * Convertit un DTO ThemeDto en une entité Theme.
     *
     * @param dto Le DTO ThemeDto à convertir.
     * @return Theme L'entité Theme convertie.
     */
    Theme toEntity(ThemeDto dto);
}

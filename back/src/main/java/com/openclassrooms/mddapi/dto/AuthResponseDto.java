package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) représentant la réponse d'authentification.
 * Contient un token JWT et les informations de l'utilisateur.
 */
@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDto {

    /**
     * Le token JWT généré après l'authentification.
     */
    private String token;

    /**
     * Les informations de l'utilisateur, encapsulées dans un UserDto.
     */
    private UserDto user;
}

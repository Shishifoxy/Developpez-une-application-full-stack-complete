package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) représentant un utilisateur.
 * Utilisé pour transférer les informations d'un utilisateur entre le backend et le frontend.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    /**
     * Identifiant unique de l'utilisateur.
     */
    private Long id;

    /**
     * Nom d'utilisateur de l'utilisateur.
     */
    private String username;

    /**
     * Email de l'utilisateur.
     */
    private String email;
}

package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) représentant une requête d'inscription.
 * Contient les informations nécessaires pour enregistrer un nouvel utilisateur : nom d'utilisateur, email et mot de passe.
 */
@Getter
@Setter
public class RegisterRequestDto {

    /**
     * Nom d'utilisateur de l'utilisateur.
     */
    private String username;

    /**
     * Email de l'utilisateur.
     */
    private String email;

    /**
     * Mot de passe de l'utilisateur.
     */
    private String password;
}

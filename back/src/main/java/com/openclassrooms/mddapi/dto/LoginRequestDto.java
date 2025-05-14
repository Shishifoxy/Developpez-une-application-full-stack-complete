package com.openclassrooms.mddapi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) représentant une requête de connexion.
 * Contient l'identifiant (email ou nom d'utilisateur) et le mot de passe de l'utilisateur.
 */
@Getter
@Setter
public class LoginRequestDto {

    /**
     * Identifiant de l'utilisateur (email ou nom d'utilisateur).
     */
    private String identifier;

    /**
     * Mot de passe de l'utilisateur.
     */
    private String password;
}

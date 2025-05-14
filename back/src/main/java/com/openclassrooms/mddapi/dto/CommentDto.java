package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) représentant un commentaire.
 * Utilisé pour transférer les données d'un commentaire entre le backend et le frontend.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    /**
     * Identifiant unique du commentaire.
     */
    private Long id;

    /**
     * Contenu du commentaire.
     */
    private String content;

    /**
     * Nom d'utilisateur de l'auteur du commentaire.
     */
    private String username;

    /**
     * Date et heure de la création du commentaire.
     */
    private String createdAt;
}

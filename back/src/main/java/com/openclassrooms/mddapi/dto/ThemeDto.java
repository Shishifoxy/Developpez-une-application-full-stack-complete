package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) représentant un thème.
 * Utilisé pour transférer les données d'un thème entre le backend et le frontend.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemeDto {

    /**
     * Identifiant unique du thème.
     */
    private Long id;

    /**
     * Titre du thème.
     */
    private String title;

    /**
     * Description du thème.
     */
    private String description;

    /**
     * Indique si l'utilisateur est abonné à ce thème.
     */
    private boolean subscribed;
}

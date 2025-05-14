package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) représentant un article.
 * Utilisé pour transférer les données d'un article entre le backend et le frontend.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    /**
     * Identifiant unique de l'article.
     */
    private Long id;

    /**
     * Titre de l'article.
     */
    private String title;

    /**
     * Contenu de l'article.
     */
    private String content;

    /**
     * Nom de l'auteur de l'article.
     */
    private String author;

    /**
     * Date de publication de l'article.
     */
    private String date;

    /**
     * Identifiant du thème auquel appartient l'article.
     */
    private Long theme;
}

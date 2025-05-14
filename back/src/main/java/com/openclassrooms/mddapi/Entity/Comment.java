package com.openclassrooms.mddapi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entité représentant un commentaire.
 * Chaque commentaire est associé à un article et un auteur, et possède un contenu et une date de création.
 */
@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {

    /**
     * Identifiant unique du commentaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Contenu du commentaire.
     */
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    /**
     * Date et heure de création du commentaire.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * L'article auquel ce commentaire est associé.
     * Lien vers l'entité Article.
     */
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    /**
     * L'auteur du commentaire.
     * Lien vers l'entité User.
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;
}

package com.openclassrooms.mddapi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant un article.
 * L'article est composé d'un titre, d'un contenu, d'un auteur, d'un thème et de commentaires associés.
 */
@Entity
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article {

    /**
     * Identifiant unique de l'article.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre de l'article.
     */
    private String title;

    /**
     * Contenu de l'article.
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Date et heure de création de l'article.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * L'auteur de l'article.
     * Lien vers l'entité User.
     */
    @ManyToOne(cascade = CascadeType.ALL)  // JPA standard, cascade les opérations de persistance sur author
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    /**
     * Le thème auquel l'article appartient.
     * Lien vers l'entité Theme.
     */
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    /**
     * Liste des commentaires associés à cet article.
     * Lien vers l'entité Comment.
     */
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)  // CascadeType.ALL pour supprimer les commentaires liés à l'article
    private List<Comment> comments = new ArrayList<>();
}

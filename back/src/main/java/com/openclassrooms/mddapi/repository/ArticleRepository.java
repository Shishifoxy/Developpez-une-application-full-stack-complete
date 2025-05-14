package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository pour l'entité Article.
 * Fournit des méthodes pour accéder aux articles dans la base de données.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * Récupère la liste des articles associés à un thème spécifique.
     *
     * @param themeId L'identifiant du thème.
     * @return List<Article> La liste des articles associés au thème.
     */
    List<Article> findByThemeId(Long themeId);

    /**
     * Récupère la liste des articles d'un auteur spécifique.
     *
     * @param authorId L'identifiant de l'auteur.
     * @return List<Article> La liste des articles de l'auteur.
     */
    List<Article> findByAuthorId(Long authorId);
}

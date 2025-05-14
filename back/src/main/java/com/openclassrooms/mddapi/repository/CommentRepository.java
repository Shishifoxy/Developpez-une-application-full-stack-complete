package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository pour l'entité Comment.
 * Fournit des méthodes pour accéder aux commentaires associés à des articles dans la base de données.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Récupère la liste des commentaires associés à un article spécifique.
     *
     * @param articleId L'identifiant de l'article.
     * @return List<Comment> La liste des commentaires associés à l'article.
     */
    List<Comment> findByArticleId(Long articleId);
}

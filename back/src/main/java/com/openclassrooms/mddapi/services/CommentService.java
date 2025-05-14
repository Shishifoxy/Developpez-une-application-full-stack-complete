package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.Entity.Article;
import com.openclassrooms.mddapi.Entity.Comment;
import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service pour gérer les commentaires.
 * Permet de récupérer, ajouter et supprimer des commentaires associés aux articles.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Récupère la liste des commentaires associés à un article spécifique.
     *
     * @param articleId L'identifiant de l'article.
     * @return List<Comment> La liste des commentaires associés à l'article.
     */
    public List<Comment> getCommentsByArticle(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    /**
     * Supprime un commentaire spécifique.
     *
     * @param commentId L'identifiant du commentaire à supprimer.
     * @throws RuntimeException Si le commentaire n'est pas trouvé.
     */
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Commentaire introuvable"));

        commentRepository.delete(comment);
    }

    /**
     * Ajoute un nouveau commentaire à un article.
     *
     * @param articleId L'identifiant de l'article auquel ajouter le commentaire.
     * @param userId L'identifiant de l'utilisateur auteur du commentaire.
     * @param content Le contenu du commentaire.
     * @return Comment Le commentaire créé et sauvegardé.
     * @throws RuntimeException Si l'article ou l'utilisateur n'est pas trouvé.
     */
    public Comment addComment(Long articleId, Long userId, String content) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article introuvable"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setAuthor(user);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }
}

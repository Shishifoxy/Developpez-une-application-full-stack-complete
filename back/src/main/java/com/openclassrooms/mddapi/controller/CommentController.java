package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.Entity.Comment;
import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.UserService;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur pour la gestion des commentaires.
 * Permet d'ajouter, supprimer et récupérer des commentaires associés aux articles.
 */
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    /**
     * Ajoute un commentaire à un article.
     *
     * @param articleId L'identifiant de l'article auquel ajouter un commentaire.
     * @param commentDto Les données du commentaire à ajouter.
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @return ResponseEntity<CommentDto> Le commentaire ajouté.
     */
    @PostMapping("/{articleId}")
    @Operation(summary = "Ajouter un commentaire", description = "Permet d'ajouter un commentaire à un article.")
    public ResponseEntity<CommentDto> addComment(
            @PathVariable Long articleId,
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        User author = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Comment comment = commentService.addComment(
                articleId,
                author.getId(),
                commentDto.getContent()
        );

        return ResponseEntity.ok(commentMapper.toDto(comment));
    }

    /**
     * Supprime un commentaire.
     *
     * @param commentId L'identifiant du commentaire à supprimer.
     * @return ResponseEntity<Void> La réponse de la suppression (no content).
     */
    @DeleteMapping("/{commentId}")
    @Operation(summary = "Supprimer un commentaire", description = "Permet de supprimer un commentaire existant.")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère tous les commentaires d'un article.
     *
     * @param articleId L'identifiant de l'article dont on souhaite récupérer les commentaires.
     * @return ResponseEntity<List<CommentDto>> La liste des commentaires de l'article.
     */
    @GetMapping("/{articleId}")
    @Operation(summary = "Obtenir les commentaires d'un article", description = "Récupère tous les commentaires associés à un article.")
    public ResponseEntity<List<CommentDto>> getArticleComments(
            @PathVariable Long articleId) {
        List<Comment> comments = commentService.getCommentsByArticle(articleId);
        List<CommentDto> commentDtos = comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(commentDtos);
    }
}

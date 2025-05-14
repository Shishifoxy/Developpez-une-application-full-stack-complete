package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.Entity.Article;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur pour gérer les articles.
 * Permet de récupérer, créer des articles et obtenir des informations détaillées sur un article spécifique.
 */
@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    /**
     * Récupère la liste de tous les articles.
     *
     * @return ResponseEntity<List<ArticleDto>> La liste des articles.
     */
    @GetMapping
    @Operation(summary = "Obtenir tous les articles", description = "Récupère la liste de tous les articles.")
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        List<ArticleDto> articleDtos = articles.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articleDtos);
    }

    /**
     * Récupère un article par son identifiant.
     *
     * @param id L'identifiant de l'article à récupérer.
     * @return ResponseEntity<ArticleDto> L'article demandé.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un article par ID", description = "Récupère un article spécifique en fonction de son identifiant.")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return ResponseEntity.ok(articleMapper.toDto(article));
    }

    /**
     * Crée un nouvel article.
     *
     * @param articleDto Les données de l'article à créer.
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @return ResponseEntity<ArticleDto> L'article créé.
     */
    @PostMapping
    @Operation(summary = "Créer un article", description = "Permet de créer un nouvel article.")
    public ResponseEntity<ArticleDto> createArticle(@RequestBody ArticleDto articleDto,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        Article savedArticle = articleService.createArticle(
                articleDto.getTitle(),
                articleDto.getContent(),
                articleDto.getTheme(),
                userDetails
        );
        return ResponseEntity.ok(articleMapper.toDto(savedArticle));
    }
}

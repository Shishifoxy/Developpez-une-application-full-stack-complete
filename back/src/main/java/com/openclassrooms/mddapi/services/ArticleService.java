package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.Entity.Article;
import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.Entity.Theme;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service pour gérer les articles.
 * Permet de récupérer, créer des articles et d'effectuer d'autres opérations liées aux articles.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    /**
     * Récupère tous les articles.
     *
     * @return List<Article> La liste de tous les articles.
     */
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /**
     * Récupère un article par son identifiant.
     *
     * @param id L'identifiant de l'article.
     * @return Article L'article trouvé.
     * @throws RuntimeException Si l'article n'est pas trouvé.
     */
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article introuvable"));
    }

    /**
     * Crée un nouvel article.
     *
     * @param title Le titre de l'article.
     * @param content Le contenu de l'article.
     * @param themeId L'identifiant du thème auquel l'article appartient.
     * @param userDetails Les détails de l'utilisateur qui crée l'article.
     * @return Article L'article créé.
     * @throws RuntimeException Si l'auteur ou le thème est introuvable.
     */
    public Article createArticle(String title, String content, Long themeId, UserDetails userDetails) {
        User author = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Auteur introuvable"));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Thème introuvable"));

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setTheme(theme);
        article.setCreatedAt(LocalDateTime.now());

        return articleRepository.save(article);
    }
}

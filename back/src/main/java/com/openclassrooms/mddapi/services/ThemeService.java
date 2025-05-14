package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.Entity.Theme;
import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/**
 * Service pour gérer les thèmes et les abonnements des utilisateurs.
 * Permet de récupérer tous les thèmes, les abonnements d'un utilisateur, ainsi que d'abonner ou désabonner un utilisateur à un thème.
 */
@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Récupère tous les thèmes disponibles dans la base de données.
     *
     * @return List<Theme> La liste de tous les thèmes.
     */
    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }

    /**
     * Récupère les abonnements d'un utilisateur spécifique.
     *
     * @param username Le nom d'utilisateur de l'utilisateur.
     * @return List<Theme> La liste des thèmes auxquels l'utilisateur est abonné.
     * @throws RuntimeException Si l'utilisateur n'est pas trouvé.
     */
    public List<Theme> getUserSubscriptions(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return new ArrayList<>(user.getSubscriptions());
    }

    /**
     * Abonne un utilisateur à un thème.
     *
     * @param username Le nom d'utilisateur de l'utilisateur à abonner.
     * @param themeId L'identifiant du thème auquel l'utilisateur souhaite s'abonner.
     * @throws RuntimeException Si l'utilisateur ou le thème n'est pas trouvé.
     */
    public void subscribe(String username, Long themeId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Thème introuvable"));
        user.getSubscriptions().add(theme);
        userRepository.save(user);
    }

    /**
     * Désabonne un utilisateur d'un thème.
     *
     * @param username Le nom d'utilisateur de l'utilisateur à désabonner.
     * @param themeId L'identifiant du thème auquel l'utilisateur souhaite se désabonner.
     * @throws RuntimeException Si l'utilisateur ou le thème n'est pas trouvé.
     */
    public void unsubscribe(String username, Long themeId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Thème introuvable"));
        user.getSubscriptions().remove(theme);
        userRepository.save(user);
    }

    /**
     * Récupère un thème par son identifiant.
     *
     * @param id L'identifiant du thème.
     * @return Theme Le thème trouvé.
     * @throws RuntimeException Si le thème n'est pas trouvé.
     */
    public Theme findThemeById(Long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thème introuvable"));
    }
}

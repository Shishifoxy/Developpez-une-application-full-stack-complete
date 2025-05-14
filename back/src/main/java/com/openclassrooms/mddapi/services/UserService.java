package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour gérer les utilisateurs.
 * Permet de récupérer, créer et mettre à jour des utilisateurs.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à rechercher.
     * @return Optional<User> L'utilisateur trouvé, ou un Optional vide si l'utilisateur n'existe pas.
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Récupère un utilisateur par son email.
     *
     * @param email L'email de l'utilisateur à rechercher.
     * @return Optional<User> L'utilisateur trouvé, ou un Optional vide si l'utilisateur n'existe pas.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Récupère un utilisateur par son nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur de l'utilisateur à rechercher.
     * @return Optional<User> L'utilisateur trouvé, ou un Optional vide si l'utilisateur n'existe pas.
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Sauvegarde un utilisateur dans la base de données.
     *
     * @param user L'utilisateur à sauvegarder.
     * @return User L'utilisateur sauvegardé.
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Met à jour un utilisateur dans la base de données.
     *
     * @param user L'utilisateur à mettre à jour.
     * @return User L'utilisateur mis à jour.
     */
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * Récupère un utilisateur par son email ou son nom d'utilisateur.
     *
     * @param identifier L'email ou le nom d'utilisateur de l'utilisateur à rechercher.
     * @return User L'utilisateur trouvé.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé.
     */
    public User findByEmailOrUsername(String identifier) {
        return userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByUsername(identifier))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
    }
}

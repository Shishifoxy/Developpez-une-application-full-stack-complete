package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository pour l'entité User.
 * Fournit des méthodes pour accéder aux utilisateurs dans la base de données.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Récupère un utilisateur en fonction de son nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur de l'utilisateur à récupérer.
     * @return Optional<User> L'utilisateur trouvé, ou un Optional vide si l'utilisateur n'existe pas.
     */
    Optional<User> findByUsername(String username);

    /**
     * Récupère un utilisateur en fonction de son email.
     *
     * @param email L'email de l'utilisateur à récupérer.
     * @return Optional<User> L'utilisateur trouvé, ou un Optional vide si l'utilisateur n'existe pas.
     */
    Optional<User> findByEmail(String email);
}

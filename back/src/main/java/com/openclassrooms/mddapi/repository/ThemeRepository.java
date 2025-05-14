package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pour l'entité Theme.
 * Fournit des méthodes pour accéder aux thèmes dans la base de données.
 * Hérite des fonctionnalités de JpaRepository pour effectuer des opérations CRUD sur les thèmes.
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}

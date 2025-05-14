package com.openclassrooms.mddapi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité représentant un thème.
 * Un thème a un titre, une description, et un ensemble d'utilisateurs abonnés.
 */
@Entity
@Table(name = "theme")
@Data
@NoArgsConstructor
public class Theme {

    /**
     * Identifiant unique du thème.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Titre du thème.
     */
    private String title;

    /**
     * Description du thème.
     */
    private String description;

    /**
     * Ensemble des utilisateurs abonnés à ce thème.
     * Lien vers l'entité User via la relation Many-to-Many.
     */
    @ManyToMany(mappedBy = "subscriptions")
    private Set<User> subscribers = new HashSet<>();
}

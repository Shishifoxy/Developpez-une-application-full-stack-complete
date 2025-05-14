package com.openclassrooms.mddapi.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité représentant un utilisateur.
 * Un utilisateur possède un nom d'utilisateur, un email, un mot de passe et un ensemble de thèmes auxquels il est abonné.
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    /**
     * Identifiant unique de l'utilisateur.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom d'utilisateur de l'utilisateur.
     * Doit être unique.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Email de l'utilisateur.
     * Doit être unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Mot de passe de l'utilisateur.
     * Ne doit pas être nul.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Ensemble des thèmes auxquels l'utilisateur est abonné.
     * Relation Many-to-Many avec l'entité Theme via une table de jointure.
     */
    @ManyToMany
    @JoinTable(
            name = "user_theme",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    @Cascade(CascadeType.ALL)
    private Set<Theme> subscriptions = new HashSet<>();
}

package com.openclassrooms.mddapi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entité représentant une inscription à un thème.
 * Chaque inscription est associée à un utilisateur et un thème, avec une date d'abonnement.
 */
@Entity
@Table(name = "subscription")
@Data
@NoArgsConstructor
public class Subscription {

    /**
     * Identifiant unique de l'inscription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * L'utilisateur associé à cette inscription.
     * Lien vers l'entité User.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Le thème auquel l'utilisateur est abonné.
     * Lien vers l'entité Theme.
     */
    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    /**
     * Date et heure de l'abonnement à un thème.
     */
    private LocalDateTime subscribedAt = LocalDateTime.now();
}

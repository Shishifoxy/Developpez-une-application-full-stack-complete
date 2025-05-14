package com.openclassrooms.mddapi.exception;

/**
 * Exception personnalisée pour les erreurs de type "non autorisé" (401).
 * Cette exception est lancée lorsqu'un utilisateur essaie d'accéder à une ressource sans les droits nécessaires.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Constructeur de l'exception UnauthorizedException.
     *
     * @param message Le message d'erreur à associer à l'exception.
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}

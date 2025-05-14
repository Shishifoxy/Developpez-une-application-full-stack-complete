package com.openclassrooms.mddapi.exception;

/**
 * Exception personnalisée pour les erreurs de type "mauvaise requête" (400).
 * Cette exception est lancée lorsqu'une requête est mal formulée ou contient des données invalides.
 */
public class BadRequestException extends RuntimeException {

    /**
     * Constructeur de l'exception BadRequestException.
     *
     * @param message Le message d'erreur à associer à l'exception.
     */
    public BadRequestException(String message) {
        super(message);
    }
}

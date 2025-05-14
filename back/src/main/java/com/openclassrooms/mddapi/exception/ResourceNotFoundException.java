package com.openclassrooms.mddapi.exception;

/**
 * Exception personnalisée pour les erreurs de type "ressource non trouvée" (404).
 * Cette exception est lancée lorsqu'une ressource demandée n'est pas trouvée dans le système.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructeur de l'exception ResourceNotFoundException.
     *
     * @param message Le message d'erreur à associer à l'exception.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

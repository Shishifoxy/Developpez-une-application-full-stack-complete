package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Gestionnaire global des exceptions.
 * Permet de capturer et de gérer toutes les exceptions spécifiques ou générales lancées dans l'application,
 * et de renvoyer une réponse d'erreur formatée.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère toutes les exceptions non capturées de manière générique.
     *
     * @param ex L'exception lancée.
     * @param request La requête Web associée.
     * @return ResponseEntity Contenant un message d'erreur et un statut HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Gère l'exception lorsque la ressource demandée n'a pas été trouvée (404).
     *
     * @param ex L'exception ResourceNotFoundException.
     * @param request La requête Web associée.
     * @return ResponseEntity Contenant un message d'erreur et un statut HTTP 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    /**
     * Gère l'exception lorsque la requête est mal formulée (400).
     *
     * @param ex L'exception BadRequestException.
     * @param request La requête Web associée.
     * @return ResponseEntity Contenant un message d'erreur et un statut HTTP 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère l'exception lorsque l'utilisateur n'est pas autorisé à accéder à une ressource (401).
     *
     * @param ex L'exception UnauthorizedException.
     * @param request La requête Web associée.
     * @return ResponseEntity Contenant un message d'erreur et un statut HTTP 401.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage, HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Gère les erreurs de validation des arguments dans la requête (400).
     *
     * @param ex L'exception MethodArgumentNotValidException.
     * @param request La requête Web associée.
     * @return ResponseEntity Contenant un message d'erreur et un statut HTTP 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = "Validation failed: " + ex.getBindingResult().getFieldErrors().toString();
        return new ResponseEntity<>(new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Classe représentant la réponse d'erreur formatée envoyée au client.
     */
    public static class ErrorResponse {
        private String message;
        private int status;

        public ErrorResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        // Getters et setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}

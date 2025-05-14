package com.openclassrooms.mddapi.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utilitaire pour la gestion des tokens JWT (JSON Web Tokens).
 * Permet de générer des tokens JWT, extraire le nom d'utilisateur à partir du token,
 * et valider l'intégrité d'un token.
 */
@Component
public class JwtUtils {

    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Clé secrète de taille correcte pour HS256

    private final long jwtExpirationMs = 86400000; // 24h

    /**
     * Génère un token JWT pour un utilisateur spécifique.
     *
     * @param username Le nom d'utilisateur pour lequel générer le token.
     * @return Le token JWT généré.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtSecret)  // Utilisation de la clé générée
                .compact();
    }

    /**
     * Extrait le nom d'utilisateur à partir du token JWT.
     *
     * @param token Le token JWT à analyser.
     * @return Le nom d'utilisateur extrait du token.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Valide l'intégrité du token JWT en vérifiant la signature et l'expiration.
     *
     * @param authToken Le token JWT à valider.
     * @return true si le token est valide, sinon false.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log l'erreur pour plus de détails
            System.out.println("Token is invalid: " + e.getMessage());
            return false;
        }
    }
}

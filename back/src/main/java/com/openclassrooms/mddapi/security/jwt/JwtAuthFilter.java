package com.openclassrooms.mddapi.security.jwt;

import com.openclassrooms.mddapi.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.io.IOException;

/**
 * Filtre d'authentification JWT.
 * Ce filtre intercepte les requêtes HTTP, extrait le token JWT de l'en-tête "Authorization",
 * valide le token et configure l'authentification dans le contexte de sécurité.
 */
@Component
public class JwtAuthFilter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Méthode qui filtre les requêtes entrantes et extrait le token JWT de l'en-tête "Authorization".
     * Si le token est valide, l'utilisateur est authentifié et son contexte de sécurité est mis à jour.
     *
     * @param request La requête HTTP entrante.
     * @param response La réponse HTTP.
     * @param chain La chaîne de filtres pour la requête.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     * @throws ServletException Si une exception de servlet se produit.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();


        if (path.startsWith("/api/auth/register") || path.startsWith("/api/auth/login")) {
            chain.doFilter(request, response);
            return;
        }

        String token = parseJwt(httpRequest);

        if (token != null && jwtUtils.validateJwtToken(token)) {
            String username = jwtUtils.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    /**
     * Extrait le token JWT de l'en-tête "Authorization" de la requête HTTP.
     * Le token doit commencer par "Bearer " suivi du token réel.
     *
     * @param request La requête HTTP contenant l'en-tête "Authorization".
     * @return Le token JWT ou null si aucun token n'est présent.
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}

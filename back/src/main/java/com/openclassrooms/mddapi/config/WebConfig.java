package com.openclassrooms.mddapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration Web pour l'application.
 * Permet de configurer les préfixes de chemin pour les contrôleurs REST.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure les chemins des contrôleurs REST en ajoutant un préfixe "/api" à toutes les routes.
     *
     * @param configurer Configurateur de correspondance de chemin pour l'application.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api",
                c -> c.isAnnotationPresent(RestController.class));
    }
}

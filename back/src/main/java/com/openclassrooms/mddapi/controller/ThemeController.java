package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.Entity.Theme;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.services.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur pour gérer les thèmes.
 * Permet de récupérer les thèmes, s'abonner, se désabonner et obtenir les abonnements d'un utilisateur.
 */
@RestController
@RequestMapping("/themes")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;
    private final ThemeMapper themeMapper;

    /**
     * Récupère la liste de tous les thèmes.
     *
     * @return ResponseEntity<List<ThemeDto>> La liste des thèmes.
     */
    @GetMapping
    @Operation(summary = "Obtenir tous les thèmes", description = "Récupère la liste de tous les thèmes disponibles.")
    public ResponseEntity<List<ThemeDto>> getAllThemes() {
        List<Theme> themes = themeService.findAllThemes();
        List<ThemeDto> themeDtos = themes.stream()
                .map(themeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(themeDtos);
    }

    /**
     * S'abonne à un thème.
     *
     * @param themeId L'identifiant du thème auquel s'abonner.
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @return ResponseEntity<Void> La réponse indiquant l'abonnement effectué.
     */
    @PostMapping("/subscribe/{themeId}")
    @Operation(summary = "S'abonner à un thème", description = "Permet à un utilisateur de s'abonner à un thème.")
    public ResponseEntity<Void> subscribeToTheme(@PathVariable Long themeId, @AuthenticationPrincipal UserDetails userDetails) {
        themeService.subscribe(userDetails.getUsername(), themeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Se désabonne d'un thème.
     *
     * @param themeId L'identifiant du thème dont l'utilisateur souhaite se désabonner.
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @return ResponseEntity<Void> La réponse indiquant la désinscription effectuée.
     */
    @PostMapping("/unsubscribe/{themeId}")
    @Operation(summary = "Se désabonner d'un thème", description = "Permet à un utilisateur de se désabonner d'un thème.")
    public ResponseEntity<Void> unsubscribeFromTheme(@PathVariable Long themeId, @AuthenticationPrincipal UserDetails userDetails) {
        themeService.unsubscribe(userDetails.getUsername(), themeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Récupère les abonnements de l'utilisateur.
     *
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @return ResponseEntity<List<ThemeDto>> La liste des thèmes auxquels l'utilisateur est abonné.
     */
    @GetMapping("/subscriptions")
    @Operation(summary = "Obtenir les abonnements de l'utilisateur", description = "Récupère la liste des thèmes auxquels l'utilisateur est abonné.")
    public ResponseEntity<List<ThemeDto>> getUserSubscriptions(@AuthenticationPrincipal UserDetails userDetails) {
        List<Theme> subscribedThemes = themeService.getUserSubscriptions(userDetails.getUsername());
        List<ThemeDto> themeDtos = subscribedThemes.stream()
                .map(themeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(themeDtos);
    }

    /**
     * Récupère un thème spécifique par son identifiant.
     *
     * @param id L'identifiant du thème à récupérer.
     * @return ResponseEntity<ThemeDto> Le thème demandé.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un thème par ID", description = "Récupère un thème spécifique en fonction de son identifiant.")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable Long id) {
        Theme theme = themeService.findThemeById(id);
        ThemeDto themeDto = themeMapper.toDto(theme);
        return ResponseEntity.ok(themeDto);
    }
}

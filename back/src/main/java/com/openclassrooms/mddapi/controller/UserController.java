package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Contrôleur pour gérer les informations de l'utilisateur.
 * Permet de récupérer et de mettre à jour le profil de l'utilisateur.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Récupère le profil de l'utilisateur authentifié.
     *
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @return ResponseEntity<UserDto> Les informations de l'utilisateur.
     */
    @GetMapping
    @Operation(summary = "Obtenir le profil de l'utilisateur", description = "Récupère les informations du profil de l'utilisateur authentifié.")
    public ResponseEntity<UserDto> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.findByUsername(userDetails.getUsername())
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Met à jour le profil de l'utilisateur authentifié.
     *
     * @param userDetails Les informations de l'utilisateur authentifié.
     * @param updatedDto Les nouvelles données pour mettre à jour le profil de l'utilisateur.
     * @return ResponseEntity<UserDto> Le profil mis à jour de l'utilisateur.
     */
    @PutMapping
    @Operation(summary = "Mettre à jour le profil de l'utilisateur", description = "Permet à un utilisateur de mettre à jour ses informations de profil.")
    public ResponseEntity<UserDto> updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody UserDto updatedDto) {
        return userService.findByUsername(userDetails.getUsername())
                .map(existingUser -> {
                    existingUser.setUsername(updatedDto.getUsername());
                    existingUser.setEmail(updatedDto.getEmail());
                    User updatedUser = userService.update(existingUser);
                    return ResponseEntity.ok(userMapper.toDto(updatedUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

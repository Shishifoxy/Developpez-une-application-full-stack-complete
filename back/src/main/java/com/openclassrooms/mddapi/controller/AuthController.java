package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.AuthResponseDto;
import com.openclassrooms.mddapi.dto.LoginRequestDto;
import com.openclassrooms.mddapi.dto.RegisterRequestDto;
import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.exception.GlobalExceptionHandler;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Contrôleur d'authentification pour la gestion des utilisateurs.
 * Permet l'enregistrement des utilisateurs et leur connexion via des tokens JWT.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Enregistre un nouvel utilisateur.
     *
     * @param request Les données d'inscription.
     * @return ResponseEntity La réponse contenant le token JWT et les informations de l'utilisateur.
     */
    @PostMapping("/register")
    @Operation(summary = "Inscription d'un utilisateur", description = "Permet de créer un nouvel utilisateur dans le système.")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto request) {
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new GlobalExceptionHandler.ErrorResponse("Email déjà utilisé", 400));
        }
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new GlobalExceptionHandler.ErrorResponse("Nom d'utilisateur déjà utilisé", 400));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userService.save(user);

        String token = jwtUtils.generateToken(savedUser.getUsername());
        return ResponseEntity.ok(new AuthResponseDto(token, userMapper.toDto(savedUser)));
    }

    /**
     * Authentifie un utilisateur et génère un token JWT.
     *
     * @param request Les informations d'identification (identifiant et mot de passe).
     * @return ResponseEntity La réponse contenant le token JWT et les informations de l'utilisateur.
     */
    @PostMapping("/login")
    @Operation(summary = "Connexion d'un utilisateur", description = "Permet de connecter un utilisateur et de lui fournir un token JWT.")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            User user = userService.findByEmailOrUsername(request.getIdentifier());
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Mot de passe incorrect");
            }
            String token = jwtUtils.generateToken(user.getUsername());
            return ResponseEntity.ok(new AuthResponseDto(token, userMapper.toDto(user)));

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("Utilisateur non trouvé");
        }
    }
}

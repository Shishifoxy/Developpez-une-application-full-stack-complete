package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.Entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service personnalisé pour charger les détails de l'utilisateur à partir de la base de données.
 * Implémente l'interface UserDetailsService de Spring Security.
 * Permet de récupérer les informations de l'utilisateur lors de l'authentification.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Charge les détails d'un utilisateur par son nom d'utilisateur.
     *
     * @param username Le nom d'utilisateur à rechercher.
     * @return UserDetails Les détails de l'utilisateur.
     * @throws UsernameNotFoundException Si l'utilisateur n'est pas trouvé dans la base de données.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}

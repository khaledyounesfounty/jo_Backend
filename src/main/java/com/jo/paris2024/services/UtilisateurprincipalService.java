package com.jo.paris2024.services;

import com.jo.paris2024.DTO.UserDTO;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.entities.Utilisateurprincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UtilisateurprincipalService {
    void registerNewUser(Utilisateurprincipal registrationRequest);
    Utilisateurprincipal getUserByUsername(String username);
    Utilisateur getUtilisateurLogin() ;
    UserDTO registerUser(Utilisateurprincipal user);
    Utilisateurprincipal getUserDetails(String userName);
}

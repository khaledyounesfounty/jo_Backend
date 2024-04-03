package com.jo.paris2024.services;

import com.jo.paris2024.DTO.UserDTO;
import com.jo.paris2024.entities.Utilisateurprincipal;

public interface UtilisateurprincipalService {
    void registerNewUser(Utilisateurprincipal registrationRequest);
    Utilisateurprincipal getUserByUsername(String username);
    Utilisateurprincipal getCurrentUser();
    UserDTO registerUser(Utilisateurprincipal user);
}

package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Utilisateurprincipal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurprincipalRepository extends JpaRepository<Utilisateurprincipal, Integer> {
    Utilisateurprincipal findByEmail(String username);
}
package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.UserDTO;
import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.entities.Utilisateurprincipal;
import com.jo.paris2024.repository.PanierRepository;
import com.jo.paris2024.repository.UtilisateurRepository;
import com.jo.paris2024.repository.UtilisateurprincipalRepository;
import com.jo.paris2024.services.UtilisateurprincipalService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class UtilisateurprincipalServiceimpl implements UtilisateurprincipalService {
    Logger logger = Logger.getLogger("UtilisateurprincipalServiceimpl");
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurprincipalRepository userRepository;
    private final PanierRepository panierRepository;
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurprincipalServiceimpl(
            PasswordEncoder passwordEncoder,
            UtilisateurprincipalRepository userRepository,
            PanierRepository panierRepository,
            UtilisateurRepository utilisateurRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.panierRepository = panierRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional
    public void registerNewUser(Utilisateurprincipal registrationRequest) {
        // Check if user already exists
        if (userRepository.findByEmail(registrationRequest.getEmail()) != null) {
            throw new IllegalStateException("L'utilisateur existe déjà");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(registrationRequest.getMotDePasse());
        registrationRequest.setMotDePasse(encodedPassword);

        // Save the main user entity
        Utilisateurprincipal utilisateurPrincipal = userRepository.save(registrationRequest);

        // Create and save Utilisateur entity
        Utilisateur user = new Utilisateur();
        user.setUtilisateurprincipal(utilisateurPrincipal);
        user.setCleUtilisateur(generateCle());
        Utilisateur newUser = utilisateurRepository.save(user);

        // Create and save Panier entity
        Panier panier = new Panier();
        panier.setUtilisateur(newUser);
        panier.setSommmeTotal(0.0); // Initialize sum total
        Panier savedPanier = panierRepository.save(panier);

        // Set the Panier to the newUser and save
        newUser.setPanier(savedPanier);
        utilisateurRepository.save(newUser);

        logger.info("Le Panier a été créé pour l'utilisateur avec nom: " + utilisateurPrincipal.getNom() + " et ID de Panier: " + newUser.getPanier().getId());
    }

    public static String generateCle() {
        int length = 100;
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }


    @Override
    public Utilisateurprincipal getUserByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public Utilisateur getUtilisateurLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("L'utilisateur connecté est: " + auth.getName());
        Utilisateur utilisateur = userRepository.findByEmail(auth.getName()).getUtilisateur();
        logger.info("L'utilisateur connecté est: " + utilisateur.getUtilisateurprincipal().getNom());
        return utilisateur;
    }

    @Override
    public UserDTO registerUser(Utilisateurprincipal user) {
        registerNewUser(user);
        return new UserDTO(user);
    }

    @Override
    public Utilisateurprincipal getUserDetails(String userName) {
        Utilisateurprincipal utilisateurprincipal = userRepository.findByEmail(userName);
        return utilisateurprincipal;
    }
}

package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.UserDTO;
import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.entities.Utilisateurprincipal;
import com.jo.paris2024.repository.PanierRepository;
import com.jo.paris2024.repository.UtilisateurRepository;
import com.jo.paris2024.repository.UtilisateurprincipalRepository;
import com.jo.paris2024.services.UtilisateurprincipalService;
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


    @Override
    public void registerNewUser(Utilisateurprincipal registrationRequest) {

        if (userRepository.findByEmail(registrationRequest.getEmail()) != null) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }
        String encodedPassword = passwordEncoder.encode(registrationRequest.getMotDePasse());
        logger.info("L'utilisateur est enregistré + " + registrationRequest);
        registrationRequest.setMotDePasse(encodedPassword);
        logger.info("L'utilisateur est enregistré + " + registrationRequest);
        Utilisateurprincipal utilisateurprincipal = userRepository.save(registrationRequest);

        Utilisateur user = new Utilisateur();
        user.setUtilisateurprincipal(utilisateurprincipal);
        user.setCleUtilisateur(generateCle());
        Utilisateur newUser = utilisateurRepository.save(user);
        Panier panier = new Panier();
        panier.setUtilisateur(user);
        Panier p = panierRepository.save(panier);
        p.setSommmeTotal(0.0);
        panierRepository.save(p);
        newUser.setPanier(p);
        logger.info("Le Panier a ete creer pour l'utilisateur + " + utilisateurprincipal.getNom() + " Id de Panier est : " + panier.getId());

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
        return userRepository.findByEmail(auth.getName()).getUtilisateur();
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

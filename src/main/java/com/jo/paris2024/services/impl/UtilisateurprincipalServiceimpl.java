package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.UserDTO;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.entities.Utilisateurprincipal;
import com.jo.paris2024.repository.UtilisateurRepository;
import com.jo.paris2024.repository.UtilisateurprincipalRepository;
import com.jo.paris2024.services.UtilisateurprincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Logger;

@Service
public class UtilisateurprincipalServiceimpl implements UtilisateurprincipalService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurprincipalRepository userRepository;
    Logger logger = Logger.getLogger("UtilisateurprincipalServiceimpl");
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void registerNewUser(Utilisateurprincipal registrationRequest) {

        if (userRepository.findByEmail(registrationRequest.getEmail())!=null) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }
        String encodedPassword = passwordEncoder.encode(registrationRequest.getMotDePasse());
        logger.info("L'utilisateur est enregistré + "+registrationRequest);
        registrationRequest.setMotDePasse(encodedPassword);
        logger.info("L'utilisateur est enregistré + "+registrationRequest);
        Utilisateurprincipal utilisateurprincipal =userRepository.save(registrationRequest);

        Utilisateur user =new Utilisateur();
        user.setUtilisateurprincipal(utilisateurprincipal);
        user.setCleUtilisateur(generateUtilisateur());
        utilisateurRepository.save(user);

    }


    public static String generateUtilisateur() {
        int length =100;
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
    public Utilisateurprincipal getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username);
    }
    @Override
    public UserDTO registerUser(Utilisateurprincipal user) {
        registerNewUser(user);
        return new UserDTO(user);
    }
}

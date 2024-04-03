package com.jo.paris2024.security;

import com.jo.paris2024.entities.Utilisateurprincipal;
import com.jo.paris2024.repository.UtilisateurprincipalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurprincipalRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("Entrée dans la méthode loadUserByUsername..!!!");
        Utilisateurprincipal user = userRepository.findByEmail(username);
        if(user == null){
            logger.error("On n'a pas trouvé l'utilisateur..!!!");
            throw new UsernameNotFoundException("L'utilisateur n'existe pas..!!!");
        }
        logger.info("L'utilisateur est connecté..!!!");
        return new CustomUserDetails(user);
    }

}
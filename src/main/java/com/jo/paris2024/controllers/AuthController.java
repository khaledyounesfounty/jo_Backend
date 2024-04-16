package com.jo.paris2024.controllers;


import com.jo.paris2024.DTO.JwtResponseDTO;
import com.jo.paris2024.DTO.UserDTO;
import com.jo.paris2024.entities.Utilisateurprincipal;
import com.jo.paris2024.security.JwtService;
import com.jo.paris2024.services.UtilisateurprincipalService;
import com.jo.paris2024.DTO.AuthRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UtilisateurprincipalService utilisateurprincipalService;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UtilisateurprincipalService utilisateurprincipalService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.utilisateurprincipalService = utilisateurprincipalService;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            if (utilisateurprincipalService.getUserByUsername(authRequestDTO.getUsername()) == null) {
                return ResponseEntity.badRequest().body("Nom d'utilisateur ou mot de passe incorrect");
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            if (authentication.isAuthenticated()) {
               JwtResponseDTO jwtResponseDTO = new JwtResponseDTO(jwtService.GenerateToken(authRequestDTO.getUsername()), new UserDTO(utilisateurprincipalService.getUserByUsername(authRequestDTO.getUsername())));
                return ResponseEntity.ok(jwtResponseDTO);
            } else {
                return ResponseEntity.badRequest().body("Nom d'utilisateur ou mot de passe incorrect");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    @PostMapping("/api/v1/register")
    public ResponseEntity<?> registerUser(@RequestBody Utilisateurprincipal userDTO) {
        return  ResponseEntity.ok(utilisateurprincipalService.registerUser(userDTO));
    }

    @GetMapping("/user/details")
    public ResponseEntity<?> getDetailUser(Authentication authentification){
        String userName =authentification.getName();
        return ResponseEntity.ok(utilisateurprincipalService.getUserDetails(userName));
    }

}

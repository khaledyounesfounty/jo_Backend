package com.jo.paris2024.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "utilisateurprincipal")
public class Utilisateurprincipal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Size(max = 255)
    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Size(max = 255)
    @NotNull
    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Size(max = 5)
    @NotNull
    @Column(name = "role", nullable = false, length = 5)
    private String role = "USER";

    @OneToOne(mappedBy = "utilisateurprincipal")
    private Administrateur administrateur;

    @OneToMany(mappedBy = "idUtilisateur")
    @ToString.Exclude
    private Set<Billet> billets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUtilisateur")
    @ToString.Exclude
    private Set<Panier> paniers = new LinkedHashSet<>();

    @OneToOne(mappedBy = "utilisateurprincipal")
    private Utilisateur utilisateur;

}
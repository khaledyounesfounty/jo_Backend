package com.jo.paris2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateurprincipal utilisateurprincipal;


    @Size(max = 100)
    @Column(name = "cle_utilisateur", length = 100)
    private String cleUtilisateur;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_panier")
    private Panier panier;


    @OneToMany(mappedBy = "idUtilisateur")
    private Set<Billet> billets = new LinkedHashSet<>();


}
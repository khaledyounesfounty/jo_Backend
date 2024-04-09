package com.jo.paris2024.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_panier", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUtilisateur")
    private Utilisateurprincipal idUtilisateur;

    @OneToMany(mappedBy = "idPanier")
    private Set<OffreDansPanier> offreDansPaniers = new LinkedHashSet<>();

}
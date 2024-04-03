package com.jo.paris2024.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "offredanspanier")
public class Offredanspanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoffre_dans_panier", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPanier")
    private Panier idPanier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOffre")
    private Offre idOffre;

    @NotNull
    @Column(name = "quantite", nullable = false)
    private Integer quantite;

}
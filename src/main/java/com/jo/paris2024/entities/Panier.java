package com.jo.paris2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id", nullable = false)
    private Integer id;

    private Double sommmeTotal;

    @JsonIgnore
    @OneToOne(mappedBy = "panier")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "idPanier", fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new LinkedHashSet<>();

}
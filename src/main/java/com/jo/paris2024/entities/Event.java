package com.jo.paris2024.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Column(name = "titre", nullable = false)
    private String titre;
    private LocalDate dateEvent;
    private String lieu;
    private String description;
    private Integer nombreDePlacesMax;
    private Integer nombreDePlacesDisponibles;
    private Boolean isDisponible;
    private double prixUnitaire ;
    private String image;
    private String categorie;

    @OneToMany
    private Set<Billet> billets = new LinkedHashSet<>();

}

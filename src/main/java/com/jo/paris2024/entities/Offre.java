package com.jo.paris2024.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "offre")
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoffre", nullable = false)
    private Integer id;

    @NotNull(message = "Le champs description ne doit pas etre null")
    @NotEmpty(message="pas vide")
    @NotBlank(message="pas  d espaces")
    @Lob
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name ="titre",nullable = false)
    private String titre;
    @NotNull
    @Column(name = "prix", nullable = false)
    private Float prix;

    @Size(max = 100)
    @Column(name = "type", length = 100)
    private String type;

    @Size(max = 100)
    @Column(name = "categorie", length = 100)
    private String categorie;

    @NotNull
    @Column(name = "dateEvent", nullable = false)
    private LocalDate dateEvent;

    @Column(name = "nbMaxPlace")
    private Integer nbMaxPlace;

    @Column(name = "nbActualPlace")
    private Integer nbActualPlace;
    @JsonIgnore
    @OneToMany(mappedBy = "offre")
    private Set<Billet> billets = new LinkedHashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "idOffre")
    private Set<OffreDansPanier> offreDansPaniers = new LinkedHashSet<>();

}
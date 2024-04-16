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
    @NotBlank(message="pasd espaces")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name ="titre",nullable = false)
    private String titre;

    @NotNull
    @Column(name = "remise", nullable = false)
    private int remise;

    @NotNull
    @Column(name = "nb_place", nullable = false)
    private int nbPlace;

}
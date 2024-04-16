package com.jo.paris2024.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class EventDto {
    private Long id;
    @NotNull(message = "Le champs titre ne doit pas etre null")
    @NotEmpty(message = "Le champs titre ne doit pas etre vide")
    @NotBlank(message = "Le champs titre ne doit pas etre vide")
    private String titre;
    @NotNull(message = "Le champs titre ne doit pas etre null")
    private LocalDate dateEvent;
    private String lieu;
    private String description;
    @NotNull(message = "Le champs nombreDePlacesMax ne doit pas etre null")
    private Integer nombreDePlacesMax;
    @NotNull(message = "Le champs nombreDePlacesDisponibles ne doit pas etre null")
    private Integer nombreDePlacesDisponibles;
    @NotNull(message = "Le champs isDisponible ne doit pas etre null")
    private Boolean isDisponible;
    private double prixUnitaire ;
    private String image;
    private String categorie;
}

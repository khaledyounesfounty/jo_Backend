package com.jo.paris2024.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OffreDto {

    private Integer id;
    private String description;
    @NotNull(message = "Le champs titre ne doit pas etre null")
    @NotEmpty(message = "Le champs titre ne doit pas etre vide")
    @NotBlank(message = "Le champs titre ne doit pas etre vide")
    private String titre;
    @NotNull(message = "Le champs remise ne doit pas etre null")
    @NotEmpty(message = "Le champs remise ne doit pas etre vide")
    @NotBlank(message = "Le champs remise ne doit pas etre vide")
    private int remise;

}

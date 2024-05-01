package com.jo.paris2024.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.jo.paris2024.entities.Panier}
 */
@Value
public class PanierDto implements Serializable {
    Integer id;
    Double sommmeTotal;
    Set<ReservationDto> reservations;

    /**
     * DTO for {@link com.jo.paris2024.entities.Reservation}
     */
    @Value
    public static class ReservationDto implements Serializable {
        Integer id;
        OffreDto1 idOffre;
        EventDto1 idEvent;
        @NotNull
        Double prix;

        /**
         * DTO for {@link com.jo.paris2024.entities.Offre}
         */
        @Value
        public static class OffreDto1 implements Serializable {
            @NotNull(message = "Le champs description ne doit pas etre null")
            @NotEmpty(message = "pas vide")
            @NotBlank(message = "pasd espaces")
            String description;
            String titre;
        }

        /**
         * DTO for {@link com.jo.paris2024.entities.Event}
         */
        @Value
        public static class EventDto1 implements Serializable {
            @NotNull
            String titre;
            LocalDate dateEvent;
            String lieu;
            String description;
            Integer nombreDePlacesMax;
            String categorie;
        }
    }
}
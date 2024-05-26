package com.jo.paris2024.DTO;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.entities.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Billet}
 */
@Value
public class BilletDto {
    ReservationDto reservation;
    UtilisateurDto idUtilisateur;
    QrcodeDto qrcode;


    /**
     * DTO for {@link Reservation}
     */
    @Value
    public static class ReservationDto implements Serializable {
        Integer id;
        OffreDto idOffre;
        EventDto idEvent;
        @NotNull
        Double prix;
    }

    /**
     * DTO for {@link Utilisateur}
     */
    @Value
    public static class UtilisateurDto implements Serializable {
        UtilisateurprincipalDto utilisateurprincipal;

        /**
         * DTO for {@link Utilisateurprincipal}
         */
        @Value
        public static class UtilisateurprincipalDto implements Serializable {
            @NotNull
            @Size(max = 255)
            String nom;
            @NotNull
            @Size(max = 255)
            String prenom;
        }
    }

    /**
     * DTO for {@link Qrcode}
     */
    @Value
    public static class QrcodeDto implements Serializable {
        String qrImage;
    }
}
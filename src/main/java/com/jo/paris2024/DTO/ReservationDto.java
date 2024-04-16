package com.jo.paris2024.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationDto {
    private Integer id;
    private Integer idPanierId;
    private Integer idOffreId;
    private Long idEventId;
    private Double prix;

}

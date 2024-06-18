package com.jo.paris2024.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventStatisticDTO {
    private String name;
    private int totalReservations;
    private int remainingPlaces;
}
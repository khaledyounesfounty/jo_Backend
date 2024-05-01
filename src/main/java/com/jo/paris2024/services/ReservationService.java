package com.jo.paris2024.services;

import com.jo.paris2024.DTO.ReservationDto;
import com.jo.paris2024.entities.Reservation;

import java.util.Optional;

public interface ReservationService {
    void saveReservation(ReservationDto reservationDto);

    Reservation getReservationById(Integer idReservation);
}

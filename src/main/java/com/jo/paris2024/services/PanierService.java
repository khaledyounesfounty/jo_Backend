package com.jo.paris2024.services;

import com.jo.paris2024.DTO.PanierDto;
import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface PanierService {

    Panier createPanier(Panier panier);

    void deleteReservationFromPanier(Integer idReservation);
    void addReservationToPanier(Integer id );

    PanierDto getPanierDetails();

    void validerPanier();
}

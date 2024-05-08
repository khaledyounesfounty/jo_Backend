package com.jo.paris2024.services;

import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Reservation;

import java.util.List;

public interface BilletService {
    Billet creerBillet(Reservation reservation);

    List<Billet> getAllBillets();
}

package com.jo.paris2024.services;

import com.jo.paris2024.entities.Billet;
import com.jo.paris2024.entities.Reservation;

public interface BilletService {
    Billet creerBillet(Reservation reservation);
}

package com.jo.paris2024.services;

import com.jo.paris2024.entities.OffreDansPanier;

import java.util.List;
import java.util.Optional;

public interface OffreDansPanierService {
    List<OffreDansPanier> getAllOffresDansPanier();
    Optional<OffreDansPanier> getOffreDansPanierById(Integer id);
    OffreDansPanier createOffreDansPanier(OffreDansPanier offreDansPanier);
    void deleteOffreDansPanierById(Integer id);


}

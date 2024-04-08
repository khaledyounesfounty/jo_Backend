package com.jo.paris2024.services;

import com.jo.paris2024.entities.Panier;

import java.util.List;
import java.util.Optional;

public interface PanierService {
    List<Panier> getPaniers();
    Optional<Panier> getPanierById(Integer id);
    Panier createPanier(Panier panier);
    Panier updatePanier(Integer id, Panier updatedPanier);
    void deletePanier(Integer id);

    List<Panier> getALLPaniers();
}

package com.jo.paris2024.services.impl;

import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.repository.PanierRepository;
import com.jo.paris2024.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {
    @Autowired
    private PanierRepository panierRepository;
    @Override
    public List<Panier> getPaniers(){
        return panierRepository.findAll();

    }
    @Override
    public Optional<Panier>getPanierById(Integer id){
        return panierRepository.findById(id);
    }
    @Override
    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }
    @Override
    public Panier updatePanier(Integer id, Panier updatedPanier) {
        if (panierRepository.existsById(id)) {
            updatedPanier.setId(id);
            return panierRepository.save(updatedPanier);
        } else {
            return null;
        }
    }
    @Override
    public void deletePanier(Integer id) {
        panierRepository.deleteById(id);
    }

    @Override
    public List<Panier> getALLPaniers() {
        return null;
    }

}

package com.jo.paris2024.services.impl;

import com.jo.paris2024.entities.OffreDansPanier;
import com.jo.paris2024.repository.OffreDansPanierRepository;
import com.jo.paris2024.services.OffreDansPanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreDansPanierServiceImpl implements OffreDansPanierService {

    @Autowired
    private OffreDansPanierRepository offreDansPanierRepository;

    @Override
    public List<OffreDansPanier> getAllOffresDansPanier() {
        return offreDansPanierRepository.findAll();
    }

    @Override
    public Optional<OffreDansPanier> getOffreDansPanierById(Integer id) {
        return offreDansPanierRepository.findById(id);
    }

    @Override
    public OffreDansPanier createOffreDansPanier(OffreDansPanier offreDansPanier) {
        return offreDansPanierRepository.save(offreDansPanier);
    }

    @Override
    public void deleteOffreDansPanierById(Integer id) {
        offreDansPanierRepository.deleteById(id);
    }


}

package com.jo.paris2024.services.impl;

import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreServiceImpl implements OffreService {
    @Autowired
    OffreRepository offreRepository;

    @Override
    public List<Offre> getAllOffres() {

        List<Offre> offres = offreRepository.findAll();

        if (!offres.isEmpty()) {
            return offres;
        }
        throw new IllegalArgumentException("Pas D'offres disponibles");

    }
}

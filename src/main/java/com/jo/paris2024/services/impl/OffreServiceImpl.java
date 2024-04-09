package com.jo.paris2024.services.impl;

import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Offre getOffreById(Integer id) {
        return offreRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Pas d offre disponible sur cet id"));
    }
    @Override
    public void saveOffre(Offre offre) {
        if (!offreRepository.findByTitre(offre.getTitre()).isEmpty()){
            throw new IllegalArgumentException("offre existe deja avec le meme titre");
        }

        offreRepository.save(offre);

    }

    @Override
    public Offre updateOffre(Integer id, Offre offre) {


        Optional<Offre> optionalOffre = offreRepository.findById(id);

        if (optionalOffre.isPresent()) {
            Offre existingOffre = optionalOffre.get();

            existingOffre.setTitre(offre.getTitre());
            existingOffre.setDescription(offre.getDescription());



            return offreRepository.save(existingOffre);
        } else {

            return null;
        }
    }

    @Override
    public boolean offreExists(Integer id) {
        return offreRepository.existsById(id);
    }








    @Override
    public void deleteOffreById(Integer id) {
        offreRepository.deleteById(id);
    }






}



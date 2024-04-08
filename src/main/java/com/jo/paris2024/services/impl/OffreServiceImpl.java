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
    @Override
    public void saveOffre(Offre offre) {
        if (!offreRepository.findByTitre(offre.getTitre()).isEmpty()){
            throw new IllegalArgumentException("offre existe deja avec le meme titre");
        }

        offreRepository.save(offre);

    }

    @Override
    public void updateOffre(Integer id, Offre offre) {
        Offre offreExistante = getOffreBYId(id);
        offreExistante.setDescription(offre.getDescription());
        offreExistante.setPrix(offre.getPrix());
        offreExistante.setType(offre.getType());
        offreExistante.setCategorie(offre.getCategorie());
        offreExistante.setDateEvent(offre.getDateEvent());
        offreExistante.setNbMaxPlace(offre.getNbMaxPlace());
        offreExistante.setNbActualPlace(offre.getNbActualPlace());
        offreRepository.save(offreExistante);
    }

    @Override
    public void deleteOffreParId(Integer id) {

    }



    private Offre getOffreBYId(Integer id) {
        Offre o = null;
        return o;
    }

    @Override
    public void deleteOffreById(Integer id) {
        Offre offreExistante = getOffreById(id);
        offreRepository.delete(offreExistante);
    }

    public Offre getOffreById(Integer id) {
        return offreRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Pas d offre disponible sur cet id"));
    }





}



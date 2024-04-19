package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.Mapper.OffreMapper;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OffreServiceImpl implements OffreService {
    @Autowired
    OffreRepository offreRepository;
    @Autowired
    private OffreMapper offreMapper;
    Logger logger = Logger.getLogger(OffreServiceImpl.class.getName());

    @Override
    public List<Offre> getAllOffres() {

        List<Offre> offres = offreRepository.findAll();

        if (!offres.isEmpty()) {
            return offres;
        }
        throw new IllegalArgumentException("Pas D'offres disponibles");

    }

    public Offre getOffreById(Integer id) {
        return offreRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pas d offre disponible sur cet id"));
    }

    @Override
    public void saveOffre(OffreDto offre) {
        logger.info("L'offre avant la conversion" + offre.getNbPlace());
        if (!offreRepository.findByTitre(offre.getTitre()).isEmpty()) {

            throw new IllegalArgumentException("offre existe deja avec le meme titre");
        }
        Offre newOffre = offreMapper.toEntity(offre);
        logger.info("L'offre apres la conversion" + newOffre.getNbPlace());
        offreRepository.save(newOffre);

    }

    @Override
    public Offre updateOffre(Integer id, OffreDto offre) {


        Optional<Offre> optionalOffre = offreRepository.findById(id);

        if (optionalOffre.isPresent()) {

            Offre existingOffre = optionalOffre.get();
            if (!offre.getTitre().equals(optionalOffre.get().getTitre())) {
                if (!offreRepository.findByTitre(offre.getTitre()).isEmpty()) {
                    throw new IllegalArgumentException("L'offre existe déja avec le meme titre");

                }
                existingOffre.setTitre(offre.getTitre());

            }
            existingOffre.setDescription(offre.getDescription());
            existingOffre.setRemise(offre.getRemise());
            return offreRepository.save(existingOffre);
        } else {

            throw new RuntimeException("L'offre ne peut pas etre modifiée:L'offre n'existe pas");
        }
    }


    @Override
    public void deleteOffreById(Integer id) {
        Optional<Offre> optionalOffre = offreRepository.findById(id);

        if (optionalOffre.isPresent()) {
            offreRepository.deleteById(id);

        } else
            throw new RuntimeException("L'offre ne peut pas etre supprimée:L'offre n'existe pas");
        offreRepository.deleteById(id);
    }

}



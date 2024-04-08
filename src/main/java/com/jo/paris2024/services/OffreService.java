package com.jo.paris2024.services;

import com.jo.paris2024.entities.Offre;

import java.util.List;
import java.util.Optional;

public interface OffreService {

     List<Offre> getAllOffres();


     void saveOffre(Offre offre);

     void updateOffre(Integer id, Offre offre);

     void deleteOffreParId(Integer id);



     abstract void deleteOffreById(Integer id);

     Offre getOffreById(Integer id);


}

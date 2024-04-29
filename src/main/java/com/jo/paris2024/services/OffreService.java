package com.jo.paris2024.services;

import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.entities.Offre;

import java.util.List;

public interface OffreService {

     List<Offre> getAllOffres();


     void saveOffre(OffreDto offre);

     Offre updateOffre(Integer id, OffreDto offre);

      void deleteOffreById(Integer id);

     Offre getOffreById(Integer id);





}

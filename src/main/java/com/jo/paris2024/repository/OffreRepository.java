package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
  List<Offre> findByTitre(String titre);
}
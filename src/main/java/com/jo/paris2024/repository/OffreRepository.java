package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
  List<Offre> findByTitre(String titre);
}
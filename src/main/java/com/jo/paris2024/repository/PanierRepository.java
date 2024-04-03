package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
}
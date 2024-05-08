package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilletRepository extends JpaRepository<Billet, Integer> {
}
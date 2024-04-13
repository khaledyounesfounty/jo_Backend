package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByTitre(String titre);
}

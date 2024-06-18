package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findByTitre(String titre);
    List<Event> findAllByOrderByCategorieAscTitreAsc();
    List<Event> findByCategorie(String categorie);

    @Query(value = "SELECT COUNT(*) from billet b join reservation r join jo2024exam.event e where r.id=b.id_reservation\n" +
            "and e.id=r.id_event and e.titre= ?1", nativeQuery = true)
    int getNombreDeBilletEvent(String titre);
}

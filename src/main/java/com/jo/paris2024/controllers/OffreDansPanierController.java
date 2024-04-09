package com.jo.paris2024.controllers;

import com.jo.paris2024.entities.OffreDansPanier;
import com.jo.paris2024.services.OffreDansPanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offres-dans-panier")
public class OffreDansPanierController {

    @Autowired
    private OffreDansPanierService offreDansPanierService;

    @GetMapping
    public List<OffreDansPanier> getAllOffresDansPanier() {
        return offreDansPanierService.getAllOffresDansPanier();
    }

    @GetMapping("/{id}")
    public Optional<OffreDansPanier> getOffreDansPanierById(@PathVariable Integer id) {
        return offreDansPanierService.getOffreDansPanierById(id);
    }

    @PostMapping
    public OffreDansPanier createOffreDansPanier(@RequestBody OffreDansPanier offreDansPanier) {
        return offreDansPanierService.createOffreDansPanier(offreDansPanier);
    }

    @DeleteMapping("/{id}")
    public void deleteOffreDansPanierById(@PathVariable Integer id) {
        offreDansPanierService.deleteOffreDansPanierById(id);
    }

}


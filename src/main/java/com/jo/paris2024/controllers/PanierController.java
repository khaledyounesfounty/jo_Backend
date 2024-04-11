package com.jo.paris2024.controllers;

import com.jo.paris2024.entities.Panier;
import com.jo.paris2024.services.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/paniers")
public class PanierController {
    @Autowired
    private PanierService panierService;

    @GetMapping
    public ResponseEntity<List<Panier>> getAllPaniers() {
        List<Panier> paniers = panierService.getAllPaniers();
        return ResponseEntity.ok(paniers);
        //todo: ajouter le cas panier vide

    }
    @GetMapping("/{id}")
    public ResponseEntity<Panier> getPanierById(@PathVariable("id") Integer id) {
        Optional<Panier> panier = panierService.getPanierById(id);
        return panier.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
        Panier createdPanier = panierService.createPanier(panier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPanier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Panier> updatePanier(@PathVariable("id") Integer id, @RequestBody Panier updatedPanier) {
        Panier updatedEntity = panierService.updatePanier(id, updatedPanier);
        return updatedEntity != null ? ResponseEntity.ok(updatedEntity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePanier(@PathVariable("id") Integer id) {
        panierService.deletePanierById(id);
        return ResponseEntity.noContent().build();
    }
}

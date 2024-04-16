package com.jo.paris2024.controllers;

import com.jo.paris2024.DTO.PanierDto;
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


    @GetMapping("/details")
    public ResponseEntity<?> getPanierDetails() {
        PanierDto panier = panierService.getPanierDetails();
        return ResponseEntity.ok(panier);
    }

    @PostMapping
    public ResponseEntity<Panier> createPanier(@RequestBody Panier panier) {
        Panier createdPanier = panierService.createPanier(panier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPanier);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservationFromPanier(@PathVariable("id") Integer id) {
        panierService.deleteReservationFromPanier(id);
        return ResponseEntity.ok("La rreservation a été supprimée du panier");
    }


}

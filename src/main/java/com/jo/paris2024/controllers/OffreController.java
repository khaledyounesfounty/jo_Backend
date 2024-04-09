package com.jo.paris2024.controllers;

import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.OffreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offre")
public class OffreController {
    @Autowired
    OffreService offreService;
    @GetMapping
    public ResponseEntity<?> getAllOffre(){

        return ResponseEntity.ok(offreService.getAllOffres());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable Integer id) {
        Offre offre = offreService.getOffreById(id);
        return ResponseEntity.ok(offre);
    }

    @PostMapping
    public ResponseEntity<?> saveOffre(@Validated @RequestBody Offre offre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        offreService.saveOffre(offre);
        return ResponseEntity.ok("offre crée");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOffre(@PathVariable Integer id, @RequestBody Offre offre) {

        if (!offreService.offreExists(id)) {
            // Si l'offre n'existe pas, retourner une réponse avec le code 404 (Non trouvé)
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour l'offre avec les nouvelles données fournies
        Offre updatedOffre = offreService.updateOffre(id, offre);

        if (updatedOffre == null) {
            // Si la mise à jour échoue pour une raison quelconque, retourner une réponse avec le code 500 (Erreur interne du serveur)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Retourner une réponse avec le code 200 (OK) pour indiquer que la mise à jour a été effectuée avec succès
        return ResponseEntity.ok().build();
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffreById(@PathVariable Integer id) {
        offreService.deleteOffreParId(id);
        return ResponseEntity.ok().build();*/
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffre(@PathVariable Integer id) {
        if (offreService.offreExists(id)) {
            offreService.deleteOffreById(id);
            return ResponseEntity.ok("L'offre avec l'ID " + id + " a été supprimée avec succès.");
        } else {
            return ResponseEntity.notFound().build();
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'offre avec l'ID " + id + " n'a pas été trouvée.");
        }
    }
    }




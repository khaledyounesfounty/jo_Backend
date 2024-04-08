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
        offreService.updateOffre(id, offre);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffreById(@PathVariable Integer id) {
        offreService.deleteOffreParId(id);
        return ResponseEntity.ok().build();
    }


}

package com.jo.paris2024.controllers;

import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offre")
public class OffreController {
    @Autowired
    OffreService offreService;
    @GetMapping
    public ResponseEntity<?> getAllOffre(){
        return ResponseEntity.ok(offreService.getAllOffres());
    }


}

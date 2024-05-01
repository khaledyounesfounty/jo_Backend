package com.jo.paris2024.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paiment")
public class PaimentController {
    public ResponseEntity<String> paimentValide() {
        return ResponseEntity.ok( "Paiment effectué avec succès");
    }
}

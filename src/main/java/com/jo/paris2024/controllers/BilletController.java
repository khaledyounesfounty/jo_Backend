package com.jo.paris2024.controllers;

import com.jo.paris2024.Mapper.BilletMapper;
import com.jo.paris2024.entities.Utilisateur;
import com.jo.paris2024.services.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/billets")
public class BilletController {

    private final BilletService billetService;
    private final BilletMapper billetMapper;

    @Autowired
    public BilletController(BilletService billetService, BilletMapper billetMapper) {
        this.billetService = billetService;
        this.billetMapper = billetMapper;
    }

    @GetMapping
    public ResponseEntity<?> getBillets() {
        return ResponseEntity.ok(billetService.getAllBillets().stream().map(billetMapper::toBilletDto).collect(Collectors.toList()));
    }

}

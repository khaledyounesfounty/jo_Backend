package com.jo.paris2024.controllers;

import com.jo.paris2024.DTO.ReservationDto;
import com.jo.paris2024.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/v1/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @PostMapping
    public ResponseEntity<?> saveReservation(@Validated @RequestBody ReservationDto reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Les champs ne sont pas valides");
        }
        reservationService.saveReservation(reservation);
        return ResponseEntity.ok("La reservation a été ajoutée avec succes");
    }

}

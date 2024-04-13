package com.jo.paris2024.controllers;

import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.EventService;
import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping
    public ResponseEntity<?> getAllEvent() {

        return ResponseEntity.ok(eventService.getAllEvent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<?> saveOffre(@Validated @RequestBody Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        eventService.saveEvent(event);
        return ResponseEntity.ok("offre crée");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer id, @RequestBody Event event) {

        Offre updatedOffre = eventService.updateEvent(id, event);

        return ResponseEntity.ok("L'offre a été mise à jour avec succes");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEventById(id);
        return  ResponseEntity.ok("L'offre a été supprimée avec succes");

    }
}

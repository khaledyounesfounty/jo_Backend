package com.jo.paris2024.controllers;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.Mapper.EventMapper;
import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.EventService;
import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<?> getAllEvent() {
        return ResponseEntity.ok(eventService.getAllEvent().stream().map(eventMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(eventMapper.toDto(event));
    }

    @PostMapping
    public ResponseEntity<?> saveOffre(@Validated @RequestBody EventDto event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        eventService.saveEvent(event);
        return ResponseEntity.ok("L'offre a été ajoutée avec succes");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@Validated @PathVariable Integer id, @RequestBody EventDto event) {
        eventService.updateEvent(id, event);
        return ResponseEntity.ok("L'offre a été mise à jour avec succes");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok("L'offre a été supprimée avec succes");

    }
    // Faire une recherche par categorie si il est nul on return tout les events
    @GetMapping("/search/category/{categorie}")
    public ResponseEntity<?> getEventByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(eventService.getEventByCategorie(categorie).stream().map(eventMapper::toDto).collect(Collectors.toList()));
    }
}

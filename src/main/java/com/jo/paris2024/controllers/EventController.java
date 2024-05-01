package com.jo.paris2024.controllers;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.Mapper.EventMapper;
import com.jo.paris2024.Mapper.OffreMapper;
import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.EventService;
import com.jo.paris2024.services.OffreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private OffreMapper offreMapper;
    Logger logger = LoggerFactory.getLogger(EventController.class);

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
        logger.info("event : " + event);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        eventService.saveEvent(event);
        return ResponseEntity.ok("L'event a été ajoutée avec succes");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@Validated @PathVariable Integer id, @RequestBody EventDto event) {
        eventService.updateEvent(id, event);
        return ResponseEntity.ok("L'event a été mise à jour avec succes");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok("L'event a été supprimée avec succes");

    }
    // Faire une recherche par categorie si il est nul on return tout les events
    @GetMapping("/search/category/{categorie}")
    public ResponseEntity<?> getEventByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(eventService.getEventByCategorie(categorie).stream().map(eventMapper::toDto).collect(Collectors.toList()));
    }
    // get offres for a given event
    @GetMapping("/{id}/offres")
    public ResponseEntity<?> getOffresByEvent(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.getEventById(id).getOffres().stream().map(offreMapper::toDto).collect(Collectors.toList()));
    }
}

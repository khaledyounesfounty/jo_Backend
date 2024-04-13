package com.jo.paris2024.services.impl;

import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.EventRepository;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.services.EventService;
import com.jo.paris2024.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;


    @Override
    public List<Event> getAllEvent() {
        List<Event> events = eventRepository.findAll();

        if (!events.isEmpty()) {
            return events;
        }
        throw new IllegalArgumentException("Pas D'events disponibles");
    }

    @Override
    public Event getEventById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pas d event disponible sur cet id"));
    }

    @Override
    public void saveEvent(Event event) {
        if (!eventRepository.findByTitre(event.getTitre()).isEmpty()) {
            throw new IllegalArgumentException("offre existe deja avec le meme titre");
        }

        eventRepository.save(event);


    }

    @Override
    public Offre updateEvent(Integer id, Event event) {


        return null;
    }

    @Override
    public void deleteEventById(Integer id) {


    }
}



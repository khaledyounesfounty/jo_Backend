package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.Mapper.EventMapper;
import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.repository.EventRepository;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.services.EventService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private OffreRepository offreRepository;
    Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);


    @Override
    public List<Event> getAllEvent() {
        List<Event> events = eventRepository.findAllByOrderByCategorieAscTitreAsc();
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
    public void saveEvent(EventDto event) {
        if (!eventRepository.findByTitre(event.getTitre()).isEmpty()) {
            throw new IllegalArgumentException("offre existe deja avec le meme titre");
        }
        //
        Event newEvent = eventMapper.toEntity(event);
        logger.info("event : " + newEvent);
        Arrays.stream(event.getOffresIds()).forEach(offreid -> {
            Offre offre = offreRepository.findById(offreid).orElseThrow(() -> new IllegalArgumentException("Pas d'offre disponible sur cet id"));
            offre.getEvents().add(newEvent);
            newEvent.getOffres().add(offre);
            offreRepository.save(offre);
        });
        eventRepository.save(newEvent);
    }

    @Override
    public void updateEvent(Integer id, EventDto event) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event newEvent = eventMapper.toEntity(event);
            newEvent.setId(eventOptional.get().getId());
            eventRepository.save(newEvent);
        } else {
            throw new IllegalArgumentException("Pas d'event disponible sur cet id");
        }
    }

    @Override
    public void deleteEventById(Integer id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Pas d'event disponible sur cet id");
        }

    }

    @Override
    public List<Event> getEventByCategorie(String categorie) {
        if (categorie == null) {
            return eventRepository.findAllByOrderByCategorieAscTitreAsc();
        }
        return eventRepository.findByCategorie(categorie);
    }

}




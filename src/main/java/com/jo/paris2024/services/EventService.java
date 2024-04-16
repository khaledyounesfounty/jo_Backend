package com.jo.paris2024.services;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public interface EventService {
   List<Event> getAllEvent();

    Event getEventById(Integer id);

    void saveEvent(EventDto event);

    void updateEvent(Integer id, EventDto event);

    void deleteEventById(Integer id);

    List<Event> getEventByCategorie(String categorie);
}

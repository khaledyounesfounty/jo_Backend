package com.jo.paris2024.services;

import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
   List<Event> getAllEvent();

    Event getEventById(Integer id);

    void saveEvent(Event event);

    Offre updateEvent(Integer id, Event event);

    void deleteEventById(Integer id);
}

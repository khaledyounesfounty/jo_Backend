package com.example.jeuxapp.web;

import com.example.jeuxapp.entities.Event;
import com.example.jeuxapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class EventRestService {
    @Autowired


        private EventRepository eventRepository;
    @GetMapping("/events")

        public List<Event> events(){
            return eventRepository.findAll();
        }
        @GetMapping("/events/{id}")

        public Event findEvent(@PathVariable Long id){
            return eventRepository.findById(id).get();
        }
    }


package com.jo.paris2024.controller;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.DTO.OffreDto;
import com.jo.paris2024.Mapper.EventMapper;
import com.jo.paris2024.Mapper.OffreMapper;
import com.jo.paris2024.controllers.EventController;
import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventControllerTest {

    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    @Mock
    private EventMapper eventMapper;

    @Mock
    private OffreMapper offreMapper;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllEventsReturnsListOfEvents() {
        EventDto eventDto = new EventDto();
        when(eventService.getAllEvent()).thenReturn(Arrays.asList(new Event()));
        when(eventMapper.toDto(any(Event.class))).thenReturn(eventDto);

        ResponseEntity<?> response = eventController.getAllEvent();

        assertEquals(ResponseEntity.ok(Collections.singletonList(eventDto)), response);
    }

    @Test
    public void getEventByIdReturnsEvent() {
        EventDto eventDto = new EventDto();
        when(eventService.getEventById(anyInt())).thenReturn(new Event());
        when(eventMapper.toDto(any(Event.class))).thenReturn(eventDto);

        ResponseEntity<?> response = eventController.getEventById(1);

        assertEquals(ResponseEntity.ok(eventDto), response);
    }

    @Test
    public void saveOffreReturnsBadRequestWhenBindingResultHasErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = eventController.saveOffre(new EventDto(), bindingResult);

        assertEquals(ResponseEntity.badRequest().body(bindingResult.getAllErrors()), response);
    }

    @Test
    public void saveOffreReturnsOkWhenEventIsSaved() {
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<?> response = eventController.saveOffre(new EventDto(), bindingResult);

        assertEquals(ResponseEntity.ok("L'event a été ajoutée avec succes"), response);
    }

    @Test
    public void updateEventReturnsOkWhenEventIsUpdated() {
        ResponseEntity<?> response = eventController.updateEvent(1, new EventDto());

        assertEquals(ResponseEntity.ok("L'event a été mise à jour avec succes"), response);
    }

    @Test
    public void deleteEventReturnsOkWhenEventIsDeleted() {
        ResponseEntity<String> response = eventController.deleteEvent(1);

        assertEquals(ResponseEntity.ok("L'event a été supprimée avec succes"), response);
    }

    @Test
    public void getEventByCategorieReturnsListOfEvents() {
        EventDto eventDto = new EventDto();
        when(eventService.getEventByCategorie(anyString())).thenReturn(Arrays.asList(new Event()));
        when(eventMapper.toDto(any(Event.class))).thenReturn(eventDto);

        ResponseEntity<?> response = eventController.getEventByCategorie("category");

        assertEquals(ResponseEntity.ok(Collections.singletonList(eventDto)), response);
    }

    @Test
    public void getOffresByEventReturnsListOfOffres() {
        Event event = mock(Event.class);
        when(eventService.getEventById(anyInt())).thenReturn(event);
        when(event.getOffres()).thenReturn(Arrays.asList(new Offre()));
        when(offreMapper.toDto(any(Offre.class))).thenReturn(new OffreDto());

        ResponseEntity<?> response = eventController.getOffresByEvent(1);

        assertEquals(ResponseEntity.ok(Collections.singletonList(new OffreDto())), response);
    }
}

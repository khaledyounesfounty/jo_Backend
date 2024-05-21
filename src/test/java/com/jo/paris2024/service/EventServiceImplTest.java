package com.jo.paris2024.service;

import com.jo.paris2024.DTO.EventDto;
import com.jo.paris2024.entities.Event;
import com.jo.paris2024.entities.Offre;
import com.jo.paris2024.services.impl.EventServiceImpl;
import com.jo.paris2024.repository.EventRepository;
import com.jo.paris2024.repository.OffreRepository;
import com.jo.paris2024.Mapper.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private OffreRepository offreRepository;

    @Mock
    private EventMapper eventMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllEventReturnsListOfEvents() {
        when(eventRepository.findAllByOrderByCategorieAscTitreAsc()).thenReturn(Arrays.asList(new Event()));

        assertEquals(1, eventService.getAllEvent().size());
    }

    @Test
    public void getAllEventThrowsExceptionWhenNoEvents() {
        when(eventRepository.findAllByOrderByCategorieAscTitreAsc()).thenReturn(Arrays.asList());

        assertThrows(IllegalArgumentException.class, () -> eventService.getAllEvent());
    }

    @Test
    public void getEventByIdReturnsEvent() {
        when(eventRepository.findById(1)).thenReturn(Optional.of(new Event()));

        assertEquals(new Event(), eventService.getEventById(1));
    }

    @Test
    public void getEventByIdThrowsExceptionWhenNoEvent() {
        when(eventRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> eventService.getEventById(1));
    }

    @Test
    public void saveEventSavesEvent() {
        EventDto eventDto = new EventDto();
        eventDto.setOffresIds(new int[]{1}); // Ensure getOffresIds() does not return null
        Event event = new Event();
        when(eventRepository.findByTitre(eventDto.getTitre())).thenReturn(Arrays.asList());
        when(eventMapper.toEntity(eventDto)).thenReturn(event);
        when(offreRepository.findById(1)).thenReturn(Optional.of(new Offre())); // Ensure an offer with ID 1 exists

        eventService.saveEvent(eventDto);

        verify(eventRepository).save(event);
    }

    @Test
    public void saveEventThrowsExceptionWhenEventExists() {
        EventDto eventDto = new EventDto();
        when(eventRepository.findByTitre(eventDto.getTitre())).thenReturn(Arrays.asList(new Event()));

        assertThrows(IllegalArgumentException.class, () -> eventService.saveEvent(eventDto));
    }
}
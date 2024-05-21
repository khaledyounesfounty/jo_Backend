package com.jo.paris2024.repository;

import com.jo.paris2024.entities.Event;
import com.jo.paris2024.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EventRepositoryTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private Event event;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByTitreReturnsEventList() {
        when(eventRepository.findByTitre("title")).thenReturn(Arrays.asList(event));

        List<Event> events = eventRepository.findByTitre("title");

        assertEquals(1, events.size());
    }

    @Test
    public void findAllByOrderByCategorieAscTitreAscReturnsEventList() {
        when(eventRepository.findAllByOrderByCategorieAscTitreAsc()).thenReturn(Arrays.asList(event));

        List<Event> events = eventRepository.findAllByOrderByCategorieAscTitreAsc();

        assertEquals(1, events.size());
    }

    @Test
    public void findByCategorieReturnsEventList() {
        when(eventRepository.findByCategorie("category")).thenReturn(Arrays.asList(event));

        List<Event> events = eventRepository.findByCategorie("category");

        assertEquals(1, events.size());
    }
}
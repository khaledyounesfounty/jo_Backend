package com.jo.paris2024.services.impl;

import com.jo.paris2024.DTO.EventStatisticDTO;
import com.jo.paris2024.services.DashboardService;
import com.jo.paris2024.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    EventService eventService;
    Logger logger = Logger.getLogger(DashboardServiceImpl.class.getName());

    @Override
    public List<EventStatisticDTO>  getEventStatistic() {
        List<EventStatisticDTO> eventStatistic = new ArrayList<>();
        eventService.getAllEvent().forEach(event -> {
            EventStatisticDTO eventStatisticDTO = new EventStatisticDTO();
            eventStatisticDTO.setName(event.getTitre());
            eventStatisticDTO.setTotalReservations(eventService.getNombreDeBilletEvent(event.getTitre()));
            eventStatisticDTO.setRemainingPlaces(event.getNombreDePlacesMax() - eventService.getNombreDeBilletEvent(event.getTitre()));
            eventStatistic.add(eventStatisticDTO);
        });
        return eventStatistic;
    }
}
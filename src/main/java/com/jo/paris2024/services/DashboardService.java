package com.jo.paris2024.services;

import com.jo.paris2024.DTO.EventStatisticDTO;

import java.util.List;

public interface DashboardService {
    List<EventStatisticDTO> getEventStatistic();
}
package com.jo.paris2024.controllers;

import com.jo.paris2024.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;
    @GetMapping("eventStatistic")
    public ResponseEntity<?> getEventStatistic() {
        return ResponseEntity.ok(dashboardService.getEventStatistic());
    }
}

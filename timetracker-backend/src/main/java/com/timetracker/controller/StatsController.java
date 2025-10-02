package com.timetracker.controller;

import com.timetracker.dto.StatsResponse;
import com.timetracker.service.TimeEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final TimeEntryService timeEntryService;

    public StatsController(TimeEntryService timeEntryService) {
        this.timeEntryService = timeEntryService;
    }

    @GetMapping("/today")
    public ResponseEntity<StatsResponse> getTodayStats() {
        return ResponseEntity.ok(timeEntryService.getTodayStats());
    }
}
package com.timetracker.controller;

import com.timetracker.dto.CurrentTimerResponse;
import com.timetracker.dto.TimeEntryRequest;
import com.timetracker.entity.TimeEntry;
import com.timetracker.service.TimeEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-entries")
public class TimeEntryController {

    private final TimeEntryService timeEntryService;

    public TimeEntryController(TimeEntryService timeEntryService) {
        this.timeEntryService = timeEntryService;
    }

    @GetMapping("/today")
    public ResponseEntity<List<TimeEntry>> getTodayEntries() {
        return ResponseEntity.ok(timeEntryService.getTodayEntries());
    }

    @GetMapping("/current")
    public ResponseEntity<CurrentTimerResponse> getCurrentTimer() {
        return ResponseEntity.ok(timeEntryService.getCurrentTimer());
    }

    @PostMapping("/start")
    public ResponseEntity<TimeEntry> startTimer(@RequestBody TimeEntryRequest request) {
        TimeEntry entry = timeEntryService.startTimer(request.getProjectId(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(entry);
    }

    @PostMapping("/stop")
    public ResponseEntity<TimeEntry> stopTimer() {
        return ResponseEntity.ok(timeEntryService.stopCurrentTimer());
    }
}
package com.timetracker.dto;

import com.timetracker.entity.Project;
import java.time.LocalDateTime;

public class CurrentTimerResponse {
    private Boolean isRunning;
    private Long id;
    private Project project;
    private LocalDateTime startTime;
    private String description;
    private Long currentDuration;
    private String currentDurationFormatted;

    public Boolean getIsRunning() { return isRunning; }
    public void setIsRunning(Boolean isRunning) { this.isRunning = isRunning; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getCurrentDuration() { return currentDuration; }
    public void setCurrentDuration(Long currentDuration) {
        this.currentDuration = currentDuration;
    }

    public String getCurrentDurationFormatted() { return currentDurationFormatted; }
    public void setCurrentDurationFormatted(String currentDurationFormatted) {
        this.currentDurationFormatted = currentDurationFormatted;
    }
}
package com.timetracker.dto;

public class TimeEntryRequest {
    private Long projectId;
    private String description;

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
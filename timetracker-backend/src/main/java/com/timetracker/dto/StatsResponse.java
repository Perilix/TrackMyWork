package com.timetracker.dto;

public class StatsResponse {
    private Long totalTime;
    private String totalTimeFormatted;
    private Integer activeProjects;
    private Integer sessions;
    private Integer efficiency;

    public Long getTotalTime() { return totalTime; }
    public void setTotalTime(Long totalTime) { this.totalTime = totalTime; }

    public String getTotalTimeFormatted() { return totalTimeFormatted; }
    public void setTotalTimeFormatted(String totalTimeFormatted) {
        this.totalTimeFormatted = totalTimeFormatted;
    }

    public Integer getActiveProjects() { return activeProjects; }
    public void setActiveProjects(Integer activeProjects) {
        this.activeProjects = activeProjects;
    }

    public Integer getSessions() { return sessions; }
    public void setSessions(Integer sessions) { this.sessions = sessions; }

    public Integer getEfficiency() { return efficiency; }
    public void setEfficiency(Integer efficiency) { this.efficiency = efficiency; }
}
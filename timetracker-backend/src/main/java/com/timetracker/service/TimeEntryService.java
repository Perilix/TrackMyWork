package com.timetracker.service;

import com.timetracker.dto.CurrentTimerResponse;
import com.timetracker.dto.StatsResponse;
import com.timetracker.entity.Project;
import com.timetracker.entity.TimeEntry;
import com.timetracker.repository.TimeEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TimeEntryService {

    private final TimeEntryRepository timeEntryRepository;
    private final ProjectService projectService;

    public TimeEntryService(TimeEntryRepository timeEntryRepository, ProjectService projectService) {
        this.timeEntryRepository = timeEntryRepository;
        this.projectService = projectService;
    }

    public List<TimeEntry> getTodayEntries() {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime startOfNextDay = startOfDay.plusDays(1);
        return timeEntryRepository.findTodayEntries(startOfDay, startOfNextDay);
    }

    public CurrentTimerResponse getCurrentTimer() {
        return timeEntryRepository.findFirstByIsRunningTrueOrderByStartTimeDesc()
                .map(entry -> {
                    CurrentTimerResponse response = new CurrentTimerResponse();
                    response.setIsRunning(true);
                    response.setId(entry.getId());
                    response.setProject(entry.getProject());
                    response.setStartTime(entry.getStartTime());
                    response.setDescription(entry.getDescription());
                    response.setCurrentDuration(entry.getDuration());
                    response.setCurrentDurationFormatted(entry.getDurationFormatted());
                    return response;
                })
                .orElseGet(() -> {
                    CurrentTimerResponse response = new CurrentTimerResponse();
                    response.setIsRunning(false);
                    return response;
                });
    }

    @Transactional
    public TimeEntry startTimer(Long projectId, String description) {
        timeEntryRepository.findFirstByIsRunningTrueOrderByStartTimeDesc()
                .ifPresent(running -> {
                    running.setIsRunning(false);
                    running.setEndTime(LocalDateTime.now());
                    timeEntryRepository.save(running);
                });

        Project project = projectService.getProjectById(projectId);
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setProject(project);
        timeEntry.setStartTime(LocalDateTime.now());
        timeEntry.setDescription(description);
        timeEntry.setIsRunning(true);
        return timeEntryRepository.save(timeEntry);
    }

    @Transactional
    public TimeEntry stopCurrentTimer() {
        TimeEntry current = timeEntryRepository.findFirstByIsRunningTrueOrderByStartTimeDesc()
                .orElseThrow(() -> new RuntimeException("Aucun timer en cours"));
        current.setIsRunning(false);
        current.setEndTime(LocalDateTime.now());
        return timeEntryRepository.save(current);
    }

    public StatsResponse getTodayStats() {
        List<TimeEntry> todayEntries = getTodayEntries();
        long totalTime = 0;
        Set<Long> activeProjectIds = new HashSet<>();
        int sessions = 0;

        for (TimeEntry entry : todayEntries) {
            if (entry.getEndTime() != null) {
                totalTime += entry.getDuration();
                sessions++;
            } else if (entry.getIsRunning()) {
                totalTime += Duration.between(entry.getStartTime(), LocalDateTime.now()).getSeconds();
                sessions++;
            }
            activeProjectIds.add(entry.getProject().getId());
        }

        int efficiency = Math.min(100, (int) Math.round((totalTime / (8.0 * 3600)) * 100));

        StatsResponse stats = new StatsResponse();
        stats.setTotalTime(totalTime);
        stats.setTotalTimeFormatted(formatDuration(totalTime));
        stats.setActiveProjects(activeProjectIds.size());
        stats.setSessions(sessions);
        stats.setEfficiency(efficiency);
        return stats;
    }

    private String formatDuration(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        if (hours > 0) {
            return String.format("%dh %dm", hours, minutes);
        } else if (minutes > 0) {
            return String.format("%dm %ds", minutes, secs);
        } else {
            return String.format("%ds", secs);
        }
    }
}
package com.timetracker.repository;

import com.timetracker.entity.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {

    Optional<TimeEntry> findFirstByIsRunningTrueOrderByStartTimeDesc();

    List<TimeEntry> findByStartTimeBetweenOrderByStartTimeAsc(LocalDateTime start, LocalDateTime end);

    List<TimeEntry> findByProjectIdOrderByStartTimeDesc(Long projectId);

    @Query("SELECT t FROM TimeEntry t WHERE t.startTime >= :start AND t.startTime < :end ORDER BY t.startTime ASC")
    List<TimeEntry> findTodayEntries(LocalDateTime start, LocalDateTime end);
}
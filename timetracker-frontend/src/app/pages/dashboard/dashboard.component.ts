import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectService } from '../../services/project.service';
import { TimeEntryService } from '../../services/time-entry.service';
import { StatsService } from '../../services/stats.service';
import { Project } from '../../models/project.model';
import { CurrentTimer } from '../../models/time-entry.model';
import { Stats } from '../../models/stats.model';
import { TimerComponent } from '../../components/timer/timer.component';
import { ProjectListComponent } from '../../components/project-list/project-list.component';
import { StatsPanelComponent } from '../../components/stats-panel/stats-panel.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, TimerComponent, ProjectListComponent, StatsPanelComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  projects: Project[] = [];
  currentTimer: CurrentTimer = { isRunning: false };
  stats: Stats | null = null;

  constructor(
    private projectService: ProjectService,
    private timeEntryService: TimeEntryService,
    private statsService: StatsService
  ) {}

  ngOnInit() {
    this.loadProjects();
    this.loadCurrentTimer();
    this.loadStats();
  }

  loadProjects() {
    this.projectService.getAll().subscribe({
      next: (projects) => this.projects = projects,
      error: (err) => console.error('Erreur chargement projets:', err)
    });
  }

  loadCurrentTimer() {
    this.timeEntryService.getCurrentTimer().subscribe({
      next: (timer) => this.currentTimer = timer,
      error: (err) => console.error('Erreur chargement timer:', err)
    });
  }

  loadStats() {
    this.statsService.getTodayStats().subscribe({
      next: (stats) => this.stats = stats,
      error: (err) => console.error('Erreur chargement stats:', err)
    });
  }

  onTimerStarted() {
    this.loadCurrentTimer();
    this.loadStats();
  }

  onTimerStopped() {
    this.loadCurrentTimer();
    this.loadStats();
  }

  onProjectSelected(project: Project) {
    if (project.id) {
      this.timeEntryService.startTimer(project.id).subscribe({
        next: () => this.onTimerStarted(),
        error: (err) => console.error('Erreur démarrage timer:', err)
      });
    }
  }
}

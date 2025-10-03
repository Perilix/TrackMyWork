import { Component, Input, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TimeEntryService } from '../../services/time-entry.service';
import { CurrentTimer } from '../../models/time-entry.model';

@Component({
  selector: 'app-timer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.scss']
})
export class TimerComponent implements OnInit, OnDestroy {
  @Input() currentTimer: CurrentTimer = { isRunning: false };
  @Output() timerStopped = new EventEmitter<void>();

  displayTime: string = '00:00:00';
  private intervalId: any;

  constructor(private timeEntryService: TimeEntryService) {}

  ngOnInit() {
    if (this.currentTimer.isRunning) {
      this.startDisplayTimer();
    }
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  ngOnChanges() {
    if (this.currentTimer.isRunning) {
      this.startDisplayTimer();
    } else {
      this.stopDisplayTimer();
      this.displayTime = '00:00:00';
    }
  }

  startDisplayTimer() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }

    this.updateDisplayTime();
    this.intervalId = setInterval(() => {
      this.updateDisplayTime();
    }, 1000);
  }

  stopDisplayTimer() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
      this.intervalId = null;
    }
  }

  updateDisplayTime() {
    if (this.currentTimer.isRunning && this.currentTimer.startTime) {
      const start = new Date(this.currentTimer.startTime).getTime();
      const now = new Date().getTime();
      const diff = Math.floor((now - start) / 1000);

      const hours = Math.floor(diff / 3600);
      const minutes = Math.floor((diff % 3600) / 60);
      const seconds = diff % 60;

      this.displayTime = `${this.pad(hours)}:${this.pad(minutes)}:${this.pad(seconds)}`;
    }
  }

  pad(num: number): string {
    return num.toString().padStart(2, '0');
  }

  stopTimer() {
    this.timeEntryService.stopTimer().subscribe({
      next: () => {
        this.stopDisplayTimer();
        this.timerStopped.emit();
      },
      error: (err) => console.error('Erreur arrêt timer:', err)
    });
  }
}

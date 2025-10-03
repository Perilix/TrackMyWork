import { Project } from './project.model';

export interface TimeEntry {
  id?: number;
  project: Project;
  startTime: string;
  endTime?: string;
  description?: string;
  isRunning: boolean;
  duration?: number;
  durationFormatted?: string;
}

export interface CurrentTimer {
  isRunning: boolean;
  id?: number;
  project?: Project;
  startTime?: string;
  description?: string;
  currentDuration?: number;
  currentDurationFormatted?: string;
}

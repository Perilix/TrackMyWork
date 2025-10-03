import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { TimeEntry, CurrentTimer } from '../models/time-entry.model';

@Injectable({
  providedIn: 'root'
})
export class TimeEntryService {
  private apiUrl = `${environment.apiUrl}/time-entries`;

  constructor(private http: HttpClient) {}

  getTodayEntries(): Observable<TimeEntry[]> {
    return this.http.get<TimeEntry[]>(`${this.apiUrl}/today`);
  }

  getCurrentTimer(): Observable<CurrentTimer> {
    return this.http.get<CurrentTimer>(`${this.apiUrl}/current`);
  }

  startTimer(projectId: number, description?: string): Observable<TimeEntry> {
    return this.http.post<TimeEntry>(`${this.apiUrl}/start`, {
      projectId,
      description
    });
  }

  stopTimer(): Observable<TimeEntry> {
    return this.http.post<TimeEntry>(`${this.apiUrl}/stop`, {});
  }
}

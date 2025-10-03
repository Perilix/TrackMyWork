import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Stats } from '../../models/stats.model';

@Component({
  selector: 'app-stats-panel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stats-panel.component.html',
  styleUrls: ['./stats-panel.component.scss']
})
export class StatsPanelComponent {
  @Input() stats: Stats | null = null;

  getEfficiencyColor(): string {
    if (!this.stats) return '#666';
    const efficiency = this.stats.efficiency;
    if (efficiency >= 75) return '#00ff88';
    if (efficiency >= 50) return '#00d4ff';
    return '#ff0080';
  }
}

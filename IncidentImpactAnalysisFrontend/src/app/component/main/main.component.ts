import { Component } from '@angular/core';
import { ApiService } from '../../service/api.service';
import { IncidentImpactAnalysis } from '../../model/incident-impact-analysis';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  incidents:IncidentImpactAnalysis[] = [];
  currentPage:number=0;
  totalPages:number=-1;
  totalElements:number=0;

  constructor(private service:ApiService) { 
    this.service.getPage(1).subscribe(data=>{
      this.incidents = data.content;
      this.currentPage = data.currentPage;
      this.totalPages = data.totalPages;
      this.totalElements = data.totalElements;
    })
  }

  ngOnInit(): void {
  }

  changePage(page:number){
    this.service.getPage(page).subscribe(data=>{
      this.incidents = data.content;
      this.currentPage = data.currentPage;
    })
  }

  getPaginationRange(): any[] {
    const range = [];
    const maxButtons = 3; // Show 2-3 additional buttons
    const start = Math.max(1, this.currentPage - maxButtons);
    const end = Math.min(this.totalPages, this.currentPage + maxButtons);
  
    if (start > 1) range.push(1); // Always show the first page
    if (start > 2) range.push('...'); // Ellipsis for skipped pages
  
    for (let i = start; i <= end; i++) {
      range.push(i); // Pages near the current page
    }
  
    if (end < this.totalPages - 1) range.push('...'); // Ellipsis for skipped pages
    if (end < this.totalPages) range.push(this.totalPages); // Always show the last page
  
    return range;
  }

}

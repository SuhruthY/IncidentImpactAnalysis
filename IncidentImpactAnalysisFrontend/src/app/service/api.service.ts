import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse, IncidentImpactAnalysis } from '../model/incident-impact-analysis';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  url = 'http://localhost:8080/incident-impact-analysis?page=';

  constructor(private http:HttpClient) { }

  getPage(page: number): Observable<ApiResponse>{ 
    return this.http.get<ApiResponse>(this.url + page); 
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RepportService {

  private urlRisk = "http://localhost:8082/api/risk"

  constructor(private http: HttpClient) { }

  getRiskPatient(id:number):Observable<any>{
    return this.http.get(`${this.urlRisk}/${id}`)
  }
}

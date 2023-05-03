import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PatientDto } from '../model/modelDto/patientDto.model';
import { Patient } from '../model/patient.model';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private urlPatient = "http://localhost:8081/api/patients";

  constructor(private http: HttpClient) { }

  listpatient(): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.urlPatient}/list`);
  }

  patientById(id:number):Observable<Patient> {
    return this.http.get<Patient>(`${this.urlPatient}/detail/${id}`);
  }

  deletePatient(id:number):Observable<Object>{
    return this.http.delete(`${this.urlPatient}/delete/${id}`);
  }

  addPatient(patientDto: PatientDto) : Observable<Object>{
    return this.http.post(`${this.urlPatient}/add`,patientDto);

  }

  updatepatient(id:number,patient:Patient):Observable<Object>{
    return this.http.put(`${this.urlPatient}/update/${id}`,patient,{responseType:'text' as 'json'})
  }
}




import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { noteDto } from '../model/modelDto/noteDto.model';
@Injectable({
  providedIn: 'root'
})
export class NotesService {

  constructor(private http:HttpClient) { }

  private urlNotes = "http://localhost:8083/api/notes"

  addNoteByPatientId(noteDto:noteDto):Observable<Object>{
    return this.http.post(`${this.urlNotes}/add`,noteDto)
  }

  //supprimer la note pour un patient
  deleteNoteByPatientId(noteId:number):Observable<Object>{
    return this.http.delete(`${this.urlNotes}/delete/${noteId}`)
  }

   getNotesByPatientId(patientId:number):Observable<Object>{
    return this.http.get(`${this.urlNotes}/list/patient/${patientId}`)
  }
}

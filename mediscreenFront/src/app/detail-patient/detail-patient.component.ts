import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { noteDto } from '../model/modelDto/noteDto.model';
import { Patient } from '../model/patient.model';
import { Risk } from '../model/risk.model';
import { NotesService } from '../service/notes.service';
import { PatientService } from '../service/patient.service';
import { RepportService } from '../service/repport.service';


@Component({
  selector: 'app-detail-patient',
  templateUrl: './detail-patient.component.html',
  styleUrls: ['./detail-patient.component.scss']
})
export class DetailPatientComponent {

  id:number;
  patient:Patient = new Patient()
  listNotes:any
  note:noteDto = new noteDto()
  noteInpute:String
  risk=new Risk()
  showMessageSuccess=false
  showMessageError=false
  message:string

  constructor(private patientService: PatientService,
              private repportService : RepportService, 
              private route: ActivatedRoute, 
              private noteService: NotesService, 
              private router: Router){}

  ngOnInit() {
    this.id=this.route.snapshot.params['id']
    this.patientService.patientById(this.id).subscribe(data => {
      this.patient = data
    })

    this.noteService.getNotesByPatientId(this.id).subscribe(data => {
      this.listNotes = data
      
    })
  }

  deleteNote(noteId:number){ 
    this.noteService.deleteNoteByPatientId(noteId).subscribe(data =>{
      this.showMessageSuccess=true
      this.message = "Note was successfully deleted" 
      this.getNotesDetailPatient()
    },error => {
      this.showMessageError = true
      this.message = "Note could not be deleted"
    });
  }

  getNotesDetailPatient(){
    this.noteService.getNotesByPatientId(this.patient.id).subscribe(data =>{
      console.log(data);
      
      this.listNotes = data
    })
  }

  saveNote(){ 
    this.note.note = this.noteInpute
    this.note.patientId = this.patient.id
    if(this.note.note != null)
    this.noteService.addNoteByPatientId(this.note).subscribe(data =>{  
      this.showMessageSuccess=true      
      this.message = "Note was successfully added" 
      this.getNotesDetailPatient()
    },error =>{
      this.showMessageError=true
      this.message = "Note could not be added"
    });
  }

  getRisk(){
    this.repportService.getRiskPatient(this.patient.id).subscribe(data =>{
      this.risk = data
    })
  }
}

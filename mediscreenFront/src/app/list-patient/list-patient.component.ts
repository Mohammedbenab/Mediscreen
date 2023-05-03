import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { PatientService } from '../service/patient.service';
import { Router } from '@angular/router';
import { Patient } from '../model/patient.model';
import { Subscription } from 'rxjs';
import { RepportService } from '../service/repport.service';

@Component({
  selector: 'app-list-patient',
  templateUrl: './list-patient.component.html',
  styleUrls: ['./list-patient.component.scss']
})


export class ListPatientComponent implements OnInit,OnChanges{


  
   patients: Patient[];
   showMessageSuccess=false
   showMessageError=false
   message:string
  
   
  constructor(private patientService: PatientService, private router: Router, private repportService:RepportService){
    this.getPatients()
  }
  
  ngOnChanges(change:SimpleChanges):void{

  }
  
  ngOnInit() {
    this.getPatients()
  }

    getPatients(){
    this.patientService.listpatient().subscribe(data=>{
      console.log(data)
      this.patients = data
    })
  }

  detailPatient(id: number){
    this.router.navigate(['detail-patient', id]);
  }

  updatePatient(id: number){
    this.router.navigate(['update-patient', id]);
  }

  deletePatient(id: number){
    this.patientService.deletePatient(id).subscribe( data => {
      this.showMessageSuccess=true
      this.message="Patient was successefully deleted"
      this.getPatients();
      console.log(data);
      // alert(data)
    },error=>{
      console.log(error);
      
      this.showMessageError=true
      this.message = "Patient could not be deleted"
    });
    
    // window.location.reload()
  }

  getPatientRisk(){
    let id = 3
    this.repportService.getRiskPatient(id).subscribe(data => {
      console.log(data)
    })
  }

}

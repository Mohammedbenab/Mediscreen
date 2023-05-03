import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PatientDto } from '../model/modelDto/patientDto.model';
import { PatientService } from '../service/patient.service';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.scss']
})
export class AddPatientComponent implements OnInit{

  patientDto: PatientDto = new PatientDto();
  showMessageSuccess=false
  showMessageError=false
  message:string

  constructor(private patientService: PatientService, private router: Router){}

  ngOnInit(): void {
  }
  
savePatient(){
    this.patientService.addPatient(this.patientDto).subscribe( data =>{
      // this.goToPatientList();
      this.showMessageSuccess=true
      this.message = "Patient was successfully added"
      // this.router.navigate(['/services'])
    },
    error => {
      this.showMessageError=true
      this.message="Patient could not be added"
    });
  }

  goToPatientList(){
    this.router.navigate(['/services']);
  }
  
  onSubmit(){
    console.log(this.patientDto);
    this.savePatient();
  }
}

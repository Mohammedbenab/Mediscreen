import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from '../model/patient.model';
import { PatientService } from '../service/patient.service';

@Component({
  selector: 'app-update-patient',
  templateUrl: './update-patient.component.html',
  styleUrls: ['./update-patient.component.scss']
})
export class UpdatePatientComponent implements OnInit{

  id:number;
  patient:Patient = new Patient()
  showMessageSuccess=false
  showMessageError=false
  message:string

  constructor(private patientService: PatientService, private route: ActivatedRoute,
    private router: Router){}

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id']
    this.patientService.patientById(this.id).subscribe(data => {
      this.patient = data
    })
  }

  onSubmit(){
    this.patientService.updatepatient(this.id,this.patient).subscribe(data =>{
      // this.goToPatientList()
      this.showMessageSuccess=true
      this.message="Patient was successfully updated"
    },error => {
      this.showMessageError=true
      this.message="Patient could not be updated"
    });
  }

  goToPatientList(){
    this.router.navigate(['/services']);
  }

}

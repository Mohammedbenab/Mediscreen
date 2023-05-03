import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AddPatientComponent } from './add-patient/add-patient.component';
import { DetailPatientComponent } from './detail-patient/detail-patient.component';
import { HomeComponent } from './home/home.component';
import { ListPatientComponent } from './list-patient/list-patient.component';
import { ServicesComponent } from './services/services.component';
import { UpdatePatientComponent } from './update-patient/update-patient.component';

const routes: Routes = [
  // {path:'',redirectTo:'patients', pathMatch: 'full'},
  // {path:'patients',component:ListPatientComponent},
  {path:'add-patient',component:AddPatientComponent},
  {path:'update-patient/:id',component:UpdatePatientComponent},
  {path:'detail-patient/:id',component:DetailPatientComponent},
  {path:'',redirectTo:'home', pathMatch: 'full'},
  {path:'home',component:HomeComponent},
  {path:'services',component:ServicesComponent},
  {path:'about',component:AboutComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

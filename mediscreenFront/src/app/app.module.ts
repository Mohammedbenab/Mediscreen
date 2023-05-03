import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatMenuModule} from '@angular/material/menu';
import { HttpClientModule } from '@angular/common/http';
import { AddPatientComponent } from './add-patient/add-patient.component';
import { ListPatientComponent } from './list-patient/list-patient.component';
import { UpdatePatientComponent } from './update-patient/update-patient.component';
import { DetailPatientComponent } from './detail-patient/detail-patient.component';
import { FormsModule} from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { ServicesComponent } from './services/services.component';
import { AboutComponent } from './about/about.component';
import { BlogComponent } from './blog/blog.component';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    AddPatientComponent,
    ListPatientComponent,
    UpdatePatientComponent,
    DetailPatientComponent,
    HomeComponent,
    ServicesComponent,
    AboutComponent,
    BlogComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatMenuModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

package com.mediscreen.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mediscreen.patientservice.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	Patient findByPrenomAndNom(String prenom, String nom);

}

package com.medicreen.riskservice.services;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medicreen.riskservice.entities.Note;
import com.medicreen.riskservice.entities.Patient;
import com.medicreen.riskservice.entities.ResultPatientRisk;
import com.medicreen.riskservice.proxy.NotesProxy;
import com.medicreen.riskservice.proxy.PatientsProxy;


@Service
public class CalculRisk {
	
	@Autowired
	private PatientsProxy patientsProxy;
	
	@Autowired
	private NotesProxy notesProxy;
	
	
	@Value(value = "${terme}")
	private String[] listTerme;
	
	/**
	 * Cette méthode permet de diagnostiquer le risque de diabete du patient 
	 * @param patientId
	 * @return un string resultat, qui permet de fixer le degré de gravité
	 * @throws Exception
	 */
	public ResultPatientRisk calculLevelRisk(Integer patientId) throws Exception {
		Patient patient = patientsProxy.getPatientById(patientId);
		int agePatient = this.calculAge(patient.getDateDeNaissance());
		String genre = patient.getGenre();
		String result = null;
		ResultPatientRisk riskPatient = new ResultPatientRisk();
		if(patient != null ) {
			riskPatient.setAge(agePatient);
			riskPatient.setNom(patient.getNom());
			riskPatient.setPrenom(patient.getPrenom());
			List<Note> noteListPatient = notesProxy.getAllNotesPatient(patient.getId());
			if (noteListPatient.size() > 0) {
				int nombreterme = this.nbrTerme(noteListPatient);
				if(nombreterme == 2 && agePatient > 30) {
					result = "Borderline";
				}else if((genre.equals("Male")&&agePatient < 30 && nombreterme == 3) || 
						(genre.equals("Female")&&agePatient < 30 && nombreterme == 4) || (agePatient > 30 && nombreterme == 6)) {
					result = "In Danger";
				}else if((genre.equals("Male")&&agePatient < 30 && nombreterme == 5) || 
						(genre.equals("Female")&&agePatient < 30 && nombreterme == 6) || (agePatient > 30 && nombreterme == 7)) {
					result="Early onset";
				}else
					result = "None";
			}else
				throw new Exception("Not list note patient found");
		}else {
			throw new Exception("Patient not found");
		}
		riskPatient.setResult(result);
		return riskPatient;
	}
	
	
	private String encodeValue(String value) {
		String result = "";
	    try {
			result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return result;
	}
	
	/**
	 * Cette methode permet de calculer le nombre de terme trouvé dans les notes du patient
	 * @param noteList
	 * @return un nombre de int de terme > ou = à 0 
	 */
	private int nbrTerme(List<Note> noteList){
		int nbrAnomalie = 0;
		for(int i = 0; i < noteList.size(); i++) { //liste des notes du patient
			Note note = noteList.get(i);
			String noteLowerCase = note.getNote().toLowerCase();
//			String normalizeNote = this.normalizeInput(noteLowerCase);
			String normalizeNote = encodeValue(noteLowerCase);
			for(String terme : listTerme) {
				String termeLowerCase = terme.toLowerCase();
				String termeNormalized = encodeValue(termeLowerCase);
				boolean result = noteLowerCase.contains(termeLowerCase);
				if (normalizeNote.contains(termeLowerCase) || normalizeNote.contains(termeNormalized)) {
					nbrAnomalie += 1;
				}
			}
		}
		return nbrAnomalie;
	}
	
	/**
	 * Cette methode permet de supprimer les accents sur les carctères
	 * @param input est la chaine de charactère dans laquelle nous allons supprimer tous les accents de chaque charactère
	 * @return la chaine de caractère sans accents
	 */
	private String normalizeInput(String input) {
	    return input == null ? null : Normalizer.normalize(input, Normalizer.Form.NFKD);
	}
	
	/**
	 * Cette méthode permet de calculer l'age du Patient
	 * @param dateNaissance est la date de naissance du patient
	 * @return un int qui represente l'age du patient
	 */
	private int calculAge(String dateNaissance) {
		LocalDate date1 = LocalDate.parse(dateNaissance);
		Period period = date1.until(LocalDate.now());
		int yearsBetween = period.getYears();
		return yearsBetween;	
	}
}

package com.mediscreen.noteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mediscreen.noteservice.entities.Note;

@Repository
public interface NotesRepository extends MongoRepository<Note, Integer>{

	List<Note> findAllByPatientId(int patientId);

}

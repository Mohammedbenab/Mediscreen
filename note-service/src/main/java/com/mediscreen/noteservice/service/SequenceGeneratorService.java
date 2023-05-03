package com.mediscreen.noteservice.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mediscreen.noteservice.entities.DatabaseSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;



@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOperations;
	 
	 public Integer generateSequence(String seqName) {
		 Query query = new Query(Criteria.where("id").is(seqName));
		    DatabaseSequence counter = mongoOperations.findAndModify((query),
		      new Update().inc("seq",1), options().returnNew(true).upsert(true),
		      DatabaseSequence.class);
		    return !Objects.isNull(counter) ? counter.getSeq() : 1;
		}
}

package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.CandidateEntity;
import com.example.jsptest.entities.CandidateLanguageEntity;

public interface CandidateLanguageRepository extends CrudRepository<CandidateLanguageEntity, Integer> {
	
	List<CandidateLanguageEntity> findByCandidate(CandidateEntity candidate);

}

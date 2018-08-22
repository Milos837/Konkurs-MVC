package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.CandidateEntity;
import com.example.jsptest.entities.EducationEntity;

public interface EducationRepository extends CrudRepository<EducationEntity, Integer> {
	
	List<EducationEntity> findByCandidate(CandidateEntity candidate);

}

package com.example.jsptest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.CandidateEntity;

public interface CandidateRepository extends CrudRepository<CandidateEntity, Integer> {

}

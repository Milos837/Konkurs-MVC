package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.RequirementsEntity;

public interface RequirementsRepository extends CrudRepository<RequirementsEntity, Integer> {
	
	void deleteByPosting(PostingEntity posting);
	
	List<RequirementsEntity> findByPosting(PostingEntity posting);

}

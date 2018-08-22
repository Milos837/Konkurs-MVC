package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.ResponsibilitiesEntity;

public interface ReponsibilitiesRepository extends CrudRepository<ResponsibilitiesEntity, Integer>{
	
	void deleteByPosting(PostingEntity posting);
	
	List<ResponsibilitiesEntity> findByPosting(PostingEntity posting); 

}

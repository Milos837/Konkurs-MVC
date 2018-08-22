package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.PostingEntity;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Integer> {
	
	List<ApplicationEntity> findByPosting(PostingEntity posting);

}

package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.OfferingEntity;
import com.example.jsptest.entities.PostingEntity;

public interface OfferingRepository extends CrudRepository<OfferingEntity, Integer> {
	
	void deleteByPosting(PostingEntity posting);
	
	List<OfferingEntity> findByPosting(PostingEntity posting);

}

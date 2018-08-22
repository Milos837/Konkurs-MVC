package com.example.jsptest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.AttachmentEntity;

public interface AttachmentRepository extends CrudRepository<AttachmentEntity, Integer> {
	
	AttachmentEntity findByApplication(ApplicationEntity application);
	
	Boolean existsByApplication(ApplicationEntity application);

}

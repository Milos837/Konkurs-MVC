package com.example.jsptest.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.CertificateEntity;

public interface CertificateRepository extends CrudRepository<CertificateEntity, Integer> {
	
	List<CertificateEntity> findByApplication(ApplicationEntity application);

}

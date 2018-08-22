package com.example.jsptest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.CitizenshipEntity;

public interface CitizenshipRepository extends CrudRepository<CitizenshipEntity, Integer> {

}

package com.example.jsptest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

}

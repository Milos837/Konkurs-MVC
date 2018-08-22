package com.example.jsptest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jsptest.entities.PostingEntity;

public interface PostingRepository extends CrudRepository<PostingEntity, Integer> {

}

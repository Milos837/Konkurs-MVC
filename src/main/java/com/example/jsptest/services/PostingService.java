package com.example.jsptest.services;

import com.example.jsptest.entities.PostingEntity;
import com.example.jsptest.entities.dto.PostingDto;

public interface PostingService {
	
	public PostingEntity save(PostingDto newPosting);
	
	public PostingEntity update(Integer postingId, PostingDto updatedPosting);
	
	public PostingEntity delete(Integer postingId);

}

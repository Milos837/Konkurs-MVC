package com.example.jsptest.services;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.dto.ApplicationDto;

public interface ApplicationService {
	
	public ApplicationEntity save(Integer postingId, ApplicationDto newApplication);
	
	public ApplicationEntity delete(Integer applicationId);

}

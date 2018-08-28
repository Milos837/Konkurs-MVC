package com.example.jsptest.services;

import java.util.List;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.LanguageEntity;
import com.example.jsptest.entities.dto.ApplicationDto;

public interface ApplicationService {
	
	public ApplicationEntity save(Integer postingId, ApplicationDto newApplication);
	
	public ApplicationEntity delete(Integer applicationId);
	
	public List<LanguageEntity> getLanguagesForApplication(Integer appId);

}

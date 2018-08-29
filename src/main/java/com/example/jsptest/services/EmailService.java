package com.example.jsptest.services;

import com.example.jsptest.entities.util.EmailObject;

public interface EmailService {
	
	public void newAppNotification(Integer appId);
	
	public Boolean sendSimpleEmail(EmailObject email);

}

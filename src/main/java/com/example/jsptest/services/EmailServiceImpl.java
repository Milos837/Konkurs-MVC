package com.example.jsptest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.util.EmailObject;
import com.example.jsptest.repositories.ApplicationRepository;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	public ApplicationRepository applicationRepository;

	@Override
	public void newAppNotification(Integer appId) {
		if (applicationRepository.existsById(appId)) {
			ApplicationEntity app = applicationRepository.findById(appId).get();

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo("brains.ednevnik@gmail.com");
			message.setSubject("New application");
			String text = "New application for " + app.getPosting().getName() + " from candidate "
					+ app.getCandidate().getFirstName() + " " + app.getCandidate().getLastName();
			message.setText(text);

			try {
				emailSender.send(message);
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	@Override
	public Boolean sendSimpleEmail(EmailObject email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		try {
			emailSender.send(message);
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

}

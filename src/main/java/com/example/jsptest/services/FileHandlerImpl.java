package com.example.jsptest.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.AttachmentEntity;
import com.example.jsptest.repositories.ApplicationRepository;
import com.example.jsptest.repositories.AttachmentRepository;

@Service
public class FileHandlerImpl implements FileHandler {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private AttachmentRepository attachmentRepository;

	public AttachmentEntity uploadCv(MultipartFile file, Integer appId) {

		try {

			byte[] bytes = file.getBytes();

			if (applicationRepository.existsById(appId)) {
				ApplicationEntity app = applicationRepository.findById(appId).get();
				AttachmentEntity cv = new AttachmentEntity();
				cv.setDeleted(false);
				cv.setApplication(app);
				cv.setCv(bytes);
				attachmentRepository.save(cv);

				return cv;
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		return null;
	}

	public AttachmentEntity uploadML(MultipartFile file, Integer appId) {
		try {

			byte[] bytes = file.getBytes();

			if (applicationRepository.existsById(appId)) {
				ApplicationEntity app = applicationRepository.findById(appId).get();
				if (attachmentRepository.existsByApplication(app)) {
					AttachmentEntity attachment = attachmentRepository.findByApplication(app);
					attachment.setMotivation(bytes);
					attachmentRepository.save(attachment);
					
					app.setHasMotivationalLetter(true);
					applicationRepository.save(app);

					return attachment;
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		return null;
	}
	
	public AttachmentEntity uploadCL(MultipartFile file, Integer appId) {
		try {

			byte[] bytes = file.getBytes();

			if (applicationRepository.existsById(appId)) {
				ApplicationEntity app = applicationRepository.findById(appId).get();
				if (attachmentRepository.existsByApplication(app)) {
					AttachmentEntity attachment = attachmentRepository.findByApplication(app);
					attachment.setCoverLetter(bytes);
					attachmentRepository.save(attachment);
					
					app.setHasCoverLetter(true);
					applicationRepository.save(app);

					return attachment;
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		return null;
	}

}

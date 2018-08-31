package com.example.jsptest.services;

import org.springframework.web.multipart.MultipartFile;

import com.example.jsptest.entities.AttachmentEntity;

public interface FileHandler {
	
	public AttachmentEntity uploadCv(MultipartFile file, Integer appId);

	public AttachmentEntity uploadML(MultipartFile file, Integer appId);
	
	public AttachmentEntity uploadCL(MultipartFile file, Integer appId);

}

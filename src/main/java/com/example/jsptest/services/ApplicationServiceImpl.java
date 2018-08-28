package com.example.jsptest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jsptest.entities.ApplicationEntity;
import com.example.jsptest.entities.CandidateEntity;
import com.example.jsptest.entities.CandidateLanguageEntity;
import com.example.jsptest.entities.CertificateEntity;
import com.example.jsptest.entities.CitizenshipEntity;
import com.example.jsptest.entities.EducationEntity;
import com.example.jsptest.entities.LanguageEntity;
import com.example.jsptest.entities.dto.ApplicationDto;
import com.example.jsptest.entities.dto.LanguageDto;
import com.example.jsptest.repositories.ApplicationRepository;
import com.example.jsptest.repositories.CandidateLanguageRepository;
import com.example.jsptest.repositories.CandidateRepository;
import com.example.jsptest.repositories.CertificateRepository;
import com.example.jsptest.repositories.CitizenshipRepository;
import com.example.jsptest.repositories.EducationRepository;
import com.example.jsptest.repositories.LanguageRepository;
import com.example.jsptest.repositories.PostingRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private CertificateRepository certificateRepository;
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private CandidateLanguageRepository candidateLanguageRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private CitizenshipRepository citizenshipRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public ApplicationEntity save(Integer postingId, ApplicationDto newApplication) {
		ApplicationEntity application = new ApplicationEntity();
		CandidateEntity candidate = new CandidateEntity();
		
		candidate.setDeleted(false);
		candidate.setFirstName(newApplication.getFirstName());
		candidate.setFirstName(newApplication.getFirstName());
		candidate.setLastName(newApplication.getLastName());
		candidate.setGender(newApplication.getGender());
		candidate.setEmail(newApplication.getEmail());
		candidate.setIdNumber(newApplication.getIdNumber());
		candidate.setSsn(newApplication.getSsn());
		
		CitizenshipEntity citizenship = citizenshipRepository.findById(newApplication.getCitizenshipId()).get();
		
		candidate.setCitizenship(citizenship);
		candidate.setEducationLevel(newApplication.getEducationLevel());
		candidate.setNote(newApplication.getCandidateNote());
		candidate = candidateRepository.save(candidate);
		
		application.setDeleted(false);
		application.setCandidate(candidate);
		application.setNote(newApplication.getApplicationNote());
		application.setPosting(postingRepository.findById(postingId).get());
		application = applicationRepository.save(application);
		
		if (newApplication.getCertifications() != null) {
			for (CertificateEntity certification : newApplication.getCertifications()) {
				CertificateEntity cert = new CertificateEntity();
				cert.setCertificate(certification.getCertificate());
				cert.setNote(certification.getNote());
				cert.setDeleted(false);
				cert.setApplication(application);
				certificateRepository.save(cert);
			}
		}
		
		if (newApplication.getLanguage() != null) {
			for (LanguageDto language : newApplication.getLanguage()) {
				if (languageRepository.existsById(language.getLanguageId())) {
					LanguageEntity lang = languageRepository.findById(language.getLanguageId()).get();
					
					CandidateLanguageEntity canLang = new CandidateLanguageEntity();
					canLang.setDeleted(false);
					canLang.setCandidate(candidate);
					canLang.setLanguage(lang);
					canLang.setNote(language.getNote());
					candidateLanguageRepository.save(canLang);
				}
			}
		}
		
		if (newApplication.getEducation() != null) {
			for (EducationEntity education : newApplication.getEducation()) {
				EducationEntity edu = new EducationEntity();
				edu.setDeleted(false);
				edu.setSchoolName(education.getSchoolName());
				edu.setCandidate(candidate);
				edu.setNote(education.getNote());
				educationRepository.save(edu);
			}
		}
		
		emailService.newAppNotification(application.getId());
		
		return application;
	}
	
	@Override
	public ApplicationEntity delete(Integer applicationId) {
		if (applicationRepository.existsById(applicationId) && !applicationRepository.findById(applicationId).get().getDeleted()) {
			ApplicationEntity application = applicationRepository.findById(applicationId).get();
			application.setDeleted(true);
			applicationRepository.save(application);
			return application;
		}
		return null;
	}
	
	@Override
	public List<LanguageEntity> getLanguagesForApplication(Integer appId) {
		if (applicationRepository.existsById(appId)) {
			ApplicationEntity application = applicationRepository.findById(appId).get();
			List<LanguageEntity> languages = ((List<CandidateLanguageEntity>) candidateLanguageRepository
					.findByCandidate(application.getCandidate())).stream().map(c -> c.getLanguage())
							.collect(Collectors.toList());
			return languages;
		}
		return null;
	}

}

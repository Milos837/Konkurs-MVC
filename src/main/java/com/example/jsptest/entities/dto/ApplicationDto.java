package com.example.jsptest.entities.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.jsptest.entities.CertificateEntity;
import com.example.jsptest.entities.EducationEntity;
import com.example.jsptest.entities.enums.EEducationLevel;
import com.example.jsptest.entities.enums.EGender;

public class ApplicationDto {

	@NotNull(message = "First name must not be null.")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "First name can contain only uppercase and lowercase letters.")
	private String firstName;

	@NotNull(message = "Last name must not be null")
	@Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters.")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last name can contain only uppercase and lowercase letters.")
	private String lastName;

	private EGender gender;

	@NotNull(message = "Email must not be null.")
	@Email(message = "Email not formated correctly.")
	private String email;

	@NotNull(message = "ID number must not be null.")
	@Size(min = 6, max = 6, message = "ID number must have exactly {min} digits.")
	@Pattern(regexp = "^[1-9]*$", message = "ID number can contain only digits.")
	private String idNumber;

	@NotNull(message = "SSN must not be null.")
	@Size(min = 11, max = 11, message = "SSN must have exactly {min} digits.")
	@Pattern(regexp = "^[1-9]*$", message = "SSN can contain only digits.")
	private String ssn;

	private Integer citizenshipId;

	private List<LanguageDto> language;

	private EEducationLevel educationLevel;

	private List<EducationEntity> education;

	private String candidateNote;

	private List<CertificateEntity> certifications;

	private String applicationNote;

	public ApplicationDto() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Integer getCitizenshipId() {
		return citizenshipId;
	}

	public void setCitizenshipId(Integer citizenshipId) {
		this.citizenshipId = citizenshipId;
	}

	public List<LanguageDto> getLanguage() {
		return language;
	}

	public void setLanguage(List<LanguageDto> language) {
		this.language = language;
	}

	public List<EducationEntity> getEducation() {
		return education;
	}

	public void setEducation(List<EducationEntity> education) {
		this.education = education;
	}

	public String getCandidateNote() {
		return candidateNote;
	}

	public void setCandidateNote(String candidateNote) {
		this.candidateNote = candidateNote;
	}

	public String getApplicationNote() {
		return applicationNote;
	}

	public void setApplicationNote(String applicationNote) {
		this.applicationNote = applicationNote;
	}

	public EEducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EEducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public List<CertificateEntity> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<CertificateEntity> certifications) {
		this.certifications = certifications;
	}

}

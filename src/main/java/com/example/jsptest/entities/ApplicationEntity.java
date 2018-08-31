package com.example.jsptest.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "applications")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApplicationEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate")
	private CandidateEntity candidate;
	
	@OneToMany(mappedBy = "application", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<CertificateEntity> certifications;
	
	@Column
	private String note;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private AttachmentEntity attachment;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "posting")
	private PostingEntity posting;
	
	@Column
	private Boolean hasMotivationalLetter;
	
	@Column
	private Boolean hasCoverLetter;
	
	@Column
	private Boolean deleted;
	
	@Version
	private Integer version;

	public ApplicationEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CandidateEntity getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateEntity candidate) {
		this.candidate = candidate;
	}

	public List<CertificateEntity> getCertifications() {
		return certifications;
	}

	public void setCertifications(List<CertificateEntity> certifications) {
		this.certifications = certifications;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AttachmentEntity getAttachment() {
		return attachment;
	}

	public void setAttachment(AttachmentEntity attachment) {
		this.attachment = attachment;
	}

	public PostingEntity getPosting() {
		return posting;
	}

	public void setPosting(PostingEntity posting) {
		this.posting = posting;
	}

	public Boolean getHasMotivationalLetter() {
		return hasMotivationalLetter;
	}

	public void setHasMotivationalLetter(Boolean hasMotivationalLetter) {
		this.hasMotivationalLetter = hasMotivationalLetter;
	}

	public Boolean getHasCoverLetter() {
		return hasCoverLetter;
	}

	public void setHasCoverLetter(Boolean hasCoverLetter) {
		this.hasCoverLetter = hasCoverLetter;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	

}

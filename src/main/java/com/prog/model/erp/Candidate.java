package com.prog.model.erp;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candidateuid;
   ; // Reference to Job ID  title fetch

    private String name;
    private String jobuid;
    private String contact;
    private String email;
    private String currentAddress;
    private String highestQualification; // Bachelor's, Master's, etc.
    private String institution;
    private String yearOfGraduation;
    private String resumeName; // New field for storing the document name
    private String totalExperience; // Fresher, 1-2 years, etc.
    private String previousEmployer;
    private String role;
    private String durationOfEmployment;
    @Lob
    private byte[] resume; // Path or URL
    private String status; // Applied, Shortlisted, Rejected, Selected
    private String referencedetails; // Contact Name and Number
    private String coverLetter;

    
    
    
    public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCandidateuid() {
		return candidateuid;
	}
	public void setCandidateuid(String candidateuid) {
		this.candidateuid = candidateuid;
	}
	
	public String getJobuid() {
		return jobuid;
	}
	public void setJobuid(String jobuid) {
		this.jobuid = jobuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getYearOfGraduation() {
		return yearOfGraduation;
	}
	public void setYearOfGraduation(String yearOfGraduation) {
		this.yearOfGraduation = yearOfGraduation;
	}
	public String getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}
	public String getPreviousEmployer() {
		return previousEmployer;
	}
	public void setPreviousEmployer(String previousEmployer) {
		this.previousEmployer = previousEmployer;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDurationOfEmployment() {
		return durationOfEmployment;
	}
	public void setDurationOfEmployment(String durationOfEmployment) {
		this.durationOfEmployment = durationOfEmployment;
	}
	
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	
	public String getReferencedetails() {
		return referencedetails;
	}
	public void setReferencedetails(String referencedetails) {
		this.referencedetails = referencedetails;
	}

    // Getters and Setters
    
 
    // (Generated methods omitted for brevity)
    
}
package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String enrollmentuid;
    private String employeeuid;
    private String trainingUid;
    private LocalDate registrationDate;
    private String enrollmentStatus;
    private LocalDate completionDate;
    private String progressDescription;
    private String trainerFeedback;
    private String employeeFeedback;
    private String trainingContentFeedback;
    private String qualityFeedback;
    private Integer overallRating;
    private String additionalInfo;
//    @Lob
//    private byte[] certificate;
//    @Lob
//    private byte[] attachments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getEnrollmentuid() {
		return enrollmentuid;
	}

	public void setEnrollmentuid(String enrollmentuid) {
		this.enrollmentuid = enrollmentuid;
	}

	
	

	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}

	public String getTrainingUid() {
		return trainingUid;
	}

	public void setTrainingUid(String trainingUid) {
		this.trainingUid = trainingUid;
	}

	

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public String getProgressDescription() {
		return progressDescription;
	}

	public void setProgressDescription(String progressDescription) {
		this.progressDescription = progressDescription;
	}

	public String getTrainerFeedback() {
		return trainerFeedback;
	}

	public void setTrainerFeedback(String trainerFeedback) {
		this.trainerFeedback = trainerFeedback;
	}

	public String getEmployeeFeedback() {
		return employeeFeedback;
	}

	public void setEmployeeFeedback(String employeeFeedback) {
		this.employeeFeedback = employeeFeedback;
	}

	public String getTrainingContentFeedback() {
		return trainingContentFeedback;
	}

	public void setTrainingContentFeedback(String trainingContentFeedback) {
		this.trainingContentFeedback = trainingContentFeedback;
	}

	public String getQualityFeedback() {
		return qualityFeedback;
	}

	public void setQualityFeedback(String qualityFeedback) {
		this.qualityFeedback = qualityFeedback;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

//	public byte[] getCertificate() {
//		return certificate;
//	}
//
//	public void setCertificate(byte[] certificate) {
//		this.certificate = certificate;
//	}
//
//	public byte[] getAttachments() {
//		return attachments;
//	}
//
//	public void setAttachments(byte[] attachments) {
//		this.attachments = attachments;
//	}

    // Getters and Setters (Auto-generate using your IDE)

}

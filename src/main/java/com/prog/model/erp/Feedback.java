package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeUID; // Auto-incremented ID for the employee

    private String feedbackuid;
    private String feedbackType;

    @Column(length = 1000)
    private String feedbackDescription;

    private LocalDate feedbackDate;

    @Column(length = 1000)
    private String actionableSuggestion;

    @Lob
    private byte[] attachment;

    @Column(length = 255) // Limit filename length to 255 characters
    private String attachmentName;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getEmployeeUID() {
		return employeeUID;
	}

	public void setEmployeeUID(String employeeUID) {
		this.employeeUID = employeeUID;
	}

	public String getFeedbackuid() {
		return feedbackuid;
	}

	public void setFeedbackuid(String feedbackuid) {
		this.feedbackuid = feedbackuid;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getFeedbackDescription() {
		return feedbackDescription;
	}

	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}

	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getActionableSuggestion() {
		return actionableSuggestion;
	}

	public void setActionableSuggestion(String actionableSuggestion) {
		this.actionableSuggestion = actionableSuggestion;
	}



	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

}
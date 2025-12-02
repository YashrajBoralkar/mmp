package com.prog.model.erp;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class InterviewSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Candidate Information
    private String candidateuid; //name,resume
    private String interviewschuid;
    // Interview Details
    private String jobuid; //description , title 
    private String interviewDate;
    private String interviewTime;
    private String modeOfInterview; // In-Person or Virtual
    private String meetingLink; // For Virtual mode
    private String venueAddress; // For In-Person mode
    // Panel Members
    private String panelMembers; // Comma-separated names
    private String roles; // Optional roles
    // Feedback
    private String feedbackSummary;
    private String overallRating; // Excellent, Good, etc.
    private String outcome; // Passed, Failed, On Hold
    private LocalDate nextInterviewDate;
    private LocalTime nextInterviewTime;
    private String nextPanelDetails;
    private String finalDecision; // Shortlisted, Rejected, Selected
    private String rescheduledDetails;

    
    // Follow-Up Details
    private boolean nextInterviewScheduled;
    public boolean isNextInterviewScheduled() {
		return nextInterviewScheduled;
	}
    
	public String getInterviewschuid() {
		return interviewschuid;
	}

	public void setInterviewschuid(String interviewschuid) {
		this.interviewschuid = interviewschuid;
	}

	public void setNextInterviewScheduled(boolean nextInterviewScheduled) {
		this.nextInterviewScheduled = nextInterviewScheduled;
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
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getModeOfInterview() {
		return modeOfInterview;
	}
	public void setModeOfInterview(String modeOfInterview) {
		this.modeOfInterview = modeOfInterview;
	}
	public String getMeetingLink() {
		return meetingLink;
	}
	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}
	public String getVenueAddress() {
		return venueAddress;
	}
	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}
	public String getPanelMembers() {
		return panelMembers;
	}
	public void setPanelMembers(String panelMembers) {
		this.panelMembers = panelMembers;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getFeedbackSummary() {
		return feedbackSummary;
	}
	public void setFeedbackSummary(String feedbackSummary) {
		this.feedbackSummary = feedbackSummary;
	}
	public String getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public LocalDate getNextInterviewDate() {
		return nextInterviewDate;
	}
	public void setNextInterviewDate(LocalDate nextInterviewDate) {
		this.nextInterviewDate = nextInterviewDate;
	}
	public LocalTime getNextInterviewTime() {
		return nextInterviewTime;
	}
	public void setNextInterviewTime(LocalTime nextInterviewTime) {
		this.nextInterviewTime = nextInterviewTime;
	}
	public String getNextPanelDetails() {
		return nextPanelDetails;
	}
	public void setNextPanelDetails(String nextPanelDetails) {
		this.nextPanelDetails = nextPanelDetails;
	}
	public String getFinalDecision() {
		return finalDecision;
	}
	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}
	public String getRescheduledDetails() {
		return rescheduledDetails;
	}
	public void setRescheduledDetails(String rescheduledDetails) {
		this.rescheduledDetails = rescheduledDetails;
	}
	

    // Getters and Setters
    
    
    // (Omitted for brevity, auto-generate them in your IDE)
}


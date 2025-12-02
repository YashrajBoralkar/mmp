package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainingUid;
    private String employeeUID; 
    private String trainingtitle;
    private String trainerName;
    private String description;
    private String department;
    private String internalExternal; // "Internal" or "External"
    private String trainerEmail;
    private String phoneNumber;
    private String trainingMode; // "Online" or "Offline"
    @Column(name = "location", length = 1000)
    private String location; // URL for online mode
    private String venueAddress; // Address for offline mode
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String duration; // Calculated duration in "X hours Y minutes"
	
    
    // Getters and Setters
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrainingUid() {
		return trainingUid;
	}
	public void setTrainingUid(String trainingUid) {
		this.trainingUid = trainingUid;
	}
	
	public String getEmployeeUID() {
		return employeeUID;
	}
	public void setEmployeeUID(String employeeUID) {
		this.employeeUID = employeeUID;
	}
	public String getTrainingtitle() {
		return trainingtitle;
	}
	public void setTrainingtitle(String trainingtitle) {
		this.trainingtitle = trainingtitle;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getInternalExternal() {
		return internalExternal;
	}
	public void setInternalExternal(String internalExternal) {
		this.internalExternal = internalExternal;
	}
	public String getTrainerEmail() {
		return trainerEmail;
	}
	public void setTrainerEmail(String trainerEmail) {
		this.trainerEmail = trainerEmail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTrainingMode() {
		return trainingMode;
	}
	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVenueAddress() {
		return venueAddress;
	}
	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
   
    
}

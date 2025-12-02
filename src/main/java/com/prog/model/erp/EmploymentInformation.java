package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class EmploymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeuid; // Employee ID (manually entered or auto-generated)
    private String empinfouid;
    private String designation;
    private String reportingManager;
    private String employmentType; // Permanent/Contract/Intern/Freelancer
    private String workLocation;
    private String officeAddress;
    private String remoteLocation;
    private String dateOfJoining;
    private String probationEndDate;
    private String status; // Active/Inactive
    private String shiftDetails; // e.g., General/US Shiftinfo/EMEA Shiftinfo
    private String workHours; // e.g., 8 hours, 9 hours

    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmpinfouid() {
		return empinfouid;
	}

	public void setEmpinfouid(String empinfouid) {
		this.empinfouid = empinfouid;
	}

	

	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}

	

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getRemoteLocation() {
        return remoteLocation;
    }

    public void setRemoteLocation(String remoteLocation) {
        this.remoteLocation = remoteLocation;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getProbationEndDate() {
        return probationEndDate;
    }

    public void setProbationEndDate(String date) {
        this.probationEndDate = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShiftDetails() {
        return shiftDetails;
    }

    public void setShiftDetails(String shiftDetails) {
        this.shiftDetails = shiftDetails;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
    
    @PrePersist
    protected void onCreate() {
        this.insertdate = LocalDateTime.now().format(formatter);
        this.updatedate = LocalDateTime.now().format(formatter);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedate = LocalDateTime.now().format(formatter);
    }

	
}

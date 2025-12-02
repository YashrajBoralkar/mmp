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

public class Shiftinfo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String shiftinfouid;
	private String employeeuid;

    private String shiftName;
    private String startTime;
    private String endTime;
    private String shiftDuration;
    private String shiftType;
    private String shiftDate;
    private String shiftStatus;
    private String remarks;
    
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getShiftinfouid() {
		return shiftinfouid;
	}
	public void setShiftinfouid(String shiftinfouid) {
		this.shiftinfouid = shiftinfouid;
	}
	public String getEmployeeuid() {
		return employeeuid;
	}
	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
		public String getShiftDuration() {
		return shiftDuration;
	}
	public void setShiftDuration(String shiftDuration) {
		this.shiftDuration = shiftDuration;
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
	
	
	public String getShiftType() {
		return shiftType;
	}
	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
	public String getShiftDate() {
		return shiftDate;
	}
	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
	}
	public String getShiftStatus() {
		return shiftStatus;
	}
	public void setShiftStatus(String shiftStatus) {
		this.shiftStatus = shiftStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

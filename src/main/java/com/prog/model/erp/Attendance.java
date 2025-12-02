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
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use wrapper type Long for nullable support

    private String attendanceuid;
    private String employeeuid; // Auto-incremented ID for the employee

    private String attendanceDate; // Use camelCase for consistency
    private String checkInTime;
    private String checkOutTime;
    private String attendanceStatus;
    private String comments;
    private String breaks;
    private String breakStartTime;
    private String breakEndTime;
    private String totalWorkingTime;
    private String totalBreakTime;
    
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
    public String getAttendanceuid() {
		return attendanceuid;
	}

	public void setAttendanceuid(String attendanceuid) {
		this.attendanceuid = attendanceuid;
	}
	
	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}

	public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBreaks() {
        return breaks;
    }

    public void setBreaks(String breaks) {
        this.breaks = breaks;
    }

    public String getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(String breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public String getBreakEndTime() {
        return breakEndTime;
    }

    public void setBreakEndTime(String breakEndTime) {
        this.breakEndTime = breakEndTime;
    }

    public String getTotalWorkingTime() {
        return totalWorkingTime;
    }

    public void setTotalWorkingTime(String totalWorkingTime) {
        this.totalWorkingTime = totalWorkingTime;
    }

    public String getTotalBreakTime() {
        return totalBreakTime;
    }

    public void setTotalBreakTime(String totalBreakTime) {
        this.totalBreakTime = totalBreakTime;
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

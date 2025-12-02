package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goaluid;
    private String employeeUID; // Auto-incremented ID for the employee
    private String title; // Goal Title
    private String description; // Goal Description

    private LocalDate targetDate; // Target Date

    private String priority; // Priority (High, Medium, Low)
    private String status; // Status (e.g., Pending, Completed)
    private String regularUpdate;
    
	public Long getId() {
		return id;
	}
	
	
	public String getEmployeeUID() {
		return employeeUID;
	}


	public void setEmployeeUID(String employeeUID) {
		this.employeeUID = employeeUID;
	}


	public String getGoaluid() {
		return goaluid;
	}

	public void setGoaluid(String goaluid) {
		this.goaluid = goaluid;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegularUpdate() {
		return regularUpdate;
	}
	public void setRegularUpdate(String regularUpdate) {
		this.regularUpdate = regularUpdate;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
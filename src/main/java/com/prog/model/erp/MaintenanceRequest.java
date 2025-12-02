package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="maintenancerequest")
public class MaintenanceRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String maintenancerequestuid;
    private String equipmentmasteruid;

    private String maintenancetype;
    private String issuedescription;
    private String sparepartsrequired;
    private double estimatedrepaircost;

    private String estimatedcompletiondate;
    private String requestedstatus;
    private String employeeuid;
    private LocalDateTime insertdate;
   	private LocalDateTime updatedate;

   	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaintenancerequestuid() {
		return maintenancerequestuid;
	}
	public void setMaintenancerequestuid(String maintenancerequestuid) {
		this.maintenancerequestuid = maintenancerequestuid;
	}
	
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	
	public String getMaintenancetype() {
		return maintenancetype;
	}
	public void setMaintenancetype(String maintenancetype) {
		this.maintenancetype = maintenancetype;
	}
	public String getIssuedescription() {
		return issuedescription;
	}
	public void setIssuedescription(String issuedescription) {
		this.issuedescription = issuedescription;
	}
	
	public String getSparepartsrequired() {
		return sparepartsrequired;
	}
	public void setSparepartsrequired(String sparepartsrequired) {
		this.sparepartsrequired = sparepartsrequired;
	}
	public double getEstimatedrepaircost() {
		return estimatedrepaircost;
	}
	public void setEstimatedrepaircost(double estimatedrepaircost) {
		this.estimatedrepaircost = estimatedrepaircost;
	}
	public String getEstimatedcompletiondate() {
		return estimatedcompletiondate;
	}
	public void setEstimatedcompletiondate(String estimatedcompletiondate) {
		this.estimatedcompletiondate = estimatedcompletiondate;
	}
	public String getRequestedstatus() {
		return requestedstatus;
	}
	public void setRequestedstatus(String requestedstatus) {
		this.requestedstatus = requestedstatus;
	}
	
	public String getEmployeeuid() {
		return employeeuid;
	}
	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}
	
	public LocalDateTime getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(LocalDateTime insertdate) {
		this.insertdate = insertdate;
	}
	public LocalDateTime getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}
	public static DateTimeFormatter getFormatter() {
		return formatter;
	}
	
    
}

package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="maintenanceworkorder")
public class MaintenanceWorkOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String maintenanceworkorderuid;
	private String worktype;
	private String requestedate;
	private String equipmentmasteruid;
	private String taskdescription;
	private String prioritylevel;
	private String estimatedcompletiondate;
	private String status;
	@Column(name = "insertdate", updatable = false)
	private String insertdate; 
	@Column(name = "updatedate")
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaintenanceworkorderuid() {
		return maintenanceworkorderuid;
	}
	public void setMaintenanceworkorderuid(String maintenanceworkorderuid) {
		this.maintenanceworkorderuid = maintenanceworkorderuid;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getRequestedate() {
		return requestedate;
	}
	public void setRequestedate(String requestedate) {
		this.requestedate = requestedate;
	}
	
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	public String getTaskdescription() {
		return taskdescription;
	}
	public void setTaskdescription(String taskdescription) {
		this.taskdescription = taskdescription;
	}
	public String getPrioritylevel() {
		return prioritylevel;
	}
	public void setPrioritylevel(String prioritylevel) {
		this.prioritylevel = prioritylevel;
	}
	public String getEstimatedcompletiondate() {
		return estimatedcompletiondate;
	}
	public void setEstimatedcompletiondate(String estimatedcompletiondate) {
		this.estimatedcompletiondate = estimatedcompletiondate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public static DateTimeFormatter getFormatter() {
		return formatter;
	}
	
	
}

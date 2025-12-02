package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "preventivemaintenanceschedule")
public class PreventiveMaintenanceSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String preventivemaintenancescheduleuid;           
    private String equipmentmasteruid; 
    private String maintenancetask;      
    private String frequency;            
    private String nextduedate;       
    private String assignedtechnician;   
    private String checklistitems; 
    private String status;
    private LocalDateTime insertdate;
   	private LocalDateTime updatedate;

   	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPreventivemaintenancescheduleuid() {
		return preventivemaintenancescheduleuid;
	}
	public void setPreventivemaintenancescheduleuid(String preventivemaintenancescheduleuid) {
		this.preventivemaintenancescheduleuid = preventivemaintenancescheduleuid;
	}
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	public String getMaintenancetask() {
		return maintenancetask;
	}
	public void setMaintenancetask(String maintenancetask) {
		this.maintenancetask = maintenancetask;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getNextduedate() {
		return nextduedate;
	}
	public void setNextduedate(String nextduedate) {
		this.nextduedate = nextduedate;
	}
	public String getAssignedtechnician() {
		return assignedtechnician;
	}
	public void setAssignedtechnician(String assignedtechnician) {
		this.assignedtechnician = assignedtechnician;
	}
	public String getChecklistitems() {
		return checklistitems;
	}
	public void setChecklistitems(String checklistitems) {
		this.checklistitems = checklistitems;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="maintenancehistorylog")
public class MaintenanceHistoryLogForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String maintenancehistoryloguid;
	private String equipmentmasteruid;
	private String maintenancetype;
	private String servicedate;
	private String servicedetails;
	private String technicianname;
	private String partsused;
	private String nextduedate;
	private String insertdate;  
    private String updatedate;//13
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaintenancehistoryloguid() {
		return maintenancehistoryloguid;
	}
	public void setMaintenancehistoryloguid(String maintenancehistoryloguid) {
		this.maintenancehistoryloguid = maintenancehistoryloguid;
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
	public String getServicedate() {
		return servicedate;
	}
	public void setServicedate(String servicedate) {
		this.servicedate = servicedate;
	}
	public String getServicedetails() {
		return servicedetails;
	}
	public void setServicedetails(String servicedetails) {
		this.servicedetails = servicedetails;
	}
	public String getTechnicianname() {
		return technicianname;
	}
	public void setTechnicianname(String technicianname) {
		this.technicianname = technicianname;
	}
	public String getPartsused() {
		return partsused;
	}
	public void setPartsused(String partsused) {
		this.partsused = partsused;
	}
	public String getNextduedate() {
		return nextduedate;
	}
	public void setNextduedate(String nextduedate) {
		this.nextduedate = nextduedate;
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

package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="equipmentmaster")
public class EquipmentMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String equipmentmasteruid;
    private String equipmentname;
    private String category;
    private String location;
    private String purchasedate;
    private String warrantystartdate;
    private String warrantyenddate;
    private String manufacturername;  
	private String maintenancefrequency;
	private String departmentname;
    private String status;	    
    private String insertdate;
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPurchasedate() {
		return purchasedate;
	}
	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}
	
	public String getWarrantystartdate() {
		return warrantystartdate;
	}
	public void setWarrantystartdate(String warrantystartdate) {
		this.warrantystartdate = warrantystartdate;
	}
	public String getWarrantyenddate() {
		return warrantyenddate;
	}
	public void setWarrantyenddate(String warrantyenddate) {
		this.warrantyenddate = warrantyenddate;
	}
	public String getManufacturername() {
		return manufacturername;
	}
	public void setManufacturername(String manufacturername) {
		this.manufacturername = manufacturername;
	}
	public String getMaintenancefrequency() {
		return maintenancefrequency;
	}
	public void setMaintenancefrequency(String maintenancefrequency) {
		this.maintenancefrequency = maintenancefrequency;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
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

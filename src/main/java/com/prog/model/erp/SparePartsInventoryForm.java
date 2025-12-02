package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sparepartsinventory")
public class SparePartsInventoryForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sparepartsinventoryuid;
	private String sparepartsname;
	private String sparepartsdescription;
	private String category;
	private String unitofmeasure;
	private String currentstocklevel;
	private String minimimstocklevel;
	private String location;
	private String suppliername;
	private String lastuseddate;
	private String insertdate;  
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSparepartsinventoryuid() {
		return sparepartsinventoryuid;
	}
	public void setSparepartsinventoryuid(String sparepartsinventoryuid) {
		this.sparepartsinventoryuid = sparepartsinventoryuid;
	}
	public String getSparepartsname() {
		return sparepartsname;
	}
	public void setSparepartsname(String sparepartsname) {
		this.sparepartsname = sparepartsname;
	}
	public String getSparepartsdescription() {
		return sparepartsdescription;
	}
	public void setSparepartsdescription(String sparepartsdescription) {
		this.sparepartsdescription = sparepartsdescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUnitofmeasure() {
		return unitofmeasure;
	}
	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}
	public String getCurrentstocklevel() {
		return currentstocklevel;
	}
	public void setCurrentstocklevel(String currentstocklevel) {
		this.currentstocklevel = currentstocklevel;
	}
	public String getMinimimstocklevel() {
		return minimimstocklevel;
	}
	public void setMinimimstocklevel(String minimimstocklevel) {
		this.minimimstocklevel = minimimstocklevel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getLastuseddate() {
		return lastuseddate;
	}
	public void setLastuseddate(String lastuseddate) {
		this.lastuseddate = lastuseddate;
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

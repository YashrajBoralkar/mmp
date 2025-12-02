package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "warehouseinfo")
public class Warehouseinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Assuming you want an auto-generated ID for the warehouse   
    

	private String unit;

    private String warehouseuid;
    private String cityname;
    private String warehousename;
    private String address;
    private String contactperson;
    private String contactnumber;
    private String storagecapacity;
    @Lob
    private byte[] safetycertification;
	private String storagetype;
    private String zonesandsections;
    private String warehousetype;
    private String operationalhours;
    private String dockoperation;
    private String remarks;
    private String status;
	private int dockavailability;
	private String associatedinventorylocations;
    private String firesafetyequipment;
	
    private String rawmaterialuid;  // comma-separated: "RM001,RM002"
    private String productuid; 

    private String insertdate;
    private String updatedate;

    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


  

public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	   public String getUnit() {
	        return unit;
	    }

	    public void setUnit(String unit) {
	        this.unit = unit;
	    }
	    
//	    public String getCombinedStorageCapacity() {
//	        return this.storagecapacity + " " + this.unit; // Combines the storage capacity and unit
//	    }
	    
	public String getWarehousename() {
		return warehousename;
	}

	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	
	public byte[] getSafetycertification() {
		return safetycertification;
	}

	public void setSafetycertification(byte[] safetycertification) {
		this.safetycertification = safetycertification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactperson() {
		return contactperson;
	}
	 
		public String getWarehouseuid() {
			return warehouseuid;
		}

		public void setWarehouseuid(String warehouseuid) {
			this.warehouseuid = warehouseuid;
		}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getStoragecapacity() {
		return storagecapacity;
	}

	public void setStoragecapacity(String storagecapacity) {
		this.storagecapacity = storagecapacity;
	}

	public String getStoragetype() {
		return storagetype;
	}

	public void setStoragetype(String storagetype) {
		this.storagetype = storagetype;
	}

	public String getZonesandsections() {
		return zonesandsections;
	}

	public void setZonesandsections(String zonesandsections) {
		this.zonesandsections = zonesandsections;
	}

	public String getWarehousetype() {
		return warehousetype;
	}

	public void setWarehousetype(String warehousetype) {
		this.warehousetype = warehousetype;
	}

	public String getOperationalhours() {
		return operationalhours;
	}

	public void setOperationalhours(String operationalhours) {
		this.operationalhours = operationalhours;
	}

	public String getDockoperation() {
		return dockoperation;
	}

	public void setDockoperation(String dockoperation) {
		this.dockoperation = dockoperation;
	}

	public int getDockavailability() {
		return dockavailability;
	}

	public void setDockavailability(int dockavailability) {
		this.dockavailability = dockavailability;
	}

	public String getFiresafetyequipment() {
		return firesafetyequipment;
	}

	public void setFiresafetyequipment(String firesafetyequipment) {
		this.firesafetyequipment = firesafetyequipment;
	}

	public String getAssociatedinventorylocations() {
		return associatedinventorylocations;
	}

	public void setAssociatedinventorylocations(String associatedinventorylocations) {
		this.associatedinventorylocations = associatedinventorylocations;
	}

	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
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
  

	

    // Getters and Setters
}
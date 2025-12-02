package com.prog.model.erp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
@Entity
@Table(name="rawmaterialinventoryentry")
public class RawMaterialInventoryEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rawmaterialinventoryentryuid;
    private String rawmaterialreceiptnoteuid;
    private String producttype;
    private String productuid;
    private String rawmaterialuid;
    private String rawmaterialname;
    private int actualquantity;
    private String warehouseuid;
    private String storagelocation;
    private String entrydate;
    private String employeeuid;
    
    private String insertdate;
    private String updatedate;
    
private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getRawmaterialinventoryentryuid() {
		return rawmaterialinventoryentryuid;
	}
	public void setRawmaterialinventoryentryuid(String rawmaterialinventoryentryuid) {
		this.rawmaterialinventoryentryuid = rawmaterialinventoryentryuid;
	}
	

	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	

	public String getRawmaterialuid() {
		return rawmaterialuid;
	}
	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}
	public String getRawmaterialname() {
		return rawmaterialname;
	}
	public void setRawmaterialname(String rawmaterialname) {
		this.rawmaterialname = rawmaterialname;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}
	public String getStoragelocation() {
		return storagelocation;
	}
	public void setStoragelocation(String storagelocation) {
		this.storagelocation = storagelocation;
	}
	
	
	public String getEntrydate() {
		return entrydate;
	}
	public String getRawmaterialreceiptnoteuid() {
		return rawmaterialreceiptnoteuid;
	}
	public void setRawmaterialreceiptnoteuid(String rawmaterialreceiptnoteuid) {
		this.rawmaterialreceiptnoteuid = rawmaterialreceiptnoteuid;
	}
	
	public String getEmployeeuid() {
		return employeeuid;
	}
	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}
	public int getActualquantity() {
		return actualquantity;
	}
	public void setActualquantity(int actualquantity) {
		this.actualquantity = actualquantity;
	}
	public String getWarehouseuid() {
		return warehouseuid;
	}
	public void setWarehouseuid(String warehouseuid) {
		this.warehouseuid = warehouseuid;
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
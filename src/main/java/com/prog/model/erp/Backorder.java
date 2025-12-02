package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;

@Entity
public class Backorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key

    private String backorderuid;
    private String salesorderuid;
    private String lotuid;
    private String accepteddeliverydate;
    private String fulfillmentstatus;
    private String stockavailability;
    private String warehouseuid;
    private String expectedfulfillmentdate;
    private String trackbackorder;
    private String customerdate;
    private String createddate;
    private String lastmodifiedby;
    
    @Column(name = "insertdate", updatable = false)
    private String insertdate;  

    @Column(name = "updatedate")
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	public String getBackorderuid() {
		return backorderuid;
	}
	public void setBackorderuid(String backorderuid) {
		this.backorderuid = backorderuid;
	}
	public String getSalesorderuid() {
		return salesorderuid;
	}
	public void setSalesorderuid(String salesorderuid) {
		this.salesorderuid = salesorderuid;
	}
	public String getLotuid() {
		return lotuid;
	}
	public void setLotuid(String lotuid) {
		this.lotuid = lotuid;
	}

	public String getAccepteddeliverydate() {
		return accepteddeliverydate;
	}
	public void setAccepteddeliverydate(String accepteddeliverydate) {
		this.accepteddeliverydate = accepteddeliverydate;
	}
	public String getFulfillmentstatus() {
		return fulfillmentstatus;
	}
	public void setFulfillmentstatus(String fulfillmentstatus) {
		this.fulfillmentstatus = fulfillmentstatus;
	}
	public String getStockavailability() {
		return stockavailability;
	}
	public void setStockavailability(String stockavailability) {
		this.stockavailability = stockavailability;
	}
	public String getWarehouseuid() {
		return warehouseuid;
	}
	public void setWarehouseuid(String warehouseuid) {
		this.warehouseuid = warehouseuid;
	}
	public String getExpectedfulfillmentdate() {
		return expectedfulfillmentdate;
	}
	public void setExpectedfulfillmentdate(String expectedfulfillmentdate) {
		this.expectedfulfillmentdate = expectedfulfillmentdate;
	}
	public String getTrackbackorder() {
		return trackbackorder;
	}
	public void setTrackbackorder(String trackbackorder) {
		this.trackbackorder = trackbackorder;
	}
	
	public String getCustomerdate() {
		return customerdate;
	}
	public void setCustomerdate(String customerdate) {
		this.customerdate = customerdate;
	}
	
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getLastmodifiedby() {
		return lastmodifiedby;
	}
	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
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

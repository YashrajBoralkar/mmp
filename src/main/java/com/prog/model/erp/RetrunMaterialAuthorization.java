package com.prog.model.erp;

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
@Table(name = "returnmaterialauthorization")
public class RetrunMaterialAuthorization {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	//Return Information
		private Long id;
		private String returnmaterialauthorizationuid;
		private String returndate;
		private String requestername;
		private String returnreason;
		private String quantityreturned;
		//product details
		private String productuid;
		private String batchuid;
		// Return Process
		private String returnapprovedby;
		private String suppliername;
		private String returnstatus;
		private String remarks;
		private String insertdate;  
		private String updatedate;
		    
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		   
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getReturnmaterialauthorizationuid() {
		return returnmaterialauthorizationuid;
	}
	public void setReturnmaterialauthorizationuid(String returnmaterialauthorizationuid) {
		this.returnmaterialauthorizationuid = returnmaterialauthorizationuid;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getRequestername() {
		return requestername;
	}
	public String getBatchuid() {
		return batchuid;
	}
	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}
	public void setRequestername(String requestername) {
		this.requestername = requestername;
	}
	public String getReturnreason() {
		return returnreason;
	}
	public void setReturnreason(String returnreason) {
		this.returnreason = returnreason;
	}
	
	
	public String getQuantityreturned() {
		return quantityreturned;
	}
	public void setQuantityreturned(String quantityreturned) {
		this.quantityreturned = quantityreturned;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getReturnapprovedby() {
		return returnapprovedby;
	}
	public void setReturnapprovedby(String returnapprovedby) {
		this.returnapprovedby = returnapprovedby;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


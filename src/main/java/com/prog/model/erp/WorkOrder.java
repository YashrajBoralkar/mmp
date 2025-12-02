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
@Table(name="workorder")
public class WorkOrder {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String workorderuid;
	
	private String productionplanninguid;
	private String productuid;
	private String workordertype;
	private String workorderdate;	
	private String prioritylevel;
	private String plannedquantity;  
	private String workcenter;	
	private String machinerequired;	
	private String rawmaterialconsumption;	
	private String labourassigned;
	private String completionstatus;	
	private String completedate;	
	private String reviewedby;	
	private String insertdate;
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkorderuid() {
		return workorderuid;
	}

	public void setWorkorderuid(String workorderuid) {
		this.workorderuid = workorderuid;
	}

	public String getProductionplanninguid() {
		return productionplanninguid;
	}

	public String getPlannedquantity() {
		return plannedquantity;
	}

	public void setPlannedquantity(String plannedquantity) {
		this.plannedquantity = plannedquantity;
	}

	public void setProductionplanninguid(String productionplanninguid) {
		this.productionplanninguid = productionplanninguid;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getWorkordertype() {
		return workordertype;
	}

	public void setWorkordertype(String workordertype) {
		this.workordertype = workordertype;
	}

	public String getWorkorderdate() {
		return workorderdate;
	}

	public void setWorkorderdate(String workorderdate) {
		this.workorderdate = workorderdate;
	}

	public String getPrioritylevel() {
		return prioritylevel;
	}

	public void setPrioritylevel(String prioritylevel) {
		this.prioritylevel = prioritylevel;
	}

	public String getWorkcenter() {
		return workcenter;
	}

	public void setWorkcenter(String workcenter) {
		this.workcenter = workcenter;
	}

	public String getMachinerequired() {
		return machinerequired;
	}

	public void setMachinerequired(String machinerequired) {
		this.machinerequired = machinerequired;
	}

	public String getRawmaterialconsumption() {
		return rawmaterialconsumption;
	}

	public void setRawmaterialconsumption(String rawmaterialconsumption) {
		this.rawmaterialconsumption = rawmaterialconsumption;
	}

	public String getLabourassigned() {
		return labourassigned;
	}

	public void setLabourassigned(String labourassigned) {
		this.labourassigned = labourassigned;
	}

	public String getCompletionstatus() {
		return completionstatus;
	}

	public void setCompletionstatus(String completionstatus) {
		this.completionstatus = completionstatus;
	}

	public String getCompletedate() {
		return completedate;
	}

	public void setCompletedate(String completedate) {
		this.completedate = completedate;
	}

	public String getReviewedby() {
		return reviewedby;
	}

	public void setReviewedby(String reviewedby) {
		this.reviewedby = reviewedby;
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

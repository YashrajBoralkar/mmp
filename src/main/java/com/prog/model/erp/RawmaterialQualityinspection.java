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
@Table(name = "rawmaterialqualityinspection")
public class RawmaterialQualityinspection {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	
	private String rawmaterialqualityinspectionuid;
    private String rawmaterialpurchaseorderuid;
    
    private String rawmaterialuid;
    private String rawmaterialname;
    private String rawmaterialdetails;
    
    private String inspectiondate;
    private String checkedby;
    private String qualityparameters;
    private String inspectionresult;
    private String remarks;
    
    private String updatedate;
    private String insertdate;
    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRawmaterialqualityinspectionuid() {
		return rawmaterialqualityinspectionuid;
	}
	public void setRawmaterialqualityinspectionuid(String rawmaterialqualityinspectionuid) {
		this.rawmaterialqualityinspectionuid = rawmaterialqualityinspectionuid;
	}
	public String getRawmaterialpurchaseorderuid() {
		return rawmaterialpurchaseorderuid;
	}
	public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
		this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
	}
	
	public String getInspectiondate() {
		return inspectiondate;
	}
	public void setInspectiondate(String inspectiondate) {
		this.inspectiondate = inspectiondate;
	}
	public String getCheckedby() {
		return checkedby;
	}
	public void setCheckedby(String checkedby) {
		this.checkedby = checkedby;
	}
	public String getQualityparameters() {
		return qualityparameters;
	}
	public void setQualityparameters(String qualityparameters) {
		this.qualityparameters = qualityparameters;
	}
	public String getInspectionresult() {
		return inspectionresult;
	}
	public void setInspectionresult(String inspectionresult) {
		this.inspectionresult = inspectionresult;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getRawmaterialdetails() {
		return rawmaterialdetails;
	}
	public void setRawmaterialdetails(String rawmaterialdetails) {
		this.rawmaterialdetails = rawmaterialdetails;
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


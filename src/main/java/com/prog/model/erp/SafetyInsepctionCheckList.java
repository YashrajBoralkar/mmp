package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="safetyinspectionchecklist")
public class SafetyInsepctionCheckList {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String safetyinspectionchecklistuid;
    private String equipmentmasteruid;
    private String inspectiondate; // Pending, Approved, Rejected
    private String checklistitems;
    private String inspectedby;
    private String remarks;
    private String Status;
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSafetyinspectionchecklistuid() {
		return safetyinspectionchecklistuid;
	}

	public void setSafetyinspectionchecklistuid(String safetyinspectionchecklistuid) {
		this.safetyinspectionchecklistuid = safetyinspectionchecklistuid;
	}

	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}

	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}

	public String getInspectiondate() {
		return inspectiondate;
	}

	public void setInspectiondate(String inspectiondate) {
		this.inspectiondate = inspectiondate;
	}

	public String getChecklistitems() {
		return checklistitems;
	}

	public void setChecklistitems(String checklistitems) {
		this.checklistitems = checklistitems;
	}

	public String getInspectedby() {
		return inspectedby;
	}

	public void setInspectedby(String inspectedby) {
		this.inspectedby = inspectedby;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

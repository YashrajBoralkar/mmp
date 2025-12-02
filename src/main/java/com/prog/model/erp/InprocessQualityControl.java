package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.*;

@Entity
@Table(name = "inprocessqualitycontrol")
public class InprocessQualityControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    private String inprocessqualitycontroluid;
    
    private String departmentname;
    private String productuid;
    private String productname;
    private String workorderuid;
    private String plannedquantity;
    private String productionorderuid;
    private String firstarticleinspectionuid;
    
    private String stageofproduction;
    private String inspectiondatetime;
    private String testingmethod;
    private String tolerancelimits;
    private String defecttype;
    private String approvedquantity;
    private String samplesize;
    private String defectivecount;
    
    private String defectrate;
    private String inspectionstatus;
    private String correctiveaction;
    private String inspectorname;
    private String supervisorapproval;
    private String insertdate;
   	private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInprocessqualitycontroluid() {
		return inprocessqualitycontroluid;
	}
	public void setInprocessqualitycontroluid(String inprocessqualitycontroluid) {
		this.inprocessqualitycontroluid = inprocessqualitycontroluid;
	}
	
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	
	
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	
	public String getStageofproduction() {
		return stageofproduction;
	}
	public void setStageofproduction(String stageofproduction) {
		this.stageofproduction = stageofproduction;
	}
	public String getInspectiondatetime() {
		return inspectiondatetime;
	}
	public void setInspectiondatetime(String inspectiondatetime) {
		this.inspectiondatetime = inspectiondatetime;
	}
	public String getTestingmethod() {
		return testingmethod;
	}
	public void setTestingmethod(String testingmethod) {
		this.testingmethod = testingmethod;
	}
	public String getTolerancelimits() {
		return tolerancelimits;
	}
	public void setTolerancelimits(String tolerancelimits) {
		this.tolerancelimits = tolerancelimits;
	}
	public String getDefecttype() {
		return defecttype;
	}
	public void setDefecttype(String defecttype) {
		this.defecttype = defecttype;
	}
	
	public String getApprovedquantity() {
		return approvedquantity;
	}
	public void setApprovedquantity(String approvedquantity) {
		this.approvedquantity = approvedquantity;
	}
	public String getSamplesize() {
		return samplesize;
	}
	public void setSamplesize(String samplesize) {
		this.samplesize = samplesize;
	}
	public String getDefectivecount() {
		return defectivecount;
	}
	public void setDefectivecount(String defectivecount) {
		this.defectivecount = defectivecount;
	}
	public String getDefectrate() {
		return defectrate;
	}
	public void setDefectrate(String defectrate) {
		this.defectrate = defectrate;
	}
	public String getInspectionstatus() {
		return inspectionstatus;
	}
	public void setInspectionstatus(String inspectionstatus) {
		this.inspectionstatus = inspectionstatus;
	}
	public String getCorrectiveaction() {
		return correctiveaction;
	}
	public void setCorrectiveaction(String correctiveaction) {
		this.correctiveaction = correctiveaction;
	}
	public String getInspectorname() {
		return inspectorname;
	}
	public void setInspectorname(String inspectorname) {
		this.inspectorname = inspectorname;
	}
	public String getSupervisorapproval() {
		return supervisorapproval;
	}
	public void setSupervisorapproval(String supervisorapproval) {
		this.supervisorapproval = supervisorapproval;
	}
	
	
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getWorkorderuid() {
		return workorderuid;
	}
	public void setWorkorderuid(String workorderuid) {
		this.workorderuid = workorderuid;
	}
	
	public String getPlannedquantity() {
		return plannedquantity;
	}
	public void setPlannedquantity(String plannedquantity) {
		this.plannedquantity = plannedquantity;
	}
	public String getProductionorderuid() {
		return productionorderuid;
	}
	public void setProductionorderuid(String productionorderuid) {
		this.productionorderuid = productionorderuid;
	}
	public String getFirstarticleinspectionuid() {
		return firstarticleinspectionuid;
	}
	public void setFirstarticleinspectionuid(String firstarticleinspectionuid) {
		this.firstarticleinspectionuid = firstarticleinspectionuid;
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

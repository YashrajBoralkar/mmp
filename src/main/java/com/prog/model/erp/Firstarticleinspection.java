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
@Table(name = "firstarticleinspection")
public class Firstarticleinspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    
    private String firstarticleinspectionuid; 
    private String productuid;
    private String totalinspectedunits;
    private String measurementverification; 
    private String materialverification; 
    private String functionaltesting; 
    private String defectcount; 
    private String approvalstatus; 
    private String inspectorname; 
    private String defectiveunits;
    private String insertdate;
	private String updatedate;
    private String productname;
    private String productionorderuid;        // Auto-generated or manual
    private String approvedcount;

    public String getApprovedcount() {
		return approvedcount;
	}

	public void setApprovedcount(String approvedcount) {
		this.approvedcount = approvedcount;
	}

	public String getProductionorderuid() {
		return productionorderuid;
	}

	public void setProductionorderuid(String productionorderuid) {
		this.productionorderuid = productionorderuid;
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

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getPlannedquantity() {
		return plannedquantity;
	}

	public void setPlannedquantity(String plannedquantity) {
		this.plannedquantity = plannedquantity;
	}

	public String getProductionplanninguid() {
		return productionplanninguid;
	}

	public void setProductionplanninguid(String productionplanninguid) {
		this.productionplanninguid = productionplanninguid;
	}

	public static DateTimeFormatter getFormatter() {
		return formatter;
	}

	private String plannedquantity;
    private String productionplanninguid;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String workorderuid;

    public String getWorkorderuid() {
        return workorderuid;
    }

    public void setWorkorderuid(String workorderuid) {
        this.workorderuid = workorderuid;
    }


    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstarticleinspection() {
		return firstarticleinspectionuid;
	}
	public void setFirstarticleinspectionuid(String firstarticleinspectionuid) {
		this.firstarticleinspectionuid = firstarticleinspectionuid;
	}
	
	public String getTotalinspectedunits() {
		return totalinspectedunits;
	}
	public void setTotalinspectedunits(String totalinspectedunits) {
		this.totalinspectedunits = totalinspectedunits;
	}
	public String getFirstarticleinspectionuid() {
		return firstarticleinspectionuid;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	
	public String getMeasurementverification() {
		return measurementverification;
	}
	public void setMeasurementverification(String measurementverification) {
		this.measurementverification = measurementverification;
	}
	public String getMaterialverification() {
		return materialverification;
	}
	public void setMaterialverification(String materialverification) {
		this.materialverification = materialverification;
	}
	public String getFunctionaltesting() {
		return functionaltesting;
	}
	public void setFunctionaltesting(String functionaltesting) {
		this.functionaltesting = functionaltesting;
	}
	public String getDefectcount() {
		return defectcount;
	}
	public void setDefectcount(String defectcount) {
		this.defectcount = defectcount;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String getInspectorname() {
		return inspectorname;
	}
	public void setInspectorname(String inspectorname) {
		this.inspectorname = inspectorname;
	}
	
	
	 public String getDefectiveunits() {
		return defectiveunits;
	}
	public void setDefectiveunits(String defectiveunits) {
		this.defectiveunits = defectiveunits;
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
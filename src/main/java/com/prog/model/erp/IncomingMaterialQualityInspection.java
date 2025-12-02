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
@Table(name="incomingmaterialqualityinspection")
public class IncomingMaterialQualityInspection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String incomingmaterialqualityinspectionuid;
	private String suppliername;
	private String ponumber;
	private String deliverydate;
    private String batchuid;
    private String productuid;
	private String inspectiondate;
	private String inspectorname;
	private String inspectioncriteria;
	private String samplingmethod;
	private String totalinspecteditems;
	private String defectiveitems;
	private String defectrate;
	private String inspectionstatus;
	private String actiontaken;
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
	
	
	public String getIncomingmaterialqualityinspectionuid() {
		return incomingmaterialqualityinspectionuid;
	}
	public void setIncomingmaterialqualityinspectionuid(String incomingmaterialqualityinspectionuid) {
		this.incomingmaterialqualityinspectionuid = incomingmaterialqualityinspectionuid;
	}
	
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getPonumber() {
		return ponumber;
	}
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
	public String getInspectiondate() {
		return inspectiondate;
	}
	public void setInspectiondate(String inspectiondate) {
		this.inspectiondate = inspectiondate;
	}
	public String getInspectorname() {
		return inspectorname;
	}
	public void setInspectorname(String inspectorname) {
		this.inspectorname = inspectorname;
	}
	public String getInspectioncriteria() {
		return inspectioncriteria;
	}
	public void setInspectioncriteria(String inspectioncriteria) {
		this.inspectioncriteria = inspectioncriteria;
	}
	public String getSamplingmethod() {
		return samplingmethod;
	}
	public void setSamplingmethod(String samplingmethod) {
		this.samplingmethod = samplingmethod;
	}
	public String getTotalinspecteditems() {
		return totalinspecteditems;
	}
	public void setTotalinspecteditems(String totalinspecteditems) {
		this.totalinspecteditems = totalinspecteditems;
	}
	public String getDefectiveitems() {
		return defectiveitems;
	}
	public void setDefectiveitems(String defectiveitems) {
		this.defectiveitems = defectiveitems;
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
	public String getActiontaken() {
		return actiontaken;
	}
	public void setActiontaken(String actiontaken) {
		this.actiontaken = actiontaken;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBatchuid() {
		return batchuid;
	}
	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
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

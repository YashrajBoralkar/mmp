package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class PurchaseRequisition 
{
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requisitionuid;
    private String requestdate;
    private String requestedby;
    private String department;
    private String productuid;
    private String quantity;
    private String unitofmeasure;
    private String reasonforrequest;
    private String approvalstatus;
    private String approvedby;
    private String approvaldate;
    private String prioritylevel;
    private String remarks;
    
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

	public String getRequisitionuid() {
		return requisitionuid;
	}

	public void setRequisitionuid(String requisitionuid) {
		this.requisitionuid = requisitionuid;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}

	public String getRequestedby() {
		return requestedby;
	}

	public void setRequestedby(String requestedby) {
		this.requestedby = requestedby;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public String getReasonforrequest() {
		return reasonforrequest;
	}

	public void setReasonforrequest(String reasonforrequest) {
		this.reasonforrequest = reasonforrequest;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public String getApprovaldate() {
		return approvaldate;
	}

	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate;
	}

	public String getPrioritylevel() {
		return prioritylevel;
	}

	public void setPrioritylevel(String prioritylevel) {
		this.prioritylevel = prioritylevel;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

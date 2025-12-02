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
@Table(name="cyclecountphysical")
public class CycleCountPhysical {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cyclecountuid;
	private String category;
	private String scheduleddate;
	private String cyclecounttype;
	private String countinitiatedby;
	private String warehouseuid;
	private String shelfnumber;
	private String productuid;
	private String batchuid;
	private String unitofmeasure;
	private String systemstock;
	private String physicalstock;
	private String stockdifference;
	private String reasonofdiscrepancy;
	private String remark;
	private String correctiveactiontaken;
	private String resolvedby;
	private String resolutiondate;
	private String approvalstatus;
	private String verifiedby;
	private String reviewedby;
	private String finalapproval;
	
	 private String insertdate;
     private String updatedate;

     private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCyclecountuid() {
		return cyclecountuid;
	}
	public void setCyclecountuid(String cyclecountuid) {
		this.cyclecountuid = cyclecountuid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getScheduleddate() {
		return scheduleddate;
	}
	public void setScheduleddate(String scheduleddate) {
		this.scheduleddate = scheduleddate;
	}
	public String getCyclecounttype() {
		return cyclecounttype;
	}
	public void setCyclecounttype(String cyclecounttype) {
		this.cyclecounttype = cyclecounttype;
	}
	public String getCountinitiatedby() {
		return countinitiatedby;
	}
	public void setCountinitiatedby(String countinitiatedby) {
		this.countinitiatedby = countinitiatedby;
	}
	public String getWarehouseuid() {
		return warehouseuid;
	}
	public void setWarehouseuid(String warehouseuid) {
		this.warehouseuid = warehouseuid;
	}
	public String getShelfnumber() {
		return shelfnumber;
	}
	public void setShelfnumber(String shelfnumber) {
		this.shelfnumber = shelfnumber;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	public String getBatchuid() {
		return batchuid;
	}
	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}
	public String getUnitofmeasure() {
		return unitofmeasure;
	}
	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}
	public String getSystemstock() {
		return systemstock;
	}
	public void setSystemstock(String systemstock) {
		this.systemstock = systemstock;
	}
	public String getPhysicalstock() {
		return physicalstock;
	}
	public void setPhysicalstock(String physicalstock) {
		this.physicalstock = physicalstock;
	}
	public String getStockdifference() {
		return stockdifference;
	}
	public void setStockdifference(String stockdifference) {
		this.stockdifference = stockdifference;
	}
	public String getReasonofdiscrepancy() {
		return reasonofdiscrepancy;
	}
	public void setReasonofdiscrepancy(String reasonofdiscrepancy) {
		this.reasonofdiscrepancy = reasonofdiscrepancy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCorrectiveactiontaken() {
		return correctiveactiontaken;
	}
	public void setCorrectiveactiontaken(String correctiveactiontaken) {
		this.correctiveactiontaken = correctiveactiontaken;
	}
	public String getResolvedby() {
		return resolvedby;
	}
	public void setResolvedby(String resolvedby) {
		this.resolvedby = resolvedby;
	}
	public String getResolutiondate() {
		return resolutiondate;
	}
	public void setResolutiondate(String resolutiondate) {
		this.resolutiondate = resolutiondate;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String getVerifiedby() {
		return verifiedby;
	}
	public void setVerifiedby(String verifiedby) {
		this.verifiedby = verifiedby;
	}
	public String getReviewedby() {
		return reviewedby;
	}
	public void setReviewedby(String reviewedby) {
		this.reviewedby = reviewedby;
	}
	public String getFinalapproval() {
		return finalapproval;
	}
	public void setFinalapproval(String finalapproval) {
		this.finalapproval = finalapproval;
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

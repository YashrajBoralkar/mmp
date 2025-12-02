package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class CostAdjustment {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    private String costadjustmentuid;
	    
	    private String stockuid;//fetch stockname
	    private String unitcost;
	    private String unitquantity;
	    private String adjustmentvalue;
	    private String newcost;
	    private String reason;
	    private String adjustmentdate;
	    private String adjustedby;
	    private String approvalstatus;
	    private String approvedby;
	    private String approvaldate;
	    private String comments;
	    
	    private String insertdate;
		private String updatedate;

		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getCostadjustmentuid() {
			return costadjustmentuid;
		}
		public void setCostadjustmentuid(String costadjustmentuid) {
			this.costadjustmentuid = costadjustmentuid;
		}
		public String getStockuid() {
			return stockuid;
		}
		public void setStockuid(String stockuid) {
			this.stockuid = stockuid;
		}
		public String getUnitcost() {
			return unitcost;
		}
		public void setUnitcost(String unitcost) {
			this.unitcost = unitcost;
		}
		public String getUnitquantity() {
			return unitquantity;
		}
		public void setUnitquantity(String unitquantity) {
			this.unitquantity = unitquantity;
		}
		public String getAdjustmentvalue() {
			return adjustmentvalue;
		}
		public void setAdjustmentvalue(String adjustmentvalue) {
			this.adjustmentvalue = adjustmentvalue;
		}
		public String getNewcost() {
			return newcost;
		}
		public void setNewcost(String newcost) {
			this.newcost = newcost;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getAdjustmentdate() {
			return adjustmentdate;
		}
		public void setAdjustmentdate(String adjustmentdate) {
			this.adjustmentdate = adjustmentdate;
		}
		public String getAdjustedby() {
			return adjustedby;
		}
		public void setAdjustedby(String adjustedby) {
			this.adjustedby = adjustedby;
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
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
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
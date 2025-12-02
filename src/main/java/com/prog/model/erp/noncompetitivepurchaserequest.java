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
@Table(name = "noncompetitivepurchaserequest")
public class noncompetitivepurchaserequest {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	     private Long id;
	    // General Information
	    private String noncompetitivepurchaserequestuid ;
	    private String requestdate;
	    private String requestingdepartment;  
	    private String requestedby;

	    private String supplieruid;	    
	  	private String productuid;
	    private String quantity;	    
	    private String totalcost;
	    
	    private String expecteddeliverydate;
	    private String deliverylocation;
	    private String reasonforsolesourcepurchase;    
	    private String marketresearchconducted;    
	    private String uniquefeatures;	    
	    private String riskofnotprocuring;
	  
	    private String budgetcode;	    
	    private String availablebudget;	   
	    private String fundingsource;
	    private String financeapprovalstatus;
        private String financeapprover;	    
	    private String financeapprovaldate;

	    private String procurementapprovalstatus;	    
	    private String procurementapprovername;	    
	    private String procurementapprovaldate;    
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
		
		public String getNoncompetitivepurchaserequestuid () {
			return noncompetitivepurchaserequestuid ;
		}
		public void setNoncompetitivepurchaserequestuid (String noncompetitivepurchaserequestuid ) {
			this.noncompetitivepurchaserequestuid  = noncompetitivepurchaserequestuid ;
		}
		public String getRequestdate() {
			return requestdate;
		}
		public void setRequestdate(String requestdate) {
			this.requestdate = requestdate;
		}
		public String getRequestingdepartment() {
			return requestingdepartment;
		}
		public void setRequestingdepartment(String requestingdepartment) {
			this.requestingdepartment = requestingdepartment;
		}
		public String getRequestedby() {
			return requestedby;
		}
		public void setRequestedby(String requestedby) {
			this.requestedby = requestedby;
		}
		public String getSupplieruid() {
			return supplieruid;
		}
		public void setSupplieruid(String supplieruid) {
			this.supplieruid = supplieruid;
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
		public String getTotalcost() {
			return totalcost;
		}
		public void setTotalcost(String totalcost) {
			this.totalcost = totalcost;
		}
		
		public String getExpecteddeliverydate() {
			return expecteddeliverydate;
		}
		public void setExpecteddeliverydate(String expecteddeliverydate) {
			this.expecteddeliverydate = expecteddeliverydate;
		}
		public String getDeliverylocation() {
			return deliverylocation;
		}
		public void setDeliverylocation(String deliverylocation) {
			this.deliverylocation = deliverylocation;
		}
		public String getReasonforsolesourcepurchase() {
			return reasonforsolesourcepurchase;
		}
		public void setReasonforsolesourcepurchase(String reasonforsolesourcepurchase) {
			this.reasonforsolesourcepurchase = reasonforsolesourcepurchase;
		}
		public String getMarketresearchconducted() {
			return marketresearchconducted;
		}
		public void setMarketresearchconducted(String marketresearchconducted) {
			this.marketresearchconducted = marketresearchconducted;
		}
		public String getUniquefeatures() {
			return uniquefeatures;
		}
		public void setUniquefeatures(String uniquefeatures) {
			this.uniquefeatures = uniquefeatures;
		}
		public String getRiskofnotprocuring() {
			return riskofnotprocuring;
		}
		public void setRiskofnotprocuring(String riskofnotprocuring) {
			this.riskofnotprocuring = riskofnotprocuring;
		}
		public String getBudgetcode() {
			return budgetcode;
		}
		public void setBudgetcode(String budgetcode) {
			this.budgetcode = budgetcode;
		}
		public String getAvailablebudget() {
			return availablebudget;
		}
		public void setAvailablebudget(String availablebudget) {
			this.availablebudget = availablebudget;
		}
		public String getFundingsource() {
			return fundingsource;
		}
		public void setFundingsource(String fundingsource) {
			this.fundingsource = fundingsource;
		}
		public String getFinanceapprovalstatus() {
			return financeapprovalstatus;
		}
		public void setFinanceapprovalstatus(String financeapprovalstatus) {
			this.financeapprovalstatus = financeapprovalstatus;
		}
		public String getFinanceapprover() {
			return financeapprover;
		}
		public void setFinanceapprover(String financeapprover) {
			this.financeapprover = financeapprover;
		}
		public String getFinanceapprovaldate() {
			return financeapprovaldate;
		}
		public void setFinanceapprovaldate(String financeapprovaldate) {
			this.financeapprovaldate = financeapprovaldate;
		}
		public String getProcurementapprovalstatus() {
			return procurementapprovalstatus;
		}
		public void setProcurementapprovalstatus(String procurementapprovalstatus) {
			this.procurementapprovalstatus = procurementapprovalstatus;
		}
		public String getProcurementapprovername() {
			return procurementapprovername;
		}
		public void setProcurementapprovername(String procurementapprovername) {
			this.procurementapprovername = procurementapprovername;
		}
		public String getProcurementapprovaldate() {
			return procurementapprovaldate;
		}
		public void setProcurementapprovaldate(String procurementapprovaldate) {
			this.procurementapprovaldate = procurementapprovaldate;
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

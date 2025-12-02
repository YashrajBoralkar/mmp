package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "returnstockinspection")
public class ReturnStockInspection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String returnstockuid;
    private String productuid;
    private String quantityreturned;
    private String stockcondition;
	private String inspectionnotes;
    private String restockingdecision;
    private String updatestockstatus;
    private String approvalworkflow;
    private String inspectedby;
    private String inspectiondate;
    private String decisionapprovedby;
    @Lob
    private byte[] document;
    
    
    
	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	public String getInspectiondate() {
		return inspectiondate;
	}
	public void setInspectiondate(String inspectiondate) {
		this.inspectiondate = inspectiondate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 public String getReturnstockuid() {
			return returnstockuid;
		}
		public void setReturnstockuid(String returnstockuid) {
			this.returnstockuid = returnstockuid;
		}
		
	public String getProductuid() {
			return productuid;
		}
		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}
	public String getQuantityreturned() {
		return quantityreturned;
	}
	public void setQuantityreturned(String quantityreturned) {
		this.quantityreturned = quantityreturned;
	}
	 public String getStockcondition() {
			return stockcondition;
		}
		public void setStockcondition(String stockcondition) {
			this.stockcondition = stockcondition;
		}
	public String getInspectionnotes() {
		return inspectionnotes;
	}
	public void setInspectionnotes(String inspectionnotes) {
		this.inspectionnotes = inspectionnotes;
	}
	public String getRestockingdecision() {
		return restockingdecision;
	}
	public void setRestockingdecision(String restockingdecision) {
		this.restockingdecision = restockingdecision;
	}
	public String getUpdatestockstatus() {
		return updatestockstatus;
	}
	public void setUpdatestockstatus(String updatestockstatus) {
		this.updatestockstatus = updatestockstatus;
	}
	public String getApprovalworkflow() {
		return approvalworkflow;
	}
	public void setApprovalworkflow(String approvalworkflow) {
		this.approvalworkflow = approvalworkflow;
	}
	public String getInspectedby() {
		return inspectedby;
	}
	public void setInspectedby(String inspectedby) {
		this.inspectedby = inspectedby;
	}
	
	public String getDecisionapprovedby() {
		return decisionapprovedby;
	}
	public void setDecisionapprovedby(String decisionapprovedby) {
		this.decisionapprovedby = decisionapprovedby;
	}

    
}
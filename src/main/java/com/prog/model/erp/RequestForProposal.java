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
@Table(name="requestforproposal")
public class RequestForProposal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String requestforproposaluid;
	private String rfpissuedate;
	private String submissiondeadline;
	private String supplieruid;
	private String scopeofwork;
	private String projectstartdate;
	private String projectenddate;
	private String technicalrequirement;
	private String proposedcost;
	
	private String insertdate;  
    private String updatedate;
 
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRequestforproposaluid() {
		return requestforproposaluid;
	}
	public void setRequestforproposaluid(String requestforproposaluid) {
		this.requestforproposaluid = requestforproposaluid;
	}
	public String getRfpissuedate() {
		return rfpissuedate;
	}
	public void setRfpissuedate(String rfpissuedate) {
		this.rfpissuedate = rfpissuedate;
	}
	public String getSubmissiondeadline() {
		return submissiondeadline;
	}
	public void setSubmissiondeadline(String submissiondeadline) {
		this.submissiondeadline = submissiondeadline;
	}
	public String getSupplieruid() {
		return supplieruid;
	}
	public void setSupplieruid(String supplieruid) {
		this.supplieruid = supplieruid;
	}
	public String getScopeofwork() {
		return scopeofwork;
	}
	public void setScopeofwork(String scopeofwork) {
		this.scopeofwork = scopeofwork;
	}
	public String getProjectstartdate() {
		return projectstartdate;
	}
	public void setProjectstartdate(String projectstartdate) {
		this.projectstartdate = projectstartdate;
	}
	public String getProjectenddate() {
		return projectenddate;
	}
	public void setProjectenddate(String projectenddate) {
		this.projectenddate = projectenddate;
	}
	public String getTechnicalrequirement() {
		return technicalrequirement;
	}
	public void setTechnicalrequirement(String technicalrequirement) {
		this.technicalrequirement = technicalrequirement;
	}
	public String getProposedcost() {
		return proposedcost;
	}
	public void setProposedcost(String proposedcost) {
		this.proposedcost = proposedcost;
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

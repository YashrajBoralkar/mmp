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
public class productionplanning {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	 // 1. Basic Production Details
    private String productionplanninguid;             
    private String productuid;
    private String productname;

    private String rawmaterialuid;
    private String productiontype;               
    private String productionstartdate;       
    private String productionenddate;         
    private String responsiblemanager;           

    // 2. Resource Planning
   
    private double productioncostestimation;
    private String leadtimedays;                     
    private String productiondependencies;        

    // 3. Approval & Notes
    private String approvalstatus;               
    private String approvedby;                 
    private String remarks;
    private double actual;
    private double expected;
    private double diff;
    
    private String productionplanningcompletionstatus;
    
    private String insertdate;
	private String updatedate;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductionplanninguid() {
		return productionplanninguid;
	}
	public void setProductionplanninguid(String productionplanninguid) {
		this.productionplanninguid = productionplanninguid;
	}
	
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getRawmaterialuid() {
		return rawmaterialuid;
	}
	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}
	public String getProductiontype() {
		return productiontype;
	}
	public void setProductiontype(String productiontype) {
		this.productiontype = productiontype;
	}
	public String getProductionstartdate() {
		return productionstartdate;
	}
	public void setProductionstartdate(String productionstartdate) {
		this.productionstartdate = productionstartdate;
	}
	public String getProductionenddate() {
		return productionenddate;
	}
	public void setProductionenddate(String productionenddate) {
		this.productionenddate = productionenddate;
	}
	public String getResponsiblemanager() {
		return responsiblemanager;
	}
	public void setResponsiblemanager(String responsiblemanager) {
		this.responsiblemanager = responsiblemanager;
	}
	
	public double getProductioncostestimation() {
	return productioncostestimation;
	}
    public void setProductioncostestimation(double productioncostestimation) {
    	this.productioncostestimation = productioncostestimation;
    	}
	public String getLeadtimedays() {
		return leadtimedays;
	}
	public void setLeadtimedays(String leadtimedays) {
		this.leadtimedays = leadtimedays;
	}
	public String getProductiondependencies() {
		return productiondependencies;
	}
	public void setProductiondependencies(String productiondependencies) {
		this.productiondependencies = productiondependencies;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public double getActual() {
		return actual;
	}
	public void setActual(double actual) {
		this.actual = actual;
	}
	public double getExpected() {
		return expected;
	}
	public void setExpected(double expected) {
		this.expected = expected;
	}
	public double getDiff() {
		return diff;
	}
	public void setDiff(double diff) {
		this.diff = diff;
	}
	
	 public String getProductionplanningcompletionstatus() {
		return productionplanningcompletionstatus;
	}
	public void setProductionplanningcompletionstatus(String productionplanningcompletionstatus) {
		this.productionplanningcompletionstatus = productionplanningcompletionstatus;
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

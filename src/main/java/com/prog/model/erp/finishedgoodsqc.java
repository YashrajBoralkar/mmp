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
public class finishedgoodsqc {

	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String finishgoodsqcuid;
	    private String productuid;
	    private String productname;
	    private String inspectiondate;
	    private String inprocessqualitycontroluid;
	    private String workorderuid;
	    private String firstarticleinspectionuid;
	    private String productionorderuid;
	    private String dimensionsandweight;
	    private String packagingcondition;
	    private String functionalitytest;
	    private String visualinspection;
	    private String samplesize;
	    private String defectiveitemscount;
	    private String defectrate;
	    private String finalapprovalstatus;
	    private String actiontaken;
	    private String finishedgoodsqcapprovedquantity;
	    private String approvedby;
	    private String employeeuid;
	    private String inprocessqcapprovedqty;
	    private String productionorderplannedqty;
	    private String insertdate;
		private String updatedate;

	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		@PrePersist
	    protected void onCreate() {
	        this.insertdate = LocalDateTime.now().format(formatter);
	        this.updatedate = LocalDateTime.now().format(formatter);
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedate = LocalDateTime.now().format(formatter);
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFinishgoodsqcuid() {
			return finishgoodsqcuid;
		}

		public void setFinishgoodsqcuid(String finishgoodsqcuid) {
			this.finishgoodsqcuid = finishgoodsqcuid;
		}

		public String getProductuid() {
			return productuid;
		}

		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}

		public String getInspectiondate() {
			return inspectiondate;
		}

		public void setInspectiondate(String inspectiondate) {
			this.inspectiondate = inspectiondate;
		}

		public String getInprocessqualitycontroluid() {
			return inprocessqualitycontroluid;
		}

		public void setInprocessqualitycontroluid(String inprocessqualitycontroluid) {
			this.inprocessqualitycontroluid = inprocessqualitycontroluid;
		}

		public String getWorkorderuid() {
			return workorderuid;
		}

		public void setWorkorderuid(String workorderuid) {
			this.workorderuid = workorderuid;
		}

		public String getFirstarticleinspectionuid() {
			return firstarticleinspectionuid;
		}

		public void setFirstarticleinspectionuid(String firstarticleinspectionuid) {
			this.firstarticleinspectionuid = firstarticleinspectionuid;
		}

		public String getProductionorderuid() {
			return productionorderuid;
		}

		public void setProductionorderuid(String productionorderuid) {
			this.productionorderuid = productionorderuid;
		}

		public String getDimensionsandweight() {
			return dimensionsandweight;
		}

		public void setDimensionsandweight(String dimensionsandweight) {
			this.dimensionsandweight = dimensionsandweight;
		}

		public String getPackagingcondition() {
			return packagingcondition;
		}

		public void setPackagingcondition(String packagingcondition) {
			this.packagingcondition = packagingcondition;
		}

		public String getFunctionalitytest() {
			return functionalitytest;
		}

		public void setFunctionalitytest(String functionalitytest) {
			this.functionalitytest = functionalitytest;
		}

		public String getVisualinspection() {
			return visualinspection;
		}

		public void setVisualinspection(String visualinspection) {
			this.visualinspection = visualinspection;
		}

		public String getSamplesize() {
			return samplesize;
		}

		public void setSamplesize(String samplesize) {
			this.samplesize = samplesize;
		}

		public String getDefectiveitemscount() {
			return defectiveitemscount;
		}

		public void setDefectiveitemscount(String defectiveitemscount) {
			this.defectiveitemscount = defectiveitemscount;
		}

		public String getDefectrate() {
			return defectrate;
		}

		public void setDefectrate(String defectrate) {
			this.defectrate = defectrate;
		}

		public String getFinalapprovalstatus() {
			return finalapprovalstatus;
		}

		public void setFinalapprovalstatus(String finalapprovalstatus) {
			this.finalapprovalstatus = finalapprovalstatus;
		}

		public String getActiontaken() {
			return actiontaken;
		}

		public void setActiontaken(String actiontaken) {
			this.actiontaken = actiontaken;
		}


		public String getApprovedby() {
			return approvedby;
		}

		public void setApprovedby(String approvedby) {
			this.approvedby = approvedby;
		}

		public String getEmployeeuid() {
			return employeeuid;
		}

		public void setEmployeeuid(String employeeuid) {
			this.employeeuid = employeeuid;
		}


		public static DateTimeFormatter getFormatter() {
			return formatter;
		}

		public String getInprocessqcapprovedqty() {
			return inprocessqcapprovedqty;
		}

		public void setInprocessqcapprovedqty(String inprocessqcapprovedqty) {
			this.inprocessqcapprovedqty = inprocessqcapprovedqty;
		}

		public String getProductionorderplannedqty() {
			return productionorderplannedqty;
		}

		public void setProductionorderplannedqty(String productionorderplannedqty) {
			this.productionorderplannedqty = productionorderplannedqty;
		}

		public String getProductname() {
			return productname;
		}

		public void setProductname(String productname) {
			this.productname = productname;
		}

		public String getFinishedgoodsqcapprovedquantity() {
			return finishedgoodsqcapprovedquantity;
		}

		public void setFinishedgoodsqcapprovedquantity(String finishedgoodsqcapprovedquantity) {
			this.finishedgoodsqcapprovedquantity = finishedgoodsqcapprovedquantity;
		}
		
	    

}

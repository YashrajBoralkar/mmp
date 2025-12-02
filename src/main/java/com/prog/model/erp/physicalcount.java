package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class physicalcount {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String physicalcountuid;
	    private String productuid;
	  //  private String stockmanageruid;
	    
	    private String currentSystemStock;
	    private String productname;
	    private String physicalcount;
	    private String difference;
	    private String adjustmentreason;
	    private String countedby;
	    private String countdate;
	    private String approvalstatus;
	    private String processedby;
	    
	    private String insertdate;
	    private String updatedate;

	    @Lob
	    private byte[] supportingDocument;
	    
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPhysicalcountuid() {
			return physicalcountuid;
		}
		

		public String getProductname() {
			return productname;
		}

		public void setProductname(String productname) {
			this.productname = productname;
		}

		public void setPhysicalcountuid(String physicalcountuid) {
			this.physicalcountuid = physicalcountuid;
		}

		
		
		
		public String getProductuid() {
			return productuid;
		}

		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}

//		public String getStockmanageruid() {
//			return stockmanageruid;
//		}
//
//		public void setStockmanageruid(String stockmanageruid) {
//			this.stockmanageruid = stockmanageruid;
//		}

		public String getPhysicalcount() {
			return physicalcount;
		}

		public void setPhysicalcount(String physicalcount) {
			this.physicalcount = physicalcount;
		}

		public String getDifference() {
			return difference;
		}

		public void setDifference(String difference) {
			this.difference = difference;
		}

		public String getAdjustmentreason() {
			return adjustmentreason;
		}

		public void setAdjustmentreason(String adjustmentreason) {
			this.adjustmentreason = adjustmentreason;
		}

		public String getCountedby() {
			return countedby;
		}

		public void setCountedby(String countedby) {
			this.countedby = countedby;
		}

		public String getCountdate() {
			return countdate;
		}

		public void setCountdate(String countdate) {
			this.countdate = countdate;
		}

		public String getApprovalstatus() {
			return approvalstatus;
		}

		public void setApprovalstatus(String approvalstatus) {
			this.approvalstatus = approvalstatus;
		}

		public String getProcessedby() {
			return processedby;
		}

		public void setProcessedby(String processedby) {
			this.processedby = processedby;
		}


		public byte[] getSupportingDocument() {
			return supportingDocument;
		}

		public void setSupportingDocument(byte[] supportingDocument) {
			this.supportingDocument = supportingDocument;
		}

		

		public String getCurrentSystemStock() {
			return currentSystemStock;
		}

		public void setCurrentSystemStock(String currentSystemStock) {
			this.currentSystemStock = currentSystemStock;
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

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
@Table(name = "rawmaterialpurchaserequest")
public class RawMaterialPurchaseRequest {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String rawmaterialpurchaserequestuid;
	    private String requestedby;
	    private String requestdate;
	    private String requiredbydate;
	    private String reason;
	    private String rawmaterialuid;
	    private String productuid;
		private String priority;
	    private String rawmaterialname;
	    private double requiredquantity;
	    private String productionplanninguid;
		private String productname;
		
		private String insertdate;
		private String updatedate;
		    		 		    
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getRawmaterialpurchaserequestuid() {
			return rawmaterialpurchaserequestuid;
		}


		public void setRawmaterialpurchaserequestuid(String rawmaterialpurchaserequestuid) {
			this.rawmaterialpurchaserequestuid = rawmaterialpurchaserequestuid;
		}


		public String getRequestedby() {
			return requestedby;
		}


		public void setRequestedby(String requestedby) {
			this.requestedby = requestedby;
		}


		public String getRequestdate() {
			return requestdate;
		}


		public void setRequestdate(String requestdate) {
			this.requestdate = requestdate;
		}

		public String getRequiredbydate() {
			return requiredbydate;
		}


		public void setRequiredbydate(String requiredbydate) {
			this.requiredbydate = requiredbydate;
		}


		public String getReason() {
			return reason;
		}


		public void setReason(String reason) {
			this.reason = reason;
		}


		public String getRawmaterialuid() {
			return rawmaterialuid;
		}


		public void setRawmaterialuid(String rawmaterialuid) {
			this.rawmaterialuid = rawmaterialuid;
		}


		public String getProductuid() {
			return productuid;
		}


		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}


		public String getPriority() {
			return priority;
		}


		public void setPriority(String priority) {
			this.priority = priority;
		}


		public String getRawmaterialname() {
			return rawmaterialname;
		}


		public void setRawmaterialname(String rawmaterialname) {
			this.rawmaterialname = rawmaterialname;
		}


		public double getRequiredquantity() {
			return requiredquantity;
		}


		public void setRequiredquantity(double requiredquantity) {
			this.requiredquantity = requiredquantity;
		}


		public String getProductionplanninguid() {
			return productionplanninguid;
		}


		public void setProductionplanninguid(String productionplanninguid) {
			this.productionplanninguid = productionplanninguid;
		}


		public String getProductname() {
			return productname;
		}


		public void setProductname(String productname) {
			this.productname = productname;
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



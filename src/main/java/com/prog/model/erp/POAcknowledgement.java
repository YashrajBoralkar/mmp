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

@Table(name="poacknowledgment")
@Entity
public class POAcknowledgement {


	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String poacknowledgmentuid;
	    private String rawmaterialsupplieruid;
	    private String rawmaterialpurchaseorderuid;
	    private String suppliername;
	    private String deliverydateconfirmation;
	    private String acceptancestatus;
	    private String rawmaterialdetails;
	    private String rawmaterialuid;
	    private String reasonofrejection;
	    private String supplierrepresentativename;
	    private String supplierrepresentativecontact;
	    private String podate;
	    
	    private String insertdate;
	    private String updatedate;

	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPoacknowledgmentuid() {
			return poacknowledgmentuid;
		}

		public void setPoacknowledgmentuid(String poacknowledgmentuid) {
			this.poacknowledgmentuid = poacknowledgmentuid;
		}

		

		public String getRawmaterialsupplieruid() {
			return rawmaterialsupplieruid;
		}

		public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
			this.rawmaterialsupplieruid = rawmaterialsupplieruid;
		}

		public String getRawmaterialpurchaseorderuid() {
			return rawmaterialpurchaseorderuid;
		}

		public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
			this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
		}

		public String getSuppliername() {
			return suppliername;
		}

		public void setSuppliername(String suppliername) {
			this.suppliername = suppliername;
		}

		public String getDeliverydateconfirmation() {
			return deliverydateconfirmation;
		}

		public void setDeliverydateconfirmation(String deliverydateconfirmation) {
			this.deliverydateconfirmation = deliverydateconfirmation;
		}

		public String getAcceptancestatus() {
			return acceptancestatus;
		}

		public void setAcceptancestatus(String acceptancestatus) {
			this.acceptancestatus = acceptancestatus;
		}

		
		public String getRawmaterialdetails() {
			return rawmaterialdetails;
		}

		public void setRawmaterialdetails(String rawmaterialdetails) {
			this.rawmaterialdetails = rawmaterialdetails;
		}

		public String getRawmaterialuid() {
			return rawmaterialuid;
		}

		public void setRawmaterialuid(String rawmaterialuid) {
			this.rawmaterialuid = rawmaterialuid;
		}

		public String getReasonofrejection() {
			return reasonofrejection;
		}

		public void setReasonofrejection(String reasonofrejection) {
			this.reasonofrejection = reasonofrejection;
		}

		public String getSupplierrepresentativename() {
			return supplierrepresentativename;
		}

		public void setSupplierrepresentativename(String supplierrepresentativename) {
			this.supplierrepresentativename = supplierrepresentativename;
		}

		public String getSupplierrepresentativecontact() {
			return supplierrepresentativecontact;
		}

		public void setSupplierrepresentativecontact(String supplierrepresentativecontact) {
			this.supplierrepresentativecontact = supplierrepresentativecontact;
		}

		public String getPodate() {
			return podate;
		}

		public void setPodate(String podate) {
			this.podate = podate;
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

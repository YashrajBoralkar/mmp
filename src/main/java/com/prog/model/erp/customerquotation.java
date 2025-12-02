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
public class customerquotation {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
	    private Long id;	  
	    private String customerquotationuid;  
	    private String customeruid;
	    
	    private String quotationdate;
	    private String validuntil;
	    private String productuid;
	    private String quantityoffered;
	    private String quotationamount;
	    private String discountoffered; // Optional field
	    private String totalamount;
	    private String quotationstatus; // Pending, Approved, Rejected
	    private String salesrepresentative;
	    private String insertdate;
	    private String updatedate;

	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getCustomerquotationuid() {
			return customerquotationuid;
		}
		public void setCustomerquotationuid(String customerquotationuid) {
			this.customerquotationuid = customerquotationuid;
		}
		public String getCustomeruid() {
			return customeruid;
		}
		public void setCustomeruid(String customeruid) {
			this.customeruid = customeruid;
		}
		public String getQuotationdate() {
			return quotationdate;
		}
		public void setQuotationdate(String quotationdate) {
			this.quotationdate = quotationdate;
		}
		public String getValiduntil() {
			return validuntil;
		}
		public void setValiduntil(String validuntil) {
			this.validuntil = validuntil;
		}
		public String getProductuid() {
			return productuid;
		}
		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}
		public String getQuantityoffered() {
			return quantityoffered;
		}
		public void setQuantityoffered(String quantityoffered) {
			this.quantityoffered = quantityoffered;
		}
	
		public String getQuotationamount() {
			return quotationamount;
		}
		public void setQuotationamount(String quotationamount) {
			this.quotationamount = quotationamount;
		}
		
		
		public String getTotalamount() {
			return totalamount;
		}
		public void setTotalamount(String totalamount) {
			this.totalamount = totalamount;
		}
		public String getDiscountoffered() {
			return discountoffered;
		}
		public void setDiscountoffered(String discountoffered) {
			this.discountoffered = discountoffered;
		}
		public String getQuotationstatus() {
			return quotationstatus;
		}
		public void setQuotationstatus(String quotationstatus) {
			this.quotationstatus = quotationstatus;
		}
		public String getSalesrepresentative() {
			return salesrepresentative;
		}
		public void setSalesrepresentative(String salesrepresentative) {
			this.salesrepresentative = salesrepresentative;
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

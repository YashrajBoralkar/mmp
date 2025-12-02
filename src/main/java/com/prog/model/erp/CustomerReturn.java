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
@Table(name = "customerreturn")
public class CustomerReturn {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
      
	private String customerreturnuid;
	private String salesorderuid;
	private String customeruid;
	private String returndate;
	private String productuid;
	private String quantityreturned;
	private String reasonforreturn;
	private String returnstatus;
	private String refundorreplacement;
	private String insertdate;
	private String updatedate;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerreturnuid() {
		return customerreturnuid;
	}
	public void setCustomerreturnuid(String customerreturnuid) {
		this.customerreturnuid = customerreturnuid;
	}
	public String getSalesorderuid() {
		return salesorderuid;
	}
	public void setSalesorderuid(String salesorderuid) {
		this.salesorderuid = salesorderuid;
	}
	public String getCustomeruid() {
		return customeruid;
	}
	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
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
	public void setQuantityreturned(String quantityreurned) {
		this.quantityreturned = quantityreurned;
	}
	public String getReasonforreturn() {
		return reasonforreturn;
	}
	public void setReasonforreturn(String reasonforreturn) {
		this.reasonforreturn = reasonforreturn;
	}
	public String getReturnstatus() {
		return returnstatus;
	}
	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}
	public String getRefundorreplacement() {
		return refundorreplacement;
	}
	public void setRefundorreplacement(String refundorreplacement) {
		this.refundorreplacement = refundorreplacement;
	
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

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
@Table(name="salesinvoice")
public class SalesInvoiceForm {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String salesinvoiceuid;
	private String salesorderuid;
	private String customeruid;
	private String productuid;
	private String taxamount;
	private String discount;
	private String totalinvoiceamount;
	private String invoicedate;
	private String duedate;
	private String paymentstatus;
	private String paymentmethods;
	
	private String insertdate;  
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSalesinvoiceuid() {
		return salesinvoiceuid;
	}
	public void setSalesinvoiceuid(String salesinvoiceuid) {
		this.salesinvoiceuid = salesinvoiceuid;
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
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTotalinvoiceamount() {
		return totalinvoiceamount;
	}
	public void setTotalinvoiceamount(String totalinvoiceamount) {
		this.totalinvoiceamount = totalinvoiceamount;
	}
	public String getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getPaymentmethods() {
		return paymentmethods;
	}
	public void setPaymentmethods(String paymentmethods) {
		this.paymentmethods = paymentmethods;
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

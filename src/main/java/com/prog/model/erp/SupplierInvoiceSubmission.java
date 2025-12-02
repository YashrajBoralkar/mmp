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
@Table(name="supplierinvoicesubmission")
public class SupplierInvoiceSubmission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String supplierinvoiceuid;
	private String invoicedate;
	private String rawmaterialpurchaseorderuid;
	private String paymentterms; 
    private String bankaccountnumber; 
    private Double totalamount; 
	private String paymentduedate;
	private String invoiceapprovalstatus;
	private String approvalauthority;
	
	private String insertdate;  
    private String updatedate;
    
    
    
    
    
    
    
    public String getPaymentterms() {
		return paymentterms;
	}
	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}
	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSupplierinvoiceuid() {
		return supplierinvoiceuid;
	}
	public void setSupplierinvoiceuid(String supplierinvoiceuid) {
		this.supplierinvoiceuid = supplierinvoiceuid;
	}
	public String getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}


	public String getRawmaterialpurchaseorderuid() {
		return rawmaterialpurchaseorderuid;
	}
	public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
		this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
	}
	public String getPaymentduedate() {
		return paymentduedate;
	}
	public void setPaymentduedate(String paymentduedate) {
		this.paymentduedate = paymentduedate;
	}
	public String getInvoiceapprovalstatus() {
		return invoiceapprovalstatus;
	}
	public void setInvoiceapprovalstatus(String invoiceapprovalstatus) {
		this.invoiceapprovalstatus = invoiceapprovalstatus;
	}
	public String getApprovalauthority() {
		return approvalauthority;
	}
	public void setApprovalauthority(String approvalauthority) {
		this.approvalauthority = approvalauthority;
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

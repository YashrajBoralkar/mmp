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
public class customerpaymentcollection {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String customerpaymentcollectionuid;
    private String customeruid;
	private String customerinvoiceuid;
	private String paymentdate;
	private String amountpaid;
    private String paymentmode;
    private String receivedby;
    private String invoiceamount;
    private String remainingamount;
    
    private String insertdate;
    private String updatedate;

    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcustomerpaymentcollectionuid() {
		return customerpaymentcollectionuid;
	}

	public void setcustomerpaymentcollectionuid(String customerpaymentcollectionuid) {
		this.customerpaymentcollectionuid = customerpaymentcollectionuid;
	}

	

	public String getCustomerinvoiceuid() {
		return customerinvoiceuid;
	}

	public void setCustomerinvoiceuid(String customerinvoiceuid) {
		this.customerinvoiceuid = customerinvoiceuid;
	}

	public String getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getAmountpaid() {
		return amountpaid;
	}

	public void setAmountpaid(String amountpaid) {
		this.amountpaid = amountpaid;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getReceivedby() {
		return receivedby;
	}

	public void setReceivedby(String receivedby) {
		this.receivedby = receivedby;
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

	public String getCustomeruid() {
		return customeruid;
	}

	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}

	public String getInvoiceamount() {
		return invoiceamount;
	}

	public void setInvoiceamount(String invoiceamount) {
		this.invoiceamount = invoiceamount;
	}

	public String getRemainingamount() {
		return remainingamount;
	}

	public void setRemainingamount(String remainingamount) {
		this.remainingamount = remainingamount;
	}




	
	
}

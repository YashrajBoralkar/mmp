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
public class retailerpaymentcollection {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String retailerpaymentcollectionuid;
	private String retailinvoiceuid;
	private String paymentdate;
	private String amountpaid;
    private String paymentmode;
    private String receivedby;
    
    private String insertdate;
    private String updatedate;

    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRetailerpaymentcollectionuid() {
		return retailerpaymentcollectionuid;
	}

	public void setRetailerpaymentcollectionuid(String retailerpaymentcollectionuid) {
		this.retailerpaymentcollectionuid = retailerpaymentcollectionuid;
	}

	

	public String getRetailinvoiceuid() {
		return retailinvoiceuid;
	}

	public void setRetailinvoiceuid(String retailinvoiceuid) {
		this.retailinvoiceuid = retailinvoiceuid;
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




	
	
}

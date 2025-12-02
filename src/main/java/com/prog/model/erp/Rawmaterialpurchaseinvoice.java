package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;

@Entity
public class Rawmaterialpurchaseinvoice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private Long id;
    private String rawmaterialpurchaseinvoiceuid;
	private String invoicedate;
    private String rawmaterialname;
    private String orderedquantity;
    private String taxamount;
    private String totalamount;
   	private String paymentterms;
  	private String rawmaterialpurchaseorderuid;
    private String rawmaterialreceiptnoteuid;
    private String rawmaterialsupplieruid;
    private String suppliername;
    private String verifiedby;
   	private String materialdetails;

     
    private String insertdate;
    private String updatedate;
    

    // Getter
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRawmaterialpurchaseinvoiceuid() {
		return rawmaterialpurchaseinvoiceuid;
	}

	public void setRawmaterialpurchaseinvoiceuid(String rawmaterialpurchaseinvoiceuid) {
		this.rawmaterialpurchaseinvoiceuid = rawmaterialpurchaseinvoiceuid;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getRawmaterialname() {
		return rawmaterialname;
	}

	public void setRawmaterialname(String rawmaterialname) {
		this.rawmaterialname = rawmaterialname;
	}

	public String getOrderedquantity() {
		return orderedquantity;
	}

	public void setOrderedquantity(String orderedquantity) {
		this.orderedquantity = orderedquantity;
	}

	public String getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getPaymentterms() {
		return paymentterms;
	}

	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}

	public String getRawmaterialpurchaseorderuid() {
		return rawmaterialpurchaseorderuid;
	}

	public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
		this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
	}


	public String getRawmaterialreceiptnoteuid() {
		return rawmaterialreceiptnoteuid;
	}

	public void setRawmaterialreceiptnoteuid(String rawmaterialreceiptnoteuid) {
		this.rawmaterialreceiptnoteuid = rawmaterialreceiptnoteuid;
	}

	public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}

	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	
	
	public String getVerifiedby() {
		return verifiedby;
	}

	public void setVerifiedby(String verifiedby) {
		this.verifiedby = verifiedby;
	}

	public String getMaterialdetails() {
		return materialdetails;
	}

	public void setMaterialdetails(String materialdetails) {
		this.materialdetails = materialdetails;
	}

	@PrePersist
	protected void onCreate() {
		String timestamp = LocalDateTime.now().format(formatter);
		this.insertdate = timestamp;
		this.updatedate = timestamp;
		
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedate = LocalDateTime.now().format(formatter);
		
	}

	
	

}
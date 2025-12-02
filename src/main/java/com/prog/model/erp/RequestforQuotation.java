package com.prog.model.erp;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
@Entity
@Table(name="requestforquotation")
public class RequestforQuotation
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    private String requestforquotationuid;
    private String rfqdate;
    private String issuedby;
    private String rawmaterialsupplieruid;
    private String rawmaterialuid;
    private String rawmaterialname;
    //private String quantityrequired;
    private String expecteddeliverydate;
    @Lob
    @Column(name = "supplierdetails", columnDefinition = "LONGTEXT")
    private String supplierdetails;

    
    private String insertdate;
    private String updatedate;
    
    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRequestforquotationuid() {
		return requestforquotationuid;
	}
	public void setRequestforquotationuid(String requestforquotationuid) {
		this.requestforquotationuid = requestforquotationuid;
	}
	public String getRfqdate() {
		return rfqdate;
	}
	public void setRfqdate(String rfqdate) {
		this.rfqdate = rfqdate;
	}
	public String getIssuedby() {
		return issuedby;
	}
	public void setIssuedby(String issuedby) {
		this.issuedby = issuedby;
	}
	
	
/*	public String getQuantityrequired() {
		return quantityrequired;
	}
	public void setQuantityrequired(String quantityrequired) {
		this.quantityrequired = quantityrequired;
	}
	*/
	public String getExpecteddeliverydate() {
		return expecteddeliverydate;
	}
	public void setExpecteddeliverydate(String expecteddeliverydate) {
		this.expecteddeliverydate = expecteddeliverydate;
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
	public String getRawmaterialuid() {
		return rawmaterialuid;
	}
	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}
	public String getRawmaterialname() {
		return rawmaterialname;
	}
	public void setRawmaterialname(String rawmaterialname) {
		this.rawmaterialname = rawmaterialname;
	}
	public String getSupplierdetails() {
		return supplierdetails;
	}
	public void setSupplierdetails(String supplierdetails) {
		this.supplierdetails = supplierdetails;
	}
	public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}
	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
	}

}
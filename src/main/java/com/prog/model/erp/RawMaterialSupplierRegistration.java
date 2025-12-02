package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rawmaterialsupplier")
public class RawMaterialSupplierRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rawmaterialsupplieruid;

    private String suppliername;

    private String contactperson;
    private String mobilenumber;
    private String emailaddress; 
    private String businessaddress;
    private String gstnumber;
    private String rawmaterialuid;
    private String rawmaterialname;
    private String pannumber;
    private String suppliertype;
    private String bankaccountnumber;
    private String ifsccode;
    private String paymentterms;
    private String compliancecertifications;
    private String websiteurl;
    private String registrationdate;
    @Lob
    @Column(name = "supplierdoc", columnDefinition = "LONGBLOB")
    private byte[] supplierdoc;
    
	private String insertdate;
    private String updatedate;
	public Long getId() {
		
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getBusinessaddress() {
		return businessaddress;
	}
	public void setBusinessaddress(String businessaddress) {
		this.businessaddress = businessaddress;
	}
	public String getGstnumber() {
		return gstnumber;
	}
	public void setGstnumber(String gstnumber) {
		this.gstnumber = gstnumber;
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
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	public String getSuppliertype() {
		return suppliertype;
	}
	public void setSuppliertype(String suppliertype) {
		this.suppliertype = suppliertype;
	}
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}
	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public String getPaymentterms() {
		return paymentterms;
	}
	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}
	public String getCompliancecertifications() {
		return compliancecertifications;
	}
	public void setCompliancecertifications(String compliancecertifications) {
		this.compliancecertifications = compliancecertifications;
	}
	public String getWebsiteurl() {
		return websiteurl;
	}
	public void setWebsiteurl(String websiteurl) {
		this.websiteurl = websiteurl;
	}
	public String getRegistrationdate() {
		return registrationdate;
	}
	public void setRegistrationdate(String registrationdate) {
		this.registrationdate = registrationdate;
	}
	public byte[] getSupplierdoc() {
		return supplierdoc;
	}
	public void setSupplierdoc(byte[] supplierdoc) {
		this.supplierdoc = supplierdoc;
	}

    
   
   }

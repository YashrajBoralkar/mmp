package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Supplier {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String supplieruid;
	private String suppliername;
	private String suppliertype;
	private String bussinessregno;
	private String contactperson;
	private String contactnumber;
	private String email;
	private String companywebsite;
	private String headofficeaddress;
	private String warehouseaddress;
	private String bankname;
	private String bankaccountnumber;
	private String taxidnumber;
	private String paymentterms;
	private String approvedby;
	private String approvaldate;
	private String approvalstatus;
	
	
	
	//Getter Setter
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSupplieruid() {
		return supplieruid;
	}
	public void setSupplieruid(String supplieruid) {
		this.supplieruid = supplieruid;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getSuppliertype() {
		return suppliertype;
	}
	public void setSuppliertype(String suppliertype) {
		this.suppliertype = suppliertype;
	}
	public String getBussinessregno() {
		return bussinessregno;
	}
	public void setBussinessregno(String bussinessregno) {
		this.bussinessregno = bussinessregno;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanywebsite() {
		return companywebsite;
	}
	public void setCompanywebsite(String companywebsite) {
		this.companywebsite = companywebsite;
	}
	public String getHeadofficeaddress() {
		return headofficeaddress;
	}
	public void setHeadofficeaddress(String headofficeaddress) {
		this.headofficeaddress = headofficeaddress;
	}
	public String getWarehouseaddress() {
		return warehouseaddress;
	}
	public void setWarehouseaddress(String warehouseaddress) {
		this.warehouseaddress = warehouseaddress;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}
	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}
	public String getTaxidnumber() {
		return taxidnumber;
	}
	public void setTaxidnumber(String taxidnumber) {
		this.taxidnumber = taxidnumber;
	}
	public String getPaymentterms() {
		return paymentterms;
	}
	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public String getApprovaldate() {
		return approvaldate;
	}
	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
	
	
}

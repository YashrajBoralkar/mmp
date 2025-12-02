package com.prog.model.erp;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "customerregistration")
public class CustomerRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customertype;
	private String customeruid;
	private String companyname;
	private String contactperson;
	private String phonenumber;
	private String email;
	private String address;
	private String gstnumber;
	private String pannumber;
	private String bankaccountnum;
	private String ifsccode;
	private String accholder;
	@Lob
    private byte[] customerdoc;
	
	private String insertdate;
	private String updatedate;
	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGstnumber() {
		return gstnumber;
	}
	public void setGstnumber(String gstnumber) {
		this.gstnumber = gstnumber;
	}
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	
	public String getBankaccountnum() {
		return bankaccountnum;
	}
	public void setBankaccountnum(String bankaccountnum) {
		this.bankaccountnum = bankaccountnum;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public String getAccholder() {
		return accholder;
	}
	public void setAccholder(String accholder) {
		this.accholder = accholder;
	}
	
	
	
	 public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public String getCustomeruid() {
		return customeruid;
	}
	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}
	public byte[] getCustomerdoc() {
		return customerdoc;
	}
	public void setCustomerdoc(byte[] customerdoc) {
		this.customerdoc = customerdoc;
	}
	public String getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public static DateTimeFormatter getFormatter() {
		return formatter;
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

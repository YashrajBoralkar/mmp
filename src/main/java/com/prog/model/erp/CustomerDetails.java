package com.prog.model.erp;


import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customerdetails")
public class CustomerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	
	private String customeruid;
	private String customername;
	private String emailaddress;
	private String contactnumber;

	private String ordernumber;
	private String deliveryaddress;
 	private String contactperson;
	private String billingaddress;
	private String shippingaddress;
	

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCustomeruid() {
		return customeruid;
	}
	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getDeliveryaddress() {
		return deliveryaddress;
	}
	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}
	public String getBillingaddress() {
		return billingaddress;
	}
	public void setBillingaddress(String billingaddress) {
		this.billingaddress = billingaddress;
	}
	public String getShippingaddress() {
		return shippingaddress;
	}
	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}
	public String getDeliveryAddress() {
		return deliveryaddress;
	}
	public void setDeliveryAddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactPerson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getContactNumber() {
		return contactnumber;
	}
	public void setContactNumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	
	
}

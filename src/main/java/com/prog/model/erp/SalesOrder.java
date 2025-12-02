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
import jakarta.persistence.Table;

@Entity
@Table(name = "salesorder")
public class SalesOrder 
{
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String customertype;
	private String customeruid;
	private String salesorderuid;
	private String orderdate;
	private String orderstatus;
	private String productuid;
	private String deliverydate;
	private String deliverymethod;
	private String deliveryaddress;
	private String deliverystatus;
	private String orderquantity;
	private String deliveredquantity;
	private String remainingquantity;
	private String sellingprice;
	private String shippingaddress;
	private String billingaddress;
	private String totalamount; //autocalculate
	
    private String insertdate;  
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	
//selling price //fetch
//=========================================================================================================================================

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSalesorderuid() {
		return salesorderuid;
	}

	public void setSalesorderuid(String salesorderuid) {
		this.salesorderuid = salesorderuid;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	public String getDeliverymethod() {
		return deliverymethod;
	}

	public void setDeliverymethod(String deliverymethod) {
		this.deliverymethod = deliverymethod;
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public String getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(String deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

	public String getOrderquantity() {
		return orderquantity;
	}

	public void setOrderquantity(String orderquantity) {
		this.orderquantity = orderquantity;
	}

	public String getDeliveredquantity() {
		return deliveredquantity;
	}

	public void setDeliveredquantity(String deliveredquantity) {
		this.deliveredquantity = deliveredquantity;
	}

	public String getRemainingquantity() {
		return remainingquantity;
	}

	public void setRemainingquantity(String remainingquantity) {
		this.remainingquantity = remainingquantity;
	}

	public String getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(String sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getShippingaddress() {
		return shippingaddress;
	}

	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getBillingaddress() {
		return billingaddress;
	}

	public void setBillingaddress(String billingaddress) {
		this.billingaddress = billingaddress;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
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
    
	
//=========================================================================================================================================

	
	

}

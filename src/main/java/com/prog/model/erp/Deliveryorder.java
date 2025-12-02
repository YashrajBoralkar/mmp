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
@Table(name = "deliveryorder")
public class Deliveryorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliveryorderuid; 
    private String salesorderuid; 
    private String productuid; 
    private String customeruid; 
	private String customername; 
    private String deliverydate; 
    private String deliveryaddress; 
    private String quantitydispatched; 
    private String shippingmethod; 
    private String dispatchedby; 
    private String receivedby; 
    private String deliverystatus; 
    private String remarks; 

    private String insertdate;  
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Long getId() {
    
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryorderuid() {
        return deliveryorderuid;
    }

    public void setDeliveryorderuid(String deliveryorderuid) {
        this.deliveryorderuid = deliveryorderuid;
    }

    public String getSalesorderuid() {
        return salesorderuid;
    }

    public void setSalesorderuid(String salesorderuid) {
        this.salesorderuid = salesorderuid;
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


	public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public String getProductuid() {
        return productuid;
    }

    public void setProductuid(String productuid) {
        this.productuid = productuid;
    }

    public String getQuantitydispatched() {
        return quantitydispatched;
    }

    public void setQuantitydispatched(String quantitydispatched) {
        this.quantitydispatched = quantitydispatched;
    }

    public String getShippingmethod() {
        return shippingmethod;
    }

    public void setShippingmethod(String shippingmethod) {
        this.shippingmethod = shippingmethod;
    }

    public String getDispatchedby() {
        return dispatchedby;
    }

    public void setDispatchedby(String dispatchedby) {
        this.dispatchedby = dispatchedby;
    }

    public String getReceivedby() {
        return receivedby;
    }

    public void setReceivedby(String receivedby) {
        this.receivedby = receivedby;
    }

    public String getDeliverystatus() {
        return deliverystatus;
    }

    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
package com.prog.model.erp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class PurchaseOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String pouid;//for purchase order 
	private String podate;//only today's date
	private String supplieruid;//fetch 
	private String productuid;//fetch
	private int quantityordered;
	private String unitofmeasure;//in kg, pcs
	private double taxrate;
	private String deliverydate;
	private String deliverylocation;
	private String paymentterms;
	private String currency;
	private double totalamount;//same as total price
	private String approvalstatus;
	private String authorizedby;
	private String approvaldate;
	
	private BigDecimal unitprice;
    private BigDecimal totalprice;
    
    @Column(name = "insertdate", updatable = false)
    private String insertdate;  

    @Column(name = "updatedate")
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    public String getSupplieruid() {
		return supplieruid;
	}

	public void setSupplieruid(String supplieruid) {
		this.supplieruid = supplieruid;
	}

	// Getters and setters
    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPouid() {
		return pouid;
	}
	public void setPouid(String pouid) {
		this.pouid = pouid;
	}
	public String getPodate() {
		return podate;
	}
	public void setPodate(String podate) {
		this.podate = podate;
	}
	
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	
	public int getQuantityordered() {
		return quantityordered;
	}
	public void setQuantityordered(int quantityordered) {
		this.quantityordered = quantityordered;
	}
	public String getUnitofmeasure() {
		return unitofmeasure;
	}
	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}
	
	public double getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(double taxrate) {
		this.taxrate = taxrate;
	}
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
	public String getDeliverylocation() {
		return deliverylocation;
	}
	public void setDeliverylocation(String deliverylocation) {
		this.deliverylocation = deliverylocation;
	}
	public String getPaymentterms() {
		return paymentterms;
	}
	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String getAuthorizedby() {
		return authorizedby;
	}
	public void setAuthorizedby(String authorizedby) {
		this.authorizedby = authorizedby;
	}
	public String getApprovaldate() {
		return approvaldate;
	}
	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate;
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

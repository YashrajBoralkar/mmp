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
public class Stockinfo {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String stockuid;
	//private String producttype;
	private String productuid;
	private Long productquantity;
	//private String productname;
	private String totalstockquantity;
	private String stockprice;	
	//private String unitprice;	
	private String manufacturingdate;
	
	private String expirydate;
	
	private String insertdate; // Record-keeping
	private String updatedate; // Audit
	
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockuid() {
		return stockuid;
	}

	public void setStockuid(String stockuid) {
		this.stockuid = stockuid;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public Long getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(Long productquantity) {
		this.productquantity = productquantity;
	}

	public String getTotalstockquantity() {
		return totalstockquantity;
	}

	public void setTotalstockquantity(String totalstockquantity) {
		this.totalstockquantity = totalstockquantity;
	}

	public String getStockprice() {
		return stockprice;
	}

	public void setStockprice(String stockprice) {
		this.stockprice = stockprice;
	}

	public String getManufacturingdate() {
		return manufacturingdate;
	}

	public void setManufacturingdate(String manufacturingdate) {
		this.manufacturingdate = manufacturingdate;
	}
	
	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
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

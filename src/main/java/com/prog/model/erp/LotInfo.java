package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LotInfo {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String lotuid;
	private String productuid;
	private Long productquantity;
	private String stockuid;
	private String lotprice;
	private String manufacturingdate;
	private String expirydate;
	private String insertedate; // Record-keeping
	private String updateddate; // Audit
	
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
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
	public String getLotprice() {
		return lotprice;
	}

	public void setLotprice(String lotprice) {
		this.lotprice = lotprice;
	}

	public String getLotuid() {
		return lotuid;
	}

	public void setLotuid(String lotuid) {
		this.lotuid = lotuid;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	
	

	
	public String getStockuid() {
		return stockuid;
	}

	public void setStockuid(String stockuid) {
		this.stockuid = stockuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(Long productquantity) {
		this.productquantity = productquantity;
	}

	public String getInsertedate() {
		return insertedate;
	}

	public void setInsertedate(String insertedate) {
		this.insertedate = insertedate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	public static DateTimeFormatter getFormatter() {
		return formatter;
	}
	
	


}

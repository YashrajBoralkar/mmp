package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Batchinfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String batchuid;
	private String productuid;
	private String lotuids;
	private String stockuid;
    private Long lotquantity;// we insert 10 lotuid in this and    1 lots=100 products, 
	private String batchprice;
	private String manufacturingdate;
	private String expirydate;
	private String insertdate; 
	private String updatedate;
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
	public String getBatchprice() {
		return batchprice;
	}
	public void setBatchprice(String batchprice) {
		this.batchprice = batchprice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBatchuid() {
		return batchuid;
	}
	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	public String getLotuids() {
		return lotuids;
	}
	public void setLotuids(String lotuids) {
		this.lotuids = lotuids;
	}
	public Long getLotquantity() {
		return lotquantity;
	}
	public void setLotquantity(Long lotquantity) {
		this.lotquantity = lotquantity;
	}
	
	public String getStockuid() {
		return stockuid;
	}
	public void setStockuid(String stockuid) {
		this.stockuid = stockuid;
	}
	
	
	
	

}

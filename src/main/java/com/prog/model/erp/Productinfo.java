package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Productinfo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productuid;
	private String productname;
	private String productcategory;       // stores categoryuid
	private String productsubcategory; 
	private String specifications; // Optional
	private String rawmaterialuid;
	private String rawmaterialname;

    private String hsnsaccode; // Tax compliance
    private String unitofmeasure; // UOM
	private String leadtime; // Days
	private String minorderquantity; // MOQ
	private String sellingprice; // Retail
	private String taxrate; // Tax
	private String reorderlevel; // Stock replenishment
	private String safetystocklevel; // Buffer inventory
	private String itemstatus; // Active/Discontinued
	private String insertdate; // Record-keeping
	private String updateddate; // Audit
	private String manufacturingdate;
	private String expirydate;
	private String warrantyperiod;

	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	

	public String getRawmaterialname() {
		return rawmaterialname;
	}

	public void setRawmaterialname(String rawmaterialname) {
		this.rawmaterialname = rawmaterialname;
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

	public String getWarrantyperiod() {
		return warrantyperiod;
	}

	public void setWarrantyperiod(String warrantyperiod) {
		this.warrantyperiod = warrantyperiod;
	}

	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}

	public String getProductsubcategory() {
		return productsubcategory;
	}

	public void setProductsubcategory(String productsubcategory) {
		this.productsubcategory = productsubcategory;
	}

	public String getSpecifications() {
		return specifications;
	}

	
	
	
	public String getRawmaterialuid() {
		return rawmaterialuid;
	}

	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getHsnsaccode() {
		return hsnsaccode;
	}

	public void setHsnsaccode(String hsnsaccode) {
		this.hsnsaccode = hsnsaccode;
	}

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	

	public String getLeadtime() {
		return leadtime;
	}

	public void setLeadtime(String leadtime) {
		this.leadtime = leadtime;
	}

	public String getMinorderquantity() {
		return minorderquantity;
	}

	public void setMinorderquantity(String minorderquantity) {
		this.minorderquantity = minorderquantity;
	}


	public String getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(String sellingprice) {
		this.sellingprice = sellingprice;
	}

	public String getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}

	
	public String getReorderlevel() {
		return reorderlevel;
	}

	public void setReorderlevel(String reorderlevel) {
		this.reorderlevel = reorderlevel;
	}

	public String getSafetystocklevel() {
		return safetystocklevel;
	}

	public void setSafetystocklevel(String safetystocklevel) {
		this.safetystocklevel = safetystocklevel;
	}

	public String getItemstatus() {
		return itemstatus;
	}

	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}

	

	public String getInsertdate() {
		return insertdate;
	}

	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}
	
	@PrePersist
    protected void onCreate() {
        this.insertdate = LocalDateTime.now().format(formatter);
        this.updateddate = LocalDateTime.now().format(formatter);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateddate = LocalDateTime.now().format(formatter);
    }

	
}
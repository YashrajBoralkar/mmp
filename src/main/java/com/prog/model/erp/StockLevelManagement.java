package com.prog.model.erp;

import java.time.LocalDate;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class StockLevelManagement 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stockleveluid;//pk
	private String stockuid;
	private String batchuid;//fkey drop down batch table 
	
	private String warehouseuid;// f key drop down warehouse table city,address(readonly),warehuid
	private String shelfnumber;
	private String currentstockquantity;
	private String minstockquantity;
	private String maxstockquantity;
	private String reorederstock ;
	private String defectivestockquantity ;
	
	@Column(name = "insertdate", updatable = false)
    private String insertdate;  

    @Column(name = "updatedate")
    private String updatedate;  
	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
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
	public String getStockleveluid() {
		return stockleveluid;
	}
	public void setStockleveluid(String stockleveluid) {
		this.stockleveluid = stockleveluid;
	}
	public String getBatchuid() {
		return batchuid;
	}
	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}
	
	public String getWarehouseuid() {
		return warehouseuid;
	}
	public void setWarehouseuid(String warehouseuid) {
		this.warehouseuid = warehouseuid;
	}
	
	public String getShelfnumber() {
		return shelfnumber;
	}
	public void setShelfnumber(String shelfnumber) {
		this.shelfnumber = shelfnumber;
	}
	public String getCurrentstockquantity() {
		return currentstockquantity;
	}
	public void setCurrentstockquantity(String currentstockquantity) {
		this.currentstockquantity = currentstockquantity;
	}
	public String getMinstockquantity() {
		return minstockquantity;
	}
	public void setMinstockquantity(String minstockquantity) {
		this.minstockquantity = minstockquantity;
	}
	public String getMaxstockquantity() {
		return maxstockquantity;
	}
	public void setMaxstockquantity(String maxstockquantity) {
		this.maxstockquantity = maxstockquantity;
	}
	
	
	public String getReorederstock() {
		return reorederstock;
	}
	public void setReorederstock(String reorederstock) {
		this.reorederstock = reorederstock;
	}
	public String getDefectivestockquantity() {
		return defectivestockquantity;
	}
	public void setDefectivestockquantity(String defectivestockquantity) {
		this.defectivestockquantity = defectivestockquantity;
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

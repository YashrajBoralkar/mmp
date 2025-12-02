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
@Table(name="rawmaterialpurchaseorder")
public class Rawmaterialpurchaseorder
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rawmaterialpurchaseorderuid;          
    private String rawmaterialsupplieruid;
    private String rawmaterialuid;
    private String suppliername;
    private String orderdate;                
    private String materialnames; 
    private String materialdetails; 
    private String deliverydate;
    
    private double totalvalue;
    private String insertdate;
    private String updatedate;
    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRawmaterialpurchaseorderuid() {
		return rawmaterialpurchaseorderuid;
	}

	public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
		this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
	}


	public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}

	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
	}

	
	public String getRawmaterialuid() {
		return rawmaterialuid;
	}

	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getMaterialnames() {
		return materialnames;
	}

	public void setMaterialnames(String materialnames) {
		this.materialnames = materialnames;
	}

	public String getMaterialdetails() {
		return materialdetails;
	}

	public void setMaterialdetails(String materialdetails) {
		this.materialdetails = materialdetails;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}

	public double getTotalvalue() {
		return totalvalue;
	}

	public void setTotalvalue(double totalvalue) {
		this.totalvalue = totalvalue;
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
   



	
	

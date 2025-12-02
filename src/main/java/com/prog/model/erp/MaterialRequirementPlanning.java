package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="materialrequirementplanning")
public class MaterialRequirementPlanning {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String materialrequirementplanninguid;
	private String productionplanninguid;
	private String productuid;
	private String requiredquantity;
	private String availablestock;
	private String recorderlevel;
	private String suppliername;
	private String supplierleadtime;
	private String supplierleaddate;
	private String procrequeststatus;
	@Column(name = "insertdate", updatable = false)
	private String insertdate; 
	@Column(name = "updatedate")
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaterialrequirementplanninguid() {
		return materialrequirementplanninguid;
	}
	public void setMaterialrequirementplanninguid(String materialrequirementplanninguid) {
		this.materialrequirementplanninguid = materialrequirementplanninguid;
	}
	
	public String getProductionplanninguid() {
		return productionplanninguid;
	}
	public void setProductionplanninguid(String productionplanninguid) {
		this.productionplanninguid = productionplanninguid;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	public String getRequiredquantity() {
		return requiredquantity;
	}
	public void setRequiredquantity(String requiredquantity) {
		this.requiredquantity = requiredquantity;
	}
	public String getAvailablestock() {
		return availablestock;
	}
	public void setAvailablestock(String availablestock) {
		this.availablestock = availablestock;
	}
	public String getRecorderlevel() {
		return recorderlevel;
	}
	public void setRecorderlevel(String recorderlevel) {
		this.recorderlevel = recorderlevel;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getSupplierleadtime() {
		return supplierleadtime;
	}
	public void setSupplierleadtime(String supplierleadtime) {
		this.supplierleadtime = supplierleadtime;
	}
	public String getSupplierleaddate() {
		return supplierleaddate;
	}
	public void setSupplierleaddate(String supplierleaddate) {
		this.supplierleaddate = supplierleaddate;
	}
	public String getProcrequeststatus() {
		return procrequeststatus;
	}
	public void setProcrequeststatus(String procrequeststatus) {
		this.procrequeststatus = procrequeststatus;
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
	
    
}

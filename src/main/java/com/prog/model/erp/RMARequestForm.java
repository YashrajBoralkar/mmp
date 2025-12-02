package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="returnmerchandiseauthorization")
public class RMARequestForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String returnmerchandiseauthorizationuid;
	private String customeruid;
	private String productuid;
	private String batchuid;
	private String returnuid;
	private String approvalstatus;
	private String returninstructions;
	private String restockingfee;
	private String processedby;
	private String decisiondate;
	private String returntrakingnumber;
	private String returnquantity;
	private String insertdate;  
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCustomeruid() {
		return customeruid;
	}
	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}
	
	
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	public String getReturnuid() {
		return returnuid;
	}
	public void setReturnuid(String returnuid) {
		this.returnuid = returnuid;
	}
	public String getApprovalstatus() {
		return approvalstatus;
	}
	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	public String getReturninstructions() {
		return returninstructions;
	}
	public void setReturninstructions(String returninstructions) {
		this.returninstructions = returninstructions;
	}
	public String getRestockingfee() {
		return restockingfee;
	}
	public void setRestockingfee(String restockingfee) {
		this.restockingfee = restockingfee;
	}
	public String getProcessedby() {
		return processedby;
	}
	public void setProcessedby(String processedby) {
		this.processedby = processedby;
	}
	public String getDecisiondate() {
		return decisiondate;
	}
	public void setDecisiondate(String decisiondate) {
		this.decisiondate = decisiondate;
	}
	public String getReturntrakingnumber() {
		return returntrakingnumber;
	}
	public void setReturntrakingnumber(String returntrakingnumber) {
		this.returntrakingnumber = returntrakingnumber;
	}
	
	public String getReturnquantity() {
		return returnquantity;
	}
	public void setReturnquantity(String returnquantity) {
		this.returnquantity = returnquantity;
	}
	public String getBatchuid() {
		return batchuid;
	}
	public void setBatchuid(String batchuid) {
		this.batchuid = batchuid;
	}
	public String getReturnmerchandiseauthorizationuid() {
		return returnmerchandiseauthorizationuid;
	}
	public void setReturnmerchandiseauthorizationuid(String returnmerchandiseauthorizationuid) {
		this.returnmerchandiseauthorizationuid = returnmerchandiseauthorizationuid;
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

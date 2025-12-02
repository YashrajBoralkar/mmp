package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="returndetails")
public class ReturnDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String returnuid;
	private String reasonforreturn;
	private String dateofreturnrequest;
	private String supportingevidence;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReturnuid() {
		return returnuid;
	}
	public void setReturnuid(String returnuid) {
		this.returnuid = returnuid;
	}
	public String getReasonforreturn() {
		return reasonforreturn;
	}
	public void setReasonforreturn(String reasonforreturn) {
		this.reasonforreturn = reasonforreturn;
	}
	public String getDateofreturnrequest() {
		return dateofreturnrequest;
	}
	public void setDateofreturnrequest(String dateofreturnrequest) {
		this.dateofreturnrequest = dateofreturnrequest;
	}
	public String getSupportingevidence() {
		return supportingevidence;
	}
	public void setSupportingevidence(String supportingevidence) {
		this.supportingevidence = supportingevidence;
	}
	
	
}

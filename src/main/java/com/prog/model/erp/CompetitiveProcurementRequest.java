package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "competitiveprocurementrequest")
public class CompetitiveProcurementRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String competitiveprocurementrequestuid;
	@Lob
	private String supplierdetails;
	private String requestdate;
	private String approvedby;
	private String remark;
	private String employeeuid;
	private String requestforquotationuid;
	private String rawmaterialuid;
	
	private String insertdate;
	private String updatedate;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	

	// ---------- Getters & Setters ----------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompetitiveprocurementrequestuid() {
		return competitiveprocurementrequestuid;
	}

	public void setCompetitiveprocurementrequestuid(String competitiveprocurementrequestuid) {
		this.competitiveprocurementrequestuid = competitiveprocurementrequestuid;
	}

	public String getSupplierdetails() {
		return supplierdetails;
	}

	public void setSupplierdetails(String supplierdetails) {
		this.supplierdetails = supplierdetails;
	}

	public String getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}

	public String getRequestforquotationuid() {
		return requestforquotationuid;
	}

	public void setRequestforquotationuid(String requestforquotationuid) {
		this.requestforquotationuid = requestforquotationuid;
	}

	public String getRawmaterialuid() {
		return rawmaterialuid;
	}

	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
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


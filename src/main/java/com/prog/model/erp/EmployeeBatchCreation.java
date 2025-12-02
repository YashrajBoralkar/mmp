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
@Table(name ="employeebatchcreation")
public class EmployeeBatchCreation {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  

	private Long id;
	
	private String empbatchuid;	
	private String batchname;
	private String departmentuid;
	private String departmentname;
	private String batchdescription;
	private String employeeuid;
	private String employeename;
	private String status;
	private String remark;
	
	private String insertdate;
	private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEmpbatchuid() {
		return empbatchuid;
	}

	public void setEmpbatchuid(String empbatchuid) {
		this.empbatchuid = empbatchuid;
	}

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	
	public String getBatchdescription() {
		return batchdescription;
	}

	public void setBatchdescription(String batchdescription) {
		this.batchdescription = batchdescription;
	}

	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentuid() {
		return departmentuid;
	}

	public void setDepartmentuid(String departmentuid) {
		this.departmentuid = departmentuid;
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

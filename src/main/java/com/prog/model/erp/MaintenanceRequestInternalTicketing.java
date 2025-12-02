package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "maintenancerequestinternalticketing")
public class MaintenanceRequestInternalTicketing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String maintenancerequestinternalticketinguid;
	private String employeeuid;
	private String equipmentmasteruid;
	@Column
	private String departmentname;
	private String issuedescription;
	private String prioritylevel;
	private String requestdate;
	private String status;
    private String insertdate; 
    private String updatedate;   
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaintenancerequestinternalticketinguid() {
		return maintenancerequestinternalticketinguid;
	}
	public void setMaintenancerequestinternalticketinguid(String maintenancerequestinternalticketinguid) {
		this.maintenancerequestinternalticketinguid = maintenancerequestinternalticketinguid;
	}
	public String getEmployeeuid() {
		return employeeuid;
	}
	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getIssuedescription() {
		return issuedescription;
	}
	public void setIssuedescription(String issuedescription) {
		this.issuedescription = issuedescription;
	}
	public String getPrioritylevel() {
		return prioritylevel;
	}
	public void setPrioritylevel(String prioritylevel) {
		this.prioritylevel = prioritylevel;
	}
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

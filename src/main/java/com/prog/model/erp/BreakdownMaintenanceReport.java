package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "breakdownmaintenancereport")
public class BreakdownMaintenanceReport {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String breakdownmaintenancereportuid;
	private String equipmentmasteruid;
    private String breakdowndatetime;
    private String employeeuid;
    private String issuedescription;
    private String downtimeduration;
    private String rootcause;
    private String repairactiontaken;
    private String repairedby;
    private String repaircompletiondate;
    private String status;
    private String insertdate;
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBreakdownmaintenancereportuid() {
		return breakdownmaintenancereportuid;
	}

	public void setBreakdownmaintenancereportuid(String breakdownmaintenancereportuid) {
		this.breakdownmaintenancereportuid = breakdownmaintenancereportuid;
	}

	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}

	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}

	public String getBreakdowndatetime() {
		return breakdowndatetime;
	}

	public void setBreakdowndatetime(String breakdowndatetime) {
		this.breakdowndatetime = breakdowndatetime;
	}

	
	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
	}

	public String getIssuedescription() {
		return issuedescription;
	}

	public void setIssuedescription(String issuedescription) {
		this.issuedescription = issuedescription;
	}

	public String getDowntimeduration() {
		return downtimeduration;
	}

	public void setDowntimeduration(String downtimeduration) {
		this.downtimeduration = downtimeduration;
	}

	public String getRootcause() {
		return rootcause;
	}

	public void setRootcause(String rootcause) {
		this.rootcause = rootcause;
	}

	public String getRepairactiontaken() {
		return repairactiontaken;
	}

	public void setRepairactiontaken(String repairactiontaken) {
		this.repairactiontaken = repairactiontaken;
	}

	public String getRepairedby() {
		return repairedby;
	}

	public void setRepairedby(String repairedby) {
		this.repairedby = repairedby;
	}

	public String getRepaircompletiondate() {
		return repaircompletiondate;
	}

	public void setRepaircompletiondate(String repaircompletiondate) {
		this.repaircompletiondate = repaircompletiondate;
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

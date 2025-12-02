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
@Table(name="productionshiftscheduling")
public class ProductionShiftSchedule {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. Shift Details	 
    private String productionscheduleuid;
    private String productiondepartment;
    private String shiftdate;
    private String shiftstarttime;
	private String shiftendtime;
    private String totalshiftduration;

    // 2. Employee Assignment
    private String empbatchuid;
    private String employeeuid;
    private String emplyeenames;
    private String productionplanninguid;
	private String productuid;
    private String workstation;

    // 3. Shift Management

    private String breaktiming;
    private String breakstarttime;
    private String breakendtime; 
    private String totalshiftcountperday;
    private String supervisorname;
    private String supervisoruid;
    
	private String batchname;
    
    private String insertdate;
    private String updatedate;
    

	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getEmpbatchuid() {
		return empbatchuid;
	}

	public void setEmpbatchuid(String empbatchuid) {
		this.empbatchuid = empbatchuid;
	}

	public String getProductionscheduleuid() {
		return productionscheduleuid;
	}

	public void setProductionscheduleuid(String productionscheduleuid) {
		this.productionscheduleuid = productionscheduleuid;
	}

	public String getProductiondepartment() {
		return productiondepartment;
	}

	public void setProductiondepartment(String productiondepartment) {
		this.productiondepartment = productiondepartment;
	}

	public String getShiftdate() {
		return shiftdate;
	}

	public void setShiftdate(String shiftdate) {
		this.shiftdate = shiftdate;
	}

	public String getShiftstarttime() {
		return shiftstarttime;
	}

	public void setShiftstarttime(String shiftstarttime) {
		this.shiftstarttime = shiftstarttime;
	}

	public String getShiftendtime() {
		return shiftendtime;
	}

	public void setShiftendtime(String shiftendtime) {
		this.shiftendtime = shiftendtime;
	}

	public String getTotalshiftduration() {
		return totalshiftduration;
	}

	public void setTotalshiftduration(String totalshiftduration) {
		this.totalshiftduration = totalshiftduration;
	}

	public String getEmployeeuid() {
		return employeeuid;
	}

	public void setEmployeeuid(String employeeuid) {
		this.employeeuid = employeeuid;
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

	public String getWorkstation() {
		return workstation;
	}

	public void setWorkstation(String workstation) {
		this.workstation = workstation;
	}

	public String getBreaktiming() {
		return breaktiming;
	}

	public void setBreaktiming(String breaktiming) {
		this.breaktiming = breaktiming;
	}

	public String getBreakstarttime() {
		return breakstarttime;
	}

	public void setBreakstarttime(String breakstarttime) {
		this.breakstarttime = breakstarttime;
	}

	public String getBreakendtime() {
		return breakendtime;
	}

	public void setBreakendtime(String breakendtime) {
		this.breakendtime = breakendtime;
	}

	public String getTotalshiftcountperday() {
		return totalshiftcountperday;
	}

	public void setTotalshiftcountperday(String totalshiftcountperday) {
		this.totalshiftcountperday = totalshiftcountperday;
	}

	public String getSupervisorname() {
		return supervisorname;
	}

	public void setSupervisorname(String supervisorname) {
		this.supervisorname = supervisorname;
	}

	

	public String getEmplyeenames() {
		return emplyeenames;
	}

	public void setEmplyeenames(String emplyeenames) {
		this.emplyeenames = emplyeenames;
	}

	public String getSupervisoruid() {
		return supervisoruid;
	}

	public void setSupervisoruid(String supervisoruid) {
		this.supervisoruid = supervisoruid;
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

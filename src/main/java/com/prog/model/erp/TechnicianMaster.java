package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name="technicianmaster")
public class TechnicianMaster {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    private String technicianmasteruid;
    private String technicianname;
    private String contactnumber;
    private String department;
    private String skillset;
    private String availabilitystatus;
    private String insertdate;
    private String updatedate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTechnicianmasteruid() {
		return technicianmasteruid;
	}
	public void setTechnicianmasteruid(String technicianmasteruid) {
		this.technicianmasteruid = technicianmasteruid;
	}
	public String getTechnicianname() {
		return technicianname;
	}
	public void setTechnicianname(String technicianname) {
		this.technicianname = technicianname;
	}
	public String getContactnumber() {
		return contactnumber;
	}
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSkillset() {
		return skillset;
	}
	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}
	public String getAvailabilitystatus() {
		return availabilitystatus;
	}
	public void setAvailabilitystatus(String availabilitystatus) {
		this.availabilitystatus = availabilitystatus;
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
    
    
}

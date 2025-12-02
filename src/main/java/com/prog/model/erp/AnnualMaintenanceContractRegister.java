package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="annualmaintenancecontract")
public class AnnualMaintenanceContractRegister {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;	
	private String annualmaintenancecontractuid;	
	private String vendorname;	
	private String amcstartdate;	
	private String amcenddate;	
	private String equipmentmasteruid;	
	private String termsandcondition;	
	private String contactperson;	
	private String insertdate;  
    private String updatedate;    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnnualmaintenancecontractuid() {
		return annualmaintenancecontractuid;
	}

	public void setAnnualmaintenancecontractuid(String annualmaintenancecontractuid) {
		this.annualmaintenancecontractuid = annualmaintenancecontractuid;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getAmcstartdate() {
		return amcstartdate;
	}

	public void setAmcstartdate(String amcstartdate) {
		this.amcstartdate = amcstartdate;
	}

	public String getAmcenddate() {
		return amcenddate;
	}

	public void setAmcenddate(String amcenddate) {
		this.amcenddate = amcenddate;
	}

	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}

	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}

	public String getTermsandcondition() {
		return termsandcondition;
	}

	public void setTermsandcondition(String termsandcondition) {
		this.termsandcondition = termsandcondition;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
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

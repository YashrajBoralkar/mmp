package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class rawmaterialshortagealert {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;    
	    private String rawmaterialshortagealertuid;
		private String rawmaterialuid;
		private String rawmaterialname;  
	    private String minimumstocklevel;
	    private String currentstocklevel ;
	    private String alertdate;
	    private String generatedby;
	    
	    private String insertdate;
	    private String updatedate;

	  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRawmaterialshortagealertuid() {
		return rawmaterialshortagealertuid;
	}

	public void setRawmaterialshortagealertuid(String rawmaterialshortagealertuid) {
		this.rawmaterialshortagealertuid = rawmaterialshortagealertuid;
	}

	public String getRawmaterialuid() {
		return rawmaterialuid;
	}

	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}

	public String getRawmaterialname() {
		return rawmaterialname;
	}

	public void setRawmaterialname(String rawmaterialname) {
		this.rawmaterialname = rawmaterialname;
	}

	public String getMinimumstocklevel() {
		return minimumstocklevel;
	}

	public void setMinimumstocklevel(String minimumstocklevel) {
		this.minimumstocklevel = minimumstocklevel;
	}

	public String getAlertdate() {
		return alertdate;
	}
	
	

	public String getCurrentstocklevel() {
		return currentstocklevel;
	}

	public void setCurrentstocklevel(String currentstocklevel) {
		this.currentstocklevel = currentstocklevel;
	}

	public void setAlertdate(String alertdate) {
		this.alertdate = alertdate;
	}

	public String getGeneratedby() {
		return generatedby;
	}

	public void setGeneratedby(String generatedby) {
		this.generatedby = generatedby;
	}

	
	  

}

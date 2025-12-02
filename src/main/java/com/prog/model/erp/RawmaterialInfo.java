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
@Table(name="rawmaterialinfo")
public class RawmaterialInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	    private String rawmaterialuid;
	    private String rawmaterialname;
	    private String materialdescription;
	    private String category;
	    private String unitofmeasure ;
	    private String standardpurchaseprice;

	    private String leadtime;
	    private String minimumstocklevel ;
	    private String hsncode;
	    private String storageconditions;
	    private String activestatus;
	    
	    private String insertdate;
	    private String updatedate;
	    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getMaterialdescription() {
			return materialdescription;
		}

		public void setMaterialdescription(String materialdescription) {
			this.materialdescription = materialdescription;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getUnitofmeasure() {
			return unitofmeasure;
		}

		public void setUnitofmeasure(String unitofmeasure) {
			this.unitofmeasure = unitofmeasure;
		}

		public String getStandardpurchaseprice() {
			return standardpurchaseprice;
		}

		public void setStandardpurchaseprice(String standardpurchaseprice) {
			this.standardpurchaseprice = standardpurchaseprice;
		}

	
		public String getLeadtime() {
			return leadtime;
		}

		public void setLeadtime(String leadtime) {
			this.leadtime = leadtime;
		}

		public String getMinimumstocklevel() {
			return minimumstocklevel;
		}

		public void setMinimumstocklevel(String minimumstocklevel) {
			this.minimumstocklevel = minimumstocklevel;
		}

		public String getHsncode() {
			return hsncode;
		}

		public void setHsncode(String hsncode) {
			this.hsncode = hsncode;
		}

		public String getStorageconditions() {
			return storageconditions;
		}

		public void setStorageconditions(String storageconditions) {
			this.storageconditions = storageconditions;
		}

		public String getActivestatus() {
			return activestatus;
		}

		public void setActivestatus(String activestatus) {
			this.activestatus = activestatus;
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
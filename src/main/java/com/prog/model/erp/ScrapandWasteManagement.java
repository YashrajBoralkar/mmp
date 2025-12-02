package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="scarpandwastemanagement")
public class ScrapandWasteManagement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String scrapuid;
	private String productuid;
	private String scraptype;
	private String quantityscrapped;
	private String recyclingmethod;
	private String wastedisposalcampany;
	private String environmentalimpactassessment;
	private String insertdate;  
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getScrapuid() {
		return scrapuid;
	}
	public void setScrapuid(String scrapuid) {
		this.scrapuid = scrapuid;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	
	public String getScraptype() {
		return scraptype;
	}
	public void setScraptype(String scraptype) {
		this.scraptype = scraptype;
	}
	public String getQuantityscrapped() {
		return quantityscrapped;
	}
	public void setQuantityscrapped(String quantityscrapped) {
		this.quantityscrapped = quantityscrapped;
	}
	public String getRecyclingmethod() {
		return recyclingmethod;
	}
	public void setRecyclingmethod(String recyclingmethod) {
		this.recyclingmethod = recyclingmethod;
	}
	public String getWastedisposalcampany() {
		return wastedisposalcampany;
	}
	public void setWastedisposalcampany(String wastedisposalcampany) {
		this.wastedisposalcampany = wastedisposalcampany;
	}
	public String getEnvironmentalimpactassessment() {
		return environmentalimpactassessment;
	}
	public void setEnvironmentalimpactassessment(String environmentalimpactassessment) {
		this.environmentalimpactassessment = environmentalimpactassessment;
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

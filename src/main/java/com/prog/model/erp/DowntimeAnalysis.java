package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="downtimeanalysis")
public class DowntimeAnalysis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String downtimeanalysisuid;
	private String equipmentmasteruid;
	private String totaldowntime;
	private String noofbreakdown;
	private String frequentissues;
	private String preventiveactionssuggested;
	private String remarks;
	private String insertdate;  
    private String updatedate;//13
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDowntimeanalysisuid() {
		return downtimeanalysisuid;
	}
	public void setDowntimeanalysisuid(String downtimeanalysisuid) {
		this.downtimeanalysisuid = downtimeanalysisuid;
	}
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	public String getTotaldowntime() {
		return totaldowntime;
	}
	public void setTotaldowntime(String totaldowntime) {
		this.totaldowntime = totaldowntime;
	}
	
	public String getNoofbreakdown() {
		return noofbreakdown;
	}
	public void setNoofbreakdown(String noofbreakdown) {
		this.noofbreakdown = noofbreakdown;
	}
	public String getFrequentissues() {
		return frequentissues;
	}
	public void setFrequentissues(String frequentissues) {
		this.frequentissues = frequentissues;
	}
	public String getPreventiveactionssuggested() {
		return preventiveactionssuggested;
	}
	public void setPreventiveactionssuggested(String preventiveactionssuggested) {
		this.preventiveactionssuggested = preventiveactionssuggested;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

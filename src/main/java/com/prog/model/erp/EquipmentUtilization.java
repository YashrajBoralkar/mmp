package com.prog.model.erp;

import java.time.format.DateTimeFormatter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="equipmentutilization")
public class EquipmentUtilization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String equipmentutilizationuid;            
	private String equipmentmasteruid;        
    private double utilizationrate;        
    private int downtimeoccurrence;        
    private String energyconsumption;
    @Column(name = "insertdate", updatable = false)
	private String insertdate; 
	@Column(name = "updatedate")
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEquipmentutilizationuid() {
		return equipmentutilizationuid;
	}
	public void setEquipmentutilizationuid(String equipmentutilizationuid) {
		this.equipmentutilizationuid = equipmentutilizationuid;
	}
	public String getEquipmentmasteruid() {
		return equipmentmasteruid;
	}
	public void setEquipmentmasteruid(String equipmentmasteruid) {
		this.equipmentmasteruid = equipmentmasteruid;
	}
	public double getUtilizationrate() {
		return utilizationrate;
	}
	public void setUtilizationrate(double utilizationrate) {
		this.utilizationrate = utilizationrate;
	}
	public int getDowntimeoccurrence() {
		return downtimeoccurrence;
	}
	public void setDowntimeoccurrence(int downtimeoccurrence) {
		this.downtimeoccurrence = downtimeoccurrence;
	}
	
	public String getEnergyconsumption() {
		return energyconsumption;
	}
	public void setEnergyconsumption(String energyconsumption) {
		this.energyconsumption = energyconsumption;
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

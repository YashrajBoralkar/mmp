package com.prog.model.erp;

import java.time.format.DateTimeFormatter;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productionqualitycontrol")
public class ProductionQualityControl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String productionqualitycontroluid;
	private String workorderuid;	
	private String productuid;
	private String inspectorname;
	private String testperformed;
	private String measurementstandard;
	private String defectclassification;
	private String inspectionstatus;
	private String reworkrequire;
	private String rootcauseanalysisreport;
	private String insertdate;  
    private String updatedate;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProductionqualitycontroluid() {
		return productionqualitycontroluid;
	}
	public void setProductionqualitycontroluid(String productionqualitycontroluid) {
		this.productionqualitycontroluid = productionqualitycontroluid;
	}
	public String getWorkorderuid() {
		return workorderuid;
	}
	public void setWorkorderuid(String workorderuid) {
		this.workorderuid = workorderuid;
	}
	public String getProductuid() {
		return productuid;
	}
	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}
	
	public String getInspectorname() {
		return inspectorname;
	}
	public void setInspectorname(String inspectorname) {
		this.inspectorname = inspectorname;
	}
	public String getTestperformed() {
		return testperformed;
	}
	public void setTestperformed(String testperformed) {
		this.testperformed = testperformed;
	}
	public String getMeasurementstandard() {
		return measurementstandard;
	}
	public void setMeasurementstandard(String measurementstandard) {
		this.measurementstandard = measurementstandard;
	}
	public String getDefectclassification() {
		return defectclassification;
	}
	public void setDefectclassification(String defectclassification) {
		this.defectclassification = defectclassification;
	}
	public String getInspectionstatus() {
		return inspectionstatus;
	}
	public void setInspectionstatus(String inspectionstatus) {
		this.inspectionstatus = inspectionstatus;
	}
	public String getReworkrequire() {
		return reworkrequire;
	}
	public void setReworkrequire(String reworkrequire) {
		this.reworkrequire = reworkrequire;
	}
	public String getRootcauseanalysisreport() {
		return rootcauseanalysisreport;
	}
	public void setRootcauseanalysisreport(String rootcauseanalysisreport) {
		this.rootcauseanalysisreport = rootcauseanalysisreport;
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

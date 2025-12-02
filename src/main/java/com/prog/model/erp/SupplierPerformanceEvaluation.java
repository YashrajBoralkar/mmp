package com.prog.model.erp;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "supplierperformanceevaluation")
public class SupplierPerformanceEvaluation { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String supplierperformanceevaluationuid;
    private String rawmaterialsupplieruid;
    private String evaluationperiod;
    private String ontimedeliveryrate; // Auto-calculated
    private String qualityrating;
//    private String defectrate; // Auto-calculated
    private String orderaccuracy; // Auto-calculated
    private String responsivenessscore;
    private String compliancescore;
    private String overallscore; // Auto-calculated
    private String performancestatus;
    private String actionrequired;
    private String reviewedby;
    
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getSupplierperformanceevaluationuid() {
		return supplierperformanceevaluationuid;
	}


	public void setSupplierperformanceevaluationuid(String supplierperformanceevaluationuid) {
		this.supplierperformanceevaluationuid = supplierperformanceevaluationuid;
	}

	public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}


	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
	}


	public String getEvaluationperiod() {
		return evaluationperiod;
	}


	public void setEvaluationperiod(String string) {
		this.evaluationperiod = string;
	}


	public String getOntimedeliveryrate() {
		return ontimedeliveryrate;
	}


	public void setOntimedeliveryrate(String ontimedeliveryrate) {
		this.ontimedeliveryrate = ontimedeliveryrate;
	}


	public String getQualityrating() {
		return qualityrating;
	}


	public void setQualityrating(String qualityrating) {
		this.qualityrating = qualityrating;
	}

//
//	public String getDefectrate() {
//		return defectrate;
//	}
//
//
//	public void setDefectrate(String defectrate) {
//		this.defectrate = defectrate;
//	}


	public String getOrderaccuracy() {
		return orderaccuracy;
	}


	public void setOrderaccuracy(String orderaccuracy) {
		this.orderaccuracy = orderaccuracy;
	}


	public String getResponsivenessscore() {
		return responsivenessscore;
	}


	public void setResponsivenessscore(String responsivenessscore) {
		this.responsivenessscore = responsivenessscore;
	}


	public String getCompliancescore() {
		return compliancescore;
	}


	public void setCompliancescore(String compliancescore) {
		this.compliancescore = compliancescore;
	}


	public String getOverallscore() {
		return overallscore;
	}


	public void setOverallscore(String overallscore) {
		this.overallscore = overallscore;
	}


	public String getPerformancestatus() {
		return performancestatus;
	}


	public void setPerformancestatus(String performancestatus) {
		this.performancestatus = performancestatus;
	}


	public String getActionrequired() {
		return actionrequired;
	}


	public void setActionrequired(String actionrequired) {
		this.actionrequired = actionrequired;
	}


	public String getReviewedby() {
		return reviewedby;
	}


	public void setReviewedby(String reviewedby) {
		this.reviewedby = reviewedby;
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
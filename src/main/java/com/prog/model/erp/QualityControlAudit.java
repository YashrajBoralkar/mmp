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
@Table(name="qualitycontrolauditform")
public class QualityControlAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String qualitycontrolaudituid;
	private String auditdate;
	private String auditorname;
	private String pcscore;
	private String pqscore;
	private String correctiveaction;
	private String auditstatus;
	private String supervisorreview;
	private String insertdate;  
    private String updatedate;
  
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getQualitycontrolaudituid() {
		return qualitycontrolaudituid;
	}
	public void setQualitycontrolaudituid(String qualitycontrolaudituid) {
		this.qualitycontrolaudituid = qualitycontrolaudituid;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getAuditorname() {
		return auditorname;
	}
	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}
	public String getPcscore() {
		return pcscore;
	}
	public void setPcscore(String pcscore) {
		this.pcscore = pcscore;
	}
	public String getPqscore() {
		return pqscore;
	}
	public void setPqscore(String pqscore) {
		this.pqscore = pqscore;
	}
	public String getCorrectiveaction() {
		return correctiveaction;
	}
	public void setCorrectiveaction(String correctiveaction) {
		this.correctiveaction = correctiveaction;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getSupervisorreview() {
		return supervisorreview;
	}
	public void setSupervisorreview(String supervisorreview) {
		this.supervisorreview = supervisorreview;
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

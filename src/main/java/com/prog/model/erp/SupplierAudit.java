package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "supplieraudit")
public class SupplierAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String supplieraudituid;
    private String rawmaterialsupplieruid;
    private String auditdate;
    private String auditorname;
    private String qualitycompliancescore;
    private String safetystandardscompliance;
    private String deliveryperformance;
    private String correctiveactionrequired;
    private String auditoutcome;

    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

	public String getSupplieraudituid() {
		return supplieraudituid;
	}

	public void setSupplieraudituid(String supplieraudituid) {
		this.supplieraudituid = supplieraudituid;
	}

    public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}

	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
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



    public String getSafetystandardscompliance() {
        return safetystandardscompliance;
    }

    public void setSafetystandardscompliance(String safetystandardscompliance) {
        this.safetystandardscompliance = safetystandardscompliance;
    }

    public String getDeliveryperformance() {
        return deliveryperformance;
    }

    public void setDeliveryperformance(String deliveryperformance) {
        this.deliveryperformance = deliveryperformance;
    }

    public String getCorrectiveactionrequired() {
        return correctiveactionrequired;
    }

    public void setCorrectiveactionrequired(String correctiveactionrequired) {
        this.correctiveactionrequired = correctiveactionrequired;
    }

    public String getAuditoutcome() {
        return auditoutcome;
    }

    public void setAuditoutcome(String auditoutcome) {
        this.auditoutcome = auditoutcome;
    }

	public String getQualitycompliancescore() {
		return qualitycompliancescore;
	}

	public void setQualitycompliancescore(String qualitycompliancescore) {
		this.qualitycompliancescore = qualitycompliancescore;
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


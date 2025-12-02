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
@Table(name = "requestforinformation")
public class RequestForInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestforinformationuid;
    private String rfiissuedate;
    private String supplieruid;
    private String industryexpertise;
    private String productservicedetails;
    private String responsedeadline;
    private String insertdate;  
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
	public String getRequestforinformationuid() {
		return requestforinformationuid;
	}

	public void setRequestforinformationuid(String requestforinformationuid) {
		this.requestforinformationuid = requestforinformationuid;
	}

	public String getRfiissuedate() {
        return rfiissuedate;
    }

    public void setRfiissuedate(String rfiissuedate) {
        this.rfiissuedate = rfiissuedate;
    }

    
    public String getSupplieruid() {
		return supplieruid;
	}

	public void setSupplieruid(String supplieruid) {
		this.supplieruid = supplieruid;
	}

	public String getIndustryexpertise() {
        return industryexpertise;
    }

    public void setIndustryexpertise(String industryexpertise) {
        this.industryexpertise = industryexpertise;
    }

    public String getProductservicedetails() {
        return productservicedetails;
    }

    public void setProductservicedetails(String productservicedetails) {
        this.productservicedetails = productservicedetails;
    }

    public String getResponsedeadline() {
        return responsedeadline;
    }

    public void setResponsedeadline(String responsedeadline) {
        this.responsedeadline = responsedeadline;
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

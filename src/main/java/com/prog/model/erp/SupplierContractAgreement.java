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
@Table(name = "suppliercontractagreement")
public class SupplierContractAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String suppliercontractagreementuid;
    private String rawmaterialsupplieruid;
    private String contractstartdate;
    private String contractenddate;

    private String scopeofsupply;
    private String pricingagreement;
    private String qualitycompliancestandards;
    private String paymentterms;
    private String terminationclause;
    private String renewalterms;
    
    private String supplierrepresentativename;
    private String companyrepresentativename;
    private String dateofapproval;
    private String insertdate;
	private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

     
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSuppliercontractagreementuid() {
		return suppliercontractagreementuid;
	}
	public void setSuppliercontractagreementuid(String suppliercontractagreementuid) {
		this.suppliercontractagreementuid = suppliercontractagreementuid;
	}
	
	
	public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}
	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
	}
	public String getContractstartdate() {
		return contractstartdate;
	}
	public void setContractstartdate(String contractstartdate) {
		this.contractstartdate = contractstartdate;
	}
	public String getContractenddate() {
		return contractenddate;
	}
	public void setContractenddate(String contractenddate) {
		this.contractenddate = contractenddate;
	}
	public String getScopeofsupply() {
		return scopeofsupply;
	}
	public void setScopeofsupply(String scopeofsupply) {
		this.scopeofsupply = scopeofsupply;
	}
	public String getPricingagreement() {
		return pricingagreement;
	}
	public void setPricingagreement(String pricingagreement) {
		this.pricingagreement = pricingagreement;
	}
	public String getQualitycompliancestandards() {
		return qualitycompliancestandards;
	}
	public void setQualitycompliancestandards(String qualitycompliancestandards) {
		this.qualitycompliancestandards = qualitycompliancestandards;
	}
	public String getPaymentterms() {
		return paymentterms;
	}
	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}
	public String getTerminationclause() {
		return terminationclause;
	}
	public void setTerminationclause(String terminationclause) {
		this.terminationclause = terminationclause;
	}
	public String getRenewalterms() {
		return renewalterms;
	}
	public void setRenewalterms(String renewalterms) {
		this.renewalterms = renewalterms;
	}
	public String getSupplierrepresentativename() {
		return supplierrepresentativename;
	}
	public void setSupplierrepresentativename(String supplierrepresentativename) {
		this.supplierrepresentativename = supplierrepresentativename;
	}
	public String getCompanyrepresentativename() {
		return companyrepresentativename;
	}
	public void setCompanyrepresentativename(String companyrepresentativename) {
		this.companyrepresentativename = companyrepresentativename;
	}
	public String getDateofapproval() {
		return dateofapproval;
	}
	public void setDateofapproval(String dateofapproval) {
		this.dateofapproval = dateofapproval;
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

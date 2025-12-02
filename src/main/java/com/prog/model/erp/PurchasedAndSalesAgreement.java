package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchaseandsalesagreement")
public class PurchasedAndSalesAgreement 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String purchasesalesagreementuid ;
	private String agreementdate;
	private String buyername;
	private String supplieruid;
	private String contractvalue;
	private String deliveryterms;

	private String insertdate;  
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getPurchasesalesagreementuid() {
		return purchasesalesagreementuid;
	}

	public void setPurchasesalesagreementuid(String purchasesalesagreementuid) {
		this.purchasesalesagreementuid = purchasesalesagreementuid;
	}

	public String getAgreementdate() {
		return agreementdate;
	}

	public void setAgreementdate(String agreementdate) {
		this.agreementdate = agreementdate;
	}

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public String getSupplieruid() {
		return supplieruid;
	}

	public void setSupplieruid(String supplieruid) {
		this.supplieruid = supplieruid;
	}

	public String getContractvalue() {
		return contractvalue;
	}

	public void setContractvalue(String contractvalue) {
		this.contractvalue = contractvalue;
	}

	public String getDeliveryterms() {
		return deliveryterms;
	}

	public void setDeliveryterms(String deliveryterms) {
		this.deliveryterms = deliveryterms;
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

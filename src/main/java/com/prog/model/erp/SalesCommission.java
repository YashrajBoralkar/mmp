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
@Table(name="salescommission")
public class SalesCommission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String salescommissionuid;
	private String salesrepid;
	private String salesrepname;
	private String commissionperiod;
	private Double totalsales;
    private Double commissionrate;
    private Double commissionearned;
    private String approvalstatus;
    private String insertdate;
    private String updatedate;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalescommissionuid() {
		return salescommissionuid;
	}

	public void setSalescommissionuid(String salescommissionuid) {
		this.salescommissionuid = salescommissionuid;
	}

	public String getSalesrepid() {
		return salesrepid;
	}

	public void setSalesrepid(String salesrepid) {
		this.salesrepid = salesrepid;
	}

	public String getSalesrepname() {
		return salesrepname;
	}

	public void setSalesrepname(String salesrepname) {
		this.salesrepname = salesrepname;
	}

	public String getCommissionperiod() {
		return commissionperiod;
	}

	public void setCommissionperiod(String commissionperiod) {
		this.commissionperiod = commissionperiod;
	}

	public Double getTotalsales() {
		return totalsales;
	}

	public void setTotalsales(Double totalsales) {
		this.totalsales = totalsales;
	}

	public Double getCommissionrate() {
		return commissionrate;
	}

	public void setCommissionrate(Double commissionrate) {
		this.commissionrate = commissionrate;
	}

	public Double getCommissionearned() {
		return commissionearned;
	}

	public void setCommissionearned(Double commissionearned) {
		this.commissionearned = commissionearned;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
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

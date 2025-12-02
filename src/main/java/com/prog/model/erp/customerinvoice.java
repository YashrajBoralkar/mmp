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
@Table(name = "customerinvoice")
public class customerinvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerinvoiceuid;
    private String customerorderuid;
    private String customeruid;
    private String customertype;      // added
    private String companyname;       // added
    private String productuid;
    private String productname;       // added
    private String unitprice;
    private String quantitysold;
    private Double tax;
    private String applydiscount;
    private String discountapplied;
    private Double totalammount;
    private String paymentmode;
    private String invoicedate;
    
    private String insertdate;
    private String updatedate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Getters & Setters

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerinvoiceuid() {
		return customerinvoiceuid;
	}

	public void setCustomerinvoiceuid(String customerinvoiceuid) {
		this.customerinvoiceuid = customerinvoiceuid;
	}

	public String getCustomerorderuid() {
		return customerorderuid;
	}

	public void setCustomerorderuid(String customerorderuid) {
		this.customerorderuid = customerorderuid;
	}

	public String getCustomeruid() {
		return customeruid;
	}

	public void setCustomeruid(String customeruid) {
		this.customeruid = customeruid;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getProductuid() {
		return productuid;
	}

	public void setProductuid(String productuid) {
		this.productuid = productuid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getQuantitysold() {
		return quantitysold;
	}

	public void setQuantitysold(String quantitysold) {
		this.quantitysold = quantitysold;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getApplydiscount() {
		return applydiscount;
	}

	public void setApplydiscount(String applydiscount) {
		this.applydiscount = applydiscount;
	}

	public String getDiscountapplied() {
		return discountapplied;
	}

	public void setDiscountapplied(String discountapplied) {
		this.discountapplied = discountapplied;
	}

	public Double getTotalammount() {
		return totalammount;
	}

	public void setTotalammount(Double totalammount) {
		this.totalammount = totalammount;
	}

	public String getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
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

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
@Table(name = "retailinvoice")
public class retailinvoice {

	 @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	  
	 private String  retailinvoiceuid;
	 private String retailorderuid;
	 private String selleruid;
	// private String retailername;
	 private String invoicedate;
	 private String productuid;
	// private String productname;
	 private String unitprice;
	 private String quantitysold;
	 private Double tax;
	 private String applydiscount;
	 private String discountapplied;
	 private Double totalammount;
	 private String paymentmode;
	 private String insertdate;
	 private String updatedate;

			private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			

		
			public String getUnitprice() {
				return unitprice;
			}

			public void setUnitprice(String unitprice) {
				this.unitprice = unitprice;
			}

			public String getApplydiscount() {
				return applydiscount;
			}

			public void setApplydiscount(String applydiscount) {
				this.applydiscount = applydiscount;
			}

			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public String getRetailinvoiceuid() {
				return retailinvoiceuid;
			}

			public void setRetailinvoiceuid(String retailinvoiceuid) {
				this.retailinvoiceuid = retailinvoiceuid;
			}

			public String getRetailorderuid() {
				return retailorderuid;
			}

			public void setRetailorderuid(String retailorderuid) {
				this.retailorderuid = retailorderuid;
			}

			public String getSelleruid() {
				return selleruid;
			}

			public void setSelleruid(String selleruid) {
				this.selleruid = selleruid;
			}

			public String getInvoicedate() {
				return invoicedate;
			}

			public void setInvoicedate(String invoicedate) {
				this.invoicedate = invoicedate;
			}

			public String getProductuid() {
				return productuid;
			}

			public void setProductuid(String productuid) {
				this.productuid = productuid;
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

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
@Table(name = "wholesaleorder")
public class wholesaleorder {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
	    private Long id;	  
	    private String wholesaleorderuid;  
	    private String selleruid;
	    private String orderdate;
	    private String productuid;
	    private String quantity;
	    private String discount; 
	    private String totalvalue;
	    private String paymentstatus;
	    
	    private String insertdate;
	    private String updatedate;

	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getWholesaleorderuid() {
			return wholesaleorderuid;
		}

		public void setWholesaleorderuid(String wholesaleorderuid) {
			this.wholesaleorderuid = wholesaleorderuid;
		}

		public String getSelleruid() {
			return selleruid;
		}

		public void setSelleruid(String selleruid) {
			this.selleruid = selleruid;
		}

		public String getOrderdate() {
			return orderdate;
		}

		public void setOrderdate(String orderdate) {
			this.orderdate = orderdate;
		}

		public String getProductuid() {
			return productuid;
		}

		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

		public String getDiscount() {
			return discount;
		}

		public void setDiscount(String discount) {
			this.discount = discount;
		}

		public String getTotalvalue() {
			return totalvalue;
		}

		public void setTotalvalue(String totalvalue) {
			this.totalvalue = totalvalue;
		}

		public String getPaymentstatus() {
			return paymentstatus;
		}

		public void setPaymentstatus(String paymentstatus) {
			this.paymentstatus = paymentstatus;
		}

		public static DateTimeFormatter getFormatter() {
			return formatter;
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

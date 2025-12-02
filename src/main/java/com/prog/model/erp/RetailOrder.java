package com.prog.model.erp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "retailorder")
public class RetailOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String retailorderuid;
    private String selleruid;
    private String orderdate;
    private String productuid;
    private String quantityordered;
    private String totalamount;
    private String deliverydate;
    private String paymentmode;
   
    private String insertdate;
    private String updatedate;

    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getQuantityordered() {
		return quantityordered;
	}

	public void setQuantityordered(String quantityordered) {
		this.quantityordered = quantityordered;
	}


	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public String getDeliverydate() {
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
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

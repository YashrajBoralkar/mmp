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
@Table(name = "rawmaterialdeliverychallan")
public class Rawmaterialdeliverychallan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rawmaterialdeliverychallanuid;
    private String rawmaterialpurchaseorderuid;
    private String rawmaterialsupplieruid; 
    private String suppliername;
    private String rawmaterialname;
    private String rawmaterialuid;
    private String deliverydate;
    private int quantitydelivered;
    private String vehiclenumber;
    private String receivedby;

    private String insertdate;
    private String updatedate;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Getters and Setters

  
    public String getRawmaterialdeliverychallanuid() {
		return rawmaterialdeliverychallanuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRawmaterialdeliverychallanuid(String rawmaterialdeliverychallanuid) {
		this.rawmaterialdeliverychallanuid = rawmaterialdeliverychallanuid;
	}

	public String getRawmaterialsupplieruid() {
		return rawmaterialsupplieruid;
	}

	public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
		this.rawmaterialsupplieruid = rawmaterialsupplieruid;
	}

	public String getRawmaterialname() {
		return rawmaterialname;
	}

	public void setRawmaterialname(String rawmaterialname) {
		this.rawmaterialname = rawmaterialname;
	}

	public String getRawmaterialuid() {
		return rawmaterialuid;
	}

	public void setRawmaterialuid(String rawmaterialuid) {
		this.rawmaterialuid = rawmaterialuid;
	}

	public String getRawmaterialpurchaseorderuid() {
        return rawmaterialpurchaseorderuid;
    }

    public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
        this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
    }  

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

   

    public String getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(String deliverydate) {
        this.deliverydate = deliverydate;
    }

    public int getQuantitydelivered() {
        return quantitydelivered;
    }

    public void setQuantitydelivered(int quantitydelivered) {
        this.quantitydelivered = quantitydelivered;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getReceivedby() {
        return receivedby;
    }

    public void setReceivedby(String receivedby) {
        this.receivedby = receivedby;
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

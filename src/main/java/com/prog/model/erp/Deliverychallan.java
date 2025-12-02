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
@Table(name = "deliverychallan")
public class Deliverychallan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private String deliverychallanuid;
	private String orderuid;
	private String receivername;
	private String dispatchdate;
	private String vehiclenumber;
	private String drivername;
	private String signature;
	
	private String insertdate;
	private String updatedate;
	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDeliverychallanuid() {
		return deliverychallanuid;
	}

	public void setDeliverychallanuid(String deliverychallanuid) {
		this.deliverychallanuid = deliverychallanuid;
	}

	public String getOrderuid() {
		return orderuid;
	}
	public void setOrderuid(String orderuid) {
		this.orderuid = orderuid;
	}
	public String getDispatchdate() {
		return dispatchdate;
	}
	public void setDispatchdate(String dispatchdate) {
		this.dispatchdate = dispatchdate;
	}
	public String getVehiclenumber() {
		return vehiclenumber;
	}
	public void setVehiclenumber(String vehiclenumber) {
		this.vehiclenumber = vehiclenumber;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
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

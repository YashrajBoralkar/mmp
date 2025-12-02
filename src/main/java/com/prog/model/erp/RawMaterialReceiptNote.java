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
@Table(name="rawmaterialreceiptnote")
public class RawMaterialReceiptNote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rawmaterialreceiptnoteuid;
	private String rawmaterialpurchaseorderuid;
	
	private String rawmaterialdetails;
//	private String rawmaterialname;
//    private String rawmaterialuid;
//    private int orderedquantity;
//	
	private String receivername;
	private String rawmaterialstatus;
	
	private String insertdate;
	private String updatedate;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRawmaterialreceiptnoteuid() {
		return rawmaterialreceiptnoteuid;
	}

	public void setRawmaterialreceiptnoteuid(String rawmaterialreceiptnoteuid) {
		this.rawmaterialreceiptnoteuid = rawmaterialreceiptnoteuid;
	}

	public String getRawmaterialpurchaseorderuid() {
		return rawmaterialpurchaseorderuid;
	}

	public void setRawmaterialpurchaseorderuid(String rawmaterialpurchaseorderuid) {
		this.rawmaterialpurchaseorderuid = rawmaterialpurchaseorderuid;
	}

	public String getRawmaterialdetails() {
		return rawmaterialdetails;
	}

	public void setRawmaterialdetails(String rawmaterialdetails) {
		this.rawmaterialdetails = rawmaterialdetails;
	}

//	public String getRawmaterialname() {
//		return rawmaterialname;
//	}
//
//	public void setRawmaterialname(String rawmaterialname) {
//		this.rawmaterialname = rawmaterialname;
//	}
//
//	public String getRawmaterialuid() {
//		return rawmaterialuid;
//	}
//
//	public void setRawmaterialuid(String rawmaterialuid) {
//		this.rawmaterialuid = rawmaterialuid;
//	}
//
//	public int getOrderedquantity() {
//		return orderedquantity;
//	}
//
//	public void setOrderedquantity(int orderedquantity) {
//		this.orderedquantity = orderedquantity;
//	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getRawmaterialstatus() {
		return rawmaterialstatus;
	}

	public void setRawmaterialstatus(String rawmaterialstatus) {
		this.rawmaterialstatus = rawmaterialstatus;
	}

	@PrePersist
	protected void onCreate() {
		String timestamp = LocalDateTime.now().format(formatter);
		this.insertdate = timestamp;
		this.updatedate = timestamp;
		
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedate = LocalDateTime.now().format(formatter);
		
	}
	
	
	
	
}
	
	
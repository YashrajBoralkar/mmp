//package com.prog.model.erp;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.PreUpdate;
//import jakarta.persistence.Table;
//import jakarta.persistence.Transient;
//
//
//@Entity
//@Table(name="retailsalesreport")
//public class RetailSalesReport {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
//	
//	private String retailsalesreportuid;
//	private String rdate;
//	private String startdate;
//	private String enddate;
//	private String retailinvoiceuid;
//	private String remark;
//	
//	@Transient
//	private List<retailinvoice> invoiceDetails;
//	
//	private String insertdate;  
//	private String updatedate;
//	    
//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    
//      
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getRetailsalesreportuid() {
//		return retailsalesreportuid;
//	}
//	public void setRetailsalesreportuid(String retailsalesreportuid) {
//		this.retailsalesreportuid = retailsalesreportuid;
//	}
//	public String getRetailinvoiceuid() {
//		return retailinvoiceuid;
//	}
//	public void setRetailinvoiceuid(String retailinvoiceuid) {
//		this.retailinvoiceuid = retailinvoiceuid;
//	}
//	public String getRemark() {
//		return remark;
//	}
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//	@PrePersist
//    protected void onCreate() {
//        this.insertdate = LocalDateTime.now().format(formatter);
//        this.updatedate = LocalDateTime.now().format(formatter);
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        this.updatedate = LocalDateTime.now().format(formatter);
//    }	
//	
//}

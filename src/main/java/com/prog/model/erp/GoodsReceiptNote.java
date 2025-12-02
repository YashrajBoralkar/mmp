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
@Table(name = "goodsreceiptnote")
public class GoodsReceiptNote {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
        private String goodsreceiptuid;	
	    private String receiptdate;
	    private String rawmaterialsupplieruid;
	    private String warehouseuid;
	    
	    // Product Details
	    private String productuid;
	    private int quantityreceived;
	    private String remarks;
	    private String deliveryby;
	    private String deliveredquantity;
	    private String salesorderuid;
	    private String customername;
	    private String customeruid;
	    private String customertype;
	    
	    // Approval & Verification
	    private String receivedby;
	    private String verifiedby;
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
		public String getGoodsreceiptuid() {
			return goodsreceiptuid;
		}
		public void setGoodsreceiptuid(String goodsreceiptuid) {
			this.goodsreceiptuid = goodsreceiptuid;
		}
		public String getReceiptdate() {
			return receiptdate;
		}
		public void setReceiptdate(String receiptdate) {
			this.receiptdate = receiptdate;
		}
		
		public String getRawmaterialsupplieruid() {
			return rawmaterialsupplieruid;
		}
		public void setRawmaterialsupplieruid(String rawmaterialsupplieruid) {
			this.rawmaterialsupplieruid = rawmaterialsupplieruid;
		}
		public String getWarehouseuid() {
			return warehouseuid;
		}
		public void setWarehouseuid(String warehouseuid) {
			this.warehouseuid = warehouseuid;
		}
		
		public String getProductuid() {
			return productuid;
		}
		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}
		
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getReceivedby() {
			return receivedby;
		}
		public void setReceivedby(String receivedby) {
			this.receivedby = receivedby;
		}
		public String getVerifiedby() {
			return verifiedby;
		}
		public void setVerifiedby(String verifiedby) {
			this.verifiedby = verifiedby;
		}
		public String getApprovalstatus() {
			return approvalstatus;
		}
		public void setApprovalstatus(String approvalstatus) {
			this.approvalstatus = approvalstatus;
		}
		
		public int getQuantityreceived() {
			return quantityreceived;
		}
		public void setQuantityreceived(int quantityreceived) {
			this.quantityreceived = quantityreceived;
		}
		public String getDeliveryby() {
			return deliveryby;
		}
		public void setDeliveryby(String deliveryby) {
			this.deliveryby = deliveryby;
		}
		public String getDeliveredquantity() {
			return deliveredquantity;
		}
		public void setDeliveredquantity(String deliveredquantity) {
			this.deliveredquantity = deliveredquantity;
		}
		public String getSalesorderuid() {
			return salesorderuid;
		}
		public void setSalesorderuid(String salesorderuid) {
			this.salesorderuid = salesorderuid;
		}
		public String getCustomername() {
			return customername;
		}
		public void setCustomername(String customername) {
			this.customername = customername;
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

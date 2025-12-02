package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="onlinepurchaserequest")
public class OnlinePurchaseRequest {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
		private String onlinepurchaserequestuid;
		private String requestdate;
		private String requestingdepartment;
		private String requestedby;
		private String websitelink;
		private String onlinevendorname;
		private String onlinevendoremail;
		private String onlinevendorcontactnumber;
		private String paymentmethod;
		private String orderconfirmation;
		private String productuid;
		private String deliveryaddress;
		private String expecteddeliverydate;
		private String trackingnumber;
		private String courierservicename;
		private String budgetcode;
		private String finaltotalamount;
		private String availablebudget;
		private String fundingsource;
		private String financedepartmentapprovername;
		private String financedepartmentapprovalstatus;
		private String financedepartmentapprovaldate;
		private String procurementapprovername;
		private String procurementdepartmentapprovaldate;
		private String procurementdepartmentapprovalstatus;
		private String onlinepurchasequantity;
		private String totalprice;
		private String shippingcost;
		private String taxamount;
		@Lob
	    private byte[] invoiceupload;
		private String comments;
		
		private String insertdate;  
	    private String updatedate;
	    private String docName;
	    
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getOnlinepurchaserequestuid() {
			return onlinepurchaserequestuid;
		}

		public void setOnlinepurchaserequestuid(String onlinepurchaserequestuid) {
			this.onlinepurchaserequestuid = onlinepurchaserequestuid;
		}

		public String getRequestdate() {
			return requestdate;
		}

		public void setRequestdate(String requestdate) {
			this.requestdate = requestdate;
		}

		public String getRequestingdepartment() {
			return requestingdepartment;
		}

		public void setRequestingdepartment(String requestingdepartment) {
			this.requestingdepartment = requestingdepartment;
		}

		public String getRequestedby() {
			return requestedby;
		}

		public void setRequestedby(String requestedby) {
			this.requestedby = requestedby;
		}

		public String getWebsitelink() {
			return websitelink;
		}

		public void setWebsitelink(String websitelink) {
			this.websitelink = websitelink;
		}

		public String getOnlinevendorname() {
			return onlinevendorname;
		}

		public void setOnlinevendorname(String onlinevendorname) {
			this.onlinevendorname = onlinevendorname;
		}

		public String getOnlinevendoremail() {
			return onlinevendoremail;
		}

		public void setOnlinevendoremail(String onlinevendoremail) {
			this.onlinevendoremail = onlinevendoremail;
		}

		public String getOnlinevendorcontactnumber() {
			return onlinevendorcontactnumber;
		}

		public void setOnlinevendorcontactnumber(String onlinevendorcontactnumber) {
			this.onlinevendorcontactnumber = onlinevendorcontactnumber;
		}

		public String getPaymentmethod() {
			return paymentmethod;
		}

		public void setPaymentmethod(String paymentmethod) {
			this.paymentmethod = paymentmethod;
		}

		public String getOrderconfirmation() {
			return orderconfirmation;
		}

		public void setOrderconfirmation(String orderconfirmation) {
			this.orderconfirmation = orderconfirmation;
		}

		public String getProductuid() {
			return productuid;
		}

		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}

		public String getDeliveryaddress() {
			return deliveryaddress;
		}

		public void setDeliveryaddress(String deliveryaddress) {
			this.deliveryaddress = deliveryaddress;
		}

		public String getExpecteddeliverydate() {
			return expecteddeliverydate;
		}

		public void setExpecteddeliverydate(String expecteddeliverydate) {
			this.expecteddeliverydate = expecteddeliverydate;
		}

		public String getTrackingnumber() {
			return trackingnumber;
		}

		public void setTrackingnumber(String trackingnumber) {
			this.trackingnumber = trackingnumber;
		}

		public String getCourierservicename() {
			return courierservicename;
		}

		public void setCourierservicename(String courierservicename) {
			this.courierservicename = courierservicename;
		}

		public String getBudgetcode() {
			return budgetcode;
		}

		public void setBudgetcode(String budgetcode) {
			this.budgetcode = budgetcode;
		}

		public String getFinaltotalamount() {
			return finaltotalamount;
		}

		public void setFinaltotalamount(String finaltotalamount) {
			this.finaltotalamount = finaltotalamount;
		}

		public String getAvailablebudget() {
			return availablebudget;
		}

		public void setAvailablebudget(String availablebudget) {
			this.availablebudget = availablebudget;
		}

		public String getFundingsource() {
			return fundingsource;
		}

		public void setFundingsource(String fundingsource) {
			this.fundingsource = fundingsource;
		}

		public String getFinancedepartmentapprovername() {
			return financedepartmentapprovername;
		}

		public void setFinancedepartmentapprovername(String financedepartmentapprovername) {
			this.financedepartmentapprovername = financedepartmentapprovername;
		}

		public String getFinancedepartmentapprovalstatus() {
			return financedepartmentapprovalstatus;
		}

		public void setFinancedepartmentapprovalstatus(String financedepartmentapprovalstatus) {
			this.financedepartmentapprovalstatus = financedepartmentapprovalstatus;
		}

		public String getFinancedepartmentapprovaldate() {
			return financedepartmentapprovaldate;
		}

		public void setFinancedepartmentapprovaldate(String financedepartmentapprovaldate) {
			this.financedepartmentapprovaldate = financedepartmentapprovaldate;
		}

		public String getProcurementapprovername() {
			return procurementapprovername;
		}

		public void setProcurementapprovername(String procurementapprovername) {
			this.procurementapprovername = procurementapprovername;
		}

		public String getProcurementdepartmentapprovaldate() {
			return procurementdepartmentapprovaldate;
		}

		public void setProcurementdepartmentapprovaldate(String procurementdepartmentapprovaldate) {
			this.procurementdepartmentapprovaldate = procurementdepartmentapprovaldate;
		}

		public String getProcurementdepartmentapprovalstatus() {
			return procurementdepartmentapprovalstatus;
		}

		public void setProcurementdepartmentapprovalstatus(String procurementdepartmentapprovalstatus) {
			this.procurementdepartmentapprovalstatus = procurementdepartmentapprovalstatus;
		}

		public String getOnlinepurchasequantity() {
			return onlinepurchasequantity;
		}

		public void setOnlinepurchasequantity(String onlinepurchasequantity) {
			this.onlinepurchasequantity = onlinepurchasequantity;
		}
		

		public String getTotalprice() {
			return totalprice;
		}

		public void setTotalprice(String totalprice) {
			this.totalprice = totalprice;
		}

		public String getShippingcost() {
			return shippingcost;
		}

		public void setShippingcost(String shippingcost) {
			this.shippingcost = shippingcost;
		}

		public String getTaxamount() {
			return taxamount;
		}

		public void setTaxamount(String taxamount) {
			this.taxamount = taxamount;
		}

		public byte[] getInvoiceupload() {
			return invoiceupload;
		}

		public void setInvoiceupload(byte[] invoiceupload) {
			this.invoiceupload = invoiceupload;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public String getDocName() {
			return docName;
		}

		public void setDocName(String docName) {
			this.docName = docName;
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

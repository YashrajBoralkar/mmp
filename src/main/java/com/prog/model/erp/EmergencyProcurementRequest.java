package com.prog.model.erp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "emergencyprocurementrequest")
public class EmergencyProcurementRequest 
{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String emergencyprocurementrequestuid;
	  private String requestdate;
	  private String reasonforemergency;// Explanation for urgent need.
	  private String approvalstatus;//drop down Pending, Approved, Rejected. 
	  
	  private String insertdate;  
	  private String updatedate;
	    
	  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmergencyprocurementrequestuid() {
			return emergencyprocurementrequestuid;
		}

		public void setEmergencyprocurementrequestuid(String emergencyprocurementrequestuid) {
			this.emergencyprocurementrequestuid = emergencyprocurementrequestuid;
		}

		public String getRequestdate() {
			return requestdate;
		}

		public void setRequestdate(String requestdate) {
			this.requestdate = requestdate;
		}

		public String getReasonforemergency() {
			return reasonforemergency;
		}

		public void setReasonforemergency(String reasonforemergency) {
			this.reasonforemergency = reasonforemergency;
		}

		
		public String getApprovalstatus() {
			return approvalstatus;
		}

		public void setApprovalstatus(String approvalstatus) {
			this.approvalstatus = approvalstatus;
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

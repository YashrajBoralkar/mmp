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


@Entity
public class Leaveinfo {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate leave IDs
	 	private Long id;
	 
	 	private String leaveuid;
	 	private String employeeuid;
	 	private String leaveapplicationdate;
	    private String leaveType;
	    private String fromDate;
	    private String toDate;
	    private String totalDays;
	    private String reason;
	    @Lob
	    private byte[] supportingDocument;
	    private String leaveStatus;
	    private String approvedBy;
	    private String approvalDate;
	    private String remarks;
	    private String docName;
	   
	    private String insertdate;
	    private String updatedate;

	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    // Generated setters & getters by using this all file  
		
		public String getDocName() {
			return docName;
		}

		public String getLeaveuid() {
			return leaveuid;
		}

		public void setLeaveuid(String leaveuid) {
			this.leaveuid = leaveuid;
		}

		public String getEmployeeuid() {
			return employeeuid;
		}

		public void setEmployeeuid(String employeeuid) {
			this.employeeuid = employeeuid;
		}

		public void setDocName(String docName) {
			this.docName = docName;
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		

		public byte[] getSupportingDocument() {
			return supportingDocument;
		}
		
		public String getLeaveapplicationdate() {
			return leaveapplicationdate;
		}

		public void setLeaveapplicationdate(String leaveapplicationdate) {
			this.leaveapplicationdate = leaveapplicationdate;
		}

		public String getLeaveType() {
			return leaveType;
		}
		public void setLeaveType(String leaveType) {
			this.leaveType = leaveType;
		}
		public String getFromDate() {
			return fromDate;
		}
		public void setFromDate(String fromDate) {
			this.fromDate = fromDate;
		}
		public String getToDate() {
			return toDate;
		}
		public void setToDate(String toDate) {
			this.toDate = toDate;
		}
		public String getTotalDays() {
			return totalDays;
		}
		public void setTotalDays(String totalDays) {
			this.totalDays = totalDays;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public void setSupportingDocument(byte[] supportingDocument) {
			this.supportingDocument = supportingDocument;
		}
		public String getLeaveStatus() {
			return leaveStatus;
		}
		public void setLeaveStatus(String leaveStatus) {
			this.leaveStatus = leaveStatus;
		}
		public String getApprovedBy() {
			return approvedBy;
		}
		public void setApprovedBy(String approvedBy) {
			this.approvedBy = approvedBy;
		}
		public String getApprovalDate() {
			return approvalDate;
		}
		public void setApprovalDate(String approvalDate) {
			this.approvalDate = approvalDate;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
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

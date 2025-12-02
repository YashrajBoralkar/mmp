package com.prog.model.erp;
	import jakarta.persistence.*;

	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

	@Entity
	public class Offboarding {

		    @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
		    private String offboardinguid;
		    private String employeeuid; // Auto-incremented ID for the employee
		    private String lastWorkingDay;
	  		@Column(length = 500)
		    private String reasonForExit;
	  		private String noticePeriodDetails;
		    private String startDate;
		    private String endDate;
		    private String servedNoticePeriod;
		    @Column(length = 1000)
		    private String feedbackOnExit;
		    private String clearanceDetails;
			private String companyAssetReturned;
			private String nocIssued;
			private String experienceLetterIssued;
			private String finalSettlementStatus;
			
			 private String insertdate;
			 private String updatedate;

			 private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


			public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}
			

			public String getOffboardinguid() {
				return offboardinguid;
			}

			public void setOffboardinguid(String offboardinguid) {
				this.offboardinguid = offboardinguid;
			}
			

			public String getEmployeeuid() {
				return employeeuid;
			}

			public void setEmployeeuid(String employeeuid) {
				this.employeeuid = employeeuid;
			}

			public String getLastWorkingDay() {
				return lastWorkingDay;
			}

			public void setLastWorkingDay(String lastWorkingDay) {
				this.lastWorkingDay = lastWorkingDay;
			}

			public String getReasonForExit() {
				return reasonForExit;
			}

			public void setReasonForExit(String reasonForExit) {
				this.reasonForExit = reasonForExit;
			}

			public String getNoticePeriodDetails() {
				return noticePeriodDetails;
			}

			public void setNoticePeriodDetails(String noticePeriodDetails) {
				this.noticePeriodDetails = noticePeriodDetails;
			}

			public String getStartDate() {
				return startDate;
			}

			public void setStartDate(String startDate) {
				this.startDate = startDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getServedNoticePeriod() {
				return servedNoticePeriod;
			}

			public void setServedNoticePeriod(String servedNoticePeriod) {
				this.servedNoticePeriod = servedNoticePeriod;
			}

			public String getFeedbackOnExit() {
				return feedbackOnExit;
			}

			public void setFeedbackOnExit(String feedbackOnExit) {
				this.feedbackOnExit = feedbackOnExit;
			}

			public String getClearanceDetails() {
				return clearanceDetails;
			}

			public void setClearanceDetails(String clearanceDetails) {
				this.clearanceDetails = clearanceDetails;
			}

			public String getCompanyAssetReturned() {
				return companyAssetReturned;
			}

			public void setCompanyAssetReturned(String companyAssetReturned) {
				this.companyAssetReturned = companyAssetReturned;
			}

			public String getNocIssued() {
				return nocIssued;
			}

			public void setNocIssued(String nocIssued) {
				this.nocIssued = nocIssued;
			}

			public String getExperienceLetterIssued() {
				return experienceLetterIssued;
			}

			public void setExperienceLetterIssued(String experienceLetterIssued) {
				this.experienceLetterIssued = experienceLetterIssued;
			}

			public String getFinalSettlementStatus() {
				return finalSettlementStatus;
			}

			public void setFinalSettlementStatus(String finalSettlementStatus) {
				this.finalSettlementStatus = finalSettlementStatus;
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



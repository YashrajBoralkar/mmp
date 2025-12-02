package com.prog.model.erp;


	import jakarta.persistence.*;
	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

	@Entity
	public class Onboarding {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String onboardinguid;
	    private String employeeuid;
	    private String designation;
	    private String dateofjoining;
	    private String managername; // Dropdown or lookup
	    private String academiccertificates; // Dropdown: Submitted/Pending
	    private String identityproof; // Dropdown: Submitted/Pending
	    private String taxdocuments; // Dropdown: Submitted/Pending
	    private String offerlettersigned; // Dropdown: Submitted/Pending
	    private String employeeagreementsigned; // Dropdown: Submitted/Pending
	    private String systemaccess;
	    private String accesstype;
	    private String levelofaccess;
	    private String orientationdate;
	    private String mandatorytrainingprogram;
	    private String assetid;
	    private String assetname;
	    private String assettype;
	    private String assignedto;
	    private String allocationdate;
	    private String conditionatallocation;
	    private String serialnumber;
	    private String configurationdetails;
	    // Approval and Tracking
	    private String approvedby;
	    
	    private String insertdate;
	    private String updatedate;

	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		

		public String getOnboardinguid() {
			return onboardinguid;
		}

		public void setOnboardinguid(String onboardinguid) {
			this.onboardinguid = onboardinguid;
		}

		public String getEmployeeuid() {
			return employeeuid;
		}

		public void setEmployeeuid(String employeeuid) {
			this.employeeuid = employeeuid;
		}
		
		

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getDateofjoining() {
			return dateofjoining;
		}

		public void setDateofjoining(String dateofjoining) {
			this.dateofjoining = dateofjoining;
		}

		public String getManagername() {
			return managername;
		}

		public void setManagername(String managername) {
			this.managername = managername;
		}

		public String getAcademiccertificates() {
			return academiccertificates;
		}

		public void setAcademiccertificates(String academiccertificates) {
			this.academiccertificates = academiccertificates;
		}

		public String getIdentityproof() {
			return identityproof;
		}

		public void setIdentityproof(String identityproof) {
			this.identityproof = identityproof;
		}

		public String getTaxdocuments() {
			return taxdocuments;
		}

		public void setTaxdocuments(String taxdocuments) {
			this.taxdocuments = taxdocuments;
		}

		public String getOfferlettersigned() {
			return offerlettersigned;
		}

		public void setOfferlettersigned(String offerlettersigned) {
			this.offerlettersigned = offerlettersigned;
		}

		public String getEmployeeagreementsigned() {
			return employeeagreementsigned;
		}

		public void setEmployeeagreementsigned(String employeeagreementsigned) {
			this.employeeagreementsigned = employeeagreementsigned;
		}

		public String getSystemaccess() {
			return systemaccess;
		}

		public void setSystemaccess(String systemaccess) {
			this.systemaccess = systemaccess;
		}

		public String getAccesstype() {
			return accesstype;
		}

		public void setAccesstype(String accesstype) {
			this.accesstype = accesstype;
		}

		public String getLevelofaccess() {
			return levelofaccess;
		}

		public void setLevelofaccess(String levelofaccess) {
			this.levelofaccess = levelofaccess;
		}

		public String getOrientationdate() {
			return orientationdate;
		}

		public void setOrientationdate(String orientationdate) {
			this.orientationdate = orientationdate;
		}

		public String getMandatorytrainingprogram() {
			return mandatorytrainingprogram;
		}

		public void setMandatorytrainingprogram(String mandatorytrainingprogram) {
			this.mandatorytrainingprogram = mandatorytrainingprogram;
		}

		public String getAssetid() {
			return assetid;
		}

		public void setAssetid(String assetid) {
			this.assetid = assetid;
		}

		public String getAssetname() {
			return assetname;
		}

		public void setAssetname(String assetname) {
			this.assetname = assetname;
		}

		public String getAssettype() {
			return assettype;
		}

		public void setAssettype(String assettype) {
			this.assettype = assettype;
		}

		public String getAssignedto() {
			return assignedto;
		}

		public void setAssignedto(String assignedto) {
			this.assignedto = assignedto;
		}

		

		public String getAllocationdate() {
			return allocationdate;
		}

		public void setAllocationdate(String allocationdate) {
			this.allocationdate = allocationdate;
		}

		public String getConditionatallocation() {
			return conditionatallocation;
		}

		public void setConditionatallocation(String conditionatallocation) {
			this.conditionatallocation = conditionatallocation;
		}

		public String getSerialnumber() {
			return serialnumber;
		}

		public void setSerialnumber(String serialnumber) {
			this.serialnumber = serialnumber;
		}

		public String getConfigurationdetails() {
			return configurationdetails;
		}

		public void setConfigurationdetails(String configurationdetails) {
			this.configurationdetails = configurationdetails;
		}

		public String getApprovedby() {
			return approvedby;
		}

		public void setApprovedby(String approvedby) {
			this.approvedby = approvedby;
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




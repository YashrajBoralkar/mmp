package com.prog.model.erp;


	   
import jakarta.persistence.*;
	
	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
	

	@Entity
	@Table(name = "Nonconformancereport")
	public class Nonconformancereport {
	    
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for MySQL
	    private Long id;
	    
	    private String nonconformancereportuid;
	    private String productuid;

	    private String productname;
		private String department;
	    private String inspectiondate;
	    private String descriptionofdefect;
	    private String rootcauseanalysis;
	    private String defectseverity;
	    private String immediateactiontaken;
	    private String preventiveactionsuggested;
	    private String approvalstatus;
	    private String suppervisorapproval;
	    
		private String insertdate;
		private String updatedate; 
		 
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		public String getInsertdate() {
			return insertdate;
		}
		public void setInsertdate(String insertdate) {
			this.insertdate = insertdate;
		}
		public String getUpdateddate() {
			return updatedate;
		}
		public void setUpdatedate(String updatedate) {
			this.updatedate = updatedate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNonconformancereportuid() {
			return nonconformancereportuid;
		}
		public void setNonconformancereportuid(String nonconformancereportuid) {
			this.nonconformancereportuid = nonconformancereportuid;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getInspectiondate() {
			return inspectiondate;
		}
		public void setInspectiondate(String inspectiondate) {
			this.inspectiondate = inspectiondate;
		}
		
		public String getDescriptionofdefect() {
			return descriptionofdefect;
		}
		public void setDescriptionofdefect(String descriptionofdefect) {
			this.descriptionofdefect = descriptionofdefect;
		}
		public String getRootcauseanalysis() {
			return rootcauseanalysis;
		}
		public void setRootcauseanalysis(String rootcauseanalysis) {
			this.rootcauseanalysis = rootcauseanalysis;
		}
		public String getDefectseverity() {
			return defectseverity;
		}
		public void setDefectseverity(String defectseverity) {
			this.defectseverity = defectseverity;
		}
		public String getImmediateactiontaken() {
			return immediateactiontaken;
		}
		public void setImmediateactiontaken(String immediateactiontaken) {
			this.immediateactiontaken = immediateactiontaken;
		}
		public String getPreventiveactionsuggested() {
			return preventiveactionsuggested;
		}
		public void setPreventiveactionsuggested(String preventiveactionsuggested) {
			this.preventiveactionsuggested = preventiveactionsuggested;
		}
		public String getApprovalstatus() {
			return approvalstatus;
		}
		public void setApprovalstatus(String approvalstatus) {
			this.approvalstatus = approvalstatus;
		}
		public String getSuppervisorapproval() {
			return suppervisorapproval;
		}
		public void setSuppervisorapproval(String suppervisorapproval) {
			this.suppervisorapproval = suppervisorapproval;
		}
		public String getProductuid() {
			return productuid;
		}
		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
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



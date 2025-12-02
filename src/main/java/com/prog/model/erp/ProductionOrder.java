package com.prog.model.erp;
import java.time.LocalDate;
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
@Table(name = "productionorder")
public class ProductionOrder {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    private String productionorderuid;                

	    private String productuid;                        
	    private String productionplanninguid;             
	    private String workorderuid;                      
	    private String productname;                       
	   
	    private int workorderquantity;                   
	    private int productionorderquantity;                 

	    private String plannedstartdate; 
	    private String plannedenddate;  
	    private String productionorderstartdate;                 
	    private String productionorderenddate;
	    private String prioritylevel;                    
	    private String productionstatus;                  
	    private String productionremarks;                
	    private String supervisorname;

	    private String insertdate;
	    private String updatedate;
		
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getProductionorderuid() {
			return productionorderuid;
		}

		public void setProductionorderuid(String productionorderuid) {
			this.productionorderuid = productionorderuid;
		}

		public String getProductuid() {
			return productuid;
		}

		public void setProductuid(String productuid) {
			this.productuid = productuid;
		}

		public String getProductionplanninguid() {
			return productionplanninguid;
		}

		public void setProductionplanninguid(String productionplanninguid) {
			this.productionplanninguid = productionplanninguid;
		}

		public String getWorkorderuid() {
			return workorderuid;
		}

		public void setWorkorderuid(String workorderuid) {
			this.workorderuid = workorderuid;
		}

		public String getProductname() {
			return productname;
		}

		public void setProductname(String productname) {
			this.productname = productname;
		}

		public int getWorkorderquantity() {
			return workorderquantity;
		}

		public void setWorkorderquantity(int workorderquantity) {
			this.workorderquantity = workorderquantity;
		}

		

		public int getProductionorderquantity() {
			return productionorderquantity;
		}

		public void setProductionorderquantity(int productionorderquantity) {
			this.productionorderquantity = productionorderquantity;
		}

		public String getPlannedstartdate() {
			return plannedstartdate;
		}

		public void setPlannedstartdate(String plannedstartdate) {
			this.plannedstartdate = plannedstartdate;
		}

		public String getPlannedenddate() {
			return plannedenddate;
		}

		public void setPlannedenddate(String plannedenddate) {
			this.plannedenddate = plannedenddate;
		}

		public String getProductionorderstartdate() {
			return productionorderstartdate;
		}

		public void setProductionorderstartdate(String productionorderstartdate) {
			this.productionorderstartdate = productionorderstartdate;
		}

		public String getProductionorderenddate() {
			return productionorderenddate;
		}

		public void setProductionorderenddate(String productionorderenddate) {
			this.productionorderenddate = productionorderenddate;
		}

		public String getPrioritylevel() {
			return prioritylevel;
		}

		public void setPrioritylevel(String prioritylevel) {
			this.prioritylevel = prioritylevel;
		}


		public String getProductionstatus() {
			return productionstatus;
		}

		public void setProductionstatus(String productionstatus) {
			this.productionstatus = productionstatus;
		}

		public String getProductionremarks() {
			return productionremarks;
		}

		public void setProductionremarks(String productionremarks) {
			this.productionremarks = productionremarks;
		}

		public String getSupervisorname() {
			return supervisorname;
		}

		public void setSupervisorname(String supervisorname) {
			this.supervisorname = supervisorname;
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

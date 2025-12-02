package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="maintenancecosttracking")
public class MaintenanceCostTracking {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String maintenancecosttrackinguid;
	    private String equipmentmasteruid;  // Equipment ID is stored here
//	    @JsonFormat(pattern="yyyy-MM-dd")
	    private String date;
	    private String typeofmaintenance;
	    private String labourcost;
	    private String partscost;
	    private String othercosts; // Optional
	    private String totalcost; // Auto-calculated
	    
	    
	    private String insertdate;  
		private String updatedate;
		    
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMaintenancecosttrackinguid() {
			return maintenancecosttrackinguid;
		}

		public void setMaintenancecosttrackinguid(String maintenancecosttrackinguid) {
			this.maintenancecosttrackinguid = maintenancecosttrackinguid;
		}

		public String getEquipmentmasteruid() {
			return equipmentmasteruid;
		}

		public void setEquipmentmasteruid(String equipmentmasteruid) {
			this.equipmentmasteruid = equipmentmasteruid;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getTypeofmaintenance() {
			return typeofmaintenance;
		}

		public void setTypeofmaintenance(String typeofmaintenance) {
			this.typeofmaintenance = typeofmaintenance;
		}

		public String getLabourcost() {
			return labourcost;
		}

		public void setLabourcost(String labourcost) {
			this.labourcost = labourcost;
		}

		public String getPartscost() {
			return partscost;
		}

		public void setPartscost(String partscost) {
			this.partscost = partscost;
		}

		public String getOthercosts() {
			return othercosts;
		}

		public void setOthercosts(String othercosts) {
			this.othercosts = othercosts;
		}

		public String getTotalcost() {
			return totalcost;
		}

		public void setTotalcost(String totalcost) {
			this.totalcost = totalcost;
		}

		public String getInsertdate() {
			return insertdate;
		}

		public void setInsertdate(String insertdate) {
			this.insertdate = insertdate;
		}

		public String getUpdatedate() {
			return updatedate;
		}

		public void setUpdatedate(String updatedate) {
			this.updatedate = updatedate;
		}

		public static DateTimeFormatter getFormatter() {
			return formatter;
		}

		
		
	    
}

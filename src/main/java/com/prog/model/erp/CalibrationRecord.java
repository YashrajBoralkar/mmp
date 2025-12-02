package com.prog.model.erp;

import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "calibrationrecord")
public class CalibrationRecord {
			
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String calibrationrecorduid;
		private String equipmentmasteruid;
		private String serialnumber;
		private String calibrationdate; 
		private String nextduedate;
		private String performedby;
		private String status;
		private String inserteddate;
		private String updateddate;
		
	    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCalibrationrecorduid() {
			return calibrationrecorduid;
		}

		public void setCalibrationrecorduid(String calibrationrecorduid) {
			this.calibrationrecorduid = calibrationrecorduid;
		}

		public String getEquipmentmasteruid() {
			return equipmentmasteruid;
		}

		public void setEquipmentmasteruid(String equipmentmasteruid) {
			this.equipmentmasteruid = equipmentmasteruid;
		}

		public String getSerialnumber() {
			return serialnumber;
		}

		public void setSerialnumber(String serialnumber) {
			this.serialnumber = serialnumber;
		}

		public String getCalibrationdate() {
			return calibrationdate;
		}

		public void setCalibrationdate(String calibrationdate) {
			this.calibrationdate = calibrationdate;
		}

		public String getNextduedate() {
			return nextduedate;
		}

		public void setNextduedate(String nextduedate) {
			this.nextduedate = nextduedate;
		}

		public String getPerformedby() {
			return performedby;
		}

		public void setPerformedby(String performedby) {
			this.performedby = performedby;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getInserteddate() {
			return inserteddate;
		}

		public void setInserteddate(String inserteddate) {
			this.inserteddate = inserteddate;
		}

		public String getUpdateddate() {
			return updateddate;
		}

		public void setUpdateddate(String updateddate) {
			this.updateddate = updateddate;
		}

		public static DateTimeFormatter getFormatter() {
			return formatter;
		}
	    
}

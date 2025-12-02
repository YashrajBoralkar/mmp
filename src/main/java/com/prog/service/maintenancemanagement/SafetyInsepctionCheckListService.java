package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.SafetyInsepctionCheckListDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.SafetyInsepctionCheckList;

@Service
public class SafetyInsepctionCheckListService {
	@Autowired
	private SafetyInsepctionCheckListDao safetyInsepctionCheckListDao;
	
	// Save a Safety Inspection Checklist to the database
    public int saveSafetyInspection(SafetyInsepctionCheckList checklist) {
        // Generate a unique UID for the checklist entry
        String safetyinspectionchecklistUid = generatesafetyinspectionchecklistUid();
        // Set the generated UID to the checklist object
        checklist.setSafetyinspectionchecklistuid(safetyinspectionchecklistUid);
        return safetyInsepctionCheckListDao.addSafetyInspection(checklist); // Call the DAO method to persist the checklist in the database
    }
    
 // Retrieve all Safety Inspection Checklist records

 		public List<Map<String, Object>> getAllSafetyInspection() {
 		    // Call DAO method to fetch all safety inspection entries as a list of key-value pairs
 			return safetyInsepctionCheckListDao.getAllSafetyInspection();
 		}

	 // Retrieve a Safety Inspection Checklist record by its unique ID
	    public SafetyInsepctionCheckList getSafetyInspectionById(Long id) {
	        return safetyInsepctionCheckListDao.getSafetyInspectionById(id); // Call DAO method to fetch a single Safety Inspection Checklist entry using the given ID
	    } 
	 // Update an existing Safety Inspection Checklist entry
	    public int updateSafetyInspection(SafetyInsepctionCheckList checklist) {
	        return safetyInsepctionCheckListDao.updateSafetyInspection(checklist); // Call DAO method to update the checklist details in the database
	    }

	 // Delete a Safety Inspection Checklist entry by its ID
	    public int deleteSafetyInspection(Long id) {
	        return safetyInsepctionCheckListDao.deleteSafetyInspection(id); // Call DAO method to delete the checklist record from the database using the provided ID
	    }
    
 // Helper method to generate a unique Safety Inspection Checklist UID
    private String generatesafetyinspectionchecklistUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder scheduleUid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	scheduleUid.append(characters.charAt(random.nextInt(characters.length())));
        }
        // Prefix with "SIC" to indicate Safety Inspection Checklist UID and return
        return "SIC" + scheduleUid.toString();
    }

        
	public List<String> getEquipmentDetailsById(){
		return safetyInsepctionCheckListDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return safetyInsepctionCheckListDao.getEquipmentDetails(equipmentuid);
	}
}

package com.prog.service.inventory;

import java.util.List;


import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prog.Dao.inventory.RawmaterialShortageAlertDao;
import com.prog.model.erp.rawmaterialshortagealert;


@Service
public class RawmaterialShortageAlertService {

	
	@Autowired
    private RawmaterialShortageAlertDao RawmaterialShortageAlertDao;

	public int savematerialshortagealert(rawmaterialshortagealert alert) {
	    // Generate a unique UID for the Materialshortagealert return to rawmaterial entry
	    String rawmaterialshortagealertUid = generaterawmaterialshortagealertUid();

	    // Set the generated UID in the return request object
	    alert.setRawmaterialshortagealertuid(rawmaterialshortagealertUid);

	    // Save the return request details to the database using the DAO method
	    return RawmaterialShortageAlertDao.addMaterialShortageAlert(alert);
	}
	
	
	private String generaterawmaterialshortagealertUid() {
	    int length = 4; // UID should be 4 digits long
	    String characters = "0123456789"; // Allowed characters for generating UID (numeric only)
	    Random random = new Random(); // Create a new Random object for generating random numbers
	    StringBuilder rawmaterialshortagealertUid = new StringBuilder(length); // Initialize UID builder

	    // Generate a random 4-digit numeric UID
	    for (int i = 0; i < length; i++) {
	    	rawmaterialshortagealertUid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	    // Prefix the UID with "MSA" to indicate Raw Material Materialshortagealert
	    return "RMSA" + rawmaterialshortagealertUid.toString();
	}
	
	 // Retrieve rawmaterial details by rawmaterial UID
    public List<Map<String, Object>> getDataByrawmaterialUid(String rawmaterialuid) {
        return RawmaterialShortageAlertDao.getDataByrawmaterialUid(rawmaterialuid);
    }

    // Retrieve all rawmaterial UIDs
    public List<String> fetchrawmaterialUIds() {
        return RawmaterialShortageAlertDao.fetchrawmaterialUIds();
    }
	
    
    // Retrieve all Materialshortagealert records
    public List<Map<String, Object>> getAllMaterialShortageAlerts() {
		  return RawmaterialShortageAlertDao.getAllMaterialShortageAlerts(); // Calls the DAO method to fetch a list of all rawmaterial entries
	}
    
 // Retrieve an Materialshortagealert record by its ID
    public rawmaterialshortagealert getMaterialShortageAlertsById(Long id) {
        return RawmaterialShortageAlertDao.getMaterialShortageAlertsById(id); // Calls the DAO method to fetch a specific rawmaterial entry using its unique ID

    }
    
    // Delete an raw material Materialshortagealert return to  by its ID
    public int deleteMaterialShortageAlerts(Long id) {
        return RawmaterialShortageAlertDao.deleteMaterialShortageAlerts(id); // Calls the DAO method to delete a specific rawmaterial entry based on its ID

    }
	
 // Update an existing Materialshortagealert entry
    public int updateMaterialShortageAlerts(rawmaterialshortagealert alert) {
        return RawmaterialShortageAlertDao.updateMaterialShortageAlerts(alert);  // Calls the DAO method to update the raw material return to supplier details in the database

    }
}

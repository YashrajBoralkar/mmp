package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.MaintenanceCostTrackingDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceCostTracking;

@Service
public class MaintenanceCostTrackingService {
	@Autowired
	private MaintenanceCostTrackingDao maintenanceCostTrackingDao;
	
	// This method saves a new maintenance cost entry
    public int saveMaintenanceCostTracking(MaintenanceCostTracking cost) {
        String maintenancecosttrackinguid = generateCostentryuid(); // UID generator
        cost.setMaintenancecosttrackinguid(maintenancecosttrackinguid); // Set UID before saving
        return maintenanceCostTrackingDao.saveMaintenanceCostTracking(cost); // Save via DAO
    }
    
 // This method fetches all maintenance cost records (with equipment name via JOIN)
    // ➤ Called by controller to display list on the front-end
    public List<Map<String,Object>> getAllMaintenanceCostTrackingData() {
        return maintenanceCostTrackingDao.getAllMaintenanceCostTrackingData(); // DAO returns full list
    }

    // This method fetches one maintenance cost record by its ID
    // ➤ Used when editing a specific entry
    public MaintenanceCostTracking getMaintenanceCostTrackingById(Long id) {
        return maintenanceCostTrackingDao.getMaintenanceCostTrackingById(id); // DAO returns the matching record
    }
    
 // This method updates an existing maintenance cost entry
    public int  updateMaintenanceCostTracking(MaintenanceCostTracking cost) {
    	return maintenanceCostTrackingDao.updateMaintenanceCostTracking(cost); // form must send an ID so DAO knows which record to update
    }

    // This method deletes a maintenance cost entry based on ID
    public void deleteMaintenanceCostTrackingById(Long id) {
    	maintenanceCostTrackingDao.deleteMaintenanceCostTrackingById(id); // DAO executes delete query
    }

    
    // This method generates a random UID for new entries
    
    private String generateCostentryuid() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder costentryuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            costentryuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "MCT" + costentryuid.toString(); // Final UID
    }
    
    public List<String> getEquipmentDetailsById(){
		return maintenanceCostTrackingDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return maintenanceCostTrackingDao.getEquipmentDetails(equipmentuid);
	}
}

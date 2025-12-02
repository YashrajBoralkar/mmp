package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.TechnicianMasterDao;
import com.prog.model.erp.TechnicianMaster;

@Service
public class TechnicianMasterService {
	@Autowired
	private TechnicianMasterDao technicianMasterDao;
	
	/*
     * Saves a new technician record to the database.
     * Generates a unique technician UID and sets it before saving.
     */
    public int saveTechnician(TechnicianMaster technicianmaster) {
        String technicianmasteruid = generateTechnicianMasterUid(); // Generate UID
        technicianmaster.setTechnicianmasteruid(technicianmasteruid);
        return technicianMasterDao.addTechnician(technicianmaster); // Save Technician with UID
    }

    public TechnicianMaster getTechnicianMasterById(Long id) {
    	return technicianMasterDao.getTechnicianMasterById(id);
   }

	public List<Map<String, Object>> findAllTechnicianMasterList(){
		return technicianMasterDao.findAllTechnicianMasterList();
	}
	
	 /*
	   * Updates an existing technician's record.
	   */
	 public int updateTechnicianMaster(TechnicianMaster technician) {
		return technicianMasterDao.updateTechnicianMaster(technician); // saves or updates based on id
	 }

	 
	 public void deleteTechnicianMasterById(Long id) {
		 technicianMasterDao.deleteTechnicianMasterById(id);	
	}
	
    /*
     * Generates a unique Technician Master UID.
     * Format: TM + 6-digit random number (e.g., TM123456)
     */
    public String generateTechnicianMasterUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder technicianMasterUid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            technicianMasterUid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "TM" + technicianMasterUid.toString(); // Prefix with "TM" for Technician Master UID
    }
  

}

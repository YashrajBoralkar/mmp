package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.EquipmentMasterDao;
import com.prog.model.erp.EquipmentMaster;

@Service
public class EquipmentMasterService {
	@Autowired
	private EquipmentMasterDao equipmentMasterDao;

	// Save a equipmentmaster to the database
	public int saveEquipmentmaster(EquipmentMaster em) {
		// Generate a unique UID for the equipment entry
		String equipmentmasterUid = generateequipmentmasterUid();
		// Set the generated UID to the equipment object
		em.setEquipmentmasteruid(equipmentmasterUid);
		return equipmentMasterDao.addEquipmentmaster(em); // Call the DAO method to persist the equipmentmaster in the
															// database
	}

	// Helper method to generate a unique equipmentmaster UID
	private String generateequipmentmasterUid() {
		int length = 4;
		String characters = "0123456789";
		Random random = new Random();
		StringBuilder equipmentmasterUid = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			equipmentmasterUid.append(characters.charAt(random.nextInt(characters.length())));
		}
		// Prefix with "EM" to indicate equipmentmaster UID and return
		return "EM" + equipmentmasterUid.toString();
	}

	// Retrieve all equipmentmaster
	public List<Map<String, Object>> getAllEquipmentmaster() {
		return equipmentMasterDao.getAllEquipmentmaster();
	} // Call DAO to fetch all equipmentmaster

	public EquipmentMaster getEquipmentmasterById(Long id) {
		return equipmentMasterDao.getEquipmentmasterById(id);
	}

	// Delete a equipmentmaster entry by its ID
	public int deleteEquipmentmaster(Long id) {
		return equipmentMasterDao.deleteEquipmentmaster(id); // Call DAO method to delete the equipmentmaster record
	}

	// Update an existing equipmentmaster entry
	public int updateEquipmentmaster(EquipmentMaster equipment) {
		return equipmentMasterDao.updateEquipmentmaster(equipment); // Call DAO method to update the equipmentmaster
	}
	
	public List<String> getDepartmentName(){
		return equipmentMasterDao.getDepartmentName();
	}

}

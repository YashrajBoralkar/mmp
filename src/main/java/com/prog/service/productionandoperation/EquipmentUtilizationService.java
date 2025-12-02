package com.prog.service.productionandoperation;


import java.util.List;

import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prog.Dao.productionandoperation.EquipmentUtilizationDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.EquipmentUtilization;
import com.prog.model.erp.MaintenanceRequest;
import com.prog.model.erp.productionplanning;

@Service
public class EquipmentUtilizationService {
	@Autowired
	private EquipmentUtilizationDao equipmentUtilizationDao;
	
	public int saveEquipmentUtilization(EquipmentUtilization eq) {
		String equipmentutilizationuid = generateEquipmentUtilizationuid();
		eq.setEquipmentutilizationuid(equipmentutilizationuid);
		return equipmentUtilizationDao.saveEquipmentUtilization(eq);
		
	}

	private String generateEquipmentUtilizationuid() {
		   int length =4; // Length of the uid (example: 8 characters)
	        String characters = "1234567890";
	        Random random = new Random();
	        StringBuilder equipmentutilizationuid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	        	equipmentutilizationuid.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "EU" + equipmentutilizationuid.toString();
	    }
	

	public List<Map<String,Object>> findallEquipmentUtilization(){
		return equipmentUtilizationDao.findallEquipmentUtilization();
		
	}
	
	public EquipmentUtilization getEquipmentUtilizationbyid(Long id) {
		return equipmentUtilizationDao.getEquipmentUtilizationbyid(id);
	}

	 public int updateEquipmentUtilization(EquipmentUtilization eu) {
	        return equipmentUtilizationDao.updateEquipmentUtilization(eu);
	 }
	
	public void deleteEquipmentUtilization(Long id) {
		equipmentUtilizationDao.deleteEquipmentUtilization(id);
	}
	public List<String> getEquipmentMasteruid(){
		return equipmentUtilizationDao.getEquipmentMasteruid();
	}
	
	public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
		return equipmentUtilizationDao.getEquipmentMasterDetails(equipmentmasteruid);
	}
	
}

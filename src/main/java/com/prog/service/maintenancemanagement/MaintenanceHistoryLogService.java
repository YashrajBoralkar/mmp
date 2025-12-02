package com.prog.service.maintenancemanagement;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.MaintenanceHistoryLogDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceHistoryLogForm;

@Service
public class MaintenanceHistoryLogService {
	@Autowired
	private MaintenanceHistoryLogDao maintenanceHistoryLogDao;
	
	public int addMaintenanceHistoryLogData(MaintenanceHistoryLogForm mhl) {
		String mhlUID=generateMaintenanceHistoryLogUId();
		mhl.setMaintenancehistoryloguid(mhlUID);
		return maintenanceHistoryLogDao.addMaintenanceHistoryLogData(mhl);
	}
	
	public MaintenanceHistoryLogForm getMaintenanceHistoryLogById(Long id) {
		return maintenanceHistoryLogDao.getMaintenanceHistoryLogById(id);
	}
	
	public List<Map<String, Object>> getAllMaintenanceHistoryLogData(){
		return maintenanceHistoryLogDao.getAllMaintenanceHistoryLogData();
	}
	
	public int updateMaintenanceHistoryLogData(MaintenanceHistoryLogForm mhl) {
		return maintenanceHistoryLogDao.updateMaintenanceHistoryLogData(mhl);
	}
	
	public void deleteMaintenanceHistoryLogById(Long id) {
		maintenanceHistoryLogDao.deleteMaintenanceHistoryLogById(id);
	}
	
	//UID Generate
		private String generateMaintenanceHistoryLogUId() {
		    int length = 4;  
		    String characters = "1234567890";
		    Random random = new Random();
		    StringBuilder mhluid = new StringBuilder(length);

		    
		    for (int i = 0; i < length; i++) {
		    	mhluid.append(characters.charAt(random.nextInt(characters.length())));
		    }

		     return "MHL" + mhluid.toString();
		}
	
	public List<String> getEquipmentDetailsById(){
		return maintenanceHistoryLogDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return maintenanceHistoryLogDao.getEquipmentDetails(equipmentuid);
	}
}

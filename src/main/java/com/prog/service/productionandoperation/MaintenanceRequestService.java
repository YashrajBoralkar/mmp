package com.prog.service.productionandoperation;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.MaintenanceRequestDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceRequest;

@Service
public class MaintenanceRequestService {
	@Autowired
	private MaintenanceRequestDao maintenanceRequestDao;
	
	public int saveMaintenanceRequest(MaintenanceRequest mr) {
		String MaintenanceRequestuid = generateMaintenanceRequest();
		mr.setMaintenancerequestuid(MaintenanceRequestuid);
		
		return maintenanceRequestDao.saveMaintenanceRequest(mr);
	}
	
	public List<Map<String,Object>> getMaintenanceRequestList(){
		return maintenanceRequestDao.getMaintenanceRequestList();
		
	}
	public MaintenanceRequest getMaintenanceRequestbyid(Long id) {
		return maintenanceRequestDao.getMaintenanceRequestbyid(id);
	}
	
	 /* Updates an existing maintenance request.  */
    public int updateMaintenanceRequest(MaintenanceRequest request) {
        return maintenanceRequestDao.updateMaintenanceRequest(request);
    }
    /** Deletes a maintenance request by its ID. */
	public void deleteMaintenanceRequestById(Long id) {
		maintenanceRequestDao.deleteMaintenanceRequestById(id);
	}
	
	private String generateMaintenanceRequest() {
		int length = 4; // Length of the maintenancerequestuid 
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder maintenancerequestuid = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			maintenancerequestuid.append(characters.charAt(random.nextInt(characters.length())));
		}
		return "MR" + maintenancerequestuid.toString();
	}

	public List<String> getEquipmentMasteruid(){
		return maintenanceRequestDao.getEquipmentMasteruid();
	}
	
	public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
		return maintenanceRequestDao.getEquipmentMasterDetails(equipmentmasteruid);
	}
	
	public List<String> getEmployeeuid(){
		return maintenanceRequestDao.getEmployeeuid();
	}
	
	public String getEmployeeFullNameById(String employeeuid) {
	    return maintenanceRequestDao.getEmployeeFullNameById(employeeuid);
	}

}

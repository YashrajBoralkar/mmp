package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.MaintenanceWorkOrderDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceWorkOrder;

@Service
public class MaintenanceWorkOrderService {
	@Autowired
	private MaintenanceWorkOrderDao maintenanceWorkOrderDao;
	
	 // Saves a new maintenance work order with a generated UID
    public int saveMaintenanceworkorder(MaintenanceWorkOrder order) {
        String maintenanceworkorderuid = generateWorkOrderUid(); // Generate unique ID
        order.setMaintenanceworkorderuid(maintenanceworkorderuid);
        return maintenanceWorkOrderDao.saveMaintenanceworkorder(order);
    }
    
    public List<Map<String,Object>> getAllMaintenanceWorkOrder() {
        return maintenanceWorkOrderDao.getAllMaintenanceWorkOrder();
    }
    
    // Retrieves a work order by its ID
    public MaintenanceWorkOrder getMaintenanceWorkOrderById(Long id) {
        return maintenanceWorkOrderDao.getMaintenanceWorkOrderById(id);
    }
    
    // Updates an existing maintenance work order
    public int updateMaintenanceWorkOrder(MaintenanceWorkOrder order) {
        return maintenanceWorkOrderDao.updateMaintenanceWorkOrder(order);
    }

    // Deletes a work order by its ID
    public void deleteMaintenanceWorkOrder(Long id) {
    	maintenanceWorkOrderDao.deleteMaintenanceWorkOrder(id);
    }
    
 // Generates a unique work order UID
    private String generateWorkOrderUid() {
        Random random = new Random();
        int number = 1000 + random.nextInt(9000); // Generates a number between 1000-9999
        return "MIWO" + number; // Prefix for maintenance work order UID
    }
    
    public List<String> getEquipmentDetailsById(){
		return maintenanceWorkOrderDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return maintenanceWorkOrderDao.getEquipmentDetails(equipmentuid);
	}
}

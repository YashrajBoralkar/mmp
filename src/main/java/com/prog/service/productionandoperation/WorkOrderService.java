package com.prog.service.productionandoperation;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.WorkOrderDao;
import com.prog.model.erp.WorkOrder;

@Service
public class WorkOrderService {
	@Autowired
	private WorkOrderDao workOrderDao;

	// save
	public void save(WorkOrder workorder) {
		String woUID = generateWoUId();
		workorder.setWorkorderuid(woUID);
		workOrderDao.add(workorder);
	}

	// to show list
	public List<Map<String, Object>> fetchAllData() {
		return workOrderDao.findallplan();
	}

	// update the list
	public WorkOrder getWorkOrderById(long id) {
		return workOrderDao.getWorkOrderById(id);
	}

	public void updateworkorder(WorkOrder updatewo) {
		workOrderDao.updateworkorder(updatewo);
	}

	// delete the data
	public void deleteById(Long id) {
		workOrderDao.deleteById(id);

	}

	// UID Generate
	private String generateWoUId() {
		int length = 4; // Length of the PuId (for example 8 characters)
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder Woid = new StringBuilder(length);
		// Generate random characters for the stiUId
		for (int i = 0; i < length; i++) {
			Woid.append(characters.charAt(random.nextInt(characters.length())));
		}
		return "WO" + Woid.toString();
	}

	public List<String> getProductionPlanningUId() {
		return workOrderDao.getProductionPlanningUId();
	}
	
	public List<Map<String,Object>> getProductDetalisByProductionPlanningUID(String productionplanninguid){
		return workOrderDao.getProductDetalisByProductionPlanningUID(productionplanninguid);
	}
	
	//-----------//
	
	public List<Map<String, Object>> getProductionPlanningByProductUid(String productuid) {
        return workOrderDao.getProductionPlanningByProductUid(productuid);
    }
	
	public List<Map<String, Object>> getAllProducts() {
	    return workOrderDao.getAllProducts();
	}


    public List<Map<String, Object>> getApprovedRawMaterials(String productionplanninguid) {
        return workOrderDao.getApprovedRawMaterials(productionplanninguid);
    }

   
	

}

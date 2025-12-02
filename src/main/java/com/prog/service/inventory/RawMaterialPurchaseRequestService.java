package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.RawMaterialPurchaseRequestDAO;
import com.prog.model.erp.RawMaterialPurchaseRequest;


@Service
public class RawMaterialPurchaseRequestService {

	    @Autowired
	    RawMaterialPurchaseRequestDAO rawmaterialpurchaserequestdao;

	    /**
	     * Saves a new material request with a generated request ID
	     */
	    public int saveMaterialRequest(RawMaterialPurchaseRequest request) {
	        return rawmaterialpurchaserequestdao.saveRequests(request);
	    }
	    
	    /**
	     * Updates an existing material request by ID
	     */
	    public int updateMaterialRequest(RawMaterialPurchaseRequest updatedRequest) {
	        return rawmaterialpurchaserequestdao.updateMaterialRequest(updatedRequest);
	    }

	    /**
	     * Retrieves all material requests with joined product/raw material names
	     */
	    public List<Map<String, Object>> getAllMaterialRequests() {
	        return rawmaterialpurchaserequestdao.getAllMaterialRequests();
	    }

	    /**
	     * Deletes a material request by its ID
	     */
	    public void deleteRequest(String rawmaterialpurchaserequestuid) {
	    	rawmaterialpurchaserequestdao.deleteRequest(rawmaterialpurchaserequestuid);
	    }

	    /**
	     * Fetches material summary (name + required qty) by production planning UID
	     */
	    public List<Map<String, Object>> getMaterialSummaryByPlanningUID(String productionplanninguid) {
	        return rawmaterialpurchaserequestdao.getMaterialSummaryByPlanningUID(productionplanninguid);
	    }

	    /**
	     * Fetches all production planning UIDs along with associated product names
	     */
	    public List<Map<String, Object>> getAllProductionPlanningUIDs() {
	        return rawmaterialpurchaserequestdao.getAllProductionPlanningUIDs();
	    }

	    /**
	     * Fetches product name for a given production planning UID
	     */
	    public Map<String, Object> getProductNameByPlanningUID(String productionplanninguid) {
	        return rawmaterialpurchaserequestdao.getProductNameByPlanningUID(productionplanninguid);
	    }

	    /**
	     * Generates a random 4-digit material request ID with 'MRID' prefix
	     */
	    private String generateMaterialRequestUid() {
	        int length = 4;
	        String characters = "1234567890";
	        Random random = new Random();
	        StringBuilder uid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            uid.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        return "RMPR" + uid.toString();
	    }
	    public List<Map<String, Object>> getRequestsByRequestId(String rawmaterialpurchaserequestuid) {
	        return rawmaterialpurchaserequestdao.getRequestsByRequestId(rawmaterialpurchaserequestuid);
	    }
	    
	    // ✅ Get employee names with UID for dropdowns (used in manager/approver fields)
	    public List<Map<String, String>> getEmployeeNames() {
	        return rawmaterialpurchaserequestdao.getEmployeeNames();
	    }
	    
	}



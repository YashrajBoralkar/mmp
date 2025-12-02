package com.prog.service.purchase;

import java.util.List;



import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.NonCompetitivePurchaseRequestDAO;
import com.prog.model.erp.noncompetitivepurchaserequest;

@Service
public class NonCompetitivePurchaseRequestService {


	    @Autowired
	    private NonCompetitivePurchaseRequestDAO nonCompetitivePurchaseRequestDAO;

	    public int savePurchaseRequest(noncompetitivepurchaserequest request) {
	        String noncompetitiverequestUid = generatenoncompetitiverequestUid();
	        request.setNoncompetitivepurchaserequestuid(noncompetitiverequestUid);
	        return nonCompetitivePurchaseRequestDAO.addPurchaseRequest(request); // Call DAO to save customer quotation
	    }

	    

	    public List<Map<String, Object>> getAllPurchaseRequest() {
	        return nonCompetitivePurchaseRequestDAO.getAllPurchaseRequest(); // Call DAO to fetch all physical counts
	    }

	    public noncompetitivepurchaserequest getPurchaseRequestById(Long id) {
	        return nonCompetitivePurchaseRequestDAO.getPurchaseRequestById(id); // Call DAO to fetch physical count by ID
	    }  
	    
	    public int updatePurchaseRequest(noncompetitivepurchaserequest request) {
	        return nonCompetitivePurchaseRequestDAO.updatePurchaseRequest(request); // Call DAO to update customer quotation
	    }
	    
	    
	    
	    public int deletePurchaseRequest(Long id) {
	        return nonCompetitivePurchaseRequestDAO.deletePurchaseRequest(id); // Call DAO to delete customer quotation by ID
	    }


		 // Helper method to generate a unique UID
	    private String generatenoncompetitiverequestUid() {
	        int length = 4;
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder noncompetitiverequestUid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	        	noncompetitiverequestUid.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "NCPR" + noncompetitiverequestUid.toString();
	    }





//	    // Retrieve product details by product UID
//	    public List<Map<String, Object>> getDataBySupplieruid(String supplieruid) {
//	        return nonCompetitivePurchaseRequestDAO.getDataBySupplieruid(supplieruid);
//	    }
//
//	    // Retrieve all product UIDs
//	    public List<String> fetchSupplierUIds() {
//	        return nonCompetitivePurchaseRequestDAO.fetchSupplierUIds();
//	    }
//
//	    
//	 // Retrieve product details by product UID
//	    public List<Map<String, Object>> getDataByProductUid(String productuid) {
//	        return nonCompetitivePurchaseRequestDAO.getDataByProductUid(productuid);
//	    }
//
//	    // Retrieve all product UIDs
//	    public List<String> fetchProductUIds() {
//	        return nonCompetitivePurchaseRequestDAO.fetchProductUIds();
//	    }
//
//	    
//	 // Retrieve product details by product UID
//	    public List<Map<String, Object>> getDataByPLogisticsUid(String logisticsuid) {
//	        return nonCompetitivePurchaseRequestDAO.getDataByPLogisticsUid(logisticsuid);
//	    }
//
//	    // Retrieve all product UIDs
//	    public List<String> fetchLogisticsUIds() {
//	        return nonCompetitivePurchaseRequestDAO.fetchLogisticsUIds();
//	    }
		
}

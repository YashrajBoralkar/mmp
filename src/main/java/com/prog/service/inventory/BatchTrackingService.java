package com.prog.service.inventory;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.BatchTrackingDAO;




@Service
public class BatchTrackingService {
	
	
	@Autowired
    private BatchTrackingDAO batchtrackingDAO;

	public List<Map<String, Object>> fetchBatchDetailsByBatchId(String batchuid) {
	    return batchtrackingDAO.getBatchDetailsByBatchId(batchuid);
	}

	
	
	public List<String> fetchBatchId(){
		return batchtrackingDAO.getBatchId();
		
	}
	
	
	public ResponseEntity<Map<String, Integer>> updateBatchTrackingDetails(Map<String, Object> batchDetails) {
	    if (batchDetails == null || batchDetails.isEmpty()) {
	        throw new IllegalArgumentException("Batchinfo details cannot be null or empty.");
	    }
	    return batchtrackingDAO.updateBatchDetails(batchDetails);
	}



}

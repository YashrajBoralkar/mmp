package com.prog.service.inventory;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.LotTrackingDAO;


@Service
public class LotTrackingService {
	
	@Autowired
	private LotTrackingDAO lottrackingdao;
	
	
	public List<Map<String, Object>> fetchLotDetailsByLotid(String lotuid){
		return lottrackingdao.getLotDetailsByLotId(lotuid);
	}
	
	
	public List<String> fetchlotId(){
		return lottrackingdao.getLotId();
	}
	
	
	public ResponseEntity<Map<String, Integer>> updateLotTrackingDetails(Map<String, Object> LotDetails) {
	    if (LotDetails == null || LotDetails.isEmpty()) {
	        throw new IllegalArgumentException("Lot details cannot be null or empty.");
	    }
	    return lottrackingdao.updateLotDetails(LotDetails);
	}

}

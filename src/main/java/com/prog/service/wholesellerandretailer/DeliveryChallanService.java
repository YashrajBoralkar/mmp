package com.prog.service.wholesellerandretailer;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.wholesellerandretailer.DeliveryChallanDao;
import com.prog.model.erp.Deliverychallan;


@Service
public class DeliveryChallanService {
	@Autowired
	private DeliveryChallanDao dcDao;

	public int postChallan(Deliverychallan challan) {
		String puid = generateChallanUid();
		challan.setDeliverychallanuid(puid);
		return dcDao.postChallan(challan);
	}
	
	public void deleteChallan(Long dcid) {
		dcDao.deleteChallan(dcid);
	}
	
	public List<Map<String, Object>> getAllChallan() {
		return dcDao.getAllChallan();
	}
	
	public List<Map<String, Object>> getChallanById(Long dcid) {
		return dcDao.getChallanById(dcid);
	}
	
	public int updateChallan(Deliverychallan challan) {
		return dcDao.updateChallan(challan);
	}

	private String generateChallanUid() {
	    int length = 4;  // Length of the UID
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder uid = new StringBuilder(length);

	    // Generate random characters for the UID
	    for (int i = 0; i < length; i++) {
	        uid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	    return "DC" + uid.toString();
	}

}

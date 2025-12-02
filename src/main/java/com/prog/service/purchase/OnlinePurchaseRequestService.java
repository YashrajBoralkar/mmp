package com.prog.service.purchase;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.OnlinePurchaseRequestDao;
import com.prog.model.erp.OnlinePurchaseRequest;


@Service
public class OnlinePurchaseRequestService {
	@Autowired
	private OnlinePurchaseRequestDao onlinePurchaseRequestDao;
	
	public int addOnlinePurchaseRequest(OnlinePurchaseRequest opr) {
		String oprUID=generateOPRUId();
		opr.setOnlinepurchaserequestuid(oprUID);
		return onlinePurchaseRequestDao.addOnlinePurchaseRequest(opr);
	}
	
	public List<Map<String,Object>> getAllOnlinePurchaseRequest(){
		return onlinePurchaseRequestDao.getAllOnlinePurchaseRequest();
	}
	
	public OnlinePurchaseRequest getOnlinePurchaseRequestById(Long id) {
		return onlinePurchaseRequestDao.getOnlinePurchaseRequestById(id);
	}
	public int updateOnlinePurchaseRequestData(OnlinePurchaseRequest opr) {
		return onlinePurchaseRequestDao.updateOnlinePurchaseRequestData(opr);
	}
	
	public void deleteOnlinePurchaseRequestById(Long id) {
		onlinePurchaseRequestDao.deleteOPRFormDataById(id);
	}
	// UID Generate
		 	private String generateOPRUId() {
		 	    int length = 4;  // Length of the PuId (for example 8 characters)
		 	    String characters = "1234567890";
		 	    Random random = new Random();
		 	    StringBuilder opruid = new StringBuilder(length);

		 	    // Generate random characters for the stiUId
		 	    for (int i = 0; i < length; i++) {
		 	    	opruid.append(characters.charAt(random.nextInt(characters.length())));
		 	    }

		 	     return "OPR" + opruid.toString();
		 	}
//	public List<String>	getProductDetalisByUId(){
//		return onlinePurchaseRequestDao.getProductDetailsByuid();
//	}
//	public ProductDetails getProductDetails(String productuid){
//		return onlinePurchaseRequestDao.getProductDetails(productuid);
//	}
}

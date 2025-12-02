//package com.prog.service.inventory;
//
//import java.util.List;
//
//import java.util.Map;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.prog.Dao.inventory.ExpiryDAO;
//import com.prog.model.inventory.ExpiryTracking;
//
//
//@Service
//public class ExpiryService {
//
//	@Autowired
//	private ExpiryDAO expirydao;
//	
//	public int saveExpiry(ExpiryTracking expirytracking) {
//		String expiryuid = generateExpiryuid();
//		expirytracking.setExpiryuid(expiryuid);
//		return expirydao.addExpiry(expirytracking);
//	}
//	private String generateExpiryuid() {
//        int length =4; // Length of the Expiryuid (example: 8 characters)
//        String characters = "1234567890";
//        Random random = new Random();
//        StringBuilder expiryuid = new StringBuilder(length);
//
//        for (int i = 0; i < length; i++) {
//        	expiryuid.append(characters.charAt(random.nextInt(characters.length())));
//        }
//        return "EXT" + expiryuid.toString();
//    }
//	
//	public List<Map<String,Object>>getExpiryTracking(){
//		return expirydao.showthefindall();
//		
//	}
//	
//	public List<Map<String,Object>> getproductdetailsbyProductuid(String productuid) {
//		return expirydao.getproductdetailsbyproductuid(productuid);
//	}
//	
//	public void deleteexpiry(Long id) {
//		expirydao.deleteexpiry(id);
//	}
//	
//	public ExpiryTracking getExpiryTrackingbyid(Long id) {
//		return expirydao.getExpiryTrackingbyid(id);
//	}
//	
//	public void updateExpiryTracking(ExpiryTracking expirytracking) 
//	{
//		expirydao.updateExpiryTracking(expirytracking);
//	}
//	
//}

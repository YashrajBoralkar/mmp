package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.PurchaseRequisitionDao;
import com.prog.model.erp.PurchaseRequisition;



@Service
public class PurchaseRequistionService 
{
	 @Autowired
     private PurchaseRequisitionDao prequisition;
//============================================================================================================================	 
	 private String generatepurchaserUID() {
	        int length = 4;  // Length of the UID (for example 8 characters)
	        String characters = "1234567890";
	        Random random = new Random();
	        StringBuilder StId = new StringBuilder(length);

	        // Generate random characters for the UID
	        for (int i = 0; i < length; i++) {
	            StId.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        return "PR" + StId.toString();
	 }
	 
//==================================================================================================================

	 public List<Map<String, Object>> getAllPurchasedLevels() {
	        return prequisition.getPurchasedLevelWithiteemhouse();  // Fetches data from DAO
	    }
		 
	 
	 
	 
	 public int savepurchaserequisiton(PurchaseRequisition pr) 
	 {
		String requisitionuid=generatepurchaserUID();
		pr.setRequisitionuid(requisitionuid);
	  return prequisition.savePurchaseRequisitionForm(pr) ;
	  
	    }
//==================================================================================================================

	 
	 public List<PurchaseRequisition>getallpurchasedreq()
	 {
		 return prequisition.getallpurchaserequest();
		 
	 }
	 
//=========================================================================================================================

		
	 public PurchaseRequisition getprformbyruid(long id) {
		    return prequisition.getPrbyRequisitionUid(id);
		}

	 
//=========================================================================================================================
	
	public void deleteprform(long id) 
	{
		
		prequisition.deletepurchased(id);
	}
	
	
//=========================================================================================================================
	
	public void updatepurchaseform(PurchaseRequisition pr) 
	{
		prequisition.updatePrByUid(pr);
	}
//=========================================================================================================================

}

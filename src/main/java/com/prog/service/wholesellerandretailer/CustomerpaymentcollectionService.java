package com.prog.service.wholesellerandretailer;

import java.util.List;



import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.wholesellerandretailer.CustomerpaymentcollectionDAO;
import com.prog.model.erp.customerpaymentcollection;
@Service
public class CustomerpaymentcollectionService {

	 @Autowired
	    private CustomerpaymentcollectionDAO customerpaymentcollectionDAO;
	 
	// Save a Retailer Payment Collection record to the database
	    public int saveCustomerpaymentcollection(customerpaymentcollection payment) {
	        // Generate a unique UID for the payment collection entry
	        String customerpaymentcollectionUid = generateretailerpaymentcollectionUid();
	        // Set the generated UID into the payment object
	        payment.setcustomerpaymentcollectionuid(customerpaymentcollectionUid);
	        return customerpaymentcollectionDAO.addCustomerPaymentCollection(payment); // Call DAO to save the payment collection data into the database

	    }
	    
	    
	 // Helper method to generate a unique Customer Payment Collection UID
	    private String generateretailerpaymentcollectionUid() {
	        int length = 4;
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder customerpaymentcollectionUid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	        	customerpaymentcollectionUid.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        // Prefix with 'RPC' to create a unique identifier
	        return "CPC" + customerpaymentcollectionUid.toString();
	    }


	    
	 // Update an existing Retailer Payment Collection entry
	    public int updateCustomerPaymentCollection(customerpaymentcollection payment) {
	        return customerpaymentcollectionDAO.updateCustomerPaymentCollection(payment);     // Call the DAO method to update the Retailer Payment Collection details in the database

	    }
	    
	 // Delete a Retailer Payment Collection record by its ID
	    public int deleteCustomerPaymentCollection(Long id) {
	        return customerpaymentcollectionDAO.deleteCustomerPaymentCollection(id); // Call the DAO method to delete the record from the database using the provided ID

	    }
		
	    
	    
	 // Retrieve a Physical Count by its ID
	    public customerpaymentcollection getCustomerPaymentCollectionById(Long id) {
	        return customerpaymentcollectionDAO.getCustomerPaymentCollectionById(id); // Call DAO to fetch physical count by ID
	    }
	
	 

	 // Retrieve all Retailer Payment Collection records
		public List<Map<String, Object>> getAllCustomerPaymentCollection() {
			  return customerpaymentcollectionDAO.getAllCustomerPaymentCollection();  // Call DAO to fetch all retailer payment collection data as a list of key-value pairs

		}
	    
	    
	    
}

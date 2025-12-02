package com.prog.service.wholesellerandretailer;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.wholesellerandretailer.CustomerorderDAO;
import com.prog.model.erp.CustomerOrder;
import com.prog.model.erp.CustomerRegistration;




@Service
public class CustomerOrderService {


    @Autowired
    private CustomerorderDAO customerorderDAO;
    
 // Save a Wholesale Order to the database
    public int saveWholesaleOrder(CustomerOrder order) {
        // Generate a unique UID for the order based on customer type
        String customerOrderUid = generateCustomerOrderUid(order.getCustomertype());
        order.setCustomerorderuid(customerOrderUid);
        return customerorderDAO.addWholesaleOrder(order);
    }
	
	
	
	
	// Retrieve all Wholesale Orders
    public List<Map<String, Object>> getAllWholesaleOrder() {
        return customerorderDAO.getAllWholesaleOrder();  // Call DAO layer to fetch all wholesale order records from the database

    }
    
 // Delete a Wholesale Order by its ID
    public int deleteWholesaleOrder(Long id) {
        return customerorderDAO.deleteWholesaleOrder(id);   // Call the DAO method to delete the wholesale order with the given ID from the database

    }
    
// Retrieve a Wholesale Order by its ID
    public CustomerOrder getWholesaleOrderById(Long id) {
        return customerorderDAO.getWholesaleOrderById(id); // Call DAO method to fetch a wholesale order record from the database based on the given ID

    }
    
 // Update a Wholesale Order
    public int updateWholesaleOrder(CustomerOrder order) {
        return customerorderDAO.updateWholesaleOrder(order);  // Call the DAO method to update the wholesale order details in the database

    }

	 

		
 // Helper method to generate a unique Wholesale Order UID
	    // Helper method to generate order UID based on type
	    private String generateCustomerOrderUid(String type) {
	        int length = 4; // Random number length
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder randomPart = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            randomPart.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        String prefix;
	        if ("Wholesaler".equalsIgnoreCase(type)) {
	            prefix = "WHO";
	        } else if ("Retailer".equalsIgnoreCase(type)) {
	            prefix = "RETO";
	        } else {
	            prefix = "OTHO";
	        }

	        return prefix + randomPart.toString();
	    }
	    
	    public List<String> getCustomerUidsByType(String type) {
	        return customerorderDAO.getCustomerUidsByType(type);
	    }

	    public CustomerRegistration getCustomerDetailsByUid(String uid) {
	        return customerorderDAO.getCustomerDetailsByUid(uid);
	    }
	    
	    // Fetch order by ID
	    public 	CustomerOrder findById(Long id) {
	        return customerorderDAO.findById(id);
	    }

	    // Fetch customer registration by UID
	    public CustomerRegistration findByCustomerUid(String customeruid) {
	        return customerorderDAO.findByCustomerUid(customeruid);
	    }
	    
	    public List<String>getCustomerOrderUId(){
	      	return customerorderDAO.getCustomerOrderUId();
	      }
	   	public List<Map<String, Object>> getCustomerOrderDetailsByuid(String retailorderuid){
	   		return customerorderDAO.getCustomerOrderDetailsByuid(retailorderuid);
	   	}

	 
}

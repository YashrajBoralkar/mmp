package com.prog.service.wholesellerandretailer;

import java.util.List;


import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.wholesellerandretailer.CustomerInvoiceDAO;
import com.prog.model.erp.customerinvoice;


@Service
public class CustomerInvoiceService {

	@Autowired
	private CustomerInvoiceDAO customerinvoicedao;
	
	public int saveinvoice(customerinvoice customerinvoice) {
		String customerinvoiceuid = generateCustomerinvoiceuid();
		customerinvoice.setCustomerinvoiceuid(customerinvoiceuid);
		return customerinvoicedao.addInvoice(customerinvoice);
	}
	private String generateCustomerinvoiceuid() {
        int length =4; // Length of the retailinvoiceuid (example: 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder customerinvoiceuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	customerinvoiceuid.append(characters.charAt(random.nextInt(characters.length())));
        }
       return "CINV" + customerinvoiceuid.toString();
    }
	
	
	public List<Map<String,Object>> getAllCustomerInvoice(){
		return customerinvoicedao.getAllCustomerInvoice();
		
	}
	public customerinvoice getRetailInvoicebyid(Long id) {
		return customerinvoicedao.getCustomerInvoicebyid(id);
	}
	public void deleteinvoice(Long id) {
		customerinvoicedao.deleteInvoice(id);
		}
	public void updateCustomerInvoice(customerinvoice customerinvoice) 
	{
		customerinvoicedao.updateInvoice(customerinvoice);;
	}
	
	//FETCHING PURPOSE COMBINE METHOD
	
	// Retrieve a Retailer Payment Collection record by its ID
    public List<Map<String, Object>> getDataByretailinvoiceUid(String customerinvoiceuid) {
        return customerinvoicedao.getDataByretailinvoiceUid(customerinvoiceuid);// Call DAO to fetch the Retailer Payment Collection entry using the provided ID

    }

 // Retrieve all Retail Invoice UIDs
    public List<String> fetchretailinvoiceUIds() {
        return customerinvoicedao.fetchretailinvoiceUIds(); // Call DAO method to fetch a list of all retail invoice UIDs from the database

    }
    
    
//--------------------------------------------------------------Customer Registration and Order Table------------------------------------------------------------------------------------------------------
    
    
    public List<String> getCustomerUidsByType(String customertype) {
        return customerinvoicedao.getCustomerUidsByType(customertype);
    }

    public String getCompanyName(String customeruid) {
        return customerinvoicedao.getCompanyName(customeruid);
    }
    
    public List<Map<String, String>> getOrdersByCustomerUid(String customeruid) {
        return customerinvoicedao.getOrdersByCustomerUid(customeruid);
    }

    public List<Map<String, Object>> getOrderDetailsByUid(String customerorderuid) {
        return customerinvoicedao.getOrderDetailsByUid(customerorderuid);
    }
    
    
  //----------------------------------Customer Invoice Table Data Fetching---------------------------------------------


    public List<Map<String, Object>> getDataByCustomerInvoiceUid(String customerinvoiceuid) {
        return customerinvoicedao.getDataByCustomerInvoiceUid(customerinvoiceuid);
    }

    public List<String> fetchCustomerInvoiceUIds() {
        return customerinvoicedao.fetchCustomerInvoiceUIds();
    }


	
}

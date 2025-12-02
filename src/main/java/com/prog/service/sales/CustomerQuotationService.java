package com.prog.service.sales;



import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.sales.CustomerQuotationDAO;
import com.prog.model.erp.customerquotation;


@Service
public class CustomerQuotationService {

    @Autowired
    private CustomerQuotationDAO customerQuotationDAO;

    // Save a Customer Quotation to the database
    public int saveCustomerQuotation(customerquotation quotation) {
        String customerquotationUid = generateCustomerquotationUid();
        quotation.setCustomerquotationuid(customerquotationUid);
        return customerQuotationDAO.addCustomerQuotation(quotation); // Call DAO to save customer quotation
    }

    // Retrieve all Customer Quotations
    public List<Map<String, Object>> getAllCustomerQuotations() {
        return customerQuotationDAO.getAllQuotations(); // Call DAO to fetch all customer quotations
    }

   //  Retrieve a Customer Quotation by its ID
    public customerquotation getCustomerQuotationById(Long id) {
        return customerQuotationDAO.getQuotationById(id); // Call DAO to fetch customer quotation by ID
    }

    // Update a Customer Quotation
    public int updateCustomerQuotation(customerquotation quotation) {
        return customerQuotationDAO.updateCustomerQuotation(quotation); // Call DAO to update customer quotation
    }

    // Delete a Customer Quotation by its ID
    public int deleteCustomerQuotation(Long id) {
        return customerQuotationDAO.deleteQuotation(id); // Call DAO to delete customer quotation by ID
    }

    // Helper method to generate a unique Quotation UID
    private String generateCustomerquotationUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder quotationUid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            quotationUid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "CTQ" + quotationUid.toString();
    }


    
    public List<String> fetchCustomerUIds() {
        return customerQuotationDAO.fetchCustomerUIds();
    }
    
    
    public List<Map<String, Object>> getDataByCustomerUid(String customeruid) {
        return customerQuotationDAO.getDataByCustomerUid(customeruid);
    }

	 // Retrieve product details by product UID
	    public List<Map<String, Object>> getDataByProductUid(String productuid) {
	        return customerQuotationDAO.getDataByProductUid(productuid);
	    }

	    // Retrieve all product UIDs
	    public List<String> fetchProductUIds() {
	        return customerQuotationDAO.fetchProductUIds();
	    }
}

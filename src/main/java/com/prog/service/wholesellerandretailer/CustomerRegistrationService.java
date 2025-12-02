package com.prog.service.wholesellerandretailer;

import java.util.List;


import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.wholesellerandretailer.CustomerRegistrationDao;
import com.prog.model.erp.CustomerDetails;
import com.prog.model.erp.CustomerRegistration;
import com.prog.model.erp.RetailOrder;



@Service
public class CustomerRegistrationService {

    @Autowired
    private CustomerRegistrationDao customerDao;

    public int addRegistration(CustomerRegistration customer) {

        String type = customer.getCustomertype();

        if ("Wholesaler".equals(type)) {
            customer.setCustomeruid("WID" + generateUid());
        } else if ("Retailer".equals(type)) {
            customer.setCustomeruid("RET" + generateUid());
        } else if ("Other".equalsIgnoreCase(type)) {
            customer.setCustomeruid("OTH" + generateUid());
        }

        return customerDao.addRegistration(customer);
    }

    private String generateUid() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder uid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return uid.toString();
    }

    public List<Map<String, Object>> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public CustomerRegistration getCustomerById(Long id) {
        return customerDao.getWrDataById(id);
    }

    public int updateCustomerData(CustomerRegistration customer) {
        return customerDao.updateWr(customer);
    }

    public void deleteCustomerData(Long id) {
        customerDao.deletewrData(id);
    }

    // FETCHING PURPOSE
    public List<String> getAllRetailerUids() {
        return customerDao.getAllRetailerUids();
    }

    public List<String> getAllWholesellerUids() {
        return customerDao.getAllWholesellerUids();
    }

    public CustomerRegistration getDetailsByRetailerUid(String customerUid) {
        return customerDao.getSellerDetailsByRetailerUid(customerUid);
    }
    
    
    // FETCHING IN SALES ORDER
    
    public CustomerRegistration getcustomerbyuid(String customeruid) 
	{
		return customerDao.getdeatilscus(customeruid);
	}
    
    public List<String>showcustomerdeatils()
	{
		return customerDao.showallcustomerdeatils();
	}
	
	
}



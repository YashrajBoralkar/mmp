package com.prog.service.inventory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.CustomerDetailsDao;
import com.prog.model.erp.CustomerDetails;


@Service
public class CustomerDetailsService {

	@Autowired 
	private CustomerDetailsDao customerDetailsDao ;
	
	public CustomerDetails getCustomerDetailsById(String customerid) {
        return customerDetailsDao.getCustomerDetailsByid(customerid);
    }

	public List<String> getAllCustomerDetails() {
		return customerDetailsDao.getAllCustomerDetails();
	}
	
	
	//Sales Order(VINAY)
	
//	public List<String>showcustomerdeatils()
//	{
//		return customerDetailsDao.showallcustomerdeatils();
//	}
//=================================================================================================================================	

//	public CustomerDetails getcustomerbyuid(String customeruid) 
//	{
//		return customerDetailsDao.getdeatilscus(customeruid);
//	}
//	
	
	
	
	
	/// Fetching in RMA REQUEST CONTROLLER

	public List<String> getAllCustDetails(){
		return customerDetailsDao.getAllCustomerDetailsById();		
	}
	
	public CustomerDetails getCustDetailsById(String customeruid) {
		return customerDetailsDao.getCustomerDetailsById(customeruid);
	}	
	
	
	
	/// Fetching in DELIVERY ORDER 

	//fetch customer uid
		public List<String> getCustomeruIdInDeliveryOrder() {
			return customerDetailsDao.getCustomeruInDeliveryOrder();
		}

		public List<Map<String, Object>> getcustomerDetailsbyidInDeliveryOrder(String customeruid) {
			return customerDetailsDao.getCustomerByuidInDeliveryOrder(customeruid);
		}
}

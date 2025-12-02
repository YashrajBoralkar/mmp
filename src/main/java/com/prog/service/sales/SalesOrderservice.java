package com.prog.service.sales;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.sales.SalesOrderdao;
import com.prog.model.erp.SalesOrder;


@Service
public class SalesOrderservice 
{
	@Autowired
	private SalesOrderdao sodao;
//================================================================================================================================	

	 private String generatesalesorderUID() {
	        int length =4 ;  // Length of the UID (for example 8 characters)
	        String characters = "1234567890";
	        Random random = new Random();
	        StringBuilder StId = new StringBuilder(length);

	        // Generate random characters for the UID
	        for (int i = 0; i < length; i++) {
	            StId.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        return "SO" + StId.toString();
	 }
	
//================================================================================================================================	
	 public int savesales(SalesOrder so) 
	 {
			String salesorderuid=generatesalesorderUID();
			so.setSalesorderuid(salesorderuid);
		     return sodao.savesalesorder(so);
     }
//================================================================================================================================	
	
	 public List<SalesOrder>showlistofsales()
	 {
		return sodao.showsaleslist() ;
	 }
//================================================================================================================================	

	 public void deletsalesfromlist(Long id) 
	 {
		  sodao.Deletsalesorderbyid(id);
		 
	 }
//================================================================================================================================	
	 
	 public SalesOrder getsalesbyid(Long id) 
	 {
		 return sodao.selectorderbyid(id);
		 
	 }
//=================================================================================================================================	

	 public void updatesalesorderform(SalesOrder so) 
	 {
		sodao.updatesaleorder(so);
	
	 }
//=================================================================================================================================	
	 
	 public List<Map<String,Object>>showjoinedsales()
	 {
		 return sodao.showjoindata();
	 }
//=================================================================================================================================	
	//Fetching IN DELIVERY ORDER FORM
	 
//		 public List<String> getAllSalesorderUidInDeliveryOrder() {
//		     return sodao.getSalesOrderInDeliveryOrder();
//		 }
//	  
//	   public  List<Map<String, Object>> getSalesorderDetailsBySalesorderuidInDeliveryOrder(String salesorderuid) {
//	      return sodao.getSalesOrderByuidInDeliveryOrder(salesorderuid);
//		}
	 
	   
//=================================================================================================================================	
		
	   //fetch batch uid , product uid 
	    public List<String> getCustomerIdInDevileryOrder(){
	 		return sodao.getCustomerUidInDeliveryOrder();
	 	}
	    public List<String> getSalesorderUidIndeliveryorder(){
			return sodao.getSalesOrderUIDInDeliveryOrder();		
		}
		
	    public List<Map<String, Object>> getSalesOrderDetailsbyid(String salesorderuid){
	    	return sodao.getSalesOrderByuidInDeliveryOrder(salesorderuid);
	    }
	    
		public List<String> getcustomerUIDbytype(String customertype){
			return sodao.getcustomerUIDbytype(customertype);
		}
		

		
	 
	 //Fetching
	 
//	 public List<String> getAllSalesorderUid() {
//	     return backorderDao.getAllSalesorderUids();
//	 }
//  
//public  Salesorder getSalesorderDetailsBySalesorderuid(String salesorderuid) {
//      return backorderDao.getSalesOrderBySalesorderuid(salesorderuid);
//	}
	 
}

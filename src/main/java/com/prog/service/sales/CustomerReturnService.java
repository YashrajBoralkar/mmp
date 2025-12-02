package com.prog.service.sales;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.sales.CustomerReturnDao;
import com.prog.model.erp.CustomerReturn;

@Service
public class CustomerReturnService {
    
    @Autowired
    private CustomerReturnDao customerReturnDao;

    public void addCustomerReturn(CustomerReturn customerReturn) {
    	String customerreturnuid = generateReturnUid(); // Generate unique return UID
        customerReturn.setCustomerreturnuid(customerreturnuid); 
        customerReturnDao.addCustomerReturn(customerReturn); // Save in DB
    }
    
    public String generateReturnUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder customerreturnuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	customerreturnuid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "CUR" + customerreturnuid.toString(); // Prefix "CR" for Customer Return UID
    }
    
    
   
    public List<Map<String, Object>> getAllCustomerReturn() {
        return customerReturnDao.showFindAll();
    }
    
    public List<String>getSalesOrderUId(){
    	return customerReturnDao.getSalesOrderUId();
    }
 	public List<Map<String, Object>> ggetSalesOrderDetailsByuid(String salesorderuid){
 		return customerReturnDao.getSalesOrderDetailsByuid(salesorderuid);
 	}
 	
 	  public List<String>getProductOrderUId(){
 	    	return customerReturnDao.getProductOrderUId();
 	    }
 	 	public List<Map<String, Object>> getProductOrderDetailsByuid(String productuid){
 	 		return customerReturnDao.getproductDetailsByuid(productuid);
 	 	}
 	 	
 	 	
 	 	public void deleteById(Long id) {
 	 		 customerReturnDao.deleteById(id);
 	    }

		
public int updateCustomerReturn(CustomerReturn CustomerReturn) {
	return customerReturnDao.updateCustomerReturn(CustomerReturn); 
    }

public CustomerReturn getCustomerReturnById(Long id) {
	return customerReturnDao.getCustomerReturnById(id);
}

}

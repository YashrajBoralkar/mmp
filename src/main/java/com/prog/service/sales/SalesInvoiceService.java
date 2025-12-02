package com.prog.service.sales;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.sales.SalesInvoiceDao;
import com.prog.model.erp.SalesInvoiceForm;



@Service
public class SalesInvoiceService {
	@Autowired
	private SalesInvoiceDao salesInvoiceDao;
	
	
    public int addSalesInvoiceData(SalesInvoiceForm si) {
    	String siUID=generateSalesInvoiceUId();
		si.setSalesinvoiceuid(siUID);
    	return salesInvoiceDao.addSalesInvoiceData(si);
    }
    public SalesInvoiceForm getSalesInvoiceDataById(Long id) {
    	return salesInvoiceDao.getSalesInvoiceDataById(id);
    }
    public List<Map<String,Object>> getAllSalesInvoiseData(){
    	return salesInvoiceDao.getAllSalesInvoiseData();
    }
    
    public int updateSalesInvoice(SalesInvoiceForm si) {
    	return salesInvoiceDao.updateSaleInvoice(si);
    }
    
    public void deleteSalesInvoice(Long id) {
    	salesInvoiceDao.deleteSalesInvoice(id);
    }
 // UID Generate
 	private String generateSalesInvoiceUId() {
 	    int length = 4;  // Length of the PuId (for example 8 characters)
 	    String characters = "1234567890";
 	    Random random = new Random();
 	    StringBuilder siuid = new StringBuilder(length);

 	    // Generate random characters for the stiUId
 	    for (int i = 0; i < length; i++) {
 	    	siuid.append(characters.charAt(random.nextInt(characters.length())));
 	    }

 	     return "SI" + siuid.toString();
 	}
 	public List<String>getSalesOrderUId(){
    	return salesInvoiceDao.getSalesOrderUId();
    }
 	public List<Map<String, Object>> ggetSalesOrderDetailsByuid(String batchuid){
 		return salesInvoiceDao.getSalesOrderDetailsByuid(batchuid);
 	}
}

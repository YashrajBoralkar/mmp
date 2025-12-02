package com.prog.service.inventory;

import java.util.List;


import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.StockDispatchDao;
import com.prog.model.erp.StockDispatch;

@Service
public class StockDispatchService {

	@Autowired
	private StockDispatchDao stockdispatchDao;
	
	//Add new data in table
	public int addStockDispatch(StockDispatch sd) {
		String stdUID=generateStockDispatchuId();
		sd.setDispatchuid(stdUID);
		return stockdispatchDao.addStockDispatch(sd);
	}
	// get data by id
	public StockDispatch getStockDispatchById(Long  id) {
		return stockdispatchDao.getStockDispatchById(id);
	}
	//Fetch list
	public List<Map<String, Object>> FetchAllStockDispatchdata() {
        return stockdispatchDao.fetchAllStockDispatch();
	}
	//update data
	public int updateStockDispatch(StockDispatch sd) {
		return stockdispatchDao.updateStockDispatch(sd);
	}
	//delete data by id
	public void deleteStockDispatchById(Long  id) {
		 stockdispatchDao.deleteStockDispatchById(id);
	}
	// UID Generate
	private String generateStockDispatchuId() {
	    int length = 4;  // Length of the PuId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder stduid = new StringBuilder(length);

	    // Generate random characters for the stiUId
	    for (int i = 0; i < length; i++) {
	        stduid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "SD" + stduid.toString();
	}
	
	public List<String> getBatchId(){
    	return stockdispatchDao.getBatchId();
    }
	
	
	public List<Map<String, Object>> getProductDetailsByuid(String productuid){
    	return stockdispatchDao.getProductDetailsByuid(productuid);
    }
    
   
	
	public List<Map<String, Object>> getbatchDetailsbyid(String batchuid){
		return stockdispatchDao.getbatchDetailsByuid(batchuid);
	}
    public List<String> getItemid(){
    	return stockdispatchDao.getItemId();
    }

}

package com.prog.service.inventory;

import java.sql.SQLException;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.StockLevelManagementDao;
import com.prog.model.erp.StockLevelManagement;



@Service
public class StockLevelManagementService 
{
	@Autowired
	private StockLevelManagementDao stdao;
	
//============================================================================================================================	 

	 private String generatestockUID() {
	        int length =4;  // Length of the UID (for example 8 characters)
	        String characters = "1234567890";
	        Random random = new Random();
	        StringBuilder StId = new StringBuilder(length);

	        // Generate random characters for the UID
	        for (int i = 0; i < length; i++) {
	            StId.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        return "STLM" + StId.toString();
	 }
	
//============================================================================================================================	 

	public int saveStockelevelmanagement(StockLevelManagement slm) 
	{
		String stockleveluid  = generatestockUID();
        slm.setStockleveluid(stockleveluid);
		return stdao.saveStockelevelmanagement(slm);
		
	}
//============================================================================================================================	 
	

    public List<Map<String, Object>> getAllStockLevels() {
        return stdao.getStockLevelWithWarehouse();  // Fetches data from DAO
    }
    
//============================================================================================================================	 

	 
	 public StockLevelManagement getstockById(Long id) {
	        return stdao.getstocklevelById(id);  // Fetch the enrollment by ID from DAO
	    }
//============================================================================================================================	 

	 public void deletestockelevel(Long id) {
	        stdao.deletestockbyId(id);
	    }
//============================================================================================================================	 

	 
	 public void updateStocklevel(StockLevelManagement stocklevel ) {
	    	stdao.updateStocklevel(stocklevel);
	    }
//============================================================================================================================	 

	 public List<StockLevelManagement> getStockByWarehouseUid(String warehouseuid) {
	        return stdao.getStockByWarehouseUid(warehouseuid);
	    }
//============================================================================================================================	 

}

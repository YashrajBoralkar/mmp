package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.StockinfoDAO;
import com.prog.model.erp.Stockinfo;


@Service
public class StockinfoService {

    @Autowired
    private StockinfoDAO stockdetailsdao;

    // Save stock details with auto-generated UID
    public int SaveBatchInfo(Stockinfo stockdetails) {
        if (stockdetails.getStockuid() == null || stockdetails.getStockuid().isEmpty()) {
            String stockuid = generateStockuid();
            stockdetails.setStockuid(stockuid);
        }
        return stockdetailsdao.AddStockdetails(stockdetails);
    }

    // Get all stock entries
    public List<Map<String, Object>> getAllStockdetails() {
        return stockdetailsdao.getStockdetails();
    }

    // Delete stock by ID
    public int DeleteStockdetailsByid(Long id) {
        return stockdetailsdao.DeleteStockdetails(id);
    }

    // Fetch stock by ID
    public Stockinfo FetchStockdetailsByid(Long id) {
        return stockdetailsdao.GetStockdetailsByid(id);
    }

    // Update stock details
    public int UpdateStockdetails(Stockinfo stockdetails) {
        return stockdetailsdao.UpdateStockdetails(stockdetails);
    }

    // Generate a stock UID (e.g., STK1234)
    private String generateStockuid() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder uid = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "STK" + uid.toString();
    }


    // Retrieves product details based on the provided Product UID
   	    public List<Map<String, Object>> getDataByProductUid(String productuid) {
   	        return stockdetailsdao.getDataByProductUid(productuid);  // Call DAO to fetch product-related data using the specified UID

   	    }

   	 // Retrieves all Product UIDs from the database
   	    public List<String> fetchProductUIds() {
   	        return stockdetailsdao.fetchProductUIds(); // Call DAO to get a list of all available product UIDs
   	    }
   	    
   	    public List<String> getAllStockuids(){
   	    	return stockdetailsdao.getAllStockuids();
   	    }
}

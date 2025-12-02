package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.CostAdjustmentDAO;
import com.prog.Dao.inventory.StockValuationDAO;
import com.prog.Dao.inventory.StockinfoDAO;
import com.prog.model.erp.CostAdjustment;
import com.prog.model.erp.StockValuation;


@Service
public class CostAdjustmentService {

	@Autowired
	private CostAdjustmentDAO costadjustmentdao;
	
	@Autowired
	private StockinfoDAO stockdao;
	
	@Autowired
	private StockValuationDAO stockValuationdao;
	
	
	public boolean saveCost(CostAdjustment costadjustment) {
		
		 StockValuation stockvaluation = stockValuationdao.getStockByUId(costadjustment.getStockuid()) ;  
		    if (stockvaluation == null) {
		        throw new RuntimeException("Stock not found for ID: " + costadjustment.getStockuid());
		    }
		
	    String costuid = generateCostuid();
	    costadjustment.setCostadjustmentuid(costuid);
	    
	    String unitcost = costadjustment.getAdjustmentvalue();
	    stockvaluation.setUnitcost(unitcost);
	    
	 // Convert unit cost and unit quantity from String to Double
	    double unitCost = Double.parseDouble(costadjustment.getAdjustmentvalue());
	    double unitQuantity = Double.parseDouble(stockvaluation.getUnitquantity());

	    // Perform multiplication
	    double totalCost = unitCost * unitQuantity;

	    // Convert result back to String and store it
	    stockvaluation.setTotalcost(String.valueOf(totalCost));

	

	    boolean costSaved = costadjustmentdao.addCost(costadjustment) > 0; 
	    boolean stockSaved = stockValuationdao.updateUnitCostByUid(stockvaluation) > 0; 

	    return costSaved && stockSaved;
	}

	
	private String generateCostuid() {
        int length =4; // Length of the Costuid (example: 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder costuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	costuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "COS" + costuid.toString();
    }
	
	public List<Map<String, Object>> getCostAdjustment(){
		return costadjustmentdao.showthefindall();
		
	}
	
	public void deleteCost(Long id) {
		costadjustmentdao.deleteCost(id);
	}
	
	public CostAdjustment getCostAdjustmentbyid(Long id) {
		return costadjustmentdao.getCostAdjustmentbyid(id);
	}
	
	public boolean updateCostAdjustment(CostAdjustment costadjustment) 
	{
		
		
		StockValuation stockvaluation = stockValuationdao.getStockByUId(costadjustment.getStockuid()) ;  
	    if (stockvaluation == null) {
	        throw new RuntimeException("Stock not found for ID: " + costadjustment.getStockuid());
	    }
	
    String unitcost = costadjustment.getAdjustmentvalue();
    stockvaluation.setUnitcost(unitcost);
    
 // Convert unit cost and unit quantity from String to Double
    double unitCost = Double.parseDouble(costadjustment.getAdjustmentvalue());
    double unitQuantity = Double.parseDouble(stockvaluation.getUnitquantity());

    // Perform multiplication
    double totalCost = unitCost * unitQuantity;

    // Convert result back to String and store it
    stockvaluation.setTotalcost(String.valueOf(totalCost));



    boolean costupdate = costadjustmentdao.updateCostAdjustment(costadjustment) > 0; 
    boolean stockupdate = stockValuationdao.updateUnitCostByUid(stockvaluation) > 0; 

    return costupdate && stockupdate;
		
		
	}
	
	
	public List<Map<String,Object>> getdataByStockuid(String stockuid) {
		return costadjustmentdao.getDataByStockUid(stockuid);
	}
	
	/**
	 * Fetch only Approved Product UIDs of type GOODS.
	 */
	public List<String> getApprovedGoodsProductUids() {
		return costadjustmentdao.getApprovedGoodsProductUids();
	}

	/**
	 * Fetch Product Details (Unit Price + Available Quantity) by Product UID.
	 */
	public Map<String, Object> getProductDetails(String productuid) {
		return costadjustmentdao.getProductDetails(productuid);
	}
	
	public Map<String, Object> getstockDetails(String productuid) {
		return costadjustmentdao.getstockDetails(productuid);
	}
}
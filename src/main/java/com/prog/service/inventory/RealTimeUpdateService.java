package com.prog.service.inventory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.RealTimeUpdateDAO;
import com.prog.model.erp.RealTimeUpdate;

@Service
public class RealTimeUpdateService {

	 @Autowired
	    private RealTimeUpdateDAO realtimeupdatedao;

	    // ✅ Save RealTimeUpdate with auto calculation of global quantity
	 public int addRealTimeUpdate(RealTimeUpdate realtimeupdate) {
		    Double latestGlobalQty = realtimeupdatedao.getLatestGlobalStockByProductUid(
		        realtimeupdate.getProductuid(),
		        realtimeupdate.getProducttype()
		    );
		    if (latestGlobalQty == null) latestGlobalQty = 0.0;

		    double qty = realtimeupdate.getRealtimequantity();
		    boolean isPositive = (
		        (realtimeupdate.getProducttype().equalsIgnoreCase("goods") &&
		         (realtimeupdate.getTransactiontype().equalsIgnoreCase("add inventory") ||
		          realtimeupdate.getTransactiontype().equalsIgnoreCase("return"))) ||
		        (realtimeupdate.getProducttype().equalsIgnoreCase("rawmaterial") &&
		         (realtimeupdate.getTransactiontype().equalsIgnoreCase("purchase") ||
		          realtimeupdate.getTransactiontype().equalsIgnoreCase("return")))
		    );

		    boolean isTransfer = realtimeupdate.getTransactiontype().equalsIgnoreCase("transfer");

		    double finalGlobalQty;
		    if (isTransfer) {
		        // Transfer should not change total global stock
		        finalGlobalQty = latestGlobalQty;
		    } else {
		        finalGlobalQty = isPositive ? latestGlobalQty + qty : latestGlobalQty - qty;
		        if (finalGlobalQty < 0) finalGlobalQty = 0;
		    }

		    realtimeupdate.setGlobalrealtimequantity(finalGlobalQty);
		    return realtimeupdatedao.addRealTimeUpdate(realtimeupdate);
		}


	    // ✅ Get latest global stock for a product/rawmaterial UID and type
	    public Double getLatestGlobalStockByProductUid(String productuid, String producttype) {
	        return realtimeupdatedao.getLatestGlobalStockByProductUid(productuid, producttype);
	    }

	    // ✅ Get latest stock for a product in a specific warehouse
	    public Double getLatestStockByProductAndWarehouse(String productuid, String warehouseuid) {
	        return realtimeupdatedao.getLatestStockByProductAndWarehouse(productuid, warehouseuid);
	    }

	    public List<Map<String, Object>> getAllRealTimeUpdates() {
	        return realtimeupdatedao.findAllRealtimeUpdates();
	    }

	    public void deleteRealTimeUpdate(Long id) {
	        realtimeupdatedao.deleteRealTmeUpdate(id);
	    }

	    public List<String> getWarehouseUIDsFromInventory(String producttype, String productuid) {
	        return realtimeupdatedao.getWarehouseUIDsByProductTypeAndUID(producttype, productuid);
	    }

	    public List<String> getProductUIDsByProductType(String producttype) {
	        return realtimeupdatedao.getProductUIDsByProductType(producttype);
	    }
	    
	   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
   
	    //FETCHING METHODS
	    
	    
		 // ✅ Get all global real-time quantities (for dropdown)

	    public List<RealTimeUpdate> getAllGlobalQuantities() {
	        return realtimeupdatedao.getAllRealtimeUpdates();
	    }

		    public List<Double> getAllGlobalQuantitiesByProductUid(String productuid) throws SQLException {
		        return realtimeupdatedao.getAllGlobalQuantitiesByProductUid(productuid);
		    }



	    
	}
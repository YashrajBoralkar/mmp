package com.prog.Dao.inventory;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public class BatchTrackingDAO {
	
	
	@Autowired
	private JdbcTemplate jdbctemplete;
	
	
	public BatchTrackingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbctemplete = jdbcTemplate;
    }


	public List<Map<String, Object>> getBatchDetailsByBatchId(String batchuid) {
	    String sql = "SELECT b.batchuid, b.productuid, p.productname, p.manufacturingdate, "
	               + "p.expirydate, p.warrantyperiod, b.warehouseuid, "
	               + "w.warehousename, si.shelfnumber "
	               + "FROM batchinfo b "
	               + "JOIN productinfo p ON b.productuid = p.productuid "
	               + "JOIN warehouseinfo w ON b.warehouseuid = w.warehouseuid "
	               + "JOIN stock_info si ON b.stockuid = si.stockuid "
	               + "WHERE b.batchuid = ?";
	    return jdbctemplete.queryForList(sql, batchuid);
	}

	
	
	
	public List<String> getBatchId() {
        String sql = "SELECT batchuid FROM batchinfo";
        return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("batchuid"));
    }
	
	
	
	@Transactional
	public ResponseEntity<Map<String, Integer>> updateBatchDetails(Map<String, Object> batchDetails) {
	    if (!batchDetails.containsKey("batchuid")){
	        throw new IllegalArgumentException("Required fields are missing in batch details.");
	    }

	    String updateBatchInfoSQL = "UPDATE batchinfo " +
	                                "SET productuid = ?, warehouseuid = ? " +
	                                "WHERE batchuid = ?";
	    
	    String updateProductInfoSQL = "UPDATE productinfo " +
	                                  "SET productname = ?, manufacturingdate = ?, expirydate = ?, warrantyperiod = ? " +
	                                  "WHERE productuid = ?";
	    
	    String updateWarehouseInfoSQL = "UPDATE warehouseinfo " +
	                                     "SET warehousename = ?" +
	                                     "WHERE warehouseuid = ?";
	    String updateStockInfoSQL = "UPDATE stock_info " +
						                "SET shelfnumber = ?" +
						                "WHERE stockuid = ?";
							    
	    int rowsUpdated = 0;
	    rowsUpdated += jdbctemplete.update(updateBatchInfoSQL, 
	                                        batchDetails.get("productuid"), 
	                                        batchDetails.get("warehouseuid"), 
	                                        batchDetails.get("batchuid"));
	    
	    rowsUpdated += jdbctemplete.update(updateProductInfoSQL, 
	                                        batchDetails.get("productname"), 
	                                        batchDetails.get("manufacturingdate"), 
	                                        batchDetails.get("expirydate"), 
	                                        batchDetails.get("warrantyperiod"), 
	                                        batchDetails.get("productuid"));
	    
	    rowsUpdated += jdbctemplete.update(updateWarehouseInfoSQL, 
	                                        batchDetails.get("warehousename"), 
//	                                        batchDetails.get("numberofshelf"), 
	                                        batchDetails.get("warehouseuid"));
	    rowsUpdated += jdbctemplete.update(updateStockInfoSQL, 
                                      batchDetails.get("stockuid"), 
						              batchDetails.get("shelfnumber")); 

	    
	    return ResponseEntity.ok(Map.of("rowsUpdated", rowsUpdated));
	}

	


}

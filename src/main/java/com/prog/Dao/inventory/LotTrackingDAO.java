package com.prog.Dao.inventory;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public class LotTrackingDAO {
	
	@Autowired
	private JdbcTemplate jdbctemplete;
	
	
	public LotTrackingDAO(JdbcTemplate jdbcTemplete) {
		this.jdbctemplete = jdbcTemplete;
	}
	
	
	public List<Map<String , Object>> getLotDetailsByLotId(String lotuid){
		
		String sql = "SELECT l.lotuid, l.productuid, l.productquantity, p.productname, p.manufacturingdate, p.productcategory, " +
	             "p.expirydate, p.warrantyperiod, l.warehouseuid, w.warehousename, si.shelfnumber " +
	             "FROM lot_info l " +
	             "JOIN productinfo p ON l.productuid = p.productuid " +
	             "JOIN warehouseinfo w ON l.warehouseuid = w.warehouseuid " +
	             "JOIN stock_info si ON l.stockuid = si.stockuid " +

	             "WHERE l.lotuid = ?";

		
		 return jdbctemplete.queryForList(sql, lotuid);
	
	}
	
	
	public List<String> getLotId(){
		String sql = "SELECT lotuid FROM lot_info";
		return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("lotuid"));
		
	}
	
	
	@Transactional
	public ResponseEntity<Map<String, Integer>> updateLotDetails(Map<String, Object> LotDetails) {
	    if (!LotDetails.containsKey("lotuid")) {
	        throw new IllegalArgumentException("Required fields are missing in lot details.");
	    }

	    String updateLotInfoSQL = "UPDATE lot_info " +
	                                "SET  productquantity = ?" +
	                                "WHERE lotuid = ?";
	    
	    String updateProductInfoSQL = "UPDATE productinfo " +
	                                  "SET productname = ?, manufacturingdate = ?, expirydate = ?, warrantyperiod = ?, productcategory = ? "+
	                                  "WHERE productuid = ?";
	    
	    String updateWarehouseInfoSQL = "UPDATE warehouseinfo " +
	                                     "SET warehousename = ? " +
	                                     "WHERE warehouseuid = ?";
	    String updateStockInfoSQL = "UPDATE warehouseinfo " +
						                "SET warehousename = ? " +
						                "WHERE warehouseuid = ?";

	    
	    int rowsUpdated = 0;
	    rowsUpdated += jdbctemplete.update(updateLotInfoSQL, 
	    		 
	    		LotDetails.get("productquantity"),
	    		LotDetails.get("lotuid"));
	    
	    rowsUpdated += jdbctemplete.update(updateProductInfoSQL, 
	    		LotDetails.get("productname"), 
	    		LotDetails.get("manufacturingdate"), 
	    		LotDetails.get("expirydate"), 
	    		LotDetails.get("warrantyperiod"),
	    		LotDetails.get("productcategory"),
	    		LotDetails.get("productuid"));
	    
	    rowsUpdated += jdbctemplete.update(updateWarehouseInfoSQL, 
	    		LotDetails.get("warehousename"), 
//	    		LotDetails.get("numberofshelf"), 
	    		LotDetails.get("warehouseuid"));
	    
	    rowsUpdated += jdbctemplete.update(updateStockInfoSQL, 
	    		 
	    		LotDetails.get("shelfnumber"),
	    		LotDetails.get("stockuid"));
	    
	    
	    return ResponseEntity.ok(Map.of("rowsUpdated", rowsUpdated));
	}

	


}

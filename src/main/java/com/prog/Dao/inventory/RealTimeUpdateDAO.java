package com.prog.Dao.inventory;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.RealTimeUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class RealTimeUpdateDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

 // ✅ Add New Realtime Update Entry (Modified with warehouseuid and innertransactiontype)
    public int addRealTimeUpdate(RealTimeUpdate realtimeupdate) {
        String sql = "INSERT INTO realtimeupdate " +
                "(realtimeupdateuid, producttype, productuid, transactiontype, transactiondate, " +
                "globalrealtimequantity, realtimequantity, insertdate, updatedate," +
                "warehouseuid,quantity , innertransactiontype) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        return jdbcTemplate.update(sql,
                realtimeupdate.getRealtimeupdateuid(),
                realtimeupdate.getProducttype(),
                realtimeupdate.getProductuid(),
                realtimeupdate.getTransactiontype(),
                realtimeupdate.getTransactiondate(),
                realtimeupdate.getGlobalrealtimequantity(),
                realtimeupdate.getRealtimequantity(),
                formattedTimestamp,
                formattedTimestamp,
                realtimeupdate.getWarehouseuid(),
                realtimeupdate.getQuantity(),// newly added
                realtimeupdate.getInnertransactiontype()       // newly added
        );
    }


    
 // ✅ Get Latest Stock for a given ProductUID and WarehouseUID
    public Double getLatestStockByProductAndWarehouse(String productuid, String warehouseuid) {
        String sql = "SELECT realtimequantity FROM realtimeupdate " +
                     "WHERE productuid = ? AND warehouseuid = ? " +
                     "ORDER BY id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, Double.class, productuid, warehouseuid);
        } catch (Exception e) {
            return 0.0; // default if no record found
        }
    }
 // ✅ Get Latest Global Real-Time Quantity for Product/RawMaterial UID
    	public Double getLatestGlobalStockByProductUid(String productuid, String producttype) {
    	    String sql = """
    	        SELECT globalrealtimequantity
    	        FROM realtimeupdate
    	        WHERE productuid = ? AND producttype = ?
    	        ORDER BY id DESC LIMIT 1
    	    """;
    	    try {
    	        Double latestGlobal = jdbcTemplate.queryForObject(sql, Double.class, productuid, producttype);
    	        return latestGlobal != null ? latestGlobal : 0.0;
    	    } catch (Exception e) {
    	        return 0.0;
    	    }
    	}

    // ✅ Get WarehouseUIDs for given ProductType & UID
    public List<String> getWarehouseUIDsByProductTypeAndUID(String producttype, String productuid) {
        String sql = "SELECT DISTINCT warehouseuid FROM inventoryentry WHERE producttype = ? AND productuid = ?";
        return jdbcTemplate.queryForList(sql, String.class, producttype, productuid);
    }

    // ✅ Get ProductUIDs based on ProductType
    public List<String> getProductUIDsByProductType(String producttype) {
        if ("Goods".equalsIgnoreCase(producttype)) {
            String sql = "SELECT productuid FROM productinfo";
            return jdbcTemplate.queryForList(sql, String.class);
        } else if ("RawMaterial".equalsIgnoreCase(producttype)) {
            String sql = "SELECT rawmaterialuid FROM rawmaterialinfo";
            return jdbcTemplate.queryForList(sql, String.class);
        }
        return List.of(); // Empty list fallback
    }

    // ✅ Calculate Global Quantity (helper method - can be used in service)
    public double calculateGlobalQuantity(String transactiontype, String jsonString) {
        double total = 0.0;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonString);
            for (JsonNode node : root) {
                double qty = node.has("quantity") ? node.get("quantity").asDouble() : 0.0;
                if (transactiontype.equalsIgnoreCase("Add Inventory") ||
                    transactiontype.equalsIgnoreCase("Return") ||
                    transactiontype.equalsIgnoreCase("Purchase")) {
                    total += qty;
                } else {
                    total -= qty;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // ✅ Get Realtime Update by ID (Optional, for edit or view)
    public RealTimeUpdate getRealtimeUpdateById(Long id) {
        String sql = "SELECT * FROM realtimeupdate WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<>() {
            public RealTimeUpdate mapRow(ResultSet rs, int rowNum) throws SQLException {
                RealTimeUpdate r = new RealTimeUpdate();
                r.setId(rs.getLong("id"));
                r.setRealtimeupdateuid(rs.getString("realtimeupdateuid"));
                r.setProducttype(rs.getString("producttype"));
                r.setProductuid(rs.getString("productuid"));
                r.setTransactiontype(rs.getString("transactiontype"));
                r.setTransactiondate(rs.getString("transactiondate"));
                r.setGlobalrealtimequantity(rs.getDouble("globalrealtimequantity"));
                r.setRealtimequantity(rs.getDouble("realtimequantity"));
                return r;
            }
        }, id);
    }
    
    //show list
    public List<Map<String, Object>> findAllRealtimeUpdates() {
    	String sql="SELECT r.*,r.id,r.realtimeupdateuid, r.producttype, r.productuid, r.transactiontype, r.quantity, r.warehouseuid, r.transactiondate FROM realtimeupdate r ; ";
        return jdbcTemplate.queryForList(sql);
    }

    // delete 
    public void deleteRealTmeUpdate(Long id) {
        jdbcTemplate.update("DELETE FROM realtimeupdate WHERE id = ?", id);
    }
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    //FETCHING QUERYS
    
    public List<RealTimeUpdate> getAllRealtimeUpdates() {
        String sql = "SELECT * FROM realtimeupdate";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RealTimeUpdate.class));
    }

    
    // ✅ Get All Global Quantities linked with a given ProductUID
    public List<Double> getAllGlobalQuantitiesByProductUid(String productuid) throws SQLException {
        String sql = "SELECT globalrealtimequantity FROM realtimeupdate WHERE productuid = ?";
        RowMapper<Double> rowMapper = (rs, rowNum) -> rs.getDouble("globalrealtimequantity");
        return jdbcTemplate.query(sql, rowMapper, productuid);
    }

}

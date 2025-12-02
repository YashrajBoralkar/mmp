package com.prog.Dao.inventory;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Stockinfo;

@Repository
public class StockinfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert stock details
    public int AddStockdetails(Stockinfo st) {
        String sql = "INSERT INTO stockinfo ( stockuid, productuid, productquantity,totalstockquantity, stockprice, manufacturingdate, expirydate, insertdate,updatedate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        return jdbcTemplate.update(sql,
     
                st.getStockuid(), 
                st.getProductuid(),
                st.getProductquantity(),
                st.getTotalstockquantity(),
                st.getStockprice(),
                st.getManufacturingdate(),
                st.getExpirydate(),
                formattedTimestamp,
                formattedTimestamp
        );
    }
 // Get all stock details with product names and real-time quantity
    public List<Map<String, Object>> getStockdetails() {
        String sql = "SELECT \r\n"
        		+ "    l.id, \r\n"
        		+ "    l.stockuid, \r\n"
        		+ "    l.productuid, \r\n"
        		+ "    l.totalstockquantity, \r\n"
        		+ "    l.stockprice,\r\n"
        		+ "    p.productname, \r\n"
        		+ "    p.sellingprice, \r\n"
        		+ "    l.productquantity, \r\n"
        		+ "    l.stockprice,\r\n"
        		+ "    COALESCE(SUM(r.globalrealtimequantity), 0) AS globalrealtimequantity\r\n"
        		+ "FROM stockinfo l\r\n"
        		+ "JOIN productinfo p ON p.productuid = l.productuid\r\n"
        		+ "LEFT JOIN realtimeupdate r ON r.productuid = l.productuid\r\n"
        		+ "GROUP BY l.id, l.stockuid, l.productuid, l.totalstockquantity, l.stockprice, \r\n"
        		+ "         p.productname, p.sellingprice, l.productquantity;\r\n"
        		+ "";

        return jdbcTemplate.queryForList(sql);
    }


    // Delete stock by ID
    public int DeleteStockdetails(Long id) {
        String sql = "DELETE FROM stockinfo WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Get stock details by ID
    public Stockinfo GetStockdetailsByid(Long id) {
        String sql = "SELECT * FROM stockinfo WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new StockdetailsRowMapper(), id);
    }

    public int UpdateStockdetails(Stockinfo st) {
        String sql = "UPDATE stockinfo SET manufacturingdate = ?, expirydate = ?, stockprice = ?, totalstockquantity =?, updateddate = ? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        return jdbcTemplate.update(sql,
            st.getManufacturingdate(),   
            st.getExpirydate(),          
            st.getStockprice(),  
            st.getTotalstockquantity(),               
            formattedTimestamp,          
            st.getId()                   
        );
    }

    // Get all raw materials for dropdown
    public List<Map<String, String>> getAllRawMaterial() {
        String sql = "SELECT rawmaterialuid, rawmaterialname FROM rawmaterialinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> map = new HashMap<>();
            map.put("uid", rs.getString("rawmaterialuid"));
            map.put("name", rs.getString("rawmaterialname"));
            return map;
        });
    }

    // Retrieve product details by product UID
    public List<Map<String, Object>> getDataByProductUid(String productuid) {
        String sql = "SELECT pi.productname, pi.sellingprice, rt.globalrealtimequantity FROM productinfo pi Join realtimeupdate rt on pi.productuid = rt.productuid WHERE pi.productuid = ?";
        return jdbcTemplate.queryForList(sql, productuid);
    }

    // Retrieve all product UIDs
    public List<String> fetchProductUIds() {
        String sql = "SELECT productuid FROM productinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }


    // RowMapper for single Stockdetails
    private static class StockdetailsRowMapper implements RowMapper<Stockinfo> {
        @Override
        public Stockinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Stockinfo st = new Stockinfo();
            st.setId(rs.getLong("id"));
            st.setStockuid(rs.getString("stockuid"));
            st.setProductuid(rs.getString("productuid"));
            st.setProductquantity(rs.getLong("productquantity")); 
            st.setTotalstockquantity(rs.getString("totalstockquantity"));
            st.setStockprice(rs.getString("stockprice"));
            st.setManufacturingdate(rs.getString("manufacturingdate"));
            st.setExpirydate(rs.getString("expirydate"));
            return st;
        }
    }
    
    public List<String> getAllStockuids() {
	   	 
        String sql = "SELECT stockuid FROM stockinfo"; // Replace with your actual table and column names
        return jdbcTemplate.queryForList(sql, String.class);
    
    }
}

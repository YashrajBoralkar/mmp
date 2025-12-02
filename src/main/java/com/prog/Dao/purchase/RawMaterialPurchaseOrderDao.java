package com.prog.Dao.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.Rawmaterialpurchaseorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RawMaterialPurchaseOrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
   // RowMapper for RawMaterial
    private final RowMapper<Rawmaterialpurchaseorder> rowMapper = new RowMapper<>() {
        @Override
        public Rawmaterialpurchaseorder mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Rawmaterialpurchaseorder rm = new Rawmaterialpurchaseorder();
            rm.setId(rs.getLong("id"));
            rm.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            rm.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            rm.setSuppliername(rs.getString("suppliername"));
            rm.setOrderdate(rs.getString("orderdate"));
            rm.setRawmaterialuid(rs.getString("rawmaterialuid"));
            rm.setMaterialnames(rs.getString("materialnames"));
            rm.setMaterialdetails(rs.getString("materialdetails"));     
            rm.setDeliverydate(rs.getString("deliverydate"));
            rm.setTotalvalue(rs.getDouble("totalvalue"));
             return rm;
        }
        };
           // Insert RawMaterial
            public int saveRawMaterial(Rawmaterialpurchaseorder rawMaterial)
            {
           
            String sql = "INSERT INTO rawmaterialpurchaseorder " +
                    "(rawmaterialpurchaseorderuid, rawmaterialsupplieruid, suppliername, orderdate,rawmaterialuid,materialnames, materialdetails, deliverydate, totalvalue, insertdate, updatedate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
           
            return jdbcTemplate.update(sql,
                    rawMaterial.getRawmaterialpurchaseorderuid(),
                    rawMaterial.getRawmaterialsupplieruid(),
                    rawMaterial.getSuppliername(),
                    rawMaterial.getOrderdate(),
                    rawMaterial.getRawmaterialuid(),
                    rawMaterial.getMaterialnames(),
                    rawMaterial.getMaterialdetails(),
                    rawMaterial.getDeliverydate(),
                    rawMaterial.getTotalvalue(),
                    formattedTimestamp,
                    formattedTimestamp
                    
                    );
        }
    // Get all RawMaterials
    public List<Map<String, Object>> getAllRawMaterials() {
        String sql = "SELECT * FROM rawmaterialpurchaseorder";
        return jdbcTemplate.queryForList(sql);
    }
    // Get by ID
    public Rawmaterialpurchaseorder getRawMaterialBynumber(Long id) {
        String sql = "SELECT * FROM rawmaterialpurchaseorder WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    
    // Update RawMaterial
    public int updateRawMaterial(Rawmaterialpurchaseorder rawMaterial) {
        String sql = "UPDATE rawmaterialpurchaseorder SET orderdate = ?,deliverydate = ?, updatedate = ? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        return jdbcTemplate.update(sql,           
                rawMaterial.getOrderdate(),
                rawMaterial.getDeliverydate(),
                formattedTimestamp,
                rawMaterial.getId()
        );
    }

    // Delete by ID
    public int deleteRawMaterialById(Long id) {
        String sql = "DELETE FROM rawmaterialpurchaseorder WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Fetch all suppliers
    public List<String> fetchAllSuppliers() {
        String sql = "SELECT rawmaterialsupplieruid FROM rawmaterialsupplier";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("rawmaterialsupplieruid"));
        
    }

    // Get supplier name by UID (safe)
    public List<Map<String, Object>> findSupplierNameByUid(String rawmaterialsupplieruid) {
        String sql = "SELECT suppliername,rawmaterialsupplieruid,rawmaterialname,rawmaterialuid FROM rawmaterialsupplier WHERE rawmaterialsupplieruid = ?";
        return jdbcTemplate.queryForList(sql,rawmaterialsupplieruid);
    }

    // Get material names by supplier UID
    public List<Map<String, String>> findMaterialNamesBySupplierUid(String rawmaterialsupplieruid) {
        String sql = "SELECT rawmaterialname, rawmaterialuid FROM rawmaterialsupplier WHERE rawmaterialsupplieruid = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> map = new HashMap<>();
            map.put("materialnames", rs.getString("rawmaterialname"));
            map.put("rawmaterialuid", rs.getString("rawmaterialuid"));
            return map;
        }, rawmaterialsupplieruid);
       }
    
    // ✅ FIXED COLUMN NAME: Get material unit price.
    public Map<String, Object> getMaterialDetailsByName(String materialnames) {
        // Using correct column name: materialname
        String sql = "SELECT standardpurchaseprice, rawmaterialuid FROM rawmaterialinfo WHERE rawmaterialname = ?";
        try {
            return jdbcTemplate.queryForMap(sql, materialnames);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        }
    
    
    
    //FETCHING IN POACKNOWLEDGE
    
    
    public List<String> fetchRMPOUID() {
		String sql = "SELECT rawmaterialpurchaseorderuid FROM rawmaterialpurchaseorder";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("rawmaterialpurchaseorderuid"));
	}

	public List<Map<String, Object>> getDataByRpUid(String rawmaterialpurchaseorderuid) {
		String sql = "Select rpo.rawmaterialsupplieruid, rpo.suppliername, rpo.materialdetails, rs.contactperson, rs.mobilenumber, rpo.rawmaterialuid, rpo.orderdate from rawmaterialpurchaseorder rpo join rawmaterialsupplier rs on rpo.rawmaterialsupplieruid = rs.rawmaterialsupplieruid  where rpo.rawmaterialpurchaseorderuid = ?;";
		return jdbcTemplate.queryForList(sql);
   }
	
}

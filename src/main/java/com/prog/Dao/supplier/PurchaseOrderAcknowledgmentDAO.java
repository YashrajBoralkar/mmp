package com.prog.Dao.supplier;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.POAcknowledgement;


@Repository
public class PurchaseOrderAcknowledgmentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = "INSERT INTO poacknowledgment (id,poacknowledgmentuid, rawmaterialpurchaseorderuid, rawmaterialsupplieruid,podate,deliverydateconfirmation, acceptancestatus, reasonofrejection, supplierrepresentativename, supplierrepresentativecontact, suppliername, rawmaterialuid, rawmaterialdetails , insertdate, updatedate) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int addPOAcknowledgment(POAcknowledgement poAck) {
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
	    
        return jdbcTemplate.update(INSERT_SQL,  
        		poAck.getId(),
                poAck.getPoacknowledgmentuid(),
                poAck.getRawmaterialpurchaseorderuid(),
                poAck.getRawmaterialsupplieruid(),
                poAck.getPodate(),
                poAck.getDeliverydateconfirmation(),
                poAck.getAcceptancestatus(),
                poAck.getReasonofrejection(),
                poAck.getSupplierrepresentativename(),
                poAck.getSupplierrepresentativecontact(),
                poAck.getSuppliername(),
                poAck.getRawmaterialuid(),
                poAck.getRawmaterialdetails(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    // Retrieve all PO Acknowledgment
    public List<Map<String, Object>> getAllPOAcknowledgments() {
        String sql = "select * from poacknowledgment";
        return jdbcTemplate.queryForList(sql);
    }
    
    // Retrieve a PO Acknowledgment by ID
    public POAcknowledgement getPOAcknowledgmentById(Long id) {
        String sql = "SELECT * FROM poacknowledgment WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new POAcknowledgmentRowMapper(), id);
    }
    
    
    // Update a PO Acknowledgment
    private static final String UPDATE_SQL = "UPDATE poacknowledgment SET deliverydateconfirmation = ?,podate=?, acceptancestatus = ?, reasonofrejection = ?, supplierrepresentativename = ?, supplierrepresentativecontact = ? ,updatedate=? WHERE id = ?";

    public int updateAcknowledgment(POAcknowledgement poAck) {
    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	    
        return jdbcTemplate.update(UPDATE_SQL,
                poAck.getDeliverydateconfirmation(),
                poAck.getPodate(),
                poAck.getAcceptancestatus(),
                poAck.getReasonofrejection(),
                poAck.getSupplierrepresentativename(),
                poAck.getSupplierrepresentativecontact(),
                formattedTimestamp,
                poAck.getId()
        );
    }

   // RowMapper for mapping ResultSet to PurchaseOrderAcknowledgment entity
    public static class POAcknowledgmentRowMapper implements RowMapper<POAcknowledgement> {
        @Override
        public POAcknowledgement mapRow(ResultSet rs, int rowNum) throws SQLException {
        	POAcknowledgement poAck = new POAcknowledgement();
            poAck.setPoacknowledgmentuid(rs.getString("poacknowledgmentuid"));
            poAck.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            poAck.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            poAck.setDeliverydateconfirmation(rs.getString("deliverydateconfirmation"));
            poAck.setAcceptancestatus(rs.getString("acceptancestatus"));
            poAck.setReasonofrejection(rs.getString("reasonofrejection"));
            poAck.setSupplierrepresentativename(rs.getString("supplierrepresentativename"));
            poAck.setSupplierrepresentativecontact(rs.getString("supplierrepresentativecontact"));
            poAck.setPodate(rs.getString("podate"));
            poAck.setRawmaterialdetails(rs.getString("rawmaterialdetails"));
            poAck.setId(rs.getLong("id"));
            return poAck;
        }
    }
    
   
    
 // Delete a PO Acknowledgment by ID
    public int deleteAcknowledgment(Long id) {
        String sql = "DELETE FROM poacknowledgment WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
    
    
    
    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUID(String rawmaterialpurchaseorderuid) {
        String sql = "Select rpo.rawmaterialsupplieruid, rpo.suppliername, rpo.materialdetails, rs.contactperson, rs.mobilenumber, rpo.rawmaterialuid, rpo.orderdate from rawmaterialpurchaseorder rpo join rawmaterialsupplier rs on rpo.rawmaterialsupplieruid = rs.rawmaterialsupplieruid  where rpo.rawmaterialpurchaseorderuid = ?;";

        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, rawmaterialpurchaseorderuid);

        for (Map<String, Object> row : rows) {
            String rawmaterialuid = (String) row.get("rawmaterialuid");   // e.g. "MAT001" or "MAT001,MAT002"
            String materialDetails = (String) row.get("materialdetails"); // JSON string

            String[] rawMaterialUidsArray = rawmaterialuid.split(",");

            try {
                // Parse materialdetails JSON
                Map<String, Map<String, Object>> materialDetailsMap =
                        objectMapper.readValue(materialDetails, Map.class);

                int index = 0;
                for (Map.Entry<String, Map<String, Object>> entry : materialDetailsMap.entrySet()) {
                    Map<String, Object> materialInfo = new HashMap<>();
                    materialInfo.put("rawmaterialuid", rawMaterialUidsArray[index].trim());
                    materialInfo.put("rawmaterialname", entry.getKey()); // "Steel Rods"
                    materialInfo.put("orderedquantity", entry.getValue().get("quantity"));
                    materialInfo.put("price", entry.getValue().get("price"));
                  
                    // ✅ Add supplier + PO-level metadata for each material
                    materialInfo.put("suppliername", row.get("suppliername"));
                    materialInfo.put("rawmaterialsupplieruid", row.get("rawmaterialsupplieruid"));
                    materialInfo.put("contactperson", row.get("contactperson"));
                    materialInfo.put("mobilenumber", row.get("mobilenumber"));
                    materialInfo.put("orderdate", row.get("orderdate"));
                    result.add(materialInfo);
                    index++;
                }
            } catch (Exception e) {
                System.err.println("⚠️ Failed to parse materialDetails JSON for PO: " + rawmaterialpurchaseorderuid);
            }
        }
        return result;
    }



    

    
    
}


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

import com.prog.model.erp.RawMaterialPurchaseRequest;



@Repository
public class RawMaterialPurchaseRequestDAO {

	

	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    // Maps SQL result set to MaterialRequest entity
	    class RawMaterialPurchaseRequestRowMapper implements RowMapper<RawMaterialPurchaseRequest> {
	        @Override
	        public RawMaterialPurchaseRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	RawMaterialPurchaseRequest request = new RawMaterialPurchaseRequest();
	            request.setId(rs.getLong("id"));
	            request.setRawmaterialpurchaserequestuid(rs.getString("rawmaterialpurchaserequestuid"));
	            request.setRequestedby(rs.getString("requestedby"));
	            request.setRequestdate(rs.getString("requestdate"));
	            request.setRequiredbydate(rs.getString("requiredbydate"));
	            request.setReason(rs.getString("reason"));
	            request.setPriority(rs.getString("priority"));
	            request.setRawmaterialuid(rs.getString("rawmaterialuid"));
	            request.setRawmaterialname(rs.getString("rawmaterialname"));
	            request.setProductuid(rs.getString("productuid"));
	            return request;
	        }
	    }

	   
	    public int saveRequests(RawMaterialPurchaseRequest request) {
	        String sql = "INSERT INTO rawmaterialpurchaserequest (" +
	                "rawmaterialpurchaserequestuid, productionplanninguid,rawmaterialuid,productuid, productname, rawmaterialname, requiredquantity, " +
	                "requestedby, requestdate, requiredbydate, reason, priority, insertdate, updatedate) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		     String formattedTimestamp = LocalDateTime.now().format(formatter);
		    
	           return jdbcTemplate.update(sql,
	        		   request.getRawmaterialpurchaserequestuid(),
	        		   request.getProductionplanninguid(),
	        		   request.getRawmaterialuid(),
	        		   request.getProductuid(),
	        		   request.getProductname(),
	        		   request.getRawmaterialname(),
	        		   request.getRequiredquantity(),
	        		   request.getRequestedby(),
	        		   request.getRequestdate(),
	        		   request.getRequiredbydate(),
	        		   request.getReason(),
	        		   request.getPriority(),
	        		   formattedTimestamp,
	        		   formattedTimestamp
	        		   );
	        }
	   
	    public List<Map<String, Object>> getAllMaterialRequests() {
	        String sql = """
	            SELECT 
	                mr.rawmaterialpurchaserequestuid,
	                mr.productionplanninguid,
	                mr.productname,
	                mr.requestedby,
	                mr.requestdate,
	                mr.requiredbydate,
	                mr.reason,
	                mr.priority,
	                mr.insertdate,
	                mr.updatedate,
	                GROUP_CONCAT(CONCAT(mr.rawmaterialname, ': ', mr.requiredquantity) SEPARATOR '<br>') AS rawmaterialDetails
	            FROM 
	                rawmaterialpurchaserequest mr
	            GROUP BY 
	                mr.rawmaterialpurchaserequestuid,
	                mr.productionplanninguid,
	                mr.productname,
	                mr.requestedby,
	                mr.requestdate,
	                mr.requiredbydate,
	                mr.reason,
	                mr.priority,
	                mr.insertdate,
	                mr.updatedate
	            ORDER BY mr.insertdate DESC
	        """;

	        return jdbcTemplate.queryForList(sql);
	    }


	    /**
	     * Deletes a material request by its ID
	     */
	    public int deleteRequest(String rawmaterialpurchaserequestuid) {
	        String sql = "DELETE FROM rawmaterialpurchaserequest WHERE rawmaterialpurchaserequestuid = ?";
	        return jdbcTemplate.update(sql, rawmaterialpurchaserequestuid);
	    }

	    
	    public List<Map<String, Object>> getRequestsByRequestId(String rawmaterialpurchaserequestuid) {
	        String sql = """
	            SELECT 
	                rawmaterialpurchaserequestuid,
	                productionplanninguid,
	                productname,
	                requestedby,
	                requestdate,
	                requiredbydate,
	                reason,
	                priority,
	                rawmaterialname,
	                requiredquantity
	                
	            FROM 
	                rawmaterialpurchaserequest
	            WHERE 
	                rawmaterialpurchaserequestuid = ?
	        """;
	        return jdbcTemplate.queryForList(sql, rawmaterialpurchaserequestuid);
	    }


	    /**
	     * Updates an existing material request by ID
	     */
	    public int updateMaterialRequest(RawMaterialPurchaseRequest updatedRequest) {
	        String sql = """
	            UPDATE rawmaterialpurchaserequest
	            SET 
	                requestdate = ?,
	                requiredbydate = ?,
	                reason = ?,
	                priority = ?,
	                updatedate = ?
	            WHERE rawmaterialpurchaserequestuid = ?
	        """;
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		     String formattedTimestamp = LocalDateTime.now().format(formatter);
		   
	        return jdbcTemplate.update(sql,
	            
	            updatedRequest.getRequestdate(),
	            updatedRequest.getRequiredbydate(),
	            updatedRequest.getReason(),
	            updatedRequest.getPriority(),
	            formattedTimestamp,
	            updatedRequest.getRawmaterialpurchaserequestuid());
	    }

	    /**
	     * Fetch material name by raw material UID
	     */
	    public List<Map<String, Object>> getByCode(String rawmaterialuid) {
	        String sql = "SELECT rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid = ?";
	        return jdbcTemplate.queryForList(sql, rawmaterialuid);
	    }

	    /**
	     * Get all available raw material UIDs
	     */
	    public List<Map<String, Object>> getAllRawMaterials() {
	        String sql = "SELECT rawmaterialuid FROM rawmaterialinfo";
	        return jdbcTemplate.queryForList(sql);
	    }

	    /**
	     * Get product name by product UID
	     */
	    public List<Map<String, Object>> getProductById(String productid) {
	        String sql = "SELECT productname FROM productinfo WHERE productuid = ?";
	        return jdbcTemplate.queryForList(sql, productid);
	    }

	    /**
	     * Get required raw materials and quantities by production planning UID
	     */
	    public List<Map<String, Object>> getMaterialSummaryByPlanningUID(String productionplanninguid) {
	        String sql = """
	            SELECT 
	                rm.rawmaterialname AS rawmaterialname,
	                pp.diff AS requiredquantity
	            FROM productionplanning pp
	            JOIN rawmaterialinfo rm ON pp.rawmaterialuid = rm.rawmaterialuid
	            WHERE pp.productionplanninguid = ?
	            GROUP BY rm.rawmaterialname, pp.diff
	        """;
	        return jdbcTemplate.queryForList(sql, productionplanninguid);
	    }

	    /**
	     * Get all production planning UIDs with product names
	     */
	    public List<Map<String, Object>> getAllProductionPlanningUIDs() {
	        String sql = """
	            SELECT DISTINCT 
	                pp.productionplanninguid, 
	                p.productname
	            FROM productionplanning pp
	            JOIN productinfo p ON pp.productuid = p.productuid
	        """;
	        return jdbcTemplate.queryForList(sql);
	    }

	    /**
	     * Get product name by production planning UID
	     */
	    public Map<String, Object> getProductNameByPlanningUID(String productionplanninguid) {
	        String sql = """
	            SELECT p.productname,p.productuid 
	            FROM productionplanning pp 
	            JOIN productinfo p ON pp.productuid = p.productuid 
	            WHERE pp.productionplanninguid = ?
	            LIMIT 1
	        """;
	        return jdbcTemplate.queryForMap(sql, productionplanninguid);
	    }
	    // ✅ Get all employee names and UIDs (used in dropdowns for manager/approver)
	    public List<Map<String, String>> getEmployeeNames() {
	        String sql = "SELECT employeeUID, CONCAT(first_name, ' ', last_name) AS fullName FROM employee";
	        return jdbcTemplate.query(sql, (rs, rowNum) -> {
	            Map<String, String> map = new HashMap<>();
	            map.put("uid", rs.getString("employeeUID"));
	            map.put("name", rs.getString("fullName"));
	            return map;
	        });
	    }
	}



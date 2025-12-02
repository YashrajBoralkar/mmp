package com.prog.Dao.productionandoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.WorkOrder;



@Repository
public class WorkOrderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// rowmapper
	public static class workorderentityRowMapper implements RowMapper<WorkOrder> {
		@Override
		public WorkOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
			WorkOrder wo = new WorkOrder();
			wo.setId(rs.getLong("id"));
			wo.setWorkorderuid(rs.getString("workorderuid"));
			wo.setProductionplanninguid(rs.getString("productionplanninguid"));
			wo.setProductuid(rs.getString("productuid"));
			wo.setWorkordertype(rs.getString("workordertype"));
			wo.setWorkorderdate(rs.getString("workorderdate"));
			wo.setPrioritylevel(rs.getString("prioritylevel"));
			wo.setWorkcenter(rs.getString("workcenter"));
			wo.setMachinerequired(rs.getString("machinerequired"));
			wo.setRawmaterialconsumption(rs.getString("rawmaterialconsumption"));
			wo.setLabourassigned(rs.getString("labourassigned"));
			wo.setCompletionstatus(rs.getString("completionstatus"));
			wo.setCompletedate(rs.getString("completedate"));
			wo.setReviewedby(rs.getString("reviewedby"));
			wo.setPlannedquantity(rs.getString("plannedquantity"));


			return wo;
		}
	}


	public int add(WorkOrder wo) {
	    String sql = "INSERT INTO workorder (id, workorderuid, productionplanninguid, productuid, workordertype, "
	               + "workorderdate, prioritylevel, workcenter, machinerequired, rawmaterialconsumption, labourassigned, "
	               + "completionstatus, completedate, reviewedby, plannedquantity, insertdate, updatedate) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
      
       	    return jdbcTemplate.update(sql,
	            wo.getId(),
	            wo.getWorkorderuid(),
	            wo.getProductionplanninguid(),
	            wo.getProductuid(),
	            wo.getWorkordertype(),
	            wo.getWorkorderdate(),
	            wo.getPrioritylevel(),
	            wo.getWorkcenter(),
	            wo.getMachinerequired(),
	            wo.getRawmaterialconsumption(),
	            wo.getLabourassigned(),
	            wo.getCompletionstatus(),
	            wo.getCompletedate(),
	            wo.getReviewedby(),
	            wo.getPlannedquantity(),
	            formattedTimestamp,
	            formattedTimestamp
	            );
	}

	public WorkOrder getWorkOrderById(Long id) {
		String sql = "SELECT * FROM workorder WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new workorderentityRowMapper(), id);
	}

	public List<Map<String, Object>> findallplan() {
	    String sql = "SELECT DISTINCT\r\n"
	    		+ "    wo.id AS \"ID\",\r\n"
	    		+ "    wo.workorderuid AS \"Work Order ID\",\r\n"
	    		+ "    pp.productionplanninguid AS \"Production Plan ID\",\r\n"
	    		+ "    wo.productuid AS \"Product UID\",\r\n"
	    		+ "    pi.productname AS \"Product Name\",\r\n"
	    		+ "    wo.workordertype AS \"Work Order Type\",\r\n"
	    		+ "    wo.workorderdate AS \"Work Order Date\",\r\n"
	    		+ "    wo.prioritylevel AS \"Priority Level\",\r\n"
	    		+ "    wo.workcenter AS \"Work Center\",\r\n"
	    		+ "    wo.machinerequired AS \"Machine Required\",\r\n"
	    		+ "    wo.rawmaterialconsumption AS \"Raw Material Consumption\",\r\n"
	    		+ "    wo.labourassigned AS \"Labour Assigned\",\r\n"
	    		+ "    wo.completionstatus AS \"Completion Status\",\r\n"
	    		+ "    wo.completedate AS \"Completion Date\",\r\n"
	    		+ "    wo.reviewedby AS \"Reviewed By\"\r\n"
	    		+ "FROM workorder wo\r\n"
	    		+ "LEFT JOIN productionplanning pp ON wo.productionplanninguid = pp.productionplanninguid\r\n"
	    		+ "LEFT JOIN productinfo pi ON wo.productuid = pi.productuid\r\n"
	    		+ ""
	    		+ "";
	    return jdbcTemplate.queryForList(sql);
	}


	// delete
	public int deleteById(Long id) {
		String sql = "DELETE FROM workorder WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	// update
	public int updateworkorder(WorkOrder so) {
	    String sql = "UPDATE workorder SET workordertype=?, workorderdate=?, workcenter=?, prioritylevel=?, "
	               + "machinerequired=?, rawmaterialconsumption=?, labourassigned=?, completionstatus=?, completedate=?, "
	               + "reviewedby=?, plannedquantity=?, updatedate=? WHERE id=?";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        
      	    return jdbcTemplate.update(sql,
	            so.getWorkordertype(),
	            so.getWorkorderdate(),
	            so.getWorkcenter(),
	            so.getPrioritylevel(),
	            so.getMachinerequired(),
	            so.getRawmaterialconsumption(),
	            so.getLabourassigned(),
	            so.getCompletionstatus(),
	            so.getCompletedate(),
	            so.getReviewedby(),
	            so.getPlannedquantity(),
	            formattedTimestamp,
	            so.getId());
	}

	public List<String> getProductionPlanningUId() {
		String sql = "select productionplanninguid from productionplanning";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productionplanninguid"));
	}
	
	public List<Map<String,Object>> getProductDetalisByProductionPlanningUID(String productionplanninguid){
		String sql="SELECT pp.productionplanninguid, pi.productuid, pi.productname\r\n"
				+ "FROM productionplanning pp\r\n"
				+ "JOIN productinfo pi ON pi.productuid = pp.productuid\r\n"
				+ "WHERE pp.productionplanninguid = ?";
		   return jdbcTemplate.queryForList(sql, productionplanninguid);

	}
	
	
	
	// -----------------------//
	   // Fetch production planning UID(s) by Product UID
    public List<Map<String, Object>> getProductionPlanningByProductUid(String productuid) {
        String sql = "SELECT DISTINCT productionplanninguid FROM productionplanning WHERE productuid = ?";
        return jdbcTemplate.queryForList(sql, productuid);
    }
    
    
    
    public List<Map<String, Object>> getAllProducts() {
        String sql = "SELECT DISTINCT productuid FROM productinfo"; 
        return jdbcTemplate.queryForList(sql);
    }


    // Fetch approved raw materials for given production planning UID
    public List<Map<String, Object>> getApprovedRawMaterials(String productionplanninguid) {
        // Step 1: Fetch all rawmaterialuid values for given productionplanninguid with status Approved
        String sql1 = """
            SELECT rawmaterialuid 
            FROM productionplanning 
            WHERE productionplanninguid = ? AND approvalstatus = 'Approved'
        """;
        List<String> rawMaterialUidList = jdbcTemplate.queryForList(sql1, String.class, productionplanninguid);

        // Step 2: If no raw materials found, return empty
        if (rawMaterialUidList.isEmpty()) {
            return new ArrayList<>();
        }

        // Step 3: Combine all rawmaterialuids into single list (handle comma-separated values from DB)
        List<String> allRawMaterialUids = new ArrayList<>();
        for (String rawMaterialUids : rawMaterialUidList) {
            if (rawMaterialUids != null && !rawMaterialUids.trim().isEmpty()) {
                String[] splitUids = rawMaterialUids.split(",");
                for (String uid : splitUids) {
                    allRawMaterialUids.add(uid.trim());
                }
            }
        }

        if (allRawMaterialUids.isEmpty()) {
            return new ArrayList<>();
        }

        // Step 4: Build SQL with IN clause dynamically
        String inClause = String.join(",", Collections.nCopies(allRawMaterialUids.size(), "?"));
        String sql2 = """
            SELECT  rawmaterialuid, rawmaterialname 
            FROM rawmaterialinfo 
            WHERE rawmaterialuid IN (%s) 
        """.formatted(inClause);

        // Step 5: Execute query and return list
        return jdbcTemplate.queryForList(sql2, allRawMaterialUids.toArray());
    }

	
	
	
	
	

}


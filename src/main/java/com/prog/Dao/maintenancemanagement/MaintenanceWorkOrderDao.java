package com.prog.Dao.maintenancemanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceWorkOrder;

@Repository
public class MaintenanceWorkOrderDao {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 	
	    public  class MaintenanceWorkOrderRowMapper implements RowMapper<MaintenanceWorkOrder> {

			@Override
			public MaintenanceWorkOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
				MaintenanceWorkOrder order=new MaintenanceWorkOrder();
				order.setId(rs.getLong("id"));
	            order.setMaintenanceworkorderuid(rs.getString("maintenanceworkorderuid"));
	            order.setWorktype(rs.getString("worktype"));
	            order.setRequestedate(rs.getString("requestedate")); // Now string
	            order.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
	            order.setTaskdescription(rs.getString("taskdescription"));
	            order.setPrioritylevel(rs.getString("prioritylevel"));
	            order.setEstimatedcompletiondate(rs.getString("estimatedcompletiondate")); // Now string
	            order.setStatus(rs.getString("status"));
	          
				return order;
			}
	    	
	    }

	    public int saveMaintenanceworkorder(MaintenanceWorkOrder order) {
	        String sql = "INSERT INTO maintenanceworkorder (" +
	                "maintenanceworkorderuid, worktype, requestedate, equipmentmasteruid, " +
	                "taskdescription, prioritylevel, estimatedcompletiondate, " +
	                "status, insertdate, updatedate) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

	        return jdbcTemplate.update(sql,
	                order.getMaintenanceworkorderuid(),
	                order.getWorktype(),
	                order.getRequestedate(),
	                order.getEquipmentmasteruid(),
	                order.getTaskdescription(),
	                order.getPrioritylevel(),
	                order.getEstimatedcompletiondate(),
	                order.getStatus(),
	                formattedTimestamp,
	                formattedTimestamp
	        );
	    }

	    public List<Map<String,Object>> getAllMaintenanceWorkOrder() {
	        String sql = "SELECT mwo.id,mwo.maintenanceworkorderuid, mwo.worktype, mwo.requestedate, em.equipmentmasteruid, "
	        		+ "mwo.taskdescription, mwo.prioritylevel, mwo.estimatedcompletiondate,mwo.status "
	        		+ " FROM maintenanceworkorder mwo "
	        		+ "join equipmentmaster em ON em.equipmentmasteruid=mwo.equipmentmasteruid";
	        return jdbcTemplate.queryForList(sql);
	    }
	    
	    public MaintenanceWorkOrder getMaintenanceWorkOrderById(Long id) {
	        String sql = "SELECT * FROM maintenanceworkorder WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new MaintenanceWorkOrderRowMapper(), id);
	    }
	    
	    public int updateMaintenanceWorkOrder(MaintenanceWorkOrder order) {
	        String sql = "UPDATE maintenanceworkorder SET worktype = ?, requestedate = ?, taskdescription = ?, "
	        		+ "prioritylevel = ?, assignedtechnician = ?, updatedate = ?, estimatedcompletiondate = ?, "
	        		+ "status = ? WHERE id = ?";

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedTimestamp = LocalDateTime.now().format(formatter);
		    
	        return jdbcTemplate.update(sql,
	                order.getWorktype(),
	                order.getRequestedate(),
	                order.getTaskdescription(),
	                order.getPrioritylevel(),
	                order.getUpdatedate(),
	                order.getEstimatedcompletiondate(),
	                order.getStatus(),
	                formattedTimestamp,
	                order.getId()
	        );
	    }

	    public int deleteMaintenanceWorkOrder(Long id) {
	        String sql = "DELETE FROM maintenanceworkorder WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }

	    
	    public List<String> getEquipmentDetailsById(){
			String sql="select equipmentmasteruid from equipmentmaster";
			return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("equipmentmasteruid"));
		}
		public EquipmentMaster getEquipmentDetails(String equipmentmasteruid){
			String sql="select equipmentname from equipmentmaster where equipmentmasteruid=? ";
	    	return jdbcTemplate.queryForObject(sql, new Object[] {equipmentmasteruid}, (rs, rowNum) -> {
	    		EquipmentMaster eu=new EquipmentMaster();
	    		eu.setEquipmentname(rs.getString("equipmentname"));
	    		return eu;
	    	});
		}
}

package com.prog.Dao.productionandoperation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceRequest;
import com.prog.model.erp.productionplanning;



@Repository
public class MaintenanceRequestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// RowMapper to convert SQL ResultSet rows into MaintenanceRequest objects
	private class MaintenanceRequestrowMapper implements RowMapper<MaintenanceRequest> {
		@Override
		public MaintenanceRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		MaintenanceRequest request = new MaintenanceRequest();
        request.setId(rs.getLong("id"));
        request.setMaintenancerequestuid(rs.getString("maintenancerequestuid"));
        request.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
        request.setMaintenancetype(rs.getString("maintenancetype"));
        request.setIssuedescription(rs.getString("issuedescription"));
        request.setSparepartsrequired(rs.getString("sparepartsrequired"));
        request.setEstimatedrepaircost(rs.getDouble("estimatedrepaircost"));
        request.setEstimatedcompletiondate(rs.getString("estimatedcompletiondate"));
        request.setRequestedstatus(rs.getString("requestedstatus"));
        request.setEmployeeuid(rs.getString("employeeuid"));
         return request;
    }
	}
 
    public int saveMaintenanceRequest(MaintenanceRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String sql = "INSERT INTO maintenancerequest (id, maintenancerequestuid, equipmentmasteruid, maintenancetype, " +
                "issuedescription, sparepartsrequired, estimatedrepaircost, estimatedcompletiondate, " +
                "requestedstatus, employeeuid, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            request.getId(),
            request.getMaintenancerequestuid(),
            request.getEquipmentmasteruid(),
            request.getMaintenancetype(),
            request.getIssuedescription(),
            request.getSparepartsrequired(),
            request.getEstimatedrepaircost(),
            request.getEstimatedcompletiondate(),   
            request.getRequestedstatus(),           
            request.getEmployeeuid(),
            formattedTimestamp,                      
            formattedTimestamp                       
        );
    }

    public MaintenanceRequest getMaintenanceRequestbyid(Long id) {
		String sql = "SELECT * FROM maintenancerequest WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new MaintenanceRequestrowMapper(), id);
	}
	
    public List<Map<String, Object>> getMaintenanceRequestList() {
        String sql = "SELECT mr.id, mr.maintenancerequestuid, em.equipmentmasteruid, em.equipmentname, " +
                     "mr.maintenancetype, mr.issuedescription, mr.sparepartsrequired, " +
                     "mr.estimatedrepaircost, mr.estimatedcompletiondate, mr.requestedstatus, " +
                     "e.employeeuid, e.first_name, e.last_name " +
                     "FROM maintenancerequest mr " +
                     "JOIN equipmentmaster em ON em.equipmentmasteruid = mr.equipmentmasteruid " +
                     "JOIN employee e ON e.employeeuid = mr.employeeuid";

        return jdbcTemplate.queryForList(sql);
    }
 // Update an existing maintenance request record
    public int updateMaintenanceRequest(MaintenanceRequest request) {
	    LocalDateTime now = LocalDateTime.now();

        String sql = "UPDATE maintenancerequest SET maintenancetype = ?, " +
                "issuedescription = ?, sparepartsrequired = ?, estimatedrepaircost = ?, estimatedcompletiondate = ?, " +
                "requestedstatus = ?, updatedate = ? WHERE id = ?";

        return jdbcTemplate.update(sql,
                request.getMaintenancetype(),
                request.getIssuedescription(),
                request.getSparepartsrequired(),
                request.getEstimatedrepaircost(),
                request.getEstimatedcompletiondate(),                 request.getRequestedstatus(),
                Timestamp.valueOf(now),
                request.getId());
    }

    // Delete a maintenance request record by ID
    public void deleteMaintenanceRequestById(Long id) {
        jdbcTemplate.update("DELETE FROM maintenancerequest WHERE id = ?", id);
    }
    
    public List<String> getEquipmentMasteruid(){
    	String sql="select equipmentmasteruid from equipmentmaster ";
    	return  jdbcTemplate.query(sql,(rs,rowNum) -> rs.getString("equipmentmasteruid"));
    }
    
    
    public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
        String sql = "SELECT equipmentname FROM equipmentmaster WHERE equipmentmasteruid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{equipmentmasteruid}, (rs, rowNum) -> {
            EquipmentMaster em = new EquipmentMaster();
            em.setEquipmentname(rs.getString("equipmentname"));
            return em;
        });
    }

    public List<String> getEmployeeuid(){
    	String sql="select employeeuid from employee";
    	return  jdbcTemplate.query(sql,(rs,rowNum) -> rs.getString("employeeuid"));
    }

    public String getEmployeeFullNameById(String employeeuid) {
        String sql = "SELECT first_name, last_name FROM employee WHERE employeeuid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{employeeuid}, (rs, rowNum) -> {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return firstName + " " + lastName;
        });
    }

}

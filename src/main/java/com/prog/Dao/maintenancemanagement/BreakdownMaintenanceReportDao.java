package com.prog.Dao.maintenancemanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.BreakdownMaintenanceReport;
import com.prog.model.erp.Employee;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceHistoryLogForm;


@Repository
public class BreakdownMaintenanceReportDao {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	 public static class BreakdownMaintenanceReportRowMapper implements RowMapper<BreakdownMaintenanceReport>{

		@Override
		public BreakdownMaintenanceReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			BreakdownMaintenanceReport report = new BreakdownMaintenanceReport();
			report.setId(rs.getLong("id"));
	        report.setBreakdownmaintenancereportuid(rs.getString("breakdownmaintenancereportuid"));
	        report.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
	        report.setEmployeeuid(rs.getString("employeeuid")); // ✅ Add this line
	        report.setBreakdowndatetime(rs.getString("breakdowndatetime"));
	        report.setIssuedescription(rs.getString("issuedescription"));
	        report.setDowntimeduration(rs.getString("downtimeduration"));
	        report.setRootcause(rs.getString("rootcause"));
	        report.setRepairactiontaken(rs.getString("repairactiontaken"));
	        report.setRepairedby(rs.getString("repairedby"));
	        report.setRepaircompletiondate(rs.getString("repaircompletiondate"));
	        report.setStatus(rs.getString("status"));
	      
			return report;
		}
		 
	 }

	 public int saveBreakdownMaintenanceReport(BreakdownMaintenanceReport r) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedTimestamp = LocalDateTime.now().format(formatter);

		    String sql = "INSERT INTO breakdownmaintenancereport " +
		        "(id, breakdownmaintenancereportuid, equipmentmasteruid, breakdowndatetime, employeeuid, issuedescription, " +
		        "downtimeduration, rootcause, repairactiontaken, repairedby, repaircompletiondate, status, insertdate, updatedate) " +
		        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		    return jdbcTemplate.update(sql,
		        r.getId(),                               
		        r.getBreakdownmaintenancereportuid(),    
		        r.getEquipmentmasteruid(),               
		        r.getBreakdowndatetime(),                
		        r.getEmployeeuid(),                    
		        r.getIssuedescription(),                 
		        r.getDowntimeduration(),                
		        r.getRootcause(),                        
		        r.getRepairactiontaken(),                 
		        r.getRepairedby(),                     
		        r.getRepaircompletiondate(),              
		        r.getStatus(),                           
		        formattedTimestamp,                      
		        formattedTimestamp                       
		    );
		}

	 public BreakdownMaintenanceReport getBreakdownMaintenanceReportById(Long id) {
			String sql= "select * from breakdownmaintenancereport where id=?";
			try {
		    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(BreakdownMaintenanceReport.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
		}
		
		public List<Map<String,Object>> getAllBreakdownMaintenanceReportData(){
			String sql="select bdr.id, bdr.breakdownmaintenancereportuid, em.equipmentmasteruid,em.equipmentname,bdr.breakdowndatetime, e.employeeuid,e.first_name, bdr.issuedescription, \r\n"
					+ "		        bdr.downtimeduration, bdr.rootcause, bdr.repairactiontaken, bdr.repairedby, bdr.repaircompletiondate, bdr.status\r\n"
					+ "                from breakdownmaintenancereport bdr\r\n"
					+ "                JOIN employee e ON e.employeeuid=bdr.employeeuid\r\n"
					+ "                JOIN equipmentmaster em ON em.equipmentmasteruid=bdr.equipmentmasteruid;\r\n";
			return jdbcTemplate.queryForList(sql);
		}
	 
	 public int updateBreakdownMaintenanceReport(BreakdownMaintenanceReport r) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedTimestamp = LocalDateTime.now().format(formatter);

   	  String sql = "UPDATE breakdownmaintenancereport SET  breakdowndatetime = ?,issuedescription = ?, downtimeduration = ?,"
   	  		+ " rootcause = ?,repairedby=?,repaircompletiondate=?, status=?, updatedate = ? WHERE id = ?";

   	  return jdbcTemplate.update(sql,
   			  r.getBreakdowndatetime(),
   			    r.getIssuedescription(),
   			    r.getDowntimeduration(),
   			    r.getRootcause(),
   			    r.getRepairedby(),
   			    r.getRepaircompletiondate(),
   			    r.getStatus(),
   			    formattedTimestamp,
   			    r.getId());

   
   }
	 
	 public void deleteBreakdownMaintenanceReportById(Long id) {
	        jdbcTemplate.update("DELETE FROM breakdownmaintenancereport WHERE id = ?", id);
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

		public List<String> getEmployeeDetailsById(){
			String sql="select employeeuid from employee";
			return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("employeeuid"));
		}
		public Employee getEmployeeDetails(String employeeuid){
			String sql="select first_name from employee where employeeuid=? ";
	    	return jdbcTemplate.queryForObject(sql, new Object[] {employeeuid}, (rs, rowNum) -> {
	    		Employee e=new Employee();
	    		e.setFirstName(rs.getString("first_name"));
	    		return e;
	    	});
		}
					
}

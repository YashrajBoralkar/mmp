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

import com.prog.model.erp.Employee;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceRequestInternalTicketing;

@Repository
public class MaintenanceRequestInternalTicketingDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;

	 private static class MaintenanceRequestInternalTicketingRowMapper implements RowMapper<MaintenanceRequestInternalTicketing>
	    {
		 @Override
			public MaintenanceRequestInternalTicketing mapRow(ResultSet rs, int rowNum) throws SQLException {
			 MaintenanceRequestInternalTicketing mr = new MaintenanceRequestInternalTicketing();
			 	mr.setId(rs.getLong("id"));
				mr.setMaintenancerequestinternalticketinguid(rs.getString("maintenancerequestinternalticketinguid"));
			 	mr.setDepartmentname(rs.getString("departmentname"));
				mr.setEmployeeuid(rs.getString("employeeuid"));
				mr.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
				mr.setId(rs.getLong("id"));
				mr.setIssuedescription(rs.getString("issuedescription"));
				mr.setPrioritylevel(rs.getString("prioritylevel"));
				mr.setRequestdate(rs.getString("requestdate"));
				mr.setStatus(rs.getString("status"));
				return mr;
			}
	    }
	
	 public int savemaintencerequest(MaintenanceRequestInternalTicketing mr) 
	    {
		 String sql="INSERT INTO maintenancerequestinternalticketing(departmentname, employeeuid, equipmentmasteruid, insertdate, issuedescription, maintenancerequestinternalticketinguid, prioritylevel, requestdate, status, updatedate)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		     String formattedTimestamp = LocalDateTime.now().format(formatter);
	    	
	    	return jdbcTemplate.update(sql, 
	    			mr.getDepartmentname(),
	    			mr.getEmployeeuid(),
	    			mr.getEquipmentmasteruid(),
	    			formattedTimestamp,
	    			mr.getIssuedescription(),
	    			mr.getMaintenancerequestinternalticketinguid(),
	    			mr.getPrioritylevel(),
	    			mr.getRequestdate(),
	    			mr.getStatus(),
	    			formattedTimestamp
	    			);
	  }
	 
	 
	 public List<Map<String,Object>> showMaintenanceRequestInternalTicketinglist()
		{
			String sql="SELECT mr.id, mr.maintenancerequestinternalticketinguid, mr.issuedescription, mr.prioritylevel, mr.requestdate, \r\n"
					+ "    mr.status, mr.employeeuid AS emp_in_request, e.employeeuid, e.first_name,\r\n"
					+ "    mr.departmentname , d.departmentname , mr.equipmentmasteruid, eq.equipmentmasteruid , eq.equipmentname\r\n"
					+ "FROM maintenancerequestinternalticketing mr\r\n"
					+ "LEFT JOIN employee e ON mr.employeeuid = e.employeeuid\r\n"
					+ "LEFT JOIN department d ON mr.departmentname = d.departmentname\r\n"
					+ "LEFT JOIN equipmentmaster eq ON mr.equipmentmasteruid = eq.equipmentmasteruid";
			return jdbcTemplate.queryForList(sql);
		}
	 
	 public MaintenanceRequestInternalTicketing getMaintenanceRequestInternalTicketingById(Long id)
		{
			String sql="SELECT * FROM maintenancerequestinternalticketing where id=?  ";
			try {
		    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(MaintenanceRequestInternalTicketing.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
		}		
	 
	 
	 public int updateMaintenanceRequestInternalTicketing(MaintenanceRequestInternalTicketing mr)
	    {
	    	String sql="UPDATE maintenancerequestinternalticketing SET issuedescription = ? , prioritylevel = ? , status= ? , updatedate=? where id = ?";
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		     String formattedTimestamp = LocalDateTime.now().format(formatter);
			return jdbcTemplate.update(sql, 
					mr.getIssuedescription(),
					mr.getPrioritylevel(),
					mr.getStatus(),
					formattedTimestamp,
					mr.getId()); 	
	    }
		
		public int deleteMaintenanceRequestInternalTicketing(Long id)	
		{
			String sql="DELETE FROM maintenancerequestinternalticketing WHERE id=?";
			return jdbcTemplate.update(sql,id);
			
		}
	 
	 public List<String> getDepartmentName(){
			String sql="select departmentname from department";
			return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("departmentname"));
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

package com.prog.Dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Department;
import com.prog.model.erp.Employee;

@Repository
public class departmentdao {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 public int saveDepartment(Department department) {
		 String sql ="INSERT INTO department (departmentuid,departmentname, departmenttype, description, status) VALUES (?, ?, ?, ?, ?)";
	              
          return jdbcTemplate.update(sql,
        		  department.getDepartmentuid(),
        		  department.getDepartmentname(),
        		  department.getDepartmenttype(),
        		  department.getDescription(),
        		  department.getStatus()
        		  //department.getInsertdate(),
        		//  department.getUpdatedate()
        		  );
	    }
	 
	 // Fetch a single department by ID
	    public Department getDepartmentById(Long id) {
	        String sql = "SELECT * FROM department WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new DepartmentRowMapper(), id);
	    }
	    
	    
	    // Update an existing Department
	    public int updateDepartment(Department department) {
	        String sql = "UPDATE department SET departmentname = ?, departmenttype = ?, " +
	                "description = ?,status = ? WHERE id = ?";

	        return jdbcTemplate.update(sql,
	        		department.getDepartmentname(),
	        		department.getDepartmenttype(),
	                department.getDescription(),
	                department.getStatus(),
	                department.getId());
	    }

	 // Fetch a single department by ID
	    public Department getDepartment() {
	        String sql = "SELECT * FROM department";
	        return jdbcTemplate.queryForObject(sql, new DepartmentRowMapper());
	    }
	      
	    
	    
	    
	    // Delete an department by ID
	    public void deleteById(Long id) {
	        String sql = "DELETE FROM department WHERE id = ?";
	        int rowsAffected = jdbcTemplate.update(sql, id);
	        if (rowsAffected == 0) {
	            throw new RuntimeException("No department found with id " + id);
	        }
	    }
	    
	    
	    
	    
	    
	    // RowMapper for Department
	    private static class DepartmentRowMapper implements RowMapper<Department> {
	        @Override
	        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Department department = new Department();
	        	department.setId(rs.getLong("id"));
	        	department.setDepartmentcode(rs.getString("departmentcode"));
	        	department.setDepartmentname(rs.getString("departmentname"));
	        	department.setDepartmenttype(rs.getString("departmenttype"));
	        	department.setDepartmentuid(rs.getString("departmentuid"));
	        	department.setDescription(rs.getString("description"));
	        	department.setStatus(rs.getString("status"));

	            return department;
	        }
	        
	       
	    }
	    
	    
	    //FOR COMBINE FETCHINF PURPOSE
	    
	    public List<String> getalldepartment(){
			 String sql = "SELECT departmentname FROM department";
			 return jdbcTemplate.queryForList(sql, String.class);
		 }

}

package com.prog.Dao.hrms;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Attendance;
import com.prog.model.erp.EmployeeBatchCreation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeBatchCreationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static  class EmpBatchCreationRowMapper implements RowMapper<EmployeeBatchCreation>{
		@Override
		public EmployeeBatchCreation mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeBatchCreation empbatch= new EmployeeBatchCreation();
			empbatch.setId(rs.getLong("id"));
			empbatch.setEmpbatchuid(rs.getString("empbatchuid"));
			empbatch.setBatchname(rs.getString("batchname"));
			empbatch.setBatchdescription(rs.getString("batchdescription"));
			empbatch.setEmployeeuid(rs.getString("employeeuid"));
			empbatch.setEmployeename(rs.getString("employeename"));
			empbatch.setDepartmentuid(rs.getString("departmentuid"));
			empbatch.setDepartmentname(rs.getString("departmentname"));
			empbatch.setStatus(rs.getString("status"));
			empbatch.setRemark(rs.getString("remark"));
			return empbatch;
		}	
	}
    
    
    public int addBatch(EmployeeBatchCreation empbatch) {
        String sql = "INSERT INTO employeebatchcreation (empbatchuid, batchname, batchdescription, employeeuid, employeename, departmentuid, departmentname, status, remark, insertdate, updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
    
        return jdbcTemplate.update(sql,
        		empbatch.getEmpbatchuid(),
        		empbatch.getBatchname(),
        		empbatch.getBatchdescription(),
        		empbatch.getEmployeeuid(), // comma-separated
        		empbatch.getEmployeename(), // comma-separated
        		empbatch.getDepartmentuid(),
        		empbatch.getDepartmentname(),
        		empbatch.getStatus(),
        		empbatch.getRemark(),
                formattedTimestamp,
                formattedTimestamp
        		);
    }

    public int updateBatch(EmployeeBatchCreation empbatch) {
        String sql = "UPDATE employeebatchcreation SET batchname=?, batchdescription=?, employeeuid=?, employeename=?, departmentuid=?, departmentname=?, status=?, remark=?, updatedate=? WHERE id=?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
    
        return jdbcTemplate.update(sql,
        		empbatch.getBatchname(),
        		empbatch.getBatchdescription(),
        		empbatch.getEmployeeuid(), // comma-separated
        		empbatch.getEmployeename(), // comma-separated
        		empbatch.getDepartmentuid(),
        		empbatch.getDepartmentname(),
        		empbatch.getStatus(),
        		empbatch.getRemark(),
                formattedTimestamp,
                empbatch.getId()
                );
    }

 // Hard delete
    public int deleteById(Long id) {
        String sql = "DELETE FROM employeebatchcreation WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }


    public List<Map<String, Object>> findAllBatches() {
        String sql = "SELECT * FROM employeebatchcreation ORDER BY id DESC";
        return jdbcTemplate.queryForList(sql);
    }


    public EmployeeBatchCreation findById(Long id) {
        String sql = "SELECT * FROM employeebatchcreation WHERE id=?";
	    return jdbcTemplate.queryForObject(sql, new EmpBatchCreationRowMapper(), id); // ✅ correct
	}

    public List<Map<String, Object>> findAllDepartments() {
        // If you also have a status in department table
        String sql = "SELECT departmentuid, departmentname FROM department";
        return jdbcTemplate.queryForList(sql);
    }

    
    
    public List<Map<String, Object>> findEmployeesByDepartment(String departmentuid) {
        String sql = "SELECT employeeuid, CONCAT(first_name,' ',last_name) AS employeename FROM employee WHERE departmentuid=?";
        return jdbcTemplate.queryForList(sql, departmentuid);
    }

    public Map<String, Object> findDepartmentDetails(String departmentuid) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT departmentuid, departmentname FROM department WHERE departmentuid=?", departmentuid);
        return list.isEmpty() ? null : list.get(0);
    }
    
 // Update all existing employees to set departmentname from department table
    public int updateAllEmployeeDepartmentNames() {
        String sql = "UPDATE employee e " +
                     "JOIN department d ON e.departmentuid = d.departmentuid " +
                     "SET e.departmentname = d.departmentname " +
                     "WHERE e.departmentname IS NULL";
        return jdbcTemplate.update(sql);
    }

}

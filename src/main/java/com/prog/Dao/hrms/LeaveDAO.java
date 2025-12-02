package com.prog.Dao.hrms;

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

import com.prog.model.erp.Leaveinfo;
@Repository
public class LeaveDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class LeaveRowMapper implements RowMapper<Leaveinfo>{

		@Override
		public Leaveinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Leaveinfo lf= new Leaveinfo();
			lf.setId(rs.getLong("id"));
			lf.setEmployeeuid(rs.getString("employeeuid"));
			lf.setLeaveapplicationdate("leaveapplicationdate");
			lf.setLeaveType(rs.getString("leave_type"));
			lf.setLeaveStatus(rs.getString("leave_status"));
			lf.setFromDate(rs.getString("from_date"));
			lf.setToDate(rs.getString("to_date"));
			lf.setTotalDays(rs.getNString("total_days"));
			lf.setReason(rs.getString("reason"));
			lf.setSupportingDocument(rs.getBytes("supporting_document"));
			lf.setApprovedBy(rs.getString("approved_by"));
			lf.setApprovalDate(rs.getString("approval_date"));
			lf.setRemarks(rs.getString("remarks"));
			lf.setDocName(rs.getString("doc_name"));
			
			return lf;
		}	
	}
	
	//Method to fetch the data from database
		public List<Map<String, Object >> getAllLeave(){
			String sql="select lev.id,lev.leaveapplicationdate, lev.total_days, lev.reason,\r\n"
					+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from leaveinfo lev\r\n"
					+ "join employee emp on lev.employeeuid = emp.employeeuid";
			return jdbcTemplate.queryForList(sql);
		};
		

		public Leaveinfo getLeaveById(Long id) {
		    String sql = "SELECT * FROM LEAVEINFO WHERE id = ?";
		    try {
		        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Leaveinfo.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
		}
		
		// Add a new entry to the leave form table
		public int addLeave(Leaveinfo leaveForm) {
		    // Use descriptive column names and proper mapping
		    String sql = """
		        INSERT INTO LEAVEINFO 
		        (employeeuid,leaveuid,leaveapplicationdate,leave_type, 
		         from_date, to_date,total_days, reason,supporting_document, 
		         leave_status,approved_by,approval_date, remarks, doc_name, insertdate, updatedate) 
		        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)
		        """;
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
           
		    // Return the result of the update operation
		    return jdbcTemplate.update(sql,
		            leaveForm.getEmployeeuid(),
		            leaveForm.getLeaveuid(),
		            leaveForm.getLeaveapplicationdate(),
		            leaveForm.getLeaveType(),
		            leaveForm.getFromDate(),
		            leaveForm.getToDate(),
		            leaveForm.getTotalDays(),
		            leaveForm.getReason(),
		            leaveForm.getSupportingDocument(),
		            leaveForm.getLeaveStatus(),
		            leaveForm.getApprovedBy(),
		            leaveForm.getApprovalDate(),
		            leaveForm.getRemarks(),
		            leaveForm.getDocName(),
		            formattedTimestamp,
	                formattedTimestamp
		    );
		}
		
		public int updateLeave(Leaveinfo lf) {
		    String sql = "UPDATE LEAVEINFO SET approval_date = ?,approved_by = ?, from_date = ?,leaveapplicationdate = ?,leave_status = ?,leave_type = ?, reason = ?,remarks = ?, supporting_document = ?,to_date = ?,total_days = ?, doc_name = ?,updatedate=? WHERE id = ?";
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
           
		    // Using the correct order of parameters
		    return jdbcTemplate.update(sql,
		        lf.getApprovalDate(),
		        lf.getApprovedBy(),
		        lf.getFromDate(),
		        lf.getLeaveapplicationdate(),
		        lf.getLeaveStatus(),
		        lf.getLeaveType(),
		        lf.getReason(),
		        lf.getRemarks(),
		        lf.getSupportingDocument(),
		        lf.getToDate(),
		        lf.getTotalDays(),
		        lf.getDocName(),
		        formattedTimestamp,
		        lf.getId() // Make sure to pass the correct ID value for the WHERE condition
		    );
		}

		
		public int deleteLeave(Long id) {
			String sql="DELETE FROM LEAVEINFO WHERE id = ?";
			return jdbcTemplate.update(sql,id);
		}
}

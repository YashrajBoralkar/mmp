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

import com.prog.model.erp.Shiftinfo;

@Repository
public class ShiftDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class ShiftRowMapper implements RowMapper<Shiftinfo>{

		@Override
		public Shiftinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Shiftinfo sf= new Shiftinfo();
			
			sf.setId(rs.getLong("id"));
			sf.setShiftName(rs.getString("shift_name"));
			sf.setStartTime(rs.getString("start_time"));
			sf.setEndTime(rs.getString("end_time"));
			sf.setShiftDuration(rs.getString("shift_duration"));
			sf.setEmployeeuid(rs.getString("employeeuid"));
			sf.setShiftType(rs.getString("shift_type"));
			sf.setShiftDate(rs.getString("shift_date"));
			sf.setShiftStatus(rs.getString("shift_status"));
			sf.setRemarks(rs.getString("remarks"));
			
			return sf;
		}
	}
	
	//Method to fetch the data from database
	public List<Map<String, Object>> getAllShift(){
		String sql="select st.id,st.shift_date, st.shift_duration, st.shift_status,\r\n"
				+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from shiftinfo st\r\n"
				+ "join employee emp on st.employeeuid = emp.employeeuid";
		return jdbcTemplate.queryForList(sql);
	}
	
	public Shiftinfo getShiftById(Long id) {
	    String sql = "SELECT * FROM shiftinfo WHERE id = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Shiftinfo.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	// Add a new entry to the leave form table
	public int addShift(Shiftinfo sf) {
		// Use descriptive column names and proper mapping
		String sql = """
				INSERT INTO shiftinfo 
			    (shiftinfouid,shift_name,start_time,end_time,shift_duration, employeeuid, shift_type,shift_date,shift_status,remarks, insertdate, updatedate) 
			        VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?,?)
			        """;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String formattedTimestamp = LocalDateTime.now().format(formatter);
        
		//Return the result from shift table
		return jdbcTemplate.update(sql,
			
			sf.getShiftinfouid(),
			sf.getShiftName(),
			sf.getStartTime(),
			sf.getEndTime(),
			sf.getShiftDuration(),
			sf.getEmployeeuid(),
			sf.getShiftType(),
			sf.getShiftDate(),
			sf.getShiftStatus(),
			sf.getRemarks(),
			formattedTimestamp,
            formattedTimestamp
		);
	}
	
	public int updateShift(Shiftinfo sf) {
		String sql="UPDATE shiftinfo SET shift_name = ?,start_time = ?,end_time = ?,shift_duration = ?, shift_type = ?,shift_date = ?,shift_status = ?,remarks = ?, updatedate= ? WHERE id = ?";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String formattedTimestamp = LocalDateTime.now().format(formatter);
        
		return jdbcTemplate.update(sql,
				sf.getShiftName(),
				sf.getStartTime(),
				sf.getEndTime(),
				sf.getShiftDuration(),
				sf.getShiftType(),
				sf.getShiftDate(),
				sf.getShiftStatus(),
				sf.getRemarks(),
				formattedTimestamp,
				sf.getId()
		);
	}
	
	public int deleteShift(Long id) {
		String sql="DELETE FROM shiftinfo WHERE id = ?";
		return jdbcTemplate.update(sql,id);
	}
 
}

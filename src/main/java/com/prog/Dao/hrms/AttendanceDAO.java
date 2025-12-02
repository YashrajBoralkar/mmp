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

import com.prog.model.erp.Attendance;
@Repository
public class AttendanceDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//Add new data in attendance form table
		public int  addAttendace(Attendance af) {
			String sql="INSERT INTO ATTENDANCE (id,employeeuid, attendance_status,breaks, break_end_time, break_start_time, check_in_time, check_out_time, comments, attendance_date, total_break_time, total_working_time, attendanceuid, insertdate, updatedate)VALUES (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	           
			return jdbcTemplate.update(sql,
					af.getId(),
					af.getEmployeeuid(),
					af.getAttendanceStatus(),
					af.getBreaks(),
					af.getBreakEndTime(),
					af.getBreakStartTime(),
					af.getCheckInTime(),
					af.getCheckOutTime(),
					af.getComments(),
					af.getAttendanceDate(),
					af.getTotalBreakTime(),
					af.getTotalWorkingTime(),
					af.getAttendanceuid(),
					formattedTimestamp,
	                formattedTimestamp
	                
					);
		}
		
		//Method to fetch the data from database
		public List<Map<String, Object>> getAllAttendance(){
			String sql="select atd.id,atd.attendance_date, atd.check_in_time, atd.check_out_time, atd.total_working_time,\r\n"
					+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from attendance atd \r\n"
					+ "join employee emp on atd.employeeuid = emp.employeeuid";
			return jdbcTemplate.queryForList(sql);
				};
				
		private static  class AttendanceRowMapper implements RowMapper<Attendance>{
		@Override
		public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
			Attendance af=new Attendance();
			af.setId(rs.getLong("id"));
			af.setEmployeeuid(rs.getString("employeeuid"));
			af.setAttendanceuid(rs.getString("attendanceuid"));
			af.setAttendanceDate(rs.getString("attendance_date"));
			af.setCheckInTime(rs.getString("check_in_time"));
			af.setCheckOutTime(rs.getString("check_out_time"));
			af.setAttendanceStatus(rs.getString("attendance_status"));
			af.setComments(rs.getString("comments"));
			af.setBreaks(rs.getString("breaks"));
			af.setBreakStartTime(rs.getString("break_start_time"));
			af.setBreakEndTime(rs.getString("break_end_time"));
			af.setTotalBreakTime(rs.getString("total_break_time"));
			af.setTotalWorkingTime(rs.getString("total_working_time"));
			return af;
		}	
	}
	
	
	public Attendance getAttendanceById(Long id) {
	    String sql = "SELECT * FROM attendance WHERE id = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Attendance.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	
	public int updateAttendance(Attendance af) {
	    String sql = "UPDATE ATTENDANCE SET " +
	                 "attendance_status=?, break_end_time=?, break_start_time=?, check_in_time=?, check_out_time=?, " +
	                 "comments=?, attendance_date=?, total_break_time=?, total_working_time=?, breaks=?, updatedate = ? " + // Fixed syntax
	                 "WHERE id = ?";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
	    return jdbcTemplate.update(sql,
	        af.getAttendanceStatus(),
	        af.getBreakEndTime(),
	        af.getBreakStartTime(),
	        af.getCheckInTime(),
	        af.getCheckOutTime(),
	        af.getComments(),
	        af.getAttendanceDate(),
	        af.getTotalBreakTime(),
	        af.getTotalWorkingTime(),
	        af.getBreaks(),
	        formattedTimestamp,
	        af.getId() // Ensure id is passed as the last parameter
	    );
	}

	
	public int deleteAttendance(Long id) {
			String sql=" DELETE FROM attendance WHERE id = ?";
			return jdbcTemplate.update(sql,id);
	}
}

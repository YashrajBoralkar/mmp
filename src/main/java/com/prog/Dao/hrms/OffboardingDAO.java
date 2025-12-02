package com.prog.Dao.hrms;
import java.sql.ResultSet
;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.core.RowMapper;
	import org.springframework.stereotype.Repository;

import com.prog.model.erp.Offboarding;

	@Repository
	public class OffboardingDAO {

		
		@Autowired
		private  JdbcTemplate jdbcTemplate;

		public  final String sql="insert into offboarding (offboardinguid,employeeuid,clearance_details, company_asset_returned, end_date, experience_letter_issued, feedback_on_exit, final_settlement_status, last_working_day, noc_issued, notice_period_details, reason_for_exit, served_notice_period, start_date,insertdate, updatedate) values(?, ?,  ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		public int saveOffboarding (Offboarding offboarding) {
			 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	         String formattedTimestamp = LocalDateTime.now().format(formatter);
	           
			 return jdbcTemplate.update(sql, 
					 
					 offboarding.getOffboardinguid(),
					 offboarding.getEmployeeuid(),
					 offboarding.getClearanceDetails(),
					 offboarding.getCompanyAssetReturned(),
					 offboarding.getEndDate(),
					 offboarding.getExperienceLetterIssued(),
					 offboarding.getFeedbackOnExit(),
					 offboarding.getFinalSettlementStatus(),
					 offboarding.getLastWorkingDay(),
					 offboarding.getNocIssued(),
					 offboarding.getNoticePeriodDetails(),
					 offboarding.getReasonForExit(),
					 offboarding.getServedNoticePeriod(),
					 offboarding.getStartDate(),
					 formattedTimestamp,
		             formattedTimestamp
					 );
		}

		
		private class OffboardingRowmapper implements RowMapper<Offboarding>
		{
			@Override
			public Offboarding mapRow(ResultSet rs ,int rowNum) throws SQLException {
				Offboarding off=new Offboarding();
				
				off.setId(rs.getLong("id"));
				off.setOffboardinguid(rs.getString("offboardinguid"));
				off.setEmployeeuid(rs.getString("employeeuid"));
				off.setClearanceDetails(rs.getString("clearance_details"));
				off.setCompanyAssetReturned(rs.getString("company_asset_returned"));
				off.setEndDate(rs.getString("end_date"));
				off.setExperienceLetterIssued(rs.getString("experience_letter_issued"));
				off.setFeedbackOnExit(rs.getString("feedback_on_exit"));
				off.setFinalSettlementStatus(rs.getString("final_settlement_status"));
				off.setLastWorkingDay(rs.getString("last_working_day"));    
				off.setNocIssued(rs.getString("noc_issued"));
				off.setNoticePeriodDetails(rs.getString("notice_period_details"));
				off.setReasonForExit(rs.getString("reason_for_exit"));			
				off.setServedNoticePeriod(rs.getString("served_notice_period"));
				off.setStartDate(rs.getString("start_date"));
				
				
				return off;

			}
		}
		public List<Map<String, Object>>getAlloffBoarding() {
				String sql="select offbod.id,offbod.last_working_day,offbod.notice_period_details,offbod.start_date,\r\n"
						+ "offbod.end_date,offbod.served_notice_period,offbod.final_settlement_status,\r\n"
						+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from offboarding offbod\r\n"
						+ "join employee emp on offbod.employeeuid = emp.employeeuid;\r\n"
						+ "";
				return jdbcTemplate.queryForList(sql);
					};


		public void deleteoffboardingbyId(Long id) 
		{
			String sql = "DELETE FROM offboarding WHERE id = ?";
	        int rowsAffected = jdbcTemplate.update(sql, id);
	        if (rowsAffected == 0) {
	            throw new RuntimeException("No Offboarding found with id "+id);
	        }
			
		}
		

		public Offboarding getoffboardingbyid(Long id) {
			// TODO Auto-generated method stub
			String sql="select * from offboarding where id=?";
			return jdbcTemplate.queryForObject(sql, new OffboardingRowmapper(),id);
			
		}
		
		
		public void updateoffboarding(Offboarding offboarding) {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            String formattedTimestamp = LocalDateTime.now().format(formatter);
	           
	            
			String sql = "UPDATE offboarding  SET  clearance_details=?, company_asset_returned=?, end_date=?, experience_letter_issued=?, feedback_on_exit=?, final_settlement_status=?, last_working_day=?, noc_issued=?, notice_period_details=?, reason_for_exit=?, served_notice_period=?, start_date=?, updatedate=? WHERE id =?";
	        jdbcTemplate.update(sql,
	        		offboarding.getClearanceDetails(),
	        		offboarding.getCompanyAssetReturned(),
	        		offboarding.getEndDate(),
	        		offboarding.getExperienceLetterIssued(),
	        		offboarding.getFeedbackOnExit(),
	        		offboarding.getFinalSettlementStatus(),
	        		offboarding.getLastWorkingDay(),
	        		offboarding.getNocIssued(),
	        		offboarding.getNoticePeriodDetails(),
	        		offboarding.getReasonForExit(),
	        		offboarding.getServedNoticePeriod(),
	        		offboarding.getStartDate(),
	        		formattedTimestamp,
	        		offboarding.getId()
	        		);
	    }
	}



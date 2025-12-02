package com.prog.Dao.hrms;


import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Appraisal;

@Repository
public class AppraisalDAO {
    
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 private static class AppraisalRowMapper implements RowMapper<Appraisal> {
	        @Override
	        public Appraisal mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Appraisal appraisal = new Appraisal();
	            
	            appraisal.setId(rs.getLong("id"));
	            appraisal.setAppraisaluid(rs.getString("appraisaluid"));
	            appraisal.setEmployeeUID(rs.getString("employeeuid"));
	            appraisal.setAppraisalPeriod(rs.getString("appraisal_period"));
	            appraisal.setStartDate(rs.getDate("start_date").toLocalDate());
	            appraisal.setEndDate(rs.getDate("end_date").toLocalDate());
	            appraisal.setKeyAchievements(rs.getString("key_achievements"));
	            appraisal.setAreasForImprovement(rs.getString("areas_for_improvement"));
	            appraisal.setGoalAchieved(rs.getString("goal_achieved"));
	            appraisal.setRating(rs.getString("rating"));
	            appraisal.setReviewerComments(rs.getString("reviewer_comments"));
	            appraisal.setManagerComments(rs.getString("manager_comments"));
	            return appraisal;
	        }
	    }

	 
	 //Method to fetch the data from database
			public List<Map<String, Object>> getAllAppraisals(){
				String sql="select ap.id,ap.appraisal_period, ap.start_date, ap.end_date,\r\n"
						+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from appraisal ap\r\n"
						+ "join employee emp on ap.employeeuid = emp.employeeuid;\r\n"
						+ "";
				return jdbcTemplate.queryForList(sql);
					};
    
	public int addAppraisal(Appraisal appraisal) {
  	  String sql = "INSERT INTO Appraisal(employeeuid,appraisaluid,appraisal_period,areas_for_improvement,end_date,goal_achieved,key_achievements,manager_comments,rating,reviewer_comments,start_date)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
  	  return jdbcTemplate.update(sql,
  			  appraisal.getEmployeeUID(),
  			  appraisal.getAppraisaluid(),
  			  appraisal.getAppraisalPeriod(),
  			  appraisal.getAreasForImprovement(),
  			  appraisal.getEndDate(),
  			  appraisal.getGoalAchieved(),
  			  appraisal.getKeyAchievements(),
  			  appraisal.getManagerComments(),
  			  appraisal.getRating(),
  			  appraisal.getReviewerComments(),
  			  appraisal.getStartDate());
  			  
    }
	
	
	public Appraisal getAppraisalById(Long id) {
        String sql = "SELECT * FROM appraisal WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AppraisalRowMapper(), id);
    
	}	
	
	public void updateAppraisal(Appraisal appraisal) {
        String sql = "UPDATE appraisal SET appraisal_period =?, areas_for_improvement =?, start_date =?, end_date =?, goal_achieved=?, key_achievements=?, manager_comments=?, rating=?,reviewer_comments=? WHERE id = ?";
        jdbcTemplate.update(sql,
                appraisal.getAppraisalPeriod(),
                appraisal.getAreasForImprovement(),
                appraisal.getStartDate(),
                appraisal.getEndDate(),
                appraisal.getGoalAchieved(),
                appraisal.getKeyAchievements(),
                appraisal.getManagerComments(),
                appraisal.getRating(),
                appraisal.getReviewerComments(),
                appraisal.getId());
        	
    }
    
	
	
	//Delete an employee
	 public int deleteAppraisal(Long id) {
		    String sql = "DELETE FROM appraisal  WHERE id = ?";
		    return jdbcTemplate.update(sql, id);
		}

	
}

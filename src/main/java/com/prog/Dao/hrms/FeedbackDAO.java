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

import com.prog.model.erp.Feedback;

@Repository
public class FeedbackDAO {
   
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private static class FeedbackRowMapper implements RowMapper<Feedback> {
		@Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feedback feedback = new Feedback();
            feedback.setId(rs.getLong("id"));
            feedback.setEmployeeUID(rs.getString("employeeuid"));
            feedback.setFeedbackuid(rs.getString("feedbackuid"));
            feedback.setFeedbackType(rs.getString("feedback_type"));
            feedback.setFeedbackDescription(rs.getString("feedback_description"));
            feedback.setFeedbackDate(rs.getDate("feedback_date").toLocalDate());
            feedback.setActionableSuggestion(rs.getString("actionable_suggestion"));
            feedback.setAttachment(rs.getBytes("attachment"));
            feedback.setAttachmentName(rs.getString("attachment_name"));
            
            return feedback;
        }
    }

	 //Method to fetch the data from database
		public List<Map<String, Object>> getAllFeedbacks(){
			String sql="select fb.id,fb.feedback_type, fb.feedback_date,\r\n"
					+ "emp.employeeuid, emp.first_name, emp.last_name, emp.departmentname from feedback fb\r\n"
					+ "join employee emp on fb.employeeuid = emp.employeeuid;\r\n"
					+ "";
			return jdbcTemplate.queryForList(sql);
				};

    
	public int addFeedback(Feedback feedback) {
  	  String sql = "INSERT INTO Feedback(employeeuid,feedbackuid,feedback_type,actionable_suggestion,attachment,feedback_date,feedback_description)VALUES(?,?,?,?,?,?,?)";
  	 return jdbcTemplate.update(sql,
  			feedback.getEmployeeUID(),
  			 feedback.getFeedbackuid(),
  			 feedback.getFeedbackType(),
  		     feedback.getActionableSuggestion(),
  		     feedback.getAttachment(),
  		     feedback.getFeedbackDate(),
  		     feedback.getFeedbackDescription());
}
	
	
	public Feedback getFeedbackById(Long id) {
        String sql = "SELECT * FROM feedback WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new FeedbackRowMapper(), id);
    
	}	
	
    public void updateFeedback(Feedback feedback) {
        String sql = "UPDATE feedback SET feedback_type = ?, feedback_description = ?, feedback_date = ?, actionable_suggestion = ?, attachment = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                feedback.getFeedbackType(),
                feedback.getFeedbackDescription(),
                feedback.getFeedbackDate(),
                feedback.getActionableSuggestion(),
                feedback.getAttachment(),
                feedback.getId());
    }
    
  //Delete an employee
  	 public int deleteFeedback(Long id) {
  		    String sql = "DELETE FROM Feedback WHERE id = ?";
  		    return jdbcTemplate.update(sql, id);
  		}

	
	}

	


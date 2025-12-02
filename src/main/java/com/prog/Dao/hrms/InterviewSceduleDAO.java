package com.prog.Dao.hrms;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.InterviewSchedule;

@Repository
public class InterviewSceduleDAO {

	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    // Insert an employee
    public int addInterview(InterviewSchedule interview) {
        String sql = "INSERT INTO interview_schedule (interviewschuid, candidateuid,feedback_summary,final_decision, interview_date, interview_time,jobuid, meeting_link, mode_of_interview,next_interview_date, next_interview_scheduled,next_interview_time, next_panel_details, outcome, overall_rating, panel_members, rescheduled_details, roles, venue_address ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,interview.getInterviewschuid(), interview.getCandidateuid(),
        		interview.getFeedbackSummary(),interview.getFinalDecision(),interview.getInterviewDate(),
        		interview.getInterviewTime(),interview.getJobuid(),interview.getMeetingLink(),
        		interview.getModeOfInterview(),interview.getNextInterviewDate(),interview.isNextInterviewScheduled(),interview.getNextInterviewTime(),
        		interview.getNextPanelDetails(),interview.getOutcome(),
        		interview.getOverallRating(),interview.getPanelMembers(),interview.getRescheduledDetails(),
        		interview.getRoles(),interview.getVenueAddress());
    }

    private  class InterviewScheduleRowMapper implements RowMapper<InterviewSchedule> {
        @Override
        public InterviewSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        	InterviewSchedule interviewSchedule = new InterviewSchedule();
        	interviewSchedule.setId(rs.getLong("id"));
        	interviewSchedule.setInterviewschuid(rs.getString("interviewschuid"));
        	interviewSchedule.setCandidateuid(rs.getString("candidateuid"));
        	interviewSchedule.setJobuid(rs.getString("jobuid"));
        	interviewSchedule.setFeedbackSummary(rs.getString("feedback_summary"));
        	interviewSchedule.setFinalDecision(rs.getString("final_decision"));
        	interviewSchedule.setInterviewDate(rs.getString("interview_date"));
        	interviewSchedule.setInterviewTime(rs.getString("interview_time"));
            interviewSchedule.setMeetingLink(rs.getString("meeting_link"));
        	interviewSchedule.setModeOfInterview(rs.getString("mode_of_interview"));
            interviewSchedule.setNextInterviewScheduled(rs.getBoolean("next_interview_scheduled"));
        	interviewSchedule.setNextPanelDetails(rs.getString("next_panel_details"));
        	interviewSchedule.setOutcome(rs.getString("outcome"));
        	interviewSchedule.setOverallRating(rs.getString("overall_rating"));
        	interviewSchedule.setPanelMembers(rs.getString("panel_members"));
        	interviewSchedule.setRescheduledDetails(rs.getString("rescheduled_details"));
        	interviewSchedule.setRoles(rs.getString("roles"));
        	interviewSchedule.setVenueAddress(rs.getString("venue_address"));

        	
        	
        	java.sql.Date nextInterviewDateSql = rs.getDate("next_interview_date"); // Get the SQL date
        	if (nextInterviewDateSql != null) {
        	    interviewSchedule.setNextInterviewDate(nextInterviewDateSql.toLocalDate()); // Convert to LocalDate
        	}

        	java.sql.Time nextInterviewTimeSql = rs.getTime("next_interview_time"); // Get the SQL time
        	if (nextInterviewTimeSql != null) {
        	    interviewSchedule.setNextInterviewTime(nextInterviewTimeSql.toLocalTime()); // Convert to LocalTime
        	}

        	return interviewSchedule;
        }
       
    }
    // Method to fetch all goals from the database
   
    public List<Map<String, Object>> getAllInterviewSchedule() {
        String sql = "SELECT ins.id, \r\n"
        		+ "       cd.name, \r\n"
        		+ "       jb.title, \r\n"
        		+ "       ins.interview_date, \r\n"
        		+ "       ins.interview_time, \r\n"
        		+ "       ins.mode_of_interview, \r\n"
        		+ "       ins.final_decision\r\n"
        		+ "FROM interview_schedule ins\r\n"
        		+ "JOIN job jb ON ins.jobuid = jb.jobuid\r\n"
        		+ " JOIN candidate cd ON ins.candidateuid = cd.candidateuid\r\n"
        		+ "WHERE ins.interviewschuid IS NOT NULL;\r\n"
        		+ "";
        return jdbcTemplate.queryForList(sql);
    }
	
	public InterviewSchedule getInterviewById(Long id) {
		String sql = "SELECT * FROM interview_schedule WHERE id = ?";
		 try {
		        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(InterviewSchedule.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
	}
	public int update(InterviewSchedule interviewSchedule) {
		String sql="UPDATE interview_schedule SET feedback_summary = ?, final_decision = ?, interview_date = ?,interview_time = ?, meeting_link = ?, mode_of_interview = ?,  next_interview_date = ?, next_interview_scheduled = ?, next_interview_time = ?, next_panel_details = ?, outcome = ?, overall_rating = ?, panel_members = ?, rescheduled_details = ?, roles = ?, venue_address = ? WHERE id = ?";
	return jdbcTemplate.update(
			    sql,
			    interviewSchedule.getFeedbackSummary(),
			    interviewSchedule.getFinalDecision(),
			    interviewSchedule.getInterviewDate(),
			    interviewSchedule.getInterviewTime(),
			    interviewSchedule.getMeetingLink(),
			    interviewSchedule.getModeOfInterview(),
			    interviewSchedule.getNextInterviewDate(),
			    interviewSchedule.isNextInterviewScheduled(),
			    interviewSchedule.getNextInterviewTime(),
			    interviewSchedule.getNextPanelDetails(),
			    interviewSchedule.getOutcome(),
			    interviewSchedule.getOverallRating(),
			    interviewSchedule.getPanelMembers(),
			    interviewSchedule.getRescheduledDetails(),
			    interviewSchedule.getRoles(),
			    interviewSchedule.getVenueAddress(),
			    interviewSchedule.getId() // Ensure `id` is the last parameter
			); }
	public int deleteCandidateById(Long id) {
		 String sql = "DELETE FROM interview_schedule WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	
	}
	}

	

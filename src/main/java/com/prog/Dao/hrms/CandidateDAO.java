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

import com.prog.model.erp.Candidate;
import com.prog.model.erp.Job;




@Repository
public class CandidateDAO {

	  @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    
	    // Insert an employee
	    public int addCandidate(Candidate candidate) {
	        String sql = "INSERT INTO candidate ( candidateuid,jobuid,contact, name, resume, status, cover_letter, current_address, duration_of_employment, email, highest_qualification, institution, previous_employer, role, total_experience, year_of_graduation, referencedetails, resume_name ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?)";
	        return jdbcTemplate.update(sql, candidate.getCandidateuid(),candidate.getJobuid(),candidate.getContact(), candidate.getName(),candidate.getResume(),candidate.getStatus(),candidate.getCoverLetter(),candidate.getCurrentAddress(),candidate.getDurationOfEmployment(),candidate.getEmail(),candidate.getHighestQualification(),candidate.getInstitution(),candidate.getPreviousEmployer(),candidate.getRole(),candidate.getTotalExperience(),candidate.getYearOfGraduation(),candidate.getReferencedetails(),candidate.getResumeName());
	    }

	
	    public static class CandidateRowMapper implements RowMapper<Candidate> {
	        @Override
	        public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Candidate candidate = new Candidate();
	        	candidate.setId(rs.getLong("id"));
	        	candidate.setCandidateuid(rs.getString("candidateuid"));
	        	candidate.setJobuid(rs.getString("jobuid"));
	        	candidate.setContact(rs.getString("contact"));
	        	candidate.setName(rs.getString("name"));
	        	candidate.setResume(rs.getBytes("resume"));
	        	candidate.setStatus(rs.getString("status"));
	        	candidate.setCoverLetter(rs.getString("cover_letter"));
	        	candidate.setCurrentAddress(rs.getString("current_address"));
	        	candidate.setDurationOfEmployment(rs.getString("duration_of_employment"));
	        	candidate.setEmail(rs.getString("email"));
	        	candidate.setHighestQualification(rs.getString("highest_qualification"));
	        	candidate.setInstitution(rs.getString("institution"));
	        	candidate.setPreviousEmployer(rs.getString("previous_employer"));
	        	candidate.setRole(rs.getString("role"));
	        	candidate.setTotalExperience(rs.getString("total_experience"));
	        	candidate.setYearOfGraduation(rs.getString("year_of_graduation"));
	        	candidate.setReferencedetails(rs.getString("referencedetails"));
	        	candidate.setResumeName(rs.getString("resume_name"));

	            return candidate;
	        }
	       
	    }
	    // Method to fetch all goals from the database
	   
	    public List<Map<String, Object>> getAllCandidate() {
	        String sql = "SELECT cd.id,\r\n"
	        		+ "	   jb.jobuid, \r\n"
	        		+ "       cd.name, \r\n"
	        		+ "      cd.email, \r\n"
	        		+ "       cd.contact, \r\n"
	        		+ "       cd.highest_qualification, \r\n"
	        		+ "       cd.total_experience,\r\n"
	        		+ "       cd.status\r\n"
	        		+ "FROM candidate cd\r\n"
	        		+ "JOIN job jb ON cd.jobuid = jb.jobuid\r\n"
	        		+ "";
	        return jdbcTemplate.queryForList(sql);
	    }
	    
	    public List<String> getallcandidateuids(){
			 String sql = "SELECT candidateuid FROM candidate";
			 return jdbcTemplate.queryForList(sql, String.class);
		 }
		 
		 public Candidate getCandidatedetailsByuid(String candidateuid) {
			 String sql = "SELECT name, resume FROM candidate WHERE candidateuid = ? ";
			 return jdbcTemplate.queryForObject(sql, new Object[] {candidateuid},(rs,rowNum ) -> {
				 Candidate candidate = new Candidate();
				 candidate.setName(rs.getString("name"));
				 candidate.setResume(rs.getBytes("resume"));

				 return candidate;
				 

			 });
		 }
	    
	    public  Candidate getCandidateById(Long id) {
	    String sql = "SELECT * FROM candidate WHERE id = ?";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Candidate.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	    }
	    
	    public int deleteCandidate(Long id) {
	        String sql = "DELETE FROM candidate WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
		public int updateCandidate(Candidate candidate) {
			String sql = "UPDATE candidate SET  contact=?, cover_letter=?, current_address=?, duration_of_employment=?, email=?, highest_qualification=?, institution=?, name=?, previous_employer=?, resume=?, role=?, status=?, total_experience=?, year_of_graduation=?, referencedetails=?, resume_name=?  WHERE id = ?";
		    return jdbcTemplate.update(
		        sql,
		        
		        candidate.getContact(),
		        candidate.getCoverLetter(),
		        candidate.getCurrentAddress(),
		        candidate.getDurationOfEmployment(),
		        candidate.getEmail(),
		        candidate.getHighestQualification(),
		        candidate.getInstitution(),
		        candidate.getName(),
		        candidate.getPreviousEmployer(),
		        candidate.getResume(),
		        candidate.getRole(),
		        candidate.getStatus(),
		        candidate.getTotalExperience(),
		        candidate.getYearOfGraduation(),
		        candidate.getReferencedetails(),
		        candidate.getResumeName(),		      
		        candidate.getId()
		    );
		}
}

		


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

import com.prog.model.erp.Job;

@Repository
public class JobDAO {
	  @Autowired
	    private JdbcTemplate jdbcTemplate;
	  
	  // Insert an employee
	    public int addJob(Job job) {
	        String sql = "INSERT INTO job (jobuid, department,description,posted_date,requirement, title, address,application_deadline, employment_type, posted_by, work_location) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        return jdbcTemplate.update(sql,
	        		job.getJobuid() ,
	        		job.getDepartment(), 
	        		job.getDescription(),
	        		job.getPostedDate(),
	        		job.getRequirement(),
	        		job.getTitle(),
	        		job.getAddress(),
	        		job.getApplicationDeadline(),
	        		job.getEmploymentType(),
	        		job.getPostedBy(),
	        		job.getWorkLocation());
	    }
	    
	    private static class JobRowMapper implements RowMapper<Job> {
	        @Override
	        public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Job job = new Job();
	            job.setId(rs.getLong("id"));
	            job.setJobuid(rs.getString("jobuid"));
	            job.setTitle(rs.getString("title"));
	            job.setDescription(rs.getString("description"));
	            job.setRequirement(rs.getString("requirement"));
	            job.setDepartment(rs.getString("department"));
	            job.setPostedBy(rs.getString("posted_by"));
	            job.setPostedDate(rs.getString("posted_Date"));
	            job.setApplicationDeadline(rs.getString("Application_Deadline"));
	            job.setEmploymentType(rs.getString("Employment_Type"));
	            job.setWorkLocation(rs.getString("Work_location"));
	            job.setAddress(rs.getString("Address"));	            
	            return job;
	        }
	       
	    }
	    // Method to fetch all goals from the database
	    public List<Map<String, Object>> getAllJob() {
	        String sql = "SELECT * FROM job";  // Adjust the table name and columns if needed
	        return jdbcTemplate.queryForList(sql);
	    }
	    
	    public List<String> getalljobuids(){
			 String sql = "SELECT jobuid FROM job";
			 return jdbcTemplate.queryForList(sql, String.class);
		 }
		 
		 public Job getJobdetailsByuid(String jobuid) {
			 String sql = "SELECT description, title, department FROM job WHERE jobuid = ? ";
			 return jdbcTemplate.queryForObject(sql, new Object[] {jobuid},(rs,rowNum ) -> {
				 Job job = new Job();
				 job.setTitle(rs.getString("title"));
				 job.setDescription(rs.getString("description"));
				 job.setDepartment(rs.getString("department"));

				 return job;
				 

			 });
		 }
	    

	    // Delete a job by its ID
	    public int deleteJobById(Long id) {
	        String sql = "DELETE FROM Job WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	    public Job getJobById(Long id) {
		    String sql = "SELECT * FROM Job WHERE id = ?";
		    try {
		        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Job.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
		}
	    
	     public int updateJob(Job job) {
	    	 String sql = "UPDATE Job SET department=?,description=?,posted_date=?,requirement=?, title=?, address=?,application_deadline=?, employment_type=?, posted_by=?, work_location=? WHERE id=?";
	    	 		 return jdbcTemplate.update(
	    	 				  sql,
	    	 				 
	    	 				  job.getDepartment(),
	    	 				  job.getDescription(),
	    	 				  job.getPostedDate(),
	    	 				  job.getRequirement(),
	    	 				  job.getTitle(),
	    	 				  job.getAddress(),
	    	 				  job.getApplicationDeadline(),
	    	 				  job.getEmploymentType(),
	    	 				  job.getPostedBy(),
	    	 				  job.getWorkLocation(),
	    	 		 		  job.getId());
}
}

package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.JobDAO;
import com.prog.model.erp.Employee;
import com.prog.model.erp.Job;


@Service
public class JobService {

    @Autowired
    private JobDAO jobDao;

    // Save a job to the database
    public int saveJob(Job job) {
    	String jobuid=generateJobuId();
    	job.setJobuid(jobuid);
		return jobDao.addJob(job); // Save job to database
    }

    // Retrieve all jobs
    public List<Map<String, Object>> getAllJob() {
        return jobDao.getAllJob();
    }

    // Delete a job by its ID
    public int deleteJob(Long id) {
         return jobDao.deleteJobById(id); // Use JobDAO method to delete
            
    }

	public Job getJobById(Long id) {
		return jobDao.getJobById(id);
	}

	 public int updateJob(Job job) {
	       return jobDao.updateJob(job); // Call the DAO update method
	    }
	 
	 private String generateJobuId() {
		    int length = 4;  // Length of the atduId (for example 8 characters)
		    String characters = "1234567890";
		    Random random = new Random();
		    StringBuilder jobuid = new StringBuilder(length);

		    // Generate random characters for the PuId
		    for (int i = 0; i < length; i++) {
		    	jobuid.append(characters.charAt(random.nextInt(characters.length())));
		    }

		     return "JB" + jobuid.toString();
		}
	 
	 public List<String> getAlljobDetails(){
			return jobDao.getalljobuids();
		}
		
		public Job getjobdetailsByuid(String jobuid) {
			return jobDao.getJobdetailsByuid(jobuid);
			
		}

	
}
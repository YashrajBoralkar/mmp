package com.prog.service.hrms;

import com.prog.Dao.hrms.EnrollmentDao;
import com.prog.model.erp.Employee;
import com.prog.model.erp.Enrollment;
import com.prog.model.erp.Onboarding;
import com.prog.model.erp.Training;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentDao dao;
    
    

    public int saveEnrollment(Enrollment enrollment) {
    	String enUID=generateEnrollmentUID();
    	enrollment.setEnrollmentuid(enUID);
		return dao.addEnrollment(enrollment) ;// Save the enrollment to the database
        
    }
    
    public List<Map<String, Object >>getAllEnrollment()
    {
		return dao.getAllEnrollment();
    	
    }
    public Enrollment getEnrollmentById(Long id) {
        return dao.getEnrollmentById(id);  // Fetch the enrollment by ID from DAO
    }

	/*
	 * public void updateEnrollment(Long id, Enrollment updatedProfile) {
	 * dao.updateEnrollment(id, updatedProfile); }
	 */
    public void deleteEnrollment(Long id) {
        dao.deleteById(id);
    }
 // Update an existing enrollment
    public void updateEnrollment(Enrollment enrollment) {
    	dao.updateEnrollment(enrollment);
    }
    
    public Onboarding getDesignationdetailsByuid(String employeeuid) {
		return dao.getDesignationdetailsByuid(employeeuid);
		
	}
    private String generateEnrollmentUID() {
        int length = 4;  // Length of the UID (for example 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder enId = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
        	enId.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "EN" + enId.toString();
    }


    

}

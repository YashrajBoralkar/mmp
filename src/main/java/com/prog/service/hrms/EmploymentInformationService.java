package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.EmploymentInformationDAO;
import com.prog.model.erp.EmploymentInformation;

@Service
public class EmploymentInformationService {

    @Autowired
    private EmploymentInformationDAO employmentInformationDAO;

   public void saveEmploymentInfo(EmploymentInformation employmentInformation) {
	   String empinfouid=generateEmploymentInfouId();
	   employmentInformation.setEmpinfouid(empinfouid);
		
        employmentInformationDAO.save(employmentInformation);
    } 
   

    public List<Map<String, Object>> getAllEmploymentInfo() {
        return employmentInformationDAO.findAll();
    }
    public void deleteEmploymentInfo(Long id) {
        employmentInformationDAO.delete(id);
    }
    
    public EmploymentInformation getEmploymentInfoById(Long id) {
        return employmentInformationDAO.findById(id);
    }
    
    public void updateEmploymentInfo(EmploymentInformation employmentInformation) {
        employmentInformationDAO.update(employmentInformation);
    }
    private String generateEmploymentInfouId() {
	    int length = 4;  // Length of the atduId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder empinfouid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	empinfouid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "EI" + empinfouid.toString();
	}
}
 
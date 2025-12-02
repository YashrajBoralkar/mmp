package com.prog.service.hrms;
 
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.AppraisalDAO;
import com.prog.model.erp.Appraisal;

@Service
public class AppraisalService {

   @Autowired
    private  AppraisalDAO appraisaldao;

    public int  saveAppraisal(Appraisal appraisal) {
    	String aprUID=generateAppraisaluId();
    	appraisal.setAppraisaluid(aprUID);
		return appraisaldao.addAppraisal(appraisal);
    }
    public List<Map<String, Object>> getAllAppraisal() {
        return appraisaldao.getAllAppraisals();
    }
 // Method to delete a Appraisal by ID
    public void deleteAppraisalById(Long id) {
        appraisaldao.deleteAppraisal(id);  

	}
    
    public void updateAppraisal(Appraisal appraisal) {
        appraisaldao.updateAppraisal(appraisal);
	}
	public Appraisal getAppraisalById(Long id) {
		return appraisaldao.getAppraisalById(id);
	}
	
	private String generateAppraisaluId() {
	    int length = 4;  // Length of the atduId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder apruid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	apruid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "APR" + apruid.toString();
	}
	
	

}

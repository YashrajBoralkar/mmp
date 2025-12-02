package com.prog.service.hrms;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.PayrollDetailsDao;
import com.prog.model.erp.PayrollDetails;

@Service
public class PayrollDetailsService {

    @Autowired
    private PayrollDetailsDao payrollDetailsDao;

    public void savePayrollDetails(PayrollDetails payrollDetails) {
    	String pruid=generatePayrolluId();
    	payrollDetails.setPayrolluid(pruid);
		
        payrollDetailsDao.save(payrollDetails);
    }

    public PayrollDetails getPayrollDetailsById(Long id) {
        return payrollDetailsDao.findById(id);
    }

    public List<Map<String, Object>> getAllPayrollDetails() {
        return payrollDetailsDao.findAll();
    }

    public void deletePayrollDetails(Long id) {
        payrollDetailsDao.delete(id);
    }
    private String generatePayrolluId() {
	    int length = 4;  // Length of the atduId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder pruid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	pruid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "PR" + pruid.toString();
	}

}


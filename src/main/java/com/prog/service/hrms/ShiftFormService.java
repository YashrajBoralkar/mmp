package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.ShiftDAO;
import com.prog.model.erp.Shiftinfo;



@Service
public class ShiftFormService {
	 @Autowired
	    private ShiftDAO shiftDao;

	
	    
	// Save a new goal
	    public int saveShift(Shiftinfo shiftForm) {
	    	String stUID=generateShiftuId();
	    	shiftForm.setShiftinfouid(stUID);
	    	return shiftDao.addShift(shiftForm);
	    }

	    public List<Map<String,Object>> getAllShift() {
	        return shiftDao.getAllShift();
	    }
	    

	    public void updateShiftById(Shiftinfo shiftForm) {
			 shiftDao.updateShift(shiftForm);
		}

		public Shiftinfo getShiftById(Long id) {
			return shiftDao.getShiftById(id);
		}
		
		public int deleteById(long id) {
			return shiftDao.deleteShift(id);
		}
		
		private String generateShiftuId() {
		    int length = 4;  // Length of the atduId (for example 8 characters)
		    String characters = "1234567890";
		    Random random = new Random();
		    StringBuilder stuid = new StringBuilder(length);

		    // Generate random characters for the PuId
		    for (int i = 0; i < length; i++) {
		    	stuid.append(characters.charAt(random.nextInt(characters.length())));
		    }

		     return "SHT" + stuid.toString();
		}
}

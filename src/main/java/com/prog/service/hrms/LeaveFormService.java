package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.LeaveDAO;
import com.prog.model.erp.Leaveinfo;

@Service
public class LeaveFormService {
		 @Autowired
		    private LeaveDAO leaveDao;

		// Save a new goal
		    public int saveLeave(Leaveinfo leaveForm) {
		    	String levUID=generateLeaveuid();
		    	leaveForm.setLeaveuid(levUID);
		        return leaveDao.addLeave(leaveForm);
		    }

		    public List<Map<String, Object>> getAllLeave() {
		        return leaveDao.getAllLeave();
		        		}
		    public void updateLeaveById(Leaveinfo leaveForm) {
				 leaveDao.updateLeave(leaveForm);
			}

		    
//		    public int updateLeave(LeaveForm leaveForm) {
//				return leaveDao.updateLeave(leaveForm);	
//			}
		    
		    private String generateLeaveuid() {
			    int length = 4;  // Length of the PuId (for example 8 characters)
			    String characters = "1234567890";
			    Random random = new Random();
			    StringBuilder levuid = new StringBuilder(length);

			    // Generate random characters for the PuId
			    for (int i = 0; i < length; i++) {
			        levuid.append(characters.charAt(random.nextInt(characters.length())));
			    }

			     return "LEV" + levuid.toString();
			}
		    

			public Leaveinfo getLeaveById(Long id) {
				return leaveDao.getLeaveById(id);
			}
			
			public int deleteById(long id) {
				return leaveDao.deleteLeave(id);
			}
}

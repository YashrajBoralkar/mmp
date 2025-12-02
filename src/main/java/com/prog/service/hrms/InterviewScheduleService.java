package com.prog.service.hrms;



import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.InterviewSceduleDAO;
import com.prog.model.erp.InterviewSchedule;



@Service
public class InterviewScheduleService {
    @Autowired
    private InterviewSceduleDAO interviewsceduledao;



    // Save interview schedule
    public int saveInterviewSchedule(InterviewSchedule interviewschedule) {
    	 String interschuid=generateScheduledinterviewuId();
    	 interviewschedule.setInterviewschuid(interschuid);
  		
        return interviewsceduledao.addInterview(interviewschedule); // Save to the database
    }
    
    public List<Map<String, Object>> getAllInterviewSchedule() {
    	return interviewsceduledao.getAllInterviewSchedule();
    	
    }

	public InterviewSchedule getInterviewById(Long id) {
		// TODO Auto-generated method stub
		return interviewsceduledao.getInterviewById(id);
	}

	public int deleteCandidateById(Long id) {
		// TODO Auto-generated method stub
		return interviewsceduledao.deleteCandidateById(id);
	}
	
	public int update(InterviewSchedule interviewSchedule) {
		return interviewsceduledao.update(interviewSchedule);
		
	}
	private String generateScheduledinterviewuId() {
	    int length = 4;  // Length of the atduId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder interschuid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	interschuid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "IS" + interschuid.toString();
	}

}


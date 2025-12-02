		package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.AttendanceDAO;
import com.prog.model.erp.Attendance;

@Service
public class AttendanceService {
	 @Autowired
	    private AttendanceDAO attendanceDao;

	 	// Save a new data
	    public int saveAttendance(Attendance attendanceForm) {
	    	String atdUID=generateAttendanceuId();
	    	attendanceForm.setAttendanceuid(atdUID);
			
	        return attendanceDao.addAttendace(attendanceForm);
	    }

	    public List<Map<String, Object>> getAllAttendance() {
	        return attendanceDao.getAllAttendance();
	    }
	    
	    public int updateAttendanceById(Attendance af) {
			return attendanceDao.updateAttendance(af);
		}

		public Attendance getAttendanceById(Long id) {
			return attendanceDao.getAttendanceById(id);
		}
		
		public int deleteById(long id) {
			return attendanceDao.deleteAttendance(id);
		}
		
		private String generateAttendanceuId() {
		    int length = 4;  // Length of the atduId (for example 8 characters)
		    String characters = "1234567890";
		    Random random = new Random();
		    StringBuilder atduid = new StringBuilder(length);

		    // Generate random characters for the PuId
		    for (int i = 0; i < length; i++) {
		    	atduid.append(characters.charAt(random.nextInt(characters.length())));
		    }

		     return "ATD" + atduid.toString();
		}
}
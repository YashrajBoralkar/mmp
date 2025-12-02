package com.prog.service.hrms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prog.Dao.hrms.FeedbackDAO;
import com.prog.model.erp.Feedback;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackDAO feedbackdao;

//    private static final long MAX_FILE_SIZE = 10485760; // 10MB in bytes

    public int saveFeedback(Feedback feedback) {
    	String fdbUID=generateFeedbackuId();
    	feedback.setFeedbackuid(fdbUID);
		return feedbackdao.addFeedback(feedback);  // Save Feedback object in the database
    }
    public List<Map<String, Object>> getAllFeedback() {
        return feedbackdao.getAllFeedbacks();
    }
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackdao.getFeedbackById(feedbackId);
    }
    
 // Method to delete a Feedback by ID
    public void deleteFeedbackById(Long id) {
        feedbackdao.deleteFeedback(id);  

    }
    public void updateFeedback(Feedback feedback) {
        feedbackdao.updateFeedback(feedback);
	}
    
    private String generateFeedbackuId() {
	    int length = 4;  // Length of the atduId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder fdbuid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	fdbuid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "FB" + fdbuid.toString();
	}
	
}




//    // Helper method to validate file type
//    private boolean isValidFileType(MultipartFile file) {
//        String contentType = file.getContentType();
//        return contentType.equals("application/pdf") || contentType.startsWith("image/");
//    }
//}

package com.prog.service.hrms;



import com.prog.Dao.hrms.TrainingDao;
import com.prog.model.erp.Training;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

	@Autowired
    private TrainingDao trainingDao;
    
	 public int saveTraining(Training training){
	        String trainingUid = generatetrainingUID();
	        training.setTrainingUid(trainingUid);
	        
	        return trainingDao.saveTraining(training);
	    }

    public List<String> getAllTrainingUids() {
        return trainingDao.getAllTrainingUids();
    }
    public Training getTrainingDetailsByUid(String training_uid) {
        return trainingDao.getTrainingDetailsByUid(training_uid);
    }
    
    public List<Map<String, Object>>getAllTraining(){
		return trainingDao.getAllTraining();
		
	}
    
    // Method to delete a goal by ID
    public void deleteTrainingById(Long id) {
    	trainingDao.deleteTrainingById(id);  

	}
    public void updateTraining(Training training) {
    	trainingDao.updateTraining(training);
		
	}

	public Training getTrainingById(Long id) {
		return trainingDao.gettrainingbyid(id) ;
	}
	
    
    
    private String generatetrainingUID() {
        int length = 4;  // Length of the UID (for example 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder trId = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
            trId.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "TR" + trId.toString();
    }

    
    
}

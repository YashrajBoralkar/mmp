package com.prog.service.hrms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.GoalSettingDAO;
import com.prog.model.erp.Goal;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GoalService {

    @Autowired
    private GoalSettingDAO goalsettingdao;

    // Save a new goal
    public int saveGoal(Goal goal) {
    	String gsUID=generateGoaluId();
    	goal.setGoaluid(gsUID);
		return goalsettingdao.addGoalSetting(goal);
    }

    public List<Map<String, Object>>  getAllGoals() {
        return goalsettingdao.getAllGoals();
    }

    // Method to delete a goal by ID
    public void deleteGoalById(Long id) {
        goalsettingdao.deleteGoal(id);  

	}
    public void updateGoal(Goal goal) {
        goalsettingdao.updateGoal(goal);
		
	}

	public Goal getGoalById(Long id) {
		return goalsettingdao.getGoalById(id) ;
	}
	
	 private String generateGoaluId() {
		    int length = 4;  // Length of the atduId (for example 8 characters)
		    String characters = "1234567890";
		    Random random = new Random();
		    StringBuilder gsuid = new StringBuilder(length);

		    // Generate random characters for the PuId
		    for (int i = 0; i < length; i++) {
		    	gsuid.append(characters.charAt(random.nextInt(characters.length())));
		    }

		     return "GS" + gsuid.toString();
		}

 }
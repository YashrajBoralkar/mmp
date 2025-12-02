package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.OnboardingDAO;
import com.prog.model.erp.Onboarding;


@Service	
public class OnboardingService{
		@Autowired
	private OnboardingDAO onboardingdao;
		
	
	
	public int saveonboarding(Onboarding onboarding) {
		String onbodUID=generateOnboardinguid();
		onboarding.setOnboardinguid(onbodUID);
		return onboardingdao.saveonboarding(onboarding);
	}
	
	public List<Map<String, Object>>getAllonboarding(){
		return onboardingdao.getAllonboarding();
		
	}
	
	public Onboarding getonboardingbyid(Long id) {
		return onboardingdao.getonboardingbyid(id);
	}
	
	public void deleteonboarding(Long id) {
		onboardingdao.deleteonboardingbyId(id);
	}
	
	public void updateonboarding(Onboarding onboarding) 
	{
		onboardingdao.updateonboarding(onboarding);
		
	}
	
	private String generateOnboardinguid() {
	    int length = 4;  // Length of the PuId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder onboduid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	onboduid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "ONBOD" + onboduid.toString();
	}
}







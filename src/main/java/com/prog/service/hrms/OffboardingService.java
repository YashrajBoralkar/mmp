package com.prog.service.hrms;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.OffboardingDAO;
import com.prog.model.erp.Offboarding;

	

	@Service
	public class OffboardingService {
		
		
		@Autowired
		private OffboardingDAO offboardingdao;
			
		
		
		public int saveOffboarding(Offboarding offboarding) {
			String offbodUID=generateOffboardinguid();
			offboarding.setOffboardinguid(offbodUID);
	        return offboardingdao.saveOffboarding(offboarding);
		}

		public List<Map<String, Object>>getAllBoarding(){
			return offboardingdao.getAlloffBoarding();
			
		}
		
		public void deleteonboarding(Long id) 
		{
			offboardingdao.deleteoffboardingbyId(id);
			
		}
		
		public Offboarding getoffboardingbyid(Long id) {
			return offboardingdao.getoffboardingbyid(id);
		}
		
		public void updateoffboarding(Offboarding offboarding) 
		{
			offboardingdao.updateoffboarding(offboarding);
			
		}
		
		 private String generateOffboardinguid() {
			    int length = 4;  // Length of the PuId (for example 8 characters)
			    String characters = "1234567890";
			    Random random = new Random();
			    StringBuilder offboduid = new StringBuilder(length);

			    // Generate random characters for the PuId
			    for (int i = 0; i < length; i++) {
			    	offboduid.append(characters.charAt(random.nextInt(characters.length())));
			    }

			     return "OFBOD" + offboduid.toString();
			}

	

}

package com.prog.service.wholesellerandretailer;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.wholesellerandretailer.RetailerFeedbackOrComplaintDao;
import com.prog.model.erp.retailerfeedbackorcomplaint;


@Service
public class RetailerFeedbackOrComplaintService {
	
	@Autowired
	private RetailerFeedbackOrComplaintDao dao;
	
	public void save(retailerfeedbackorcomplaint retailerfeedback) {
		String feedbackuid = generatefeedbackuid();
		retailerfeedback.setRetailerfeedbackorcomplaintuid(feedbackuid);
		dao.add(retailerfeedback);

	}
	
	
		private String generatefeedbackuid() {
		int length = 4; // Length of the contract ID (example: 8 characters)
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder contractId = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            contractId.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "RFOC" + contractId.toString();
	}
		//list
		public List<Map<String,Object>> getAlldata() {
			 return dao.findAll();
		}

		//delete
		public void deleteById(Long id) {
			dao.deleteById(id);
			
		}
		
		//update
		public retailerfeedbackorcomplaint GetById(Long id) {
			return dao.getById(id);
		}
		//post update
		public void update(retailerfeedbackorcomplaint updaterf) {
			dao.updaterf(updaterf);
		}	
}




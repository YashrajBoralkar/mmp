package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prog.Dao.inventory.BackorderDao;
import com.prog.model.erp.Backorder;

	@Service
	public class BackorderService {

	    @Autowired
	    private  BackorderDao backorderDao;

	    public void saveBackorder(Backorder backorder) {
	    	String uid = genratebackorderuid();
	    	backorder.setBackorderuid(uid);
	        backorderDao.addBackorder(backorder);
	    }
	    
	    public String genratebackorderuid() {
	    	int length = 4; // Length of the PuId (example: 8 characters)
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder backorderuid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	        	backorderuid.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "BO" + backorderuid.toString();
	    	
	    }
              
	   

	    public List<Map<String, Object>> showBackorder() {
	        return backorderDao.showBackorder();
	    }

	    
	 // Delete a backorder by ID
	    public void deleteBackorderById(Long id) {
	        backorderDao.deleteBackorder(id);
	    }


    public Backorder getBackorderById(Long id) {
		return backorderDao.getBackorderById(id);
    	
    }
    
    public int updateBackorder(Backorder backorder) {
        return backorderDao.updateBackorder(backorder);
    }

   
}
    
	
	



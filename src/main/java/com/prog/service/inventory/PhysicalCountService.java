package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.PhysicalCountDAO;
import com.prog.model.erp.physicalcount;



@Service
	public class PhysicalCountService {

	    @Autowired
	    private PhysicalCountDAO physicalCountDAO;

	    // Save a Physical Count to the database
	    public int savePhysicalCount(physicalcount physicalcount) {
	        String physicalCountUid = generatePhysicalCountUid();
	        physicalcount.setPhysicalcountuid(physicalCountUid);
	        return physicalCountDAO.addPhysicalCount(physicalcount); // Call DAO to save physical count
	    }

	    // Retrieve all Physical Counts
	    public List<Map<String, Object>> getAllPhysicalCounts() {
	        return physicalCountDAO.getAllPhysicalCounts(); // Call DAO to fetch all physical counts
	    }

	    // Retrieve a Physical Count by its ID
	    public physicalcount getPhysicalCountById(Long id) {
	        return physicalCountDAO.getPhysicalCountById(id); // Call DAO to fetch physical count by ID
	    }

	    // Update a Physical Count
	    public int updatePhysicalCount(physicalcount physicalcount) {
	        return physicalCountDAO.updatePhysicalCount(physicalcount); // Call DAO to update physical count
	    }

	    // Delete a Physical Count by its ID
	    public int deletePhysicalCount(Long id) {
	        return physicalCountDAO.deletePhysicalCount(id); // Call DAO to delete physical count by ID
	    }

	    // Retrieve product details by product UID
	    public List<Map<String, Object>> getDataByProductUid(String productuid) {
	        return physicalCountDAO.getDataByProductUid(productuid);
	    }

	    // Retrieve all product UIDs
	    public List<String> fetchProductUIds() {
	        return physicalCountDAO.fetchProductUIds();
	    }

	    // Helper method to generate a unique Physical Count UID
	    private String generatePhysicalCountUid() {
	        int length = 4;
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder physicalCountUid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            physicalCountUid.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "PC" + physicalCountUid.toString();
	    }
	

	    
//	   public List<String> fetchStockUIds() {
//	        return physicalCountDAO.fetchStockUIds();
//	    }
//	    
//	    
//	    public List<Map<String, Object>> getDataByStockUid(String stockmanageruid) {
//	        return physicalCountDAO.getDataByStockUid(stockmanageruid);
//	    } 
}

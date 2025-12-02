package com.prog.service.qualitycontrol;



	import java.util.List;

	import java.util.Map;
	import java.util.Random;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.FinishedGoodsQCDAO;
import com.prog.model.erp.finishedgoodsqc;


	@Service
	public class FinishedGoodsQCService {

		  @Autowired
		    private FinishedGoodsQCDAO finishedGoodsQCDAO;
		      
		    
		    public int saveFinishedGoodsQC(finishedgoodsqc finishedgoodsqc) {
		        String finishgoodsqcuid = generatefinishgoodsqcUid();
		        finishedgoodsqc.setFinishgoodsqcuid(finishgoodsqcuid);
		        return finishedGoodsQCDAO.addFinishedGoodsQC(finishedgoodsqc);
		    }
		    
		 // Retrieve all Physical Counts
		    public List<Map<String, Object>> getAllFinishedGoodsQC() {
		        return finishedGoodsQCDAO.getAllFinishedGoodsQC(); // Call DAO to fetch all physical counts
		    }

		    // Retrieve a Physical Count by its ID
		    public List<Map<String, Object>> getFinishedGoodsQCById(Long id) {
		        return finishedGoodsQCDAO.getFinishedGoodsQCById(id); // Call DAO to fetch physical count by ID
		    }
		    
		    
		    // Update a Physical Count
		    public int updateFinishedGoodsQC(finishedgoodsqc finishedgoodsqc) {
		        return finishedGoodsQCDAO.updateFinishedGoodsQC(finishedgoodsqc); // Call DAO to update physical count
		    }

		    // Delete a Physical Count by its ID
		    public int deleteFinishedGoodsQC(Long id) {
		        return finishedGoodsQCDAO.deleteFinishedGoodsQC(id); // Call DAO to delete physical count by ID
		    }
		    
		    
			   
		    private String generatefinishgoodsqcUid() {
		        int length = 4;
		        String characters = "0123456789";
		        Random random = new Random();
		        StringBuilder finishgoodsqcUid = new StringBuilder(length);

		        for (int i = 0; i < length; i++) {
		        	finishgoodsqcUid.append(characters.charAt(random.nextInt(characters.length())));
		        }
		        return "FGQC" + finishgoodsqcUid.toString();
		    }
		    
		    
		    
		    // Retrieve product details by product UID
		    public List<Map<String, Object>> getDataByProductUid(String productuid) {
		        return finishedGoodsQCDAO.getDataByProductUid(productuid);
		    }

		    // Retrieve all product UIDs
		    public List<String> fetchProductUIds() {
		        return finishedGoodsQCDAO.fetchProductUIds();
		    }
		    
		    
		    
		    public List<String> fetchBatchIds(){
				return finishedGoodsQCDAO.getBatchIds();
 	
		    }
		    
		    
		    
		    public List<Map<String,Object>> getdataByBatchuid(String batchuid) {
				return finishedGoodsQCDAO.getdataByBatchuid(batchuid);
			}

			public List<Map<String, Object>> getdataByEmpuid(String employeeuid) {
				return finishedGoodsQCDAO.getdataByEmpuid(employeeuid);

			}

			public List<Map<String, Object>> getEmpuid() {
				return finishedGoodsQCDAO.getEmpuid();
				
			}

			
		

	}



package com.prog.service.inventory;




	import java.util.List;

import java.util.Map;
import java.util.Random;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.StockValuationDAO;
import com.prog.model.erp.StockValuation;


	
	@Service
	public class StockValuationService {
		@Autowired
		
		private StockValuationDAO stockValuationdao;
		
		 public int saveStock(StockValuation stockValuation) {
			 
			 String Stockvalueuid=generateSTVUID();
			 
			 stockValuation.setStockvaluationuid(Stockvalueuid);
			 
			 return stockValuationdao.saveStock(stockValuation);// Saves the Salary object to the database
		 
		}
		
		 public List<Map<String, Object>> getallStockValuation() {
		        return stockValuationdao.showthefindall();
		    }
//		 public int updateStock(StockValuation stockvaluation) {
//				// TODO Auto-generated method stub
//				return stockValuationdao.updateStock(stockvaluation);
//				
//			}
		public StockValuation getstockbyid(Long id) {
			// TODO Auto-generated method stub
			return stockValuationdao.getStockById(id);
		}

		public int deleteById(long id) {
			// TODO Auto-generated method stub
			return stockValuationdao.deleteStock(id);
		}
		 

		private String generateSTVUID() {
	        int length =4 ;  // Length of the UID (for example 8 characters)
	        String characters = "1234567890";
	        Random random = new Random();
	        StringBuilder StvId = new StringBuilder(length);

	        // Generate random characters for the UID
	        for (int i = 0; i < length; i++) {
	            StvId.append(characters.charAt(random.nextInt(characters.length())));
	        }

	        return "STV" + StvId.toString();
	 }
		public List<Map<String,Object>> getStockDetailsByStockuid(String stockuid) {
			return stockValuationdao.getStockDetailsByStockuid(stockuid);
		}
		

	}



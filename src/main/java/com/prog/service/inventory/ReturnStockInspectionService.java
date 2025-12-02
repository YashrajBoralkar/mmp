package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.ReturnStockInspectionDao;
import com.prog.model.erp.ReturnStockInspection;


@Service
public class ReturnStockInspectionService {

    private final ReturnStockInspectionDao inspectionDao;

    public ReturnStockInspectionService(ReturnStockInspectionDao inspectionDao) {
        this.inspectionDao = inspectionDao;
    }

    
    public void saveInspection(ReturnStockInspection inspection) {

		if (inspection.getReturnstockuid() == null || inspection.getReturnstockuid().isEmpty()) {
            String stockuid = generatereturnstockUid();
            inspection.setReturnstockuid(stockuid);
        }

        inspectionDao.insert(inspection);
    }
    
    
    private String generatereturnstockUid() {
    	   int length = 4; // Length of the PuId (example: 8 characters)
           String characters = "0123456789";
           Random random = new Random();
           StringBuilder StocktransferUid = new StringBuilder(length);

           for (int i = 0; i < length; i++) {
           	StocktransferUid.append(characters.charAt(random.nextInt(characters.length())));
           }
           return "RS" + StocktransferUid.toString();
       }
	

	public List<Map<String, Object>> getAllInspections() {
        return inspectionDao.findAllReturnStockInfo();
    }
    
    public void deleteInspection(Long id) {
        inspectionDao.deleteById(id);
    }
    public void updateInspection(ReturnStockInspection inspection) {
        inspectionDao.update(inspection);
    }
    public ReturnStockInspection getInspectionById(Long id) {
        return inspectionDao.findById(id);
    }
    
    public List<String> fetchproductUid(){
		return inspectionDao.getproductuid();
		
	}
 public List<Map<String, Object>> getDataByproductuid(String productuid ) {
		return inspectionDao.getDataByproductuid(productuid);
		
	}




    
}
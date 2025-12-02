package com.prog.service.qualitycontrol;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.FirstArticleInspectionDao;
import com.prog.model.erp.Firstarticleinspection;


@Service
public class FirstArticleInspectionService {

    @Autowired
    private FirstArticleInspectionDao firstArticleInspectionDao;

    public int saveFai(Firstarticleinspection firstarticleinspection) {
        String firstarticleinspectionuid = generateFaiUid();
        firstarticleinspection.setFirstarticleinspectionuid(firstarticleinspectionuid);
        return firstArticleInspectionDao.saveFai(firstarticleinspection);
    }

    private String generateFaiUid() {
        int length = 4; // Length of the UID (example: 8 characters)
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder firstarticleinspectionuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	firstarticleinspectionuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "FAI" + firstarticleinspectionuid.toString();
    }
    
    public List<String> getAllProductUIDs() {
        return firstArticleInspectionDao.getAllProductUIDs();
    }

    public List<String> getAllPlanningUIDs() {
        return firstArticleInspectionDao.getAllPlanningUIDs();
    }

    public List<String> getAllWorkOrderUIDs() {
        return firstArticleInspectionDao.getAllWorkOrderUIDs();
    }

    public List<String> getAllProductionOrderUIDs() {
        return firstArticleInspectionDao.getAllProductionOrderUIDs();
    }
    
    public String getProductNameByUID(String productuid) {
        return firstArticleInspectionDao.fetchProductName(productuid);
    }
   

    public List<String> getProductionPlanningUIDs(String productuid) {
        return firstArticleInspectionDao.fetchProductionPlanningUIDs(productuid);
    }

    public List<String> getWorkOrderUIDs(String planninguid) {
        return firstArticleInspectionDao.fetchWorkOrderUIDs(planninguid);
    }

    public List<String> getProductionOrderUIDs(String workorderuid) {
        return firstArticleInspectionDao.fetchProductionOrderUIDs(workorderuid);
    }

    public Integer getPlannedQuantity(String productionorderuid) {
        return firstArticleInspectionDao.fetchPlannedQuantity(productionorderuid);
    }

    
    public List<String> getAllWorkOrderUids() {
        return firstArticleInspectionDao.fetchAllWorkOrderUids();
    }


    //Fetching uids
    public List<Map<String, Object>> getWorkorderDetailsByUid(String workorderuid) {
        return firstArticleInspectionDao.getWorkorderDetailsByUid(workorderuid);
    }

   
    public List<Map<String, Object>> getAllFirstarticleinspection() {
        return firstArticleInspectionDao.fetchAllFirstarticleinspection();
    }

    public void deleteFai(Long id) {
        firstArticleInspectionDao.deleteFaiById(id);
    }

    public Firstarticleinspection getFaiById(Long id) {
        return firstArticleInspectionDao.getFaiById(id);
    }

    public void updateFai(Firstarticleinspection firstarticleinspection) {
        firstArticleInspectionDao.updateFai(firstarticleinspection);
    }

    
    //FETCHING
    
    public List<String> fetchItemId() {
        return firstArticleInspectionDao.getItemId();
    }

	public List<String> getBatchId(){
 		return firstArticleInspectionDao.getBatchId();
 	}
	public List<Map<String, Object>> getProductDetailsByuid(String productuid){
		return firstArticleInspectionDao.getProductDetailsByuid(productuid);
	}
	public List<Map<String, Object>> getbatchDetailsbyid(String batchuid){
		return firstArticleInspectionDao.getbatchDetailsByuid(batchuid);
	}
	public List<String> getItemid(){
		return firstArticleInspectionDao.getItemId();
	}
}
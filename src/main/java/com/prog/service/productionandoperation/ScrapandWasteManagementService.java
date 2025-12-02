package com.prog.service.productionandoperation;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.ScrapandWasteManagementDao;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.ScrapandWasteManagement;

@Service
public class ScrapandWasteManagementService {
	@Autowired
	private ScrapandWasteManagementDao scrapandWasteManagementDao;
	
	// This method is used to save a new Scrap and Waste Management 
	public void addScrapandWasteManagement(ScrapandWasteManagement scarpandwastemanagement) {
	// Generate a unique Scrap UID 
	String scrapUid = generatescrapuid();
		scarpandwastemanagement.setScrapuid(scrapUid);
		scrapandWasteManagementDao.addScrapandWasteManagement(scarpandwastemanagement);
	}
	
	public List<Map<String,Object>> findAllScrapandWasteManagementData(){
		return scrapandWasteManagementDao.findAllScrapandWasteManagementData();
	}
	
	public ScrapandWasteManagement getScrapandWasteManagementById(Long id) {
		return scrapandWasteManagementDao.getScrapandWasteManagementById(id);
	}
	
	public void deleteScrapandWasteManagementById(Long id) {
		scrapandWasteManagementDao.deleteScrapandWasteManagementById(id);
	}
	
	public int updatecrapandWasteManagement(ScrapandWasteManagement swm) {
		return scrapandWasteManagementDao.updatecrapandWasteManagement(swm);
	}
	
	
	//it help in auto-generating the contractUid with prefix as SWM
	private String generatescrapuid() {
	int length = 4;
	// Length of the contract ID (example: 4 characters)
	String characters = "0123456789";
	Random random = new Random();
	StringBuilder contractId = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            contractId.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "SWM" + contractId.toString();
	}
	
	public List<String> getProductUID(){
		return scrapandWasteManagementDao.getProductUID();
	}
	public Productinfo getProductDetails(String productuid) {
		return scrapandWasteManagementDao.getProductDeatils(productuid);
	}
}

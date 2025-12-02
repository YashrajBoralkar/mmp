package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.SparePartsInventoryDao;
import com.prog.model.erp.SparePartsInventoryForm;


@Service
public class SparePartsInventoryService {
	@Autowired 
	 private SparePartsInventoryDao sparePartsInventoryDao;
	 
	 public int addsparePartsInventoryData(SparePartsInventoryForm spid) {
		 String spiUID=generateSparePartsInventoryUId();
			spid.setSparepartsinventoryuid(spiUID);
		 return sparePartsInventoryDao.addSparePartsInventoryData(spid);
	 }
	 
	 public SparePartsInventoryForm getSparePartsInventoryById(Long id) {
		 return sparePartsInventoryDao.getSparePartsInventoryById(id);
	 }
	 
	 public List<Map<String,Object>> getAllSparePartsInventoryData(){
		 return sparePartsInventoryDao.getAllSparePartsInventory();
	 }
	 
	 public int updateSparePartsInventory(SparePartsInventoryForm spif) {
		 return sparePartsInventoryDao.updateSparePartsInventory(spif);
	 }
	 public void deleteSparePartsInventoryById(Long id) {
		 sparePartsInventoryDao.deleteSparePartsInventoryById(id);
	 }
	//UID Generate
		private String generateSparePartsInventoryUId() {
		    int length = 4;  
		    String characters = "1234567890";
		    Random random = new Random();
		    StringBuilder spiuid = new StringBuilder(length);

		    
		    for (int i = 0; i < length; i++) {
		    	spiuid.append(characters.charAt(random.nextInt(characters.length())));
		    }

		     return "SPI" + spiuid.toString();
		}

}

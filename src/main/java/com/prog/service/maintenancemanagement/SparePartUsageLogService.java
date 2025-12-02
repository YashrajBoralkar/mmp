package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.SparePartUsageLogDao;
import com.prog.model.erp.SparePartUsageLog;

@Service
public class SparePartUsageLogService {

	@Autowired
	private SparePartUsageLogDao sparePartUsageLogDao;
	
	public int addSparePartUsageLogData(SparePartUsageLog spul) {
		String Sparepartusageuid = generateSparepartusageuid();
		spul.setSparepartusageuid(Sparepartusageuid);
		return sparePartUsageLogDao.addSparePartUsageLogData(spul); 
	}
	
	//list 
		public List<Map<String,Object>> AllSparePartUsageLogList() {
	        return sparePartUsageLogDao.AllSparePartUsageLogList();  // get all users from table
		}
		
		//delete
		public void deleteSparePartUsageLogById(Long id) {
			sparePartUsageLogDao.deleteSparePartUsageLogById(id);
			
		}
		//update
		public SparePartUsageLog getSparePartUsageLogById(long id) {
			return sparePartUsageLogDao.getSparePartUsageLogById(id);
		}
		//post update
		public void updateSparePartUsageLog(SparePartUsageLog updatespul) {
			sparePartUsageLogDao.updateSparePartUsageLog(updatespul);
		}
		
	
	//autogendrated data
	private String generateSparepartusageuid() {
		int length = 4; 
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder contractId = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            contractId.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "SPUL" + contractId.toString();
	}

	
	//fetch 
		public List<String> getSparepartDetailsByuid(){
			return sparePartUsageLogDao.getSparepartDetailsByuid();
		}
}

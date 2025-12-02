package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.DowntimeAnalysisDao;
import com.prog.model.erp.DowntimeAnalysis;
import com.prog.model.erp.EquipmentMaster;

@Service
public class DowntimeAnalysisService {
	@Autowired
	private DowntimeAnalysisDao downtimeAnalysisDao;
	
	public int saveDowntimeanalysis(DowntimeAnalysis dtaf) {
    	String analysisuid = generateDowntimeAnalysisuid();
        dtaf.setDowntimeanalysisuid(analysisuid);
        return downtimeAnalysisDao.saveDowntimeanalysis(dtaf);
    }
	
	public DowntimeAnalysis getDowntimeAnalysisById(Long id) {
		return downtimeAnalysisDao.getDowntimeAnalysisById(id);
	}
	
	public List<Map<String, Object>> getAlldowntimeAnalysisData(){
		return downtimeAnalysisDao.getAlldowntimeAnalysisData();
	}
	
	public int updateDowntimeAnalysis(DowntimeAnalysis downtime)
	{
		return downtimeAnalysisDao.updateDowntimeAnalysis(downtime);
	}
	
	public int deleteDowntimeAnalysisById(Long id) {
        return downtimeAnalysisDao.deleteDowntimeAnalysisById(id);
    }
	
	private String generateDowntimeAnalysisuid(){
		int length = 4;  
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder dauid = new StringBuilder(length);

	    
	    for (int i = 0; i < length; i++) {
	    	dauid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "DA" + dauid.toString();
    }
	
	public List<String> getEquipmentDetailsById(){
		return downtimeAnalysisDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return downtimeAnalysisDao.getEquipmentDetails(equipmentuid);
	}
	
	 public String getdowntimeduration(String equipmentUid) {
	        return downtimeAnalysisDao.getdowntimeduration(equipmentUid);
	    }
}

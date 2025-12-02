package com.prog.service.qualitycontrol;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.IncomingMaterialQualityInspectionDao;
import com.prog.model.erp.IncomingMaterialQualityInspection;


@Service
public class IncomingMaterialQualityInspectionService {
	@Autowired
	private IncomingMaterialQualityInspectionDao incomingMaterialQualityInspectionDao;
	
	public int addImqInspectorFormData(IncomingMaterialQualityInspection imq) {
		String imqUID=generateInspectionUID();
		imq.setIncomingmaterialqualityinspectionuid(imqUID);
		return incomingMaterialQualityInspectionDao.addImqInspectorData(imq);
	}
	
	public IncomingMaterialQualityInspection getImqInspectorDataById(Long id) {
		return incomingMaterialQualityInspectionDao.getImqInspectorDataById(id);
	}
	
	public List<Map<String,Object>> getAllImqInspectorData(){
		return incomingMaterialQualityInspectionDao.fetchAllImqInspectorData();
	}
	
	public int updateImqInspectorFormData(IncomingMaterialQualityInspection imq) {
		return incomingMaterialQualityInspectionDao.updateImqInspectorForm(imq);
	}
	
	public void deleteImqInspectorDataById(Long id) {
		incomingMaterialQualityInspectionDao.deleteImqInspectorDataById(id);
	}
	
	private String generateInspectionUID() {
		int length=4;
		String characters="1234567890";
		Random random=new Random();
		StringBuilder imqUID=new StringBuilder(length);
		//Generate random characters for the imqUId
				for(int i=0;i<length;i++) {
					imqUID.append(characters.charAt(random.nextInt(characters.length())));
				}
				return "IMQI"+imqUID.toString();
	}
	
	
//FETCHING
	
	public List<String> getBatchId(){
    	return incomingMaterialQualityInspectionDao.getBatchId();
    }
	
	
	public List<Map<String, Object>> getProductDetailsByuid(String productuid){
    	return incomingMaterialQualityInspectionDao.getProductDetailsByuid(productuid);
    }
    
   
	
	public List<Map<String, Object>> getbatchDetailsbyid(String batchuid){
		return incomingMaterialQualityInspectionDao.getbatchDetailsByuid(batchuid);
	}
    public List<String> getItemid(){
    	return incomingMaterialQualityInspectionDao.getItemId();
    }

}

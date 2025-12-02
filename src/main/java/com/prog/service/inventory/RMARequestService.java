package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.RMARequestDao;
import com.prog.model.erp.RMARequestForm;



@Service
public class RMARequestService {
	@Autowired
	private RMARequestDao rmaRequestDao;
	
	//Add new data in RMA Request form
	public int addRMARequestData( RMARequestForm rma) {
		String rmaUID=generateRMARequestUID();
		rma.setReturnmerchandiseauthorizationuid(rmaUID);
		return rmaRequestDao.addRmaRequest(rma);
	}
	//get data by id
	public RMARequestForm getRequestById(Long id) {
		return rmaRequestDao.getRMARequestById(id);
	}
	//Fetch Data in list
	public List<Map<String,Object>> showAllRMARequsetData(){
		return rmaRequestDao.fetchALLRMARequest();
	}
	//update data
	public int updateRMARequestData(RMARequestForm rma) {
		return rmaRequestDao.updateRMARequestData(rma);
	}
	//Delete data by id
	public void deleteRMARequestById(Long id) {
		rmaRequestDao.deleteRMARequestById(id);
	}
	private String generateRMARequestUID() {
		int length=4;
		String characters="1234567890";
		Random random=new Random();
		StringBuilder rmaUID=new StringBuilder(length);
		
		//Generate random characters for the rmaUId
		for(int i=0;i<length;i++) {
			rmaUID.append(characters.charAt(random.nextInt(characters.length())));
		}
		return "RMA"+rmaUID.toString();
	}
	public List<String> getBatchId(){
    	return rmaRequestDao.getBatchId();
    }
	
	
	public List<Map<String, Object>> getItemDetailsByuid(String productuid){
    	return rmaRequestDao.getItemDetailsByuid(productuid);
    }
    
   
	
	public List<Map<String, Object>> getbatchDetailsbyid(String batchuid){
		return rmaRequestDao.getbatchDetailsByuid(batchuid);
	}
    public List<String> getItemid(){
    	return rmaRequestDao.getItemId();
    }

}

package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.BatchInfoDao;
import com.prog.model.erp.Batchinfo;

@Service
public class BatchInfoService 
{
	@Autowired
    private BatchInfoDao batchInfoDao;
	@Autowired
	private BatchInfoDao batchinfodao;
		

    public List<String> getAllbatchUids() {
        return batchInfoDao.getAllBatchUids();
    }
    public Batchinfo getbatchDetailsByUid(String batchuid) {
        return batchInfoDao.getbatchDetailsByUid(batchuid);
    }
    
    public int SaveBatchInfo(Batchinfo batchinfo) {
		
		if (batchinfo.getBatchuid() == null || batchinfo.getBatchuid().isEmpty()) {
            String Batchid = generateBatchuid();
            batchinfo.setBatchuid(Batchid);
        }

		return batchinfodao.AddBatchInfo(batchinfo);
		
	}
	
	
	public List<Map<String, Object>> getAllBatchInfo(){
		return batchinfodao.getBatchInfo();
		
	}
	
	
	public int DeleteBatchInfoByid(Long id) {
		return batchinfodao.DeleteBatchInfo(id);
		
	}
	
	
	public Batchinfo FetchBatchInfoByid(Long id) {
		return batchinfodao.GetBatchInfoByid(id);
		
	}
	
	
	public int UpdateBatchInfo(Batchinfo batchinfo) {
		return batchinfodao.UpdateBatchInfo(batchinfo);
		
		
	}
	
	
	public List<Map<String, Object>> fetchlotIds(String productuid){
		return batchinfodao.getLotIds(productuid);
		
	}
	
	
	public List<String> fetchProductIds(){
		return batchinfodao.getProductIds();
		
	}
	
	
	public List<Map<String, Object>> GetDataByProductId(String productuid ) {
		return batchinfodao.getDataByProductId(productuid);
		
	}
	
	
	
	
	

	private String generateBatchuid() {
        int length = 4;  // Length of the UID (for example 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder batchid = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
        	batchid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "BTH" + batchid.toString();
    }


    


}

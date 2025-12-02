package com.prog.service.inventory;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.LotInfoDAO;
import com.prog.model.erp.LotInfo;



@Service
public class LotInfoService {
	
	@Autowired
	private LotInfoDAO lotdaoinfo;
	
	
	public int SaveBatchInfo(LotInfo lotinfo) {
		
		if (lotinfo.getLotuid() == null || lotinfo.getLotuid().isEmpty()) {
            String Lotuid = generateLotid();
            lotinfo.setLotuid(Lotuid);
        }
		return lotdaoinfo.AddLotInfo(lotinfo);
		
	}
	
	
	public List<Map<String, Object>> getAllLotInfo(){
		return lotdaoinfo.getLotInfo();
		
	}
	
	
	public int DeleteLotInfoByid(Long id) {
		return lotdaoinfo.DeleteLotInfo(id);
		
	}
	
	
	public LotInfo FetchLotInfoByid(Long id) {
		return lotdaoinfo.GetLotInfoByid(id);
		
	}
	
	
	public int UpdateLotInfo(LotInfo lotinfo) {
		return lotdaoinfo.UpdateLotInfo(lotinfo);
		
		
	}
	
	
	public List<String> fetchProductIds(){
		return lotdaoinfo.getProductIds();
		
	}
	
	
	public List<Map<String, Object>> GetDataByProductId(String productuid ) {
		return lotdaoinfo.getDataByProductId(productuid);
		
	}
	
	
	
	
	
	private String generateLotid() {
        int length = 4;  // Length of the UID (for example 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder lotid = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
        	lotid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "LOT" + lotid.toString();
    }
	

}

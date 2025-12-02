package com.prog.service.productionandoperation;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.ProductionQualityControlDao;
import com.prog.model.erp.ProductionQualityControl;

@Service
public class ProductionQualityControlService {
	@Autowired 
	private ProductionQualityControlDao productionQualityControlDao;
	
	//save
	public void saveProductionQualityControl(ProductionQualityControl productionqualitycontrol) {
		String productionqualitycontroluid = generateInspectionuid();
		productionqualitycontrol.setProductionqualitycontroluid(productionqualitycontroluid);
		productionQualityControlDao.saveProductionQualityControl(productionqualitycontrol);		
	}
	
	public ProductionQualityControl getProductionQualityControlById(long id) {
		return productionQualityControlDao.getProductionQualityControlById(id);
	}
	public List<Map<String, Object>> showProductionQualityControlList(){
		return productionQualityControlDao.showProductionQualityControlList();
	}
	
	public ProductionQualityControl getroductionQualityControlById(long id) {
		return productionQualityControlDao.getroductionQualityControlById(id);
	}
	
	public void deleteProductionQualityControlById(long id) {
		productionQualityControlDao.deleteProductionQualityControlById(id);
	}
	
	public int updatProductionQualityControl(ProductionQualityControl productionqualitycontrol) {
		return productionQualityControlDao.updatProductionQualityControl(productionqualitycontrol);
	}
	
	//Auto generate value
	private String generateInspectionuid() {
		int length = 4; 
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder contractId = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            contractId.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "PQC" + contractId.toString();
	}
	
	//Data fetching
		public List<String> getWorkOrderUID(){
			return productionQualityControlDao.getWorkOrderUID();
		}
		
		public List<Map<String,Object>> getProductDetalisByWorkOrderUID(String workorderuid){
			return productionQualityControlDao.getProductDetalisByWorkOrderUID(workorderuid);
		}
}

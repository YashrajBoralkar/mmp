package com.prog.service.purchase;

	import java.util.List;
import java.util.Map;
import java.util.Random;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.RawMaterialPurchaseOrderDao;
import com.prog.model.erp.Rawmaterialpurchaseorder;

	@Service
	public class RawMaterialPurchaseOrderService {
	    @Autowired
	    private RawMaterialPurchaseOrderDao rawMaterialDao;

	    // Save purchase order with generated PO number if not provided
	    public int saveRawmaterialpurchase(Rawmaterialpurchaseorder rm) {
	        if (rm.getRawmaterialpurchaseorderuid() == null || rm.getRawmaterialpurchaseorderuid().isEmpty()) {
	            String rawmaterialpurchaseorderuid = generatePurchaseOrderNumber();
	            rm.setRawmaterialpurchaseorderuid(rawmaterialpurchaseorderuid);
	        }
	        return rawMaterialDao.saveRawMaterial(rm);
	    }

	    // Get all raw material purchase orders
	    public List<Map<String, Object>> getAllRawmaterialpurchase() {
	        return rawMaterialDao.getAllRawMaterials();
	    }

	    // Get single purchase order by PO number
	    public Rawmaterialpurchaseorder getRawmaterialById(Long id) {
	        return rawMaterialDao.getRawMaterialBynumber(id);
	    }

	    // Update a purchase order
	    public int updateRawmaterial(Rawmaterialpurchaseorder rm) {
	        return rawMaterialDao.updateRawMaterial(rm);
	    }

	    public int deleteRawmaterialpurchaseById(Long id) {
	        return rawMaterialDao.deleteRawMaterialById(id); 
	    }

	    // Generate a random PO number like "PO1234"
	    private String generatePurchaseOrderNumber() {
	        Random random = new Random();
	        int number = 1000 + random.nextInt(9000);
	        return "RMPO" + number;
	    }
	    public List<String> getAllSuppliers() {
	        return rawMaterialDao.fetchAllSuppliers();
	    }

	    
		public List<Map<String, Object>> findSupplierNameBySupplierUid(String rawmaterialsupplieruid) {
		    return rawMaterialDao.findSupplierNameByUid(rawmaterialsupplieruid);
		}

		
		public List<Map<String, String>> getMaterialsBySupplierUid(String supplieruid) {
	        return rawMaterialDao.findMaterialNamesBySupplierUid(supplieruid);
	    }
		
		public Map<String, Object> getMaterialDetailsByName(String materialname) {
		    return rawMaterialDao.getMaterialDetailsByName(materialname);
		}
		//FETCHING IN POACK
		
		
		public List<String> fetchRMPOUID() {
			return rawMaterialDao.fetchRMPOUID();
		}
		
		
		public List<Map<String, Object>> getDataByRpUid(String rawmaterialpurchaseorderuid) {
			return rawMaterialDao.getDataByRpUid(rawmaterialpurchaseorderuid);
		}
		
		
	}

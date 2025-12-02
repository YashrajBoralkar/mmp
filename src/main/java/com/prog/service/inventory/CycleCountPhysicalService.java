package com.prog.service.inventory;


import java.util.List;
import java.util.Optional;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.CycleCountPhysicalDAO;
import com.prog.model.erp.CycleCountPhysical;



@Service
public class CycleCountPhysicalService {
	
	@Autowired
	private CycleCountPhysicalDAO physicalcyclecountdao;
	
public int SavePhysicalCycleCount(CycleCountPhysical physicalcyclecount) {
		
		if (physicalcyclecount.getCyclecountuid() == null || physicalcyclecount.getCyclecountuid().isEmpty()) {
            String cyclecountuid = generateCyclecountuid();
            physicalcyclecount.setCyclecountuid(cyclecountuid);
        }
		
		int systemStock = Integer.parseInt(physicalcyclecount.getSystemstock());
		int physicalStock = Integer.parseInt(physicalcyclecount.getPhysicalstock());

		int difference = systemStock - physicalStock;

		physicalcyclecount.setStockdifference(String.valueOf(difference));


		return physicalcyclecountdao.AddPhysicalCycleCount(physicalcyclecount);
		
	}
	
	
	public List<Map<String, Object>> getAllPhysicalCycleCount(){
		return physicalcyclecountdao.getPhysicalCycleCount();
		
	}
	
	
	public int DeletePhysicalCycleCountByid(Long id) {
		return physicalcyclecountdao.DeletePhysicalCycleCount(id);
		
	}
	
	
	public CycleCountPhysical FetchPhysicalCycleCountByid(Long id) {
		return physicalcyclecountdao.GetPhysicalCycleCountByid(id);
		
	}
	
	
	public int UpdatePhysicalCycleCount(CycleCountPhysical physicalcyclecount) {
		return physicalcyclecountdao.UpdatePhysicalCycleCount(physicalcyclecount);
		
		
	}
	
	
	private String generateCyclecountuid() {
        int length = 4;  // Length of the UID (for example 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder cyclecountuid = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
        	cyclecountuid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "CYCP" + cyclecountuid.toString();
    }
	
	
	
	public List<String> fetchBatchIds(){
		return physicalcyclecountdao.fetchBatchIds();
		
	}
	
	
	public List<Map<String, Object>> GetDataByBatchuid(String batchuid ) {
		return physicalcyclecountdao.getdataByBatchuid(batchuid);
		
	}
	
	// ✅ Fetch Product Info (Product Name + Warehouse UID) by Product UID
	public Map<String, Object> getProductInfoByProductUid(String productUid) {
	    return physicalcyclecountdao.getProductInfoByProductUid(productUid);
	}

	// ✅ Fetch Warehouse Name by Warehouse UID
	public String getWarehouseNameByUid(String warehouseUid) {
	    return physicalcyclecountdao.getWarehouseNameByUid(warehouseUid);
	}
	// ✅ Fetch Global Quantity from Realtimeupdate by Product UID
	public Double getGlobalQuantityByProductUid(String productUid) {
	    return physicalcyclecountdao.getGlobalQuantityByProductUid(productUid);
	}
	
	
	// ✅ Fetch latest realtime quantity by warehouseuid (for System Stock field)
	public Double getLatestRealtimeQuantityByWarehouse(String warehouseUid) {
	    return physicalcyclecountdao.getLatestRealtimeQuantityByWarehouse(warehouseUid);
	}
	public List<CycleCountPhysical> getAllPhysicalCycleCountEntities() {
	    return physicalcyclecountdao.getAllEntities();
	}


	



}

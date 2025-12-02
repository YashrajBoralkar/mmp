package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.StockLevelManagementDao;
import com.prog.Dao.inventory.WarehouseInfoDao;
import com.prog.model.erp.Warehouseinfo;



@Service
public class WarehouseInfoService {
	@Autowired
    private WarehouseInfoDao warehouseDao;
	@Autowired
    private StockLevelManagementDao stocklevelmanagementdao ;

	@Autowired
    private WarehouseInfoDao warehouseDAO;
    public void saveWarehouse(Warehouseinfo warehouse) {
    	
    	if (warehouse.getWarehouseuid() == null || warehouse.getWarehouseuid().isEmpty()) {
            String warehouseid = generatewarehouseid();
            warehouse.setWarehouseuid(warehouseid);
        }

        warehouseDAO.save(warehouse);
    }
    
	public List<Map<String, Object>>  getAllWarehouses() {
        return warehouseDAO.findAll();
    }
    public void deleteWarehouse(Long id) {
        warehouseDAO.delete(id);
    }
    public Warehouseinfo findWarehouseById(Long id) {
        return warehouseDAO.findById(id);
    }
    
    
    private String generatewarehouseid() {
        int length = 4;  // Length of the UID (for example 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder warehouseid = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
        	warehouseid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "WH" + warehouseid.toString();
    }
	
	public List<String> getAllwarehouseUids() {
        return warehouseDao.getAllWarehouseUids();
    }
    public Warehouseinfo getwarehouseDetailsByUid(String warehouseuid) {
        return warehouseDao.getwarehouseDetailsByUid(warehouseuid);
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //FETCHING IN GOODS RECEIPT FORM
    public List<String> getAllWarehouseuidsIngoodsreceiptnote() {
	     return warehouseDao.getAllWarehouseuidsIngoodsreceiptnote();
	 }

	public Warehouseinfo getwarehouseDetailsByWarehouseuidIngoodsreceiptnote(String productuid) {
	      return warehouseDao.getwarehouseDetailsByWarehouseuidIngoodsreceiptnote(productuid);
		}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//FETCHING IN TRANSPORTATION REQUEST FORM
        
//    public List<String> fetchwarehouseuidInTransportationrequest(){
//		return warehouseDao.getwarehouseuidInTransportationrequest();
//		
//	}
//	
//    public List<Map<String, Object>> GetDataByWarehouseUIdInTransportationrequest(String warehouseuid ) {
//		return warehouseDao.getDataBywarehouseuidInTransportationrequest(warehouseuid);
//		
//	}
//    public List<String>getDestinationWarehouseUidsExcludingSource(String sourcewarehouseuid){
//    	return warehouseDao.getDestinationWarehouseUidsExcludingSource(sourcewarehouseuid);
//    }
//
//	
	public List<Map<String, Object>> GetDataByWarehouseUId(String warehouseuid ) {
		return warehouseDao.getDataBywarehouseuid(warehouseuid);
		
	}

 public List<String> fetchwarehouseuid(){
		return warehouseDao.getwarehouseuid();
		
	}



}

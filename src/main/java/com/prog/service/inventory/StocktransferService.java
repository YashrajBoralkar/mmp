package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.StocktransferDAO;
import com.prog.model.erp.Stocktransfer;

@Service
public class StocktransferService {

    @Autowired
    private StocktransferDAO stocktransferDAO;

    // ---------------------- CREATE ----------------------
    public int saveStocktransfer(Stocktransfer stocktransfer) {
        String generatedUid = generateStocktransferUid();
        stocktransfer.setStocktransferuid(generatedUid);
        return stocktransferDAO.saveStocktransfer(stocktransfer);
    }

    // ---------------------- READ ----------------------
    public List<Map<String, Object>> getAllStockTransfers() {
        return stocktransferDAO.fetchAllStockTransfers();
    }

    public Stocktransfer getStocktransferById(Long id) {
        return stocktransferDAO.getstocktransferbyId(id);
    }

    public List<String> getWarehouseUids() {
        return stocktransferDAO.getwarehouseuid();
    }

    public List<Map<String, Object>> getWarehouseDetailsByUid(String warehouseuid) {
        return stocktransferDAO.getDataBywarehouseuid(warehouseuid);
    }

    // ---------------------- Product & Warehouse Methods ----------------------
    public List<Map<String, Object>> getAllProductUIDsFromInventory() {
        return stocktransferDAO.getAllProductUIDsFromInventory();
    }

    public String getProductNameByUid(String productuid) {
        return stocktransferDAO.getProductNameByUid(productuid);
    }

    public List<Map<String, Object>> getWarehousesByProductUid(String productuid) {
        return stocktransferDAO.getWarehousesByProductUid(productuid);
    }

    public Map<String, Object> getProductDetailsByUid(String productuid) {
        return stocktransferDAO.getProductDetailsByUid(productuid);
    }

    public Map<String, Object> getWarehouseStockByProduct(String productuid, String warehouseuid) {
        return stocktransferDAO.getWarehouseStockByProduct(productuid, warehouseuid);
    }

    public List<Map<String, Object>> getWarehouseDataByUid(String warehouseuid) {
        return stocktransferDAO.getWarehouseDataByUid(warehouseuid);
    }
    public List<Map<String, Object>> getAllDestinationWarehouses() {
        return stocktransferDAO.getAllDestinationWarehouses();
    }
    
    public List<Map<String, Object>> getDestinationWarehousesExcludingSource(String sourceUid) {
        return stocktransferDAO.fetchDestinationWarehousesExcludingSource(sourceUid);
    }

    public Map<String, Object> getDestinationWarehouseDetails(String warehouseUid) {
        return stocktransferDAO.fetchDestinationWarehouseDetails(warehouseUid);
    }


//--------------------Warehouse from realtimeupdate-----------------------------------
    
    public Map<String, Object> getStockDetails(String warehouseUid, String productType, String productUid) {
        if ("Raw_Material".equalsIgnoreCase(productType)) {
            return stocktransferDAO.fetchRawMaterialStock(warehouseUid, productUid);
        } else {
            return stocktransferDAO.fetchWarehouseStockByType(warehouseUid, productType, productUid);
        }
    }
    // ---------------------- UPDATE ----------------------
    public void updateStocktransfer(Stocktransfer stocktransfer) {
        stocktransferDAO.updateStockTransfer(stocktransfer);
    }

    // ---------------------- DELETE ----------------------
    public void deleteStocktransfer(Long id) {
        stocktransferDAO.deletestocktransferbyId(id);
    }

    // ---------------------- Employee Data ----------------------
    public List<Map<String, Object>> getAllEmployees() {
        return stocktransferDAO.getAllEmployees();
    }

    public Map<String, Object> getEmployeeByUid(String employeeuid) {
        return stocktransferDAO.getEmployeeByUid(employeeuid);
    }

    // ---------------------- Utility ----------------------
    private String generateStocktransferUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder uid = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "ST" + uid.toString();
    }
    
    //------------------------------FETCH PRODUCT TYPE -----------------
    
    public List<Map<String, Object>> getProductUIDsByType(String productType) {
        return stocktransferDAO.getProductUIDsByType(productType);
    }

    
    public List<Map<String, String>> getUIDsByProductType(String productType) {
        return stocktransferDAO.getUIDsByProductType(productType);
    }

}


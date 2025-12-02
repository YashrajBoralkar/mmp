package com.prog.service.productionandoperation;

import com.prog.Dao.productionandoperation.ProductionOrderDao;
import com.prog.model.erp.ProductionOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductionOrderService {

    @Autowired
    private ProductionOrderDao productionOrderDao;

    // 🔹 Save with UID generation
    public int saveOrder(ProductionOrder productionOrder) {
        String productionOrderUID = generateProductionOrderUID();
        productionOrder.setProductionorderuid(productionOrderUID);
        return productionOrderDao.saveProductionOrder(productionOrder);
    }

    // 🔹 UID Generator
    private String generateProductionOrderUID() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder uid = new StringBuilder("PO");
        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return uid.toString();
    }

    // 🔹 Get All Orders (for grid)
    public List<Map<String, Object>> getAllOrders() {
        return productionOrderDao.getAllOrders();
    }

    // 🔹 Delete
    public int deleteOrder(Long id) {
        return productionOrderDao.deleteOrder(id);
    }

    // 🔹 Get Single Order by ID
    public ProductionOrder getOrderById(Long id) {
        return productionOrderDao.getOrderById(id);  
    }

    // 🔹 Update
    public int updateOrder(ProductionOrder productionOrder) {
        return productionOrderDao.updateProductionOrder(productionOrder);
    }

//    // 🔹 Dropdown: Planning UIDs
//    public List<String> getPlanningUIDs() {
//        return productionOrderDao.getPlanningUIDs();
//    }
//
//    
//    // 🔹 Dropdown: Work Order UIDs
//    public List<String> getWorkOrderUIDs() {
//        return productionOrderDao.getWorkOrderUIDs();
//    }

//    // 🔹 AJAX: Work Order Qty
//    public List<Map<String, Object>> getWorkOrderQuantity(String workorderuid) {
//        return productionOrderDao.getWorkOrderQuantity(workorderuid);
//    }

    // 🔹 AJAX: Planned Completion Date
    public String getPlannedEndDate(String planninguid) {
        return String.valueOf(productionOrderDao.getPlannedEndDate(planninguid));
    }

    // 🔹 Dropdown: All Products
    public List<String> getAllProductbyPP() {
        return productionOrderDao.getProductuid();
    }

    

    // 🔹 Get All Orders (for grid) – duplicate method alias
    public List<Map<String, Object>> getAList() {
        return productionOrderDao.getAllOrders();
    }

    // 🔹 AJAX: Product Data by UID
    public List<Map<String, Object>> getProductDataByUid(String productUid) {
        return productionOrderDao.getProductdataByuid(productUid);
    }

    // 🔹 Update Production Order
    public Object updateProductionOrder(ProductionOrder po) {
        return productionOrderDao.updateProductionOrder(po);        
    }

//    // 🔹 Get Work Orders by single Production Planning UID
//    public List<String> getWorkOrdersByPlanningUID(String planningUID) {
//        return productionOrderDao.getWorkOrdersByPlanningUID(planningUID);
//    }
//
//    // 🔹 Get Work Orders by multiple Production Planning UIDs
//    public List<String> getWorkOrdersByPlanningUIDs(List<String> planningUIDs) {
//        return productionOrderDao.getWorkOrdersByPlanningUIDs(planningUIDs);
//    }
    
    //new
    public List<String> getPlanningUIDsByProduct(String productuid) {
        return productionOrderDao.getPlanningUIDsByProduct(productuid);
    }

    public List<String> getWorkOrdersByPlanningUID(String productionplanninguid) {
        return productionOrderDao.getWorkOrdersByPlanningUID(productionplanninguid);
    }

    public Integer getPlannedQuantityByWorkOrder(String workorderuid) {
        return productionOrderDao.getPlannedQuantityByWorkOrder(workorderuid);
    }
    
    public Map<String, Object> getPlannedDatesByPlanningUID(String productionplanninguid) {
        return productionOrderDao.getPlannedDatesByPlanningUID(productionplanninguid);
    }

}

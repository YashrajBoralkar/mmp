package com.prog.service.productionandoperation;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.ProductionPlanningDao;
import com.prog.model.erp.productionplanning;


@Service
public class ProductionPlanningService {

    @Autowired
    private ProductionPlanningDao productionplanningdao;

    // ✅ Save a single production planning entry (called for each material row)
    public int saveplan(productionplanning productionplanning) {
        return productionplanningdao.addPlans(productionplanning);
    }

    // 🔁 [Unused in code] Generates a random UID (4-digit numeric suffix) like PLA1234
    private String generateProductionuid() {
        int length = 4; // length of random numeric part
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder productionplanninguid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            productionplanninguid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "PLA" + productionplanninguid.toString();
    }

    // ✅ Get a list of all production planning records (aggregated view)
    public List<Map<String, Object>> getplanlist() {
        return productionplanningdao.findallplan(); // joins and groups materials
    }

    // ✅ Get all rows of a plan by UID (used for editing/updating a plan)
    public List<Map<String, Object>> getProductionPlanningbyuid(String productionplanninguid) {
        return productionplanningdao.getProductionPlanningbyuid(productionplanninguid);
    }

    // ✅ Delete production planning records by UID (deletes all material rows for that UID)
    public void deleteplan(String productionplanninguid) {
        productionplanningdao.deleteplan(productionplanninguid);
    }

    // ✅ Update only the main plan information (excluding materials)
    public void updateProductionPlanning(productionplanning productionplanning) {
        productionplanningdao.updateProductionPlanning(productionplanning);
    }

    // ✅ Get all unique product UIDs from product_info_form (used for dropdown)
    public List<String> getProductUId() {
        return productionplanningdao.getProductUId();
    }

    // ✅ Get product names by product UID (used to populate product name field on form)
    public List<String> getProductNamesByProductUid(String productuid) {
        return productionplanningdao.getProductNamesByProductUid(productuid);
    }

    // ✅ Get list of raw materials (UID + name) for a selected product
    public List<Map<String, Object>> getMaterialNamesByProductName(String productname) {
        return productionplanningdao.getMaterialNamesByProductName(productname);
    }

//    public List<String>getrawmaterialnamesbyuid(String productname){
//    	return productionplanningdao.getrawmaterialnamebyuid(productname);
//    }
    
    // ✅ Get available quantity from inventory for a given material (used for 'actual' value in form)
    public double getAvailableQuantity(String rawmaterialuid) {
        return productionplanningdao.getAvailableQuantityByMaterialuid(rawmaterialuid);
    }

    // ✅ Get employee names with UID for dropdowns (used in manager/approver fields)
    public List<Map<String, String>> getEmployeeNames() {
        return productionplanningdao.getEmployeeNames();
    }
}

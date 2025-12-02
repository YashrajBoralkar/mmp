package com.prog.service.inventory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.InventoryEntryDao;
import com.prog.model.erp.InventoryEntry;


@Service
public class InventoryEntryService {

    @Autowired
    private InventoryEntryDao dao;

    public void saveEntry(InventoryEntry entry) {
        if (entry.getId() == null) {
            if (entry.getInventoryentryuid() == null || entry.getInventoryentryuid().isEmpty()) {
                entry.setInventoryentryuid(generateInventoryEntryUid());
            }
//            if (entry.getEntrydate() == null) {
//                entry.setEntrydate(LocalDate.now());
//            }
            dao.save(entry);
        } else {
            dao.update(entry);
        }
    }

    private String generateInventoryEntryUid() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder uid = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "IE" + uid.toString();
    }

    public List<InventoryEntry> getAllEntries() {
        return dao.findAll();
    }

    public InventoryEntry getEntryById(int id) {
        return dao.findById(id);
    }

    public int updateEntry(InventoryEntry entry) {
        return dao.update(entry);
    }

    public void deleteEntry(Long id) {
        dao.delete(id);
    }

    public List<Map<String, Object>> getAllEntriesForList() {
        return dao.findAllForList();
    }

    public List<Map<String, Object>> getAllWarehouses() {
        return dao.findAllWarehouses();
    }

    public List<Map<String, Object>> getAllEmployeeUid() {
        return dao.getAllEmployeeUid();
    }

    /* ---------------- Product UID lists ---------------- */
    public List<Map<String, Object>> getApprovedProductUids(String type) {
        if ("RAW MATERIAL".equalsIgnoreCase(type)) {
            return dao.findAllRawMaterialUids();
        } else if ("GOODS".equalsIgnoreCase(type)) {
            return dao.findAllGoodsUids();
        }
        return List.of();
    }

    /* ---------------- Dependent UID lists ---------------- */
    public List<Map<String, Object>> getFinishGoodsQcUidsByProduct(String productUid) {
        return dao.findAllFinishGoodsQcUidsByProduct(productUid);
    }

//    public List<Map<String, Object>> getRawMaterialReceiptNoteUidsByRawMaterial(String rawMaterialUid) {
//        return dao.findAllRawMaterialReceiptNoteUidsByRawMaterial(rawMaterialUid);
//    }

    /* ---------------- Product details ---------------- */
    public Map<String, Object> getProductDetails(String type, String productUid, String referenceUid) {
        if ("RAW MATERIAL".equalsIgnoreCase(type)) {
            Map<String, Object> rm = dao.findRawMaterialDetailsByUid(productUid, referenceUid);
            if (!rm.isEmpty()) {
                Map<String, Object> map = new HashMap<>();
                map.put("productname", rm.get("productname"));        // ✅ matches DAO map
                map.put("approvedquantity", rm.get("approvedquantity"));
 // frontend expects approvedquantity
                return map;
            } else {
                return Collections.emptyMap();
            }
        } else if ("GOODS".equalsIgnoreCase(type)) {
            return dao.findGoodsDetailsByUid(productUid, referenceUid);
        }
        return Map.of();
    }




    /* ---------------- Warehouse details ---------------- */
    public String getWarehouseName(String warehouseuid) {
        Map<String, Object> row = dao.findWarehouseByUid(warehouseuid);
        return row != null ? String.valueOf(row.get("warehousename")) : "";
    }
    
//==================================================================================================
    //Separate fetching
    
 // Fetch product name by UID
    public String getProductName(String type, String productUid) {
        return dao.fetchProductName(type, productUid);
    }

    // Fetch approved quantity by reference UID
    public Integer getApprovedQuantity(String type, String productUid, String referenceUid) {
        return dao.fetchApprovedQuantity(type, productUid, referenceUid);
    }
    
 // ---------------- Fetch used Reference UIDs ----------------
    public List<String> getUsedReferenceUids(String type) {
        return dao.getUsedReferenceUids(type);
    }
    
    
    //FETCHING 
    // InventoryEntryService मध्ये
    public List<String> getWarehouseUidsByProductUid(String productUid) {
        return dao.findWarehouseUidsByProductUid(productUid);
    }


}

package com.prog.service.purchase;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.prog.model.erp.RawmaterialInfo;
import com.prog.Dao.purchase.CompetitiveProcurementRequestDao;
import com.prog.model.erp.CompetitiveProcurementRequest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CompetitiveProcurementRequestService {

    private final CompetitiveProcurementRequestDao cprDao;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompetitiveProcurementRequestService(CompetitiveProcurementRequestDao cprDao) {
        this.cprDao = cprDao;
    }

    // ---------------------------------------------------------------------
    // 🔹 CRUD OPERATIONS
    // ---------------------------------------------------------------------

    /** Save a new Competitive Procurement Request */
    public int saveCompetitiveProcurementRequest(Map<String, Object> data) {
        CompetitiveProcurementRequest cpr = new CompetitiveProcurementRequest();

        // ✅ Generate UID in format like CPR0123
        String uid = generateUid("CPR");
        cpr.setCompetitiveprocurementrequestuid(uid);

        // ✅ Set request fields
        cpr.setRawmaterialuid((String) data.get("rawmaterialuid"));
        cpr.setRequestforquotationuid((String) data.get("requestforquotationuid"));
        cpr.setEmployeeuid((String) (data.get("employeeuid") != null ? data.get("employeeuid") : data.get("employeeUid")));
        cpr.setRequestdate((String) (data.get("requestdate") != null ? data.get("requestdate") : data.get("requestDate")));
        cpr.setRemark((String) data.get("remark"));
        cpr.setApprovedby((String) data.get("approvedby"));

        // ✅ Handle supplier details (always JSON serialized)
        try {
            Object sup = data.get("supplierdetails");
            cpr.setSupplierdetails(sup != null ? objectMapper.writeValueAsString(sup) : null);
        } catch (Exception e) {
            cpr.setSupplierdetails(null);
        }

        return cprDao.add(cpr);
    }

    /** Update an existing request */
    public int updateRequest(Map<String, Object> data, Long id, String remark, String requestdate) {
        id = Long.valueOf(data.get("id").toString());
        Map<String, Object> existing = getById(id);

        if (existing == null) return 0;

        // --- Preserve existing values if missing ---
        data.putIfAbsent("rawmaterialuid", existing.get("rawmaterialuid"));
        data.putIfAbsent("requestforquotationuid", existing.get("requestforquotationuid"));
        data.putIfAbsent("employeeuid", existing.get("employeeuid"));
        data.putIfAbsent("employeename", existing.get("employeename"));

        if (!data.containsKey("requestdate")) {
            data.put("requestdate", existing.get("requestdate"));
        }
        if (!data.containsKey("remark")) {
            data.put("remark", existing.get("remark"));
        }

        // --- Merge supplier details ---
        String oldSuppliers = existing.get("supplierdetails") != null ? existing.get("supplierdetails").toString() : "[]";
        try {
            Object newSuppliers = data.get("supplierdetails");
            if (newSuppliers != null) {
                List<Map<String, Object>> oldList = objectMapper.readValue(oldSuppliers, List.class);
                String supplierJson = objectMapper.writeValueAsString(newSuppliers);
                List<Map<String, Object>> newList = objectMapper.readValue(supplierJson, List.class);
                oldList.addAll(newList);
                data.put("supplierdetails", objectMapper.writeValueAsString(oldList));
            } else {
                data.put("supplierdetails", oldSuppliers);
            }
        } catch (Exception e) {
            data.put("supplierdetails", oldSuppliers);
        }

        if (!data.containsKey("approvedby")) {
            data.put("approvedby", existing.get("approvedby")); // keep old approver
        }

        return cprDao.update(id, remark, requestdate);
    }

    /** Delete request by ID */
    public int deleteById(Long id) {
        return cprDao.deleteById(id);
    }

    // ---------------------------------------------------------------------
    // 🔹 QUERY OPERATIONS
    // ---------------------------------------------------------------------

    /** Get all CPRs with links */
    public List<Map<String, Object>> getAllWithLinks() {
        return cprDao.findAllcpr();
    }

    /** Get CPR by ID (parses supplier details JSON) */
    public Map<String, Object> getById(Long id) {
        Map<String, Object> record = cprDao.findById(id);
        if (record == null) return null;

        // 🔑 Parse supplierdetails JSON into a List
        Object supplierJson = record.get("supplierdetails");
        try {
            if (supplierJson != null && !supplierJson.toString().isEmpty()) {
                List<Map<String, Object>> suppliers = objectMapper.readValue(supplierJson.toString(), List.class);
                record.put("suppliers", suppliers);
            } else {
                record.put("suppliers", List.of());
            }
        } catch (Exception e) {
            e.printStackTrace();
            record.put("suppliers", List.of());
        }

        return record;
    }

    /** Get RFQs by raw material */
    public List<Map<String, Object>> getRfqsByMaterial(String rawMaterialName) {
        return cprDao.findRfqsByRawMaterial(rawMaterialName);
    }

    /** Get RFQs by UID */
    public List<Map<String, Object>> getRfqByUid(String rfqUid) {
        return cprDao.findByUid(rfqUid);
    }

    /** Get CPRs with raw material */
    public List<Map<String, Object>> getAllWithRawMaterial() {
        return cprDao.findAllWithRawMaterial();
    }

    /** Get all raw materials */
    public List<Map<String, Object>> getRawMaterials() {
        return cprDao.findAllRawMaterials();
    }

    /** Get all employees */
    public List<Map<String, Object>> getAllEmployees() {
        return cprDao.findAllEmployees();
    }

    /** Get all raw materials (entity) */
    public List<RawmaterialInfo> getAllRawMaterials() {
        return cprDao.findAll();
    }

    /** Get records by RFQ UID (with links) */
    public List<Map<String, Object>> getByRfqUid(String rfqUid) {
        return cprDao.findByRfqUidWithLinks(rfqUid);
    }

    // ---------------------------------------------------------------------
    // 🔹 UTILITY METHODS
    // ---------------------------------------------------------------------

    /** Utility method to generate UID in format PREFIX + 4-digit number */
    private String generateUid(String prefix) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10000); // 0000–9999
        return String.format("%s%04d", prefix, randomNum); // Pads with leading zeros
    }
}

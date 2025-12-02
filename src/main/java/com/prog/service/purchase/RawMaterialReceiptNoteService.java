package com.prog.service.purchase;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.RawMaterialReceiptNoteDao;
import com.prog.model.erp.RawMaterialReceiptNote;
import com.prog.model.erp.Rawmaterialpurchaseorder;

import java.util.*;

@Service
public class RawMaterialReceiptNoteService {

    @Autowired
    private RawMaterialReceiptNoteDao dao;

    public RawMaterialReceiptNote getById(Long id) {
        return dao.getById(id);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public void save(RawMaterialReceiptNote rawmgrn) {
        String grnuid = generateRawmaterialrnuid();
        rawmgrn.setRawmaterialreceiptnoteuid(grnuid);
        dao.save(rawmgrn);
    }

    public int update(RawMaterialReceiptNote rawmgrn) {
        return dao.update(rawmgrn);
    }

    public List<Rawmaterialpurchaseorder> getPurchaseOrdersWithPassedQuality() {
        return dao.getPurchaseOrdersWithPassedQuality();
    }

    public List<Map<String, Object>> getAllWithJoin() {
        return dao.getAllWithJoin();
    }

    public Map<String, Object> getDetailsByUid(String poUid) {
        return dao.getDetailsByUid(poUid);
    }

    // ✅ UID Generator
    private String generateRawmaterialrnuid() {
        int length = 4;
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder uid = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "RMRN" + uid.toString();
    }
  

    // 🔹 Fetch material breakdown for given Purchase Order UID
    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUid(String rawmaterialpurchaseorderuid) {
        return dao.getRawMaterialsByPurchaseOrderUid(rawmaterialpurchaseorderuid);
    }

    public List<Map<String, Object>> getAllNotesWithEmployeeNames() {
        return dao.findAllWithEmployeeNames();
    }
}

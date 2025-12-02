package com.prog.service.inventory;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.RawMaterialInventoryEntryDao;
import com.prog.model.erp.RawMaterialInventoryEntry;




@Service
public class RawMaterialInventoryEntryService {

    @Autowired
    private RawMaterialInventoryEntryDao dao;

    public void saveEntry(RawMaterialInventoryEntry entry) {
        if (entry.getId() == null) {
            if (entry.getRawmaterialinventoryentryuid() == null || entry.getRawmaterialinventoryentryuid().isEmpty()) {
                entry.setRawmaterialinventoryentryuid(generateInventoryEntryUid());
            }
   
            dao.save(entry);
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
        return "RMIE" + uid.toString();
    }


    public List<Map<String, Object>> getAllEntries() {
        return dao.findAll();
    }

    public RawMaterialInventoryEntry getEntryById(int id) {
        return dao.findById(id);
    }

    public int updateEntry(RawMaterialInventoryEntry entry) {
        return dao.update(entry);
    }

    public void deleteEntry(int id) {
        dao.delete(id);
    }
    
    public List<Map<String, Object>> getAllAcceptedGrns() {
        return dao.findAllWithJoin();
    }
    
    public List<Map<String, Object>> getAllEntriesForList() {
        return dao.findAllWithJoinForInventoryList();
    }
    
    public List<Map<String, Object>> getAllWarehouses() {
        return dao.findAllWarehouses();
    }
    
    public List<Map<String, Object>> getAllEmployeeUid() {
    	return dao.getAllEmployeeUid();
    }
    
    public List<Map<String, Object>> getmaterialDetails(String rawmaterialreceiptnoteuid) {
    	return dao.getmaterialDetails(rawmaterialreceiptnoteuid);
    }


	
	
}
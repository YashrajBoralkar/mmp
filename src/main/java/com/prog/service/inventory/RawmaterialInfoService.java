package com.prog.service.inventory;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.RawmaterialInfoDao;
import com.prog.model.erp.RawmaterialInfo;


@Service
public class RawmaterialInfoService {

    @Autowired
    private RawmaterialInfoDao rawmaterialInfoDao;

    // Save a new raw material entry with a generated UID

    public void saveRawMaterial(RawmaterialInfo rawMaterial) {
        String uid = generateRawMaterialUid();
        rawMaterial.setRawmaterialuid(uid);
        rawmaterialInfoDao.saveRawmaterialRegistration(rawMaterial);
    }

    // Generate a unique UID for raw material, e.g., RMU123456

    public String generateRawMaterialUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder uid = new StringBuilder("RM");

        for (int i = 0; i < length; i++) {
            uid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return uid.toString();
    }

    // Retrieve all raw material records

    public List<Map<String, Object>> getAllRawMaterials() {
        return rawmaterialInfoDao.findAll();
    }

    // Retrieve a specific raw material by UID (not ID)

    public RawmaterialInfo getRawMaterialById(Long id) {
        return rawmaterialInfoDao.getById(id);
    }

    // Update an existing raw material record

    public void updateRawMaterial(RawmaterialInfo raw) {
        rawmaterialInfoDao.update(raw);
    }
    // Delete a raw material entry using UID

    public void deleteById(Long id) {
        rawmaterialInfoDao.deleteById(id);
    }
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

// Retrieve raw material details by UID
public List<Map<String, Object>> getDataByrawmaterialUid(String rawmaterialuid) {
return rawmaterialInfoDao.getDataByrawmaterialUid(rawmaterialuid);
}

// Retrieve all rm UIDs
public List<String> fetchrawmaterialUIds() {
return rawmaterialInfoDao.fetchrawmaterialUIds();
}

//REQUESTFORQUOTATION
public List<Map<String, Object>> getRawMaterialWithSuppliers(String rawmaterialuid) {
return rawmaterialInfoDao.getSuppliersByRawMaterialUid(rawmaterialuid);
}
    
}

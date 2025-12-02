package com.prog.service.qualitycontrol;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.RawMaterialQualityInspectionDao;
import com.prog.model.erp.RawmaterialQualityinspection;

@Service
public class RawmaterialQualityinspectionService {

	@Autowired
	private RawMaterialQualityInspectionDao rawmaterialQualityinspectiondao;
	
	/*
     * Adds a new raw material quality inspection record.
     * Automatically generates a unique UID before saving.
     */
	public void addInspection(RawmaterialQualityinspection inspection) {
	    String uid = generateRawMaterialQualityInspectionUid();
	    inspection.setRawmaterialqualityinspectionuid(uid);
	    rawmaterialQualityinspectiondao.addInspection(inspection);
	}
	
	/*
     * Generates a random 6-digit UID prefixed with "RMI" for quality inspection.
     * Used as a primary identifier for each inspection entry.
     */
	public String generateRawMaterialQualityInspectionUid() {
	    int length = 4;
	    String characters = "0123456789";
	    Random random = new Random();
	    StringBuilder uidBuilder = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	        uidBuilder.append(characters.charAt(random.nextInt(characters.length())));
	    }

	    return "RMQ" + uidBuilder.toString(); 
	}


	/*
     * Fetches all inspection records from the database.
     */
	public List<Map<String, Object>> getAllInspections() {
	    return rawmaterialQualityinspectiondao.findAll();
	}


	 /*
     * Deletes a specific inspection record based on its ID.
     */
	public void deleteById(Long id) {
		rawmaterialQualityinspectiondao.deleteById(id);
	}

	/*
     * Retrieves a single inspection record by its database ID.
     * Used for pre-filling the form during edit.
     */
	public RawmaterialQualityinspection getById(Long id) {
	    return rawmaterialQualityinspectiondao.getById(id);
	}

	 /*
     * Updates an existing inspection record with new values.
     */
	public void update(RawmaterialQualityinspection inspection) {
	    rawmaterialQualityinspectiondao.update(inspection);
	}
	
	/**
     * Returns list of employee names and UIDs (used in dropdowns).
     */
    public List<Map<String, String>> getEmployeeNames() {
        return rawmaterialQualityinspectiondao.getEmployeeNames();
    }


    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUid(String rawmaterialpurchaseorderuid) {
        return rawmaterialQualityinspectiondao.getRawMaterialsByPurchaseOrderUid(rawmaterialpurchaseorderuid);
    }

    public List<String> getAllPurchaseOrderUids() {
        return rawmaterialQualityinspectiondao.getAllPurchaseOrderUids();
    }
}


    

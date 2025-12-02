package com.prog.service.supplier;

import java.util.List;


import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.supplier.PurchaseOrderAcknowledgmentDAO;
import com.prog.model.erp.POAcknowledgement;


@Service
public class POAcknowledgmentService {

    @Autowired
    private PurchaseOrderAcknowledgmentDAO purchaseOrderAcknowledgmentDAO;

    // Save a new Purchase Order Acknowledgment
    public int savePurchaseOrderAcknowledgment(POAcknowledgement acknowledgment) {
        String Poacknowledgmentuid = generatePoacknowledgmentUId();
        acknowledgment.setPoacknowledgmentuid(Poacknowledgmentuid);
        return purchaseOrderAcknowledgmentDAO.addPOAcknowledgment(acknowledgment);
    }


    
    // Retrieve all Physical Counts
    public List<Map<String, Object>> getAllPOAcknowledgments() {
        return purchaseOrderAcknowledgmentDAO.getAllPOAcknowledgments(); // Call DAO to fetch all physical counts
    }

    // Retrieve a Physical Count by its ID
    public POAcknowledgement getPOAcknowledgmentById(Long id) {
        return purchaseOrderAcknowledgmentDAO.getPOAcknowledgmentById(id); // Call DAO to fetch physical count by ID
    }   
    
    // Update an existing Purchase Order Acknowledgment
    public int updateAcknowledgment(POAcknowledgement acknowledgment) {
        return purchaseOrderAcknowledgmentDAO.updateAcknowledgment(acknowledgment);
    }
    
  

    // Delete a Purchase Order Acknowledgment by ID
    public int deleteAcknowledgment(Long id) {
        return purchaseOrderAcknowledgmentDAO.deleteAcknowledgment(id);
    }

  

	// Generate a unique Acknowledgment ID
    private String generatePoacknowledgmentUId() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder Poacknowledgmentuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	Poacknowledgmentuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "POACK" + Poacknowledgmentuid.toString();
    }
   
    
 // 🔹 Fetch material breakdown for given Purchase Order UID
    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUID(String rawmaterialpurchaseorderuid) {
        return purchaseOrderAcknowledgmentDAO.getRawMaterialsByPurchaseOrderUID(rawmaterialpurchaseorderuid);
    }

    
    
}


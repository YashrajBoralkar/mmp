package com.prog.service.qualitycontrol;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.InprocessQualityControlDao;
import com.prog.model.erp.InprocessQualityControl;

@Service
public class InprocessQualityControlService {

    @Autowired
    private InprocessQualityControlDao inprocessQualityControlDao;

    /**
     * Saves a new InprocessQualityControl record to the database.
     * A unique UID is generated and assigned before saving.
     *
     * @param control The InprocessQualityControl object to save.
     * @return Number of rows affected in the database.
     */
    public int saveProcessQualityControl(InprocessQualityControl control) {
        String qualitycontrol = generatequalitycontrolUid();
        control.setInprocessqualitycontroluid(qualitycontrol);
        return inprocessQualityControlDao.saveProcessQualityControlForm(control);
    }

    /**
     * Generates a unique UID for in-process quality control records.
     *
     * @return A UID string prefixed with "IPQC" and followed by a 4-digit number.
     */
    private String generatequalitycontrolUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder processqualitycontroluid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            processqualitycontroluid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "IPQC" + processqualitycontroluid.toString();
    }

    /**
     * Retrieves all in-process quality control records.
     *
     * @return A list of maps containing quality control data.
     */
    public List<Map<String, Object>> getAllProcessQualityControls() {
        return inprocessQualityControlDao.fetchAllQualityControl();
    }

    /**
     * Deletes a specific InprocessQualityControl record by ID.
     *
     * @param id The ID of the record to delete.
     */
    public void deleteProcessQualityControl(Long id) {
        inprocessQualityControlDao.deleteQualityControlbyId(id);
    }

    /**
     * Retrieves a specific InprocessQualityControl record by ID.
     *
     * @param id The ID of the record to retrieve.
     * @return The InprocessQualityControl object corresponding to the ID.
     */
    public InprocessQualityControl getProcessQualityControlById(Long id) {
        return inprocessQualityControlDao.getQualityControlbyId(id);
    }

    /**
     * Updates an existing InprocessQualityControl record.
     *
     * @param control The updated InprocessQualityControl object.
     */
    public void updateProcessQualityControl(InprocessQualityControl control) {
        inprocessQualityControlDao.updateQualityControl(control);
    }

    // ---------------------- FETCHING METHODS ------------------------

    /**
     * Fetches the product name corresponding to a given product UID.
     *
     * @param productuid The UID of the product.
     * @return The product name.
     */
    public String getProductNameByUid(String productuid) {
        return inprocessQualityControlDao.getProductNameByUid(productuid);
    }

    /**
     * Retrieves all product UIDs from the database.
     *
     * @return A list of product UIDs.
     */
    public List<String> getItemid() {
        return inprocessQualityControlDao.getItemId();
    }

    /**
     * Retrieves a list of approved FAI (First Article Inspection) UIDs
     * corresponding to a given product UID.
     *
     * @param productuid The UID of the product.
     * @return A list of approved FAI UIDs.
     */
    public List<String> getApprovedFAIUIDsByProductUid(String productuid) {
        return inprocessQualityControlDao.getApprovedFAIUIDsByProductUid(productuid);
    }

    /**
     * Retrieves the work order UID, production order UID, and planned quantity
     * based on a given FAI UID.
     *
     * @param firstarticleinspectionuid The UID of the FAI.
     * @return A map containing workorderuid, productionorderuid, and plannedquantity.
     */
    public Map<String, String> getOrderDetailsByFAIUid(String firstarticleinspectionuid) {
        return inprocessQualityControlDao.getOrderDetailsByFAIUid(firstarticleinspectionuid);
    }
}

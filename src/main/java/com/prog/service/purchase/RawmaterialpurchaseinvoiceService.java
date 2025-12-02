package com.prog.service.purchase;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.RawmaterialpurchaseinvoiceDao;
import com.prog.model.erp.Rawmaterialpurchaseinvoice;
import com.prog.model.erp.RawMaterialReceiptNote;


@Service
public class RawmaterialpurchaseinvoiceService {

    @Autowired
    private RawmaterialpurchaseinvoiceDao rawmaterialpurchaseinvoicedao;
    
  

    /*
     * Saves a new raw material purchase invoice with a generated unique UID.
     */
    public void saveInvoice(Rawmaterialpurchaseinvoice invoice) {
        String uid = generateRawMaterialPurchaseInvoiceUid();
        invoice.setRawmaterialpurchaseinvoiceuid(uid);
        rawmaterialpurchaseinvoicedao.saveInvoice(invoice);
    }

    /*
     * Generates a random 6-digit UID prefixed with "RPI" for purchase invoice.
     */
    public String generateRawMaterialPurchaseInvoiceUid() {
        int length = 4;
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder uidBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            uidBuilder.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "RMPI" + uidBuilder.toString();
    }

    /*
     * Fetches all raw material purchase invoices from the database.
     */
    public List<Map<String, Object>> findAll() {
        return rawmaterialpurchaseinvoicedao.getAllInvoices();
    }

    /*
     * Deletes a raw material invoice by its primary key ID.
     */
    public void deleteById(Long id) {
        rawmaterialpurchaseinvoicedao.deleteById(id);
    }
    
    /*
     * Retrieves a raw material purchase invoice by ID for editing/viewing.
     */
    public Rawmaterialpurchaseinvoice getById(Long id) {
        return rawmaterialpurchaseinvoicedao.getById(id);
    }
    
    /*
     * Updates an existing raw material purchase invoice in the database.
     */
    public void update(Rawmaterialpurchaseinvoice invoice) {
    	rawmaterialpurchaseinvoicedao.update(invoice);
    }
    
    /*
     * Finds and returns related purchase order details (e.g., supplier, quantity, etc.)
     * for the given purchase order UID. Used in AJAX.
     */
    public Map<String, String> findOrderDetailsByUid(String uid) {
        return rawmaterialpurchaseinvoicedao.findOrderDetailsByUid(uid);
    }


    /*
     * Retrieves all available raw material purchase order UIDs
     * to populate the dropdown in the invoice form.
     */
    public List<String>getAllRawmaterialpurchaseorderUid(){
    	return rawmaterialpurchaseinvoicedao.getAllRawmaterialpurchaseorderUid();
    }
    
    public String getPurchaseOrderUidByRawMaterialUid(String rawMaterialUid) {
        return rawmaterialpurchaseinvoicedao.findPurchaseOrderUidByRawMaterialUid(rawMaterialUid);
    }
    
   
 // To get GRN UID dropdown list
    public List<String> getAllRawmaterialgrnUids() {
        return rawmaterialpurchaseinvoicedao.getAllRawmaterialgrnUids();
    }
    
   /* public List<String> getAllRawmaterialgrnUids() {
        List<String> grns = rawmaterialpurchaseinvoicedao.getAllRawmaterialgrnUids();
        System.out.println("Service: Fetched GRN UIDs => " + grns);
        return grns;
    }*/


    // To get materials for selected GRN UID
    public List<Map<String, Object>> getRawMaterialsByGrnUid(String uid) {
        return rawmaterialpurchaseinvoicedao.getRawMaterialsByGrnUid(uid);
    }

   

    public List<Map<String, String>> getAllEmployees() {
        return rawmaterialpurchaseinvoicedao.getAllEmployees();
    }
 // 🔹 Fetch material breakdown for given Purchase Order UID
    public List<Map<String, Object>> getRawMaterialsByReceiptNoteUid(String rawmaterialreceiptnoteuid) {
        return rawmaterialpurchaseinvoicedao.getRawMaterialsByReceiptNoteUid(rawmaterialreceiptnoteuid);
    }



}

package com.prog.service.inventory;

import java.util.List;


import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.PurchaseOrderDAO;
import com.prog.model.erp.PurchaseOrder;

@Service
public class PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderDAO purchaseorderdao;
	
	public int savePurchaseOrder(PurchaseOrder purchaseorder) {
		String pouid = generatePouid();
		purchaseorder.setPouid(pouid);
		return purchaseorderdao.addPurchaseOrder(purchaseorder);
	}
	private String generatePouid() {
        int length =4; // Length of the Pouid (example: 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder pouid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	pouid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "PO" + pouid.toString();
    }
	
	public List<Map<String,Object>>getPurchaseOrder(){
		return purchaseorderdao.showthefindall();
		
	}
	
	public void deleteorders(Long id) {
		purchaseorderdao.deleteorders(id);
	}
	
	public PurchaseOrder getPurchaseOrderbyid(Long id) {
		return purchaseorderdao.getPurchaseOrderbyid(id);
	}
	
	public void updatePurchaseOrder(PurchaseOrder purchaseorder) 
	{
		purchaseorderdao.updatePurchaseOrder(purchaseorder);
	}

	
	   //FETCHING FOR SUPPLIER INVOICE SUBMISSION FORM
	
		public List<String> getAllPurchaseOrderDetailsDataINSupplier() {
			return purchaseorderdao.getAllSupplierPurchaseOrderDetailsData();
		}
		
		public PurchaseOrder getPurchaseOrderDetailsInSupplierById(String pouid) {
			return purchaseorderdao.getSupplierPurchaseOrderDetailsById(pouid);
		}

		//FETCHING FOR POACKNOWLEDGEMENT FORM
		public List<String> fetchPoUIds() {
	        return purchaseorderdao.fetchPoUIds();
	    }
	    
	    
	    public List<Map<String, Object>> getDataByPoUid(String pouid) {
	        return purchaseorderdao.getDataByPoUid(pouid);
	    }
	
	    
//FETCHING IN GOODS RECEPIT NOTE FORM
	    
	    public List<String> getAllPurchaseUidsIngoodsReceiptnote() {
		     return purchaseorderdao.getAllPurchaseUidsIngoodsReceiptnote();
		 }

	 
}

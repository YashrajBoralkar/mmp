package com.prog.service.supplier;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.supplier.SupplierInvoiceSubmissionDao;
import com.prog.model.erp.SupplierInvoiceSubmission;


@Service
public class SupplierInvoiceSubmissionService {
	@Autowired
	private SupplierInvoiceSubmissionDao supplierInvoiceSubmissionDao;
	
	
	public int addSupplierInvoiceSubmission(SupplierInvoiceSubmission sis) {
		String sisUID=generateInvoiceUID();
		sis.setSupplierinvoiceuid(sisUID);
		return supplierInvoiceSubmissionDao.addSupplierInvoiceSubmission(sis);
	}
	
	public SupplierInvoiceSubmission getSupplierInvoiceSubmissionById(Long id) {
		return supplierInvoiceSubmissionDao.getSupplierInvoiceSubmissionById(id);
	}
	
	public List<Map<String,Object>> fetchSupplierInvoiceSubmission(){
		return supplierInvoiceSubmissionDao.fetchSupplierInvoiceSubmissionData();
	}
	
	public int updateSupplierInvoiceSubmission(SupplierInvoiceSubmission sis) {
		return supplierInvoiceSubmissionDao.updateSupplierInvoiceSubmission(sis);
	}
	public void deleteSupplierInvoiceSubmissionById(long id) {
		supplierInvoiceSubmissionDao.deleteSupplierInvoiceSubmission(id);
	}
	

	 public List<Map<String, String>> getApprovalAuthorities() {
	        return supplierInvoiceSubmissionDao.getApprovalAuthorities();
	    }
	 
	 

	public List<String> getAllPurchaseOrderDetailsDataINSupplier() {
	    return supplierInvoiceSubmissionDao.getAllPurchaseOrderDetailsDataINSupplier();
	}

	
	public Map<String, Object> getPODataByUid(String rawmaterialpurchaseorderuid) {
        return supplierInvoiceSubmissionDao.getRawMaterialPOData(rawmaterialpurchaseorderuid);
    }
	
	private String generateInvoiceUID() {
		int length=4;
		String characters="1234567890";
		Random random=new Random();
		StringBuilder sisUID=new StringBuilder(length);
		//Generate random characters for the sisUId
				for(int i=0;i<length;i++) {
					sisUID.append(characters.charAt(random.nextInt(characters.length())));
				}
				return "SIS"+sisUID.toString();
	}
	
	
	
	
}

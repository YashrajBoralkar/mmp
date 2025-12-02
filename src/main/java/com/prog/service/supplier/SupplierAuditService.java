package com.prog.service.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.supplier.SupplierAuditDao;
import com.prog.model.erp.SupplierAudit;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SupplierAuditService {

    @Autowired
    private SupplierAuditDao supplierAuditDao;

    
    public int saveSupplierAudit(SupplierAudit audit) {
		String saudUID=generateAudituid();
		audit.setSupplieraudituid(saudUID);
		return supplierAuditDao.saveSupplierAudit(audit);
	}

    
    public int updateSupplierAudit(SupplierAudit audit) {
		return supplierAuditDao.updateSupplierAudit(audit);
	}
    
    public void deleteAuditById(Long id) {
        supplierAuditDao.deleteSupplierAuditById(id);
    }

    public List<SupplierAudit> getAllSupplierAudits() {
        return supplierAuditDao.getAllSupplierAudits();
    }

    public SupplierAudit getSupplierAuditById(Long id) {
        return supplierAuditDao.getSupplierAuditById(id);
    }


    private String generateAudituid() {
    	 int length = 4; 
         String characters = "0123456789";
         Random random = new Random();
         StringBuilder saudUID = new StringBuilder(length);

         for (int i = 0; i < length; i++) {
        	 saudUID.append(characters.charAt(random.nextInt(characters.length())));
         }
         return "SAU" + saudUID.toString();
     }



    public List<Map<String, Object>> getAuditWithSupplierDetails() {
     return supplierAuditDao.getAuditWithSupplierDetails();
}


	
}
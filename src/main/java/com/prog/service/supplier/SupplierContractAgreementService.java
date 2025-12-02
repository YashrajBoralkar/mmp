package com.prog.service.supplier;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.supplier.SupplierContractAgreementDao;
import com.prog.model.erp.SupplierContractAgreement;

@Service
public class SupplierContractAgreementService {

    @Autowired
    private SupplierContractAgreementDao supplierContractAgreementDao;

    public int saveSupplierContract(SupplierContractAgreement contract) {
        String suppliercontractagreementuid = generateContractId();
        contract.setSuppliercontractagreementuid(suppliercontractagreementuid);
        return supplierContractAgreementDao.saveSupplierContract(contract);
    }

    private String generateContractId() {
        int length = 4; // Length of the contract ID (example: 8 characters)
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder suppliercontractagreementuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	suppliercontractagreementuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "SCA" + suppliercontractagreementuid.toString();
    }

    public List<Map<String, Object>> getAllSupplierContractAgreement() {
        return supplierContractAgreementDao.fetchAllSupplierContractAgreement();
    }

    public void deleteSupplierContract(Long id) {
        supplierContractAgreementDao.deleteSupplierContractById(id);
    }

    public SupplierContractAgreement getSupplierContractById(Long id) {
        return supplierContractAgreementDao.getSupplierContractById(id);
    }

    public void updateSupplierContract(SupplierContractAgreement contract) {
        supplierContractAgreementDao.updateSupplierContract(contract);
    }
    

	
	    
//	  //COMBINE METHOD
//		  public List<String> fetchRawMaterialSupplierUids() {
//		   return supplierContractAgreementDao.fetchRawMaterialSupplierUIds();
//		    }
//		
//		   public List<Map<String, Object>> getDataBySupplierUid(String rawmaterialsupplieruid) {
//		    return supplierContractAgreementDao.getDataBySupplieruid(rawmaterialsupplieruid);
//		    }

    
}
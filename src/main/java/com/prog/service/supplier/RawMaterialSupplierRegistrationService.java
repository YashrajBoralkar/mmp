package com.prog.service.supplier;

import org.springframework.stereotype.Service;

import com.prog.Dao.supplier.RawMaterailSupplierRegistrationDao;
import com.prog.model.erp.RawMaterialSupplierRegistration;
import com.prog.model.erp.RawmaterialInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class RawMaterialSupplierRegistrationService {

    private final RawMaterailSupplierRegistrationDao supplierDao;

    public RawMaterialSupplierRegistrationService(RawMaterailSupplierRegistrationDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    // Save supplier with UID generation
    public int saveSupplier(RawMaterialSupplierRegistration supplier) {
        if (supplier.getRawmaterialsupplieruid() == null || supplier.getRawmaterialsupplieruid().trim().isEmpty()) {
            supplier.setRawmaterialsupplieruid(generateSupplierUid());
        }
        return supplierDao.saveSupplier(supplier);
    }

    // Update existing supplier
    public int updateSupplier(RawMaterialSupplierRegistration supplier) {
        return supplierDao.updateSupplier(supplier);
    }

    // Delete supplier by ID
    public int deleteSupplierByid(Long id) {
        return supplierDao.deleteSupplierByid(id);
    }

    // Get supplier by ID
    public RawMaterialSupplierRegistration getSupplierByid(Long id) {
        return supplierDao.getSupplierByid(id);
    }

    // Get all suppliers
    public List<Map<String, Object>> getAllSuppliers() {
        return supplierDao.getAllSupplierdata();
    }

    // Get material details by UIDs
    public List<RawmaterialInfo> getRawMaterialsByUids(List<String> uids) {
        return supplierDao.findByUids(uids);
    }

    // Generate random supplier UID
    private String generateSupplierUid() {
    	 int length = 4; // Length of the contract ID (example: 8 characters)
         String characters = "0123456789";
         Random random = new Random();
         StringBuilder saudUID = new StringBuilder(length);

         for (int i = 0; i < length; i++) {
        	 saudUID.append(characters.charAt(random.nextInt(characters.length())));
         }
         return "RMSPU" + saudUID.toString();
     }
    

    // Get all raw materials
    public List<RawmaterialInfo> getAllRawMaterials() {
        return supplierDao.findAllRawMaterials();
    }
    
    public List<String> getallsupplieruids() {
	     return supplierDao.getAllSupplieruids();
	 }

	public List<Map<String, Object>>getSupplierDetailsByUids(String rawmaterialsupplieruid) {
	      return supplierDao.getSupplierDetailsBySupplieruid(rawmaterialsupplieruid);
		}
	
	public List<String> findRawMaterialNamesBySupplierUid(String rawmaterialsupplieruid) {
	    return supplierDao.findrawMaterialNamesBySupplierUid(rawmaterialsupplieruid);
	}
}


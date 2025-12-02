package com.prog.service.hrms;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.EmployeeBatchCreationDao;
import com.prog.model.erp.EmployeeBatchCreation;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EmployeeBatchCreationService {

    @Autowired
    private EmployeeBatchCreationDao batchDao;

    public int saveBatch(EmployeeBatchCreation empbatch) {
    	String batchUID=generateBatchUid();
    	empbatch.setEmpbatchuid(batchUID);
        return batchDao.addBatch(empbatch);
    }

    public int updateBatch(EmployeeBatchCreation empbatch) {
        return batchDao.updateBatch(empbatch);
    }

    public int deleteBatchById(Long id) {
        return batchDao.deleteById(id);
    }

    public List<Map<String, Object>> getAllBatches() {
        return batchDao.findAllBatches();
    }

    public EmployeeBatchCreation getBatchById(Long id) {
        return batchDao.findById(id);
    }

    public List<Map<String, Object>> getAllDepartments() {
        return batchDao.findAllDepartments();
    }

    public List<Map<String, Object>> getEmployeesByDepartment(String departmentuid) {
        return batchDao.findEmployeesByDepartment(departmentuid);
    }

    public Map<String, Object> findDepartmentDetails(String departmentuid) {
        return batchDao.findDepartmentDetails(departmentuid);
    }

    private String generateBatchUid() {
    	int length = 4;  // Length of the atduId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder batchuid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	    	batchuid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "EBC" + batchuid.toString();
	}
    
    public int syncEmployeeDepartmentNames() {
        return batchDao.updateAllEmployeeDepartmentNames();
    }

}

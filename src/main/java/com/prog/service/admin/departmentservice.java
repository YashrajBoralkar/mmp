package com.prog.service.admin;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prog.Dao.admin.departmentdao;
import com.prog.model.erp.Appraisal;
import com.prog.model.erp.Department;
import com.prog.model.erp.Employee;
@Service
public class departmentservice {
	

    @Autowired
    private departmentdao departmentDao;

    public  int saveDepartment(Department department) {
    	String DEPUID=generateAppraisaluId();
    	department.setDepartmentuid(DEPUID);
    	return departmentDao.saveDepartment(department);
    }
    private String generateAppraisaluId() {
	    int length = 4;  
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder depuid = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	    	depuid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "DEP" + depuid.toString();
	}
    //show list of department
    public Department getAllEmployees() {
		 return departmentDao.getDepartment();
	}
    
    // Method to delete a Department by ID
    public void deleteDepartmentById(Long id) {
    	departmentDao.deleteById(id);  

	}
    // get department by Id	
	public Department getDepartmentById(Long id) {
		return departmentDao.getDepartmentById(id);
	}
    
    // update department by ID
    public void updateDepartment(Department department) {
        departmentDao.updateDepartment(department);
    }
    
    public List<String> getAlldepartment(){
		return departmentDao.getalldepartment();
	}

    
    
    
    
}

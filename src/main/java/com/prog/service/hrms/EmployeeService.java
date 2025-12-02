package com.prog.service.hrms;

import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.EmployeeDAO;
import com.prog.model.erp.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

	public int saveEmployee(Employee employee) {
		String empUID=generateEmployeeuId();
		employee.setEmployeeUID(empUID);
		 System.out.println("Generated UID: " + empUID);
		return employeeDAO.addEmployee(employee);
		
	}
	
	public List<Employee> getAllEmployees() {
		 return employeeDAO.getAllEmployees();
	}
   
	
	public void deleteEmployee(Long id) {
	    employeeDAO.deleteEmployee(id);
	}

	private String generateEmployeeuId() {
	    int length = 4;  // Length of the PuId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder empuid = new StringBuilder(length);

	    // Generate random characters for the PuId
	    for (int i = 0; i < length; i++) {
	        empuid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "EMP" + empuid.toString();
	}

	public Employee getEmployeeById(Long id) {
		return employeeDAO.getEmployeeById(id);	
	}

	public void updateEmployee(Employee employee) {
		 employeeDAO.updateEmployee(employee);
		
	}
	
	public List<Employee> getBasicEmployeeDetails() {
	    return employeeDAO.getBasicEmployeeDetails();
	}

	
	//FETCHING
		public List<String> getAlleDetails(){
			return employeeDAO.getallemplyeuids();
		}
		
     //FETCHING
		public Employee getEdetailsByuid(String employeeuid) {
			return employeeDAO.getEmployeedetailsByuid(employeeuid);
			
		}
	

}

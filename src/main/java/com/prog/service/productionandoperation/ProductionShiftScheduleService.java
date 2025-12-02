package com.prog.service.productionandoperation;

import java.util.List;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.ProductionShiftScheduleDao;
import com.prog.model.erp.ProductionShiftSchedule;

@Service
public class ProductionShiftScheduleService {
	@Autowired
	private ProductionShiftScheduleDao productionShiftScheduleDao;
	 
	 public int saveShiftSchedule(ProductionShiftSchedule Schedule) {
	        String scheduleUid = generateScheduleUid();
	        Schedule.setProductionscheduleuid(scheduleUid);
	        return productionShiftScheduleDao.addShiftSchedule(Schedule); // Call DAO to save customer quotation
	    }
	   
	    public List<Map<String, Object>> getAllShiftSchedules() {
	        return productionShiftScheduleDao.getAllShiftSchedules(); // Call DAO to fetch all customer quotations
	    }
	    
	    public int deleteShiftSchedule(Long id) {
	        return productionShiftScheduleDao.deleteShiftSchedule(id); // Call DAO to delete customer quotation by ID
	    }
	   
	    public ProductionShiftSchedule getShiftScheduleById(Long id) {
	        return productionShiftScheduleDao.getShiftScheduleById(id); // Call DAO to fetch physical count by ID
	    }  
	    
	 // Update a Customer Quotation
	    public int updateShiftSchedule(ProductionShiftSchedule shift) {
	        return productionShiftScheduleDao.updateShiftSchedule(shift); // Call DAO to update customer quotation
	    }
	  private String generateScheduleUid() {
	        int length = 4;
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder scheduleUid = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	        	scheduleUid.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "PSS" + scheduleUid.toString();
	    }

	
	// Retrieve product details by product UID
//    public List<Map<String, Object>> getDataByEmployeeUid(String employeeuid) {
//        return productionShiftScheduleDao.getDataByEmployeeUid(employeeuid);
//    }

	 
	  public List<String> getBatchNameByEmpBatchUid(String empbatchuid) {
		    return productionShiftScheduleDao.fetchBatchNameByEmpBatchUid(empbatchuid);
		}
	  

	  
    // Retrieve all product UIDs
    public List<String> fetchEmployeeBatchUIds() {
        return productionShiftScheduleDao.fetchEmployeeBatchUIds();
    }
  //Data fetching
    public List<Map<String, Object>> getDataByMultipleEmployeeUids(List<String> employeeUIDs) {
        return productionShiftScheduleDao.getDataByEmployeeUid(employeeUIDs);
    }
    public List<String> getProductionPlanningUID(){
		return productionShiftScheduleDao.getProductionPlanningUID();
	}
    public List<Map<String,Object>> getProductDetalisByProductionPlanningUID(String productionplanninguid){
		return productionShiftScheduleDao.getProductDetalisByProductionPlanningUID(productionplanninguid);
	}

//	public List<String> fetchEmployeeUIds(String employeeuid) {
//        return productionShiftScheduleDao.fetchEmployeeUIds(employeeuid);
//	}

	public List<Map<String, Object>> fetchEmployeeUIds() {
        return productionShiftScheduleDao.fetchEmployeeUIds();

	}

	// ✅ In ProductionShiftScheduleService.java
	public List<Map<String, Object>> getFullNameByEmployeeUid(String employeeuid) {
	    return productionShiftScheduleDao.getFullNameByEmployeeUid(employeeuid);
	}
	


	
//	 public List<Map<String, String>> getEmployeeNames() {
//	        return productionShiftScheduleDao.getEmployeeNames();
//	    }
//
//	public List<String> getFullNameByEmployeeUid(String employeeuid) {
//	    return productionShiftScheduleDao.getFullNameByEmployeeUid(employeeuid);
	}


	 
	




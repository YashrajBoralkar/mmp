package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.MaintenanceRequestInternalTicketingDao;
import com.prog.model.erp.Employee;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceRequestInternalTicketing;

@Service
public class MaintenanceRequestInternalTicketingService {

	@Autowired
	private MaintenanceRequestInternalTicketingDao maintenanceRequestInternalTicketingDao;
	
	/**
	 * Saves a Maintenance request object after generating a Maintenance request UID.
	 */
	public int savemaintenance(MaintenanceRequestInternalTicketing mr) 
	{
		String maintenanceuid=generatemaintenanceUID();
		mr.setMaintenancerequestinternalticketinguid(maintenanceuid);
		
		return maintenanceRequestInternalTicketingDao.savemaintencerequest(mr);

	}
	

	public List<Map<String,Object>> showMaintenanceRequestInternalTicketinglist()
	{
		return maintenanceRequestInternalTicketingDao.showMaintenanceRequestInternalTicketinglist();
	}
	
	/**
	 * Retrieves a single MaintenanceRequestForm object by its ID.
	 */
	public MaintenanceRequestInternalTicketing getMaintenanceRequestInternalTicketingById(Long id) 
	{
		return maintenanceRequestInternalTicketingDao.getMaintenanceRequestInternalTicketingById(id);

	}
	
	/**
	 * Updates an existing MaintenanceRequestForm record in the database.
	 */
	public void updateMaintenanceRequestInternalTicketing(MaintenanceRequestInternalTicketing mr) 
	{
		maintenanceRequestInternalTicketingDao.updateMaintenanceRequestInternalTicketing(mr);
		
	}

	/**
	 * Delete an existing MaintenanceRequestForm record in the database.
	 */
	public int deleteMaintenanceRequestInternalTicketing(Long id) 
	{
		return maintenanceRequestInternalTicketingDao.deleteMaintenanceRequestInternalTicketing(id);
		
	}
	
	/**
	 * Generates a random maintenance request UID starting with "MR" followed by a 3-digit number.
	 */
	private String generatemaintenanceUID() 
	{
		int length=4;
		String characters="1234567890";
		Random random=new Random();
		StringBuilder mr=new StringBuilder(length);
		
		for(int i=0;i<length;i++) 
		{
			mr.append(characters.charAt(random.nextInt(characters.length())));

		}
		return "MRIT"+mr.toString();
		
	}
	
	public List<String> getDepartmentName(){
		return maintenanceRequestInternalTicketingDao.getDepartmentName();
	}
	
	public List<String> getEquipmentDetailsById(){
		return maintenanceRequestInternalTicketingDao.getEquipmentDetailsById();
	}
	
	public EquipmentMaster getEquipmentDetails(String equipmentuid) {
		return maintenanceRequestInternalTicketingDao.getEquipmentDetails(equipmentuid);
	}
	
	public List<String> getEmployeeDetailsById(){
		return maintenanceRequestInternalTicketingDao.getEmployeeDetailsById();
	}
	
	public Employee getEmployeeDetails(String employeeuid) {
		return maintenanceRequestInternalTicketingDao.getEmployeeDetails(employeeuid);
	}
}

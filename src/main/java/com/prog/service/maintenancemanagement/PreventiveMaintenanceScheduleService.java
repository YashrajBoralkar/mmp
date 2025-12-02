package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.PreventiveMaintenanceScheduleDao;
import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.PreventiveMaintenanceSchedule;

@Service
public class PreventiveMaintenanceScheduleService {
	@Autowired
	private PreventiveMaintenanceScheduleDao preventiveMaintenanceScheduleDao;
	
	 public int savePreventiveSchedule(PreventiveMaintenanceSchedule pmsf) {
	        String preventivemaintenancescheduleuid = generateSchedulemaitenanceuid();
	        pmsf.setPreventivemaintenancescheduleuid(preventivemaintenancescheduleuid);
	        return preventiveMaintenanceScheduleDao.savePreventivemaintenanceschedule(pmsf);
	    }

	 public List<Map<String,Object>> getAllPreventiveSchedules() {
	        return preventiveMaintenanceScheduleDao.getAllPreventiveSchedules();
	    }

	    public PreventiveMaintenanceSchedule getPreventiveScheduleById(long id) {
	        return preventiveMaintenanceScheduleDao.getPreventiveScheduleById(id);
	    }

	    public int updatePreventiveSchedule(PreventiveMaintenanceSchedule preventive) {
	        return preventiveMaintenanceScheduleDao.updatePreventiveSchedule(preventive);
	    }

	    public int deletePreventiveSchedule(long id) {
	        return preventiveMaintenanceScheduleDao.deletePreventiveSchedule(id);
	    }


	 
	 private String generateSchedulemaitenanceuid(){
	        Random random = new Random();
	        int number = 1000 + random.nextInt(9000);
	        return "PMSF" + number;
	    }

	
	public List<String> getEquipmentMasteruid(){
		return preventiveMaintenanceScheduleDao.getEquipmentMasteruid();
	}
	
	public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
		return preventiveMaintenanceScheduleDao.getEquipmentMasterDetails(equipmentmasteruid);
	}
}

package com.prog.service.productionandoperation;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.productionandoperation.MaterialRequirementPlanningDao;
import com.prog.model.erp.MaterialRequirementPlanning;




@Service
public class MaterialRequirementPlanningService {
	@Autowired
	private MaterialRequirementPlanningDao materialRequirementPlanningDao;
	
	public int addMRPData(MaterialRequirementPlanning mrp) {
		String mrpUID=generateMRPUId();
		mrp.setMaterialrequirementplanninguid(mrpUID);
		return materialRequirementPlanningDao.addMRPData(mrp);
	}
	
	public MaterialRequirementPlanning getMRPDataById(Long id) {
		return materialRequirementPlanningDao.getMRPFormDataById(id);
	}
	public List<Map<String,Object>> getAllMRPFData(){
		return materialRequirementPlanningDao.getAllMRPData();
	}
	public int updateMRPFData(MaterialRequirementPlanning mrp) {
		return materialRequirementPlanningDao.updateMRPFData(mrp);
	}
	public void deleteMrpDataById(Long id) {
		materialRequirementPlanningDao.deleteMRPDataById(id);
	}
	// UID Generate
	private String generateMRPUId() {
		int length = 4;  // Length of the PuId (for example 8 characters)
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder mrpuid = new StringBuilder(length);

			// Generate random characters for the stiUId
		 	for (int i = 0; i < length; i++) {
		 		mrpuid.append(characters.charAt(random.nextInt(characters.length())));
		 	}

		return "MRP" + mrpuid.toString();
	}
	//Data fetching
	public List<String> getProductionPlanningUID(){
		return materialRequirementPlanningDao.getProductionPlanningUID();
	}
	public List<Map<String,Object>> getProductDetalisByProductionPlanningUID(String productionplanninguid){
		return materialRequirementPlanningDao.getProductDetalisByProductionPlanningUID(productionplanninguid);
	}
	

}

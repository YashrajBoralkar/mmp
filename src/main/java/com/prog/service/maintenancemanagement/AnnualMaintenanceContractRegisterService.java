package com.prog.service.maintenancemanagement;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.maintenancemanagement.AnnualMaintenanceContractRegisterDao;
import com.prog.model.erp.AnnualMaintenanceContractRegister;
import com.prog.model.erp.EquipmentMaster;
 
@Service
public class AnnualMaintenanceContractRegisterService {

	@Autowired
	private AnnualMaintenanceContractRegisterDao annualMaintenanceContractRegisterDao;
	
	// This method is used to save a new annual maintenance contract
		public void addAnnualMaintenanceContractRegisterData(AnnualMaintenanceContractRegister annualmaintenancecontract) {
			String Annualmaintenancecontractuid = generatecontractuid();
			annualmaintenancecontract.setAnnualmaintenancecontractuid(Annualmaintenancecontractuid);
			annualMaintenanceContractRegisterDao.addAnnualMaintenanceContractRegisterData(annualmaintenancecontract);
		}
		
		public AnnualMaintenanceContractRegister getAnnualMaintenanceContractRegisterById(Long id) {
			return annualMaintenanceContractRegisterDao.getAnnualMaintenanceContractRegisterById(id);
		}
		
		public List<Map<String, Object>> getAnnualMaintenanceContractRegisterData(){
			return annualMaintenanceContractRegisterDao.getAnnualMaintenanceContractRegisterData();
		}
		
		public int updategetAnnualMaintenanceContractRegister(AnnualMaintenanceContractRegister amc) {
			return annualMaintenanceContractRegisterDao.updategetAnnualMaintenanceContractRegister(amc);
		}
		
		public void deleteAnnualMaintenanceContractRegisterById(Long id) {
			annualMaintenanceContractRegisterDao.deleteAnnualMaintenanceContractRegisterById(id);
		}
		
		//it help in auto-generating the contractUid with prefix as AMC
			private String generatecontractuid() {
			int length = 4;
			// Length of the contract ID (example: 8 characters)
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder contractId = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            contractId.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "AMC" + contractId.toString();
		}

			public List<String> getEquipmentDetailsById(){
				return annualMaintenanceContractRegisterDao.getEquipmentDetailsById();
			}
			
			public EquipmentMaster getEquipmentDetails(String equipmentuid) {
				return annualMaintenanceContractRegisterDao.getEquipmentDetails(equipmentuid);
			}
}

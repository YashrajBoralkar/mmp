package com.prog.service.purchase;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.EmergencyProcurementRequestDao;
import com.prog.model.erp.EmergencyProcurementRequest;

@Service
public class EmergencyProcurementRequestService 
{
	@Autowired
	private EmergencyProcurementRequestDao eprdao;

	
	  //Generates a unique Emergency Procurement Request UID prefixed with "EPR" and 3 random digits.
	 
	private String generateepruid() 
	{
		int length = 4;
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder eprfId = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			eprfId.append(characters.charAt(random.nextInt(characters.length())));
		}
		return "EPR" + eprfId.toString();
	}

//=========================================================================================================================================

	
	  //Saves the Emergency Procurement Request Form data to the database.
	 
	public int epr_save_backend(EmergencyProcurementRequest erpf) {
		String eprfId = generateepruid();
		erpf.setEmergencyprocurementrequestuid(eprfId);
		
		return eprdao.saveeprform(erpf);
	}

//=========================================================================================================================================

	
	  //Returns a list of all submitted Emergency Procurement Request Forms.
	 
	public List<Map<String, Object>> show_eprf_list()
	{
		return eprdao.show_epr_list();
	}

//=========================================================================================================================================
	 //Update the Emergency Procurement Request Form data to the database.
	 
		public int updateepr(EmergencyProcurementRequest erpf) 
		{
			return eprdao.updateeprbyid(erpf);
		}
	
//=========================================================================================================================================

		public EmergencyProcurementRequest geteeprbyid(Long id) 
		{
			return eprdao.select_uid_dao(id);
		}
		
//=========================================================================================================================================

		public void deleteeprf(Long id) {
			eprdao.deleteeprf(id);
		}
}

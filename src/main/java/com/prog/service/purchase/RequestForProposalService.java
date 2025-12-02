package com.prog.service.purchase;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.RequestForProposalDao;
import com.prog.model.erp.RequestForProposal;


@Service
public class RequestForProposalService {
	@Autowired
	private RequestForProposalDao requestForProposalDao;
	
	public int addRFPFormData(RequestForProposal rfp) {
		String rfpUID=generateRFPUId();
		rfp.setRequestforproposaluid(rfpUID);
		return requestForProposalDao.addDpfData(rfp);
	}
	
	public List<Map<String,Object>> getAllRFPFormData(){
		return requestForProposalDao.getAllrfpformData();
	}
	public RequestForProposal getRFPFormDataById(Long id) {
		return requestForProposalDao.getRFPFormDataById(id);
	}
	public int updataRFPFormData(RequestForProposal rfp) {
		return requestForProposalDao.updateRfpForm(rfp);
	}
	public void deleteRFPFormById(Long id) {
		requestForProposalDao.deleteRFPFormDataById(id);
	}
	// UID Generate
	 	private String generateRFPUId() {
	 	    int length = 4;  // Length of the PuId (for example 8 characters)
	 	    String characters = "1234567890";
	 	    Random random = new Random();
	 	    StringBuilder rfpuid = new StringBuilder(length);

	 	    // Generate random characters for the stiUId
	 	    for (int i = 0; i < length; i++) {
	 	    	rfpuid.append(characters.charAt(random.nextInt(characters.length())));
	 	    }

	 	     return "RFP" + rfpuid.toString();
	 	}
	
	
}

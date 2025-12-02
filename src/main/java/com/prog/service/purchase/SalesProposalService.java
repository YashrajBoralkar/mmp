package com.prog.service.purchase;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.SalesProposalDao;
import com.prog.model.erp.SalesPraposal;

@Service
public class SalesProposalService {
	
	
	@Autowired
	private SalesProposalDao spdao;
	
	public void save(SalesPraposal salesproposal) {
		String proposalUid = generateproposaluid();
		salesproposal.setSalesproposaluid(proposalUid);
		
		spdao.addsalesproposal(salesproposal);
	}
	
	
	public List<Map<String,Object>> fetchAllData(){
		return spdao.showsalesproposallist();
	}
	
		public void deleteById(Long id) {
			spdao.deleteById(id);
			
		}
		//update
		public SalesPraposal GetById(long id) {
			return spdao.getById(id);
		}
		
		public void update(SalesPraposal updatesp) {
	    	spdao.updatesalesproposal(updatesp);
	 }
		
		private String generateproposaluid() { //UID GENERATION CODE
			int length = 4 ; 
	        String characters = "0123456789";
	        Random random = new Random();
	        StringBuilder contractId = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            contractId.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return "SP" + contractId.toString();
		}
			


		//FETCHING CLIENT INFO IN CONTROLLER
		
		public List<String> getClientId() {
			return spdao.getClientId();
		}
		public List<Map<String, Object>> getclientDetailsByuid(String clientuid){
			return spdao.getclientDetailsByuidsss(clientuid);
		}
		
		

}

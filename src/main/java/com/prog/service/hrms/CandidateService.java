package com.prog.service.hrms;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.hrms.CandidateDAO;
import com.prog.model.erp.Candidate;
import com.prog.model.erp.Job;




@Service
public class CandidateService {
    @Autowired
    private CandidateDAO Candidatedao;
    
    // Save interview schedule
    public int saveCandidate(Candidate candidate) {
 	   String cnduid=generateCandidateuId();
 	  candidate.setCandidateuid(cnduid);
 		return Candidatedao.addCandidate(candidate); // Save to the database
    }


    
    public List<Map<String, Object>> getAllCandidate() {
    	return Candidatedao.getAllCandidate();
    	
    }

	public Candidate getCandidateById(Long id) {
		return Candidatedao.getCandidateById(id);
	}

	

	public int deleteCandidateById(Long id) {
		return Candidatedao.deleteCandidate(id);
	}

	public int updatecandidate(Candidate candidate) {
		return Candidatedao.updateCandidate(candidate);
		
	}
	private String generateCandidateuId() {
	    int length = 4;  // Length of the uId (for example 8 characters)
	    String characters = "1234567890";
	    Random random = new Random();
	    StringBuilder cnduid = new StringBuilder(length);

	    // Generate random characters for the uId
	    for (int i = 0; i < length; i++) {
	    	cnduid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	     return "CD" + cnduid.toString();
	}
	
	 public List<String> getAllcandidateDetails(){
			return Candidatedao.getallcandidateuids();
		}
		
		public Candidate getcandidatedetailsByuid(String candidateuid) {
			return Candidatedao.getCandidatedetailsByuid(candidateuid);
			
		}
	

	
	
}



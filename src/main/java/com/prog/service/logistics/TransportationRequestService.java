package com.prog.service.logistics;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.logistics.TransportationRequestDao;
import com.prog.model.erp.TransportationRequest;


@Service
public class TransportationRequestService {

    @Autowired
    private TransportationRequestDao transportationRequestDao;
    
    public int saveLogistics(TransportationRequest request) {
        String transportationRequestUid = generateTransportationrequestuid();
        request.setTransportationrequestuid(transportationRequestUid);
        return transportationRequestDao.saveLogistics(request);
    }

 

	public int updateLogistics(TransportationRequest request) {
        return transportationRequestDao.updateLogistics(request);
    }


    public List<Map<String, Object>> getAllRequest() {
        return transportationRequestDao.getAllRequest();
    }

    public TransportationRequest getRequestById(Long id) {
        return transportationRequestDao.getRequestById(id);
    }

    public void deleteRequestById(Long id) {
        transportationRequestDao.deleteRequestById(id);
    }
 
 
    
    private String generateTransportationrequestuid() {
		int length=4;
		String characters="1234567890";
		Random random=new Random();
		StringBuilder TRUID=new StringBuilder(length);
		//Generate random characters for the sisUId
				for(int i=0;i<length;i++) {
					TRUID.append(characters.charAt(random.nextInt(characters.length())));
				}
				return "TRF"+TRUID.toString();
	}
   

}

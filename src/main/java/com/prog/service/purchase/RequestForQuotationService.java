package com.prog.service.purchase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.purchase.RequestForQuotationDao;
import com.prog.model.erp.RequestforQuotation;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class RequestForQuotationService {

    @Autowired
    private RequestForQuotationDao rfqDao;

    // ✅ Save or Update RFQ
    public void saveRFQ(RequestforQuotation rfq) {
    	String rfqId = generateRFQuid();
    	rfq.setRequestforquotationuid(rfqId);
    	rfqDao.saveRFQ(rfq);
		  }

    // ✅ Get RFQ by ID
    public RequestforQuotation getRFQById(Long id) {
        return rfqDao.getRFQById(id);
    }

    // ✅ Get All RFQs
    public List<Map<String, Object>> getAllRFQs() {
        return rfqDao.getAllRFQs();
    }

    // ✅ Delete RFQ by ID
    public void deleteRFQ(Long id) {
        rfqDao.deleteRFQ(id);
    }
    public void updateRFQ(RequestforQuotation rfq) {
         rfqDao.updateRFQ(rfq);
    }
    
    private String generateRFQuid() { //UID GENERATION CODE
		int length = 4 ; 
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder rfqId = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	rfqId.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "RFQT" + rfqId.toString();
	}
		

}

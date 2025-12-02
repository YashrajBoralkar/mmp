package com.prog.service.purchase;


import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prog.Dao.purchase.RequestForInformationDao;
import com.prog.model.erp.RequestForInformation;


@Service
public class RequestForInformationService {

    @Autowired
    private RequestForInformationDao RequestForInformationDao;
    

    public int saveRFIForm(RequestForInformation rfiForm) {
    	 String rfiId = generaterfiuid();
    	 rfiForm.setRequestforinformationuid(rfiId);
        return RequestForInformationDao.saveRFIForm(rfiForm);
    }

    private String generaterfiuid() {
        int length = 4; // Length of the contract ID (example: 8 characters)
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder rfiId = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	rfiId.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "RFI" + rfiId.toString();
    }
   
    public List<Map<String, Object>> getAllRFIForms() {
        return RequestForInformationDao.fetchAllRFIForms();
    }

    public void deleteRFIForm(Long id) {
    	RequestForInformationDao.deleteRFIFormById(id);
    }

    public RequestForInformation getRFIFormById(Long id) {
        return RequestForInformationDao.getRFIFormById(id);
    }

    public void updateRFIForm(RequestForInformation rfiForm) {
    	RequestForInformationDao.updateRFIForm(rfiForm);
    }
    

}

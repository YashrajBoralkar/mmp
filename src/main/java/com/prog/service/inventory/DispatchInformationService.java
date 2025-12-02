package com.prog.service.inventory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.DispatchInformationDao;
import com.prog.model.erp.DispatchInformation;


@Service
public class DispatchInformationService {
	@Autowired 
	private DispatchInformationDao dispatchInformationDao ;
	
	public DispatchInformation getDispatchInformation(String carriername){
		return dispatchInformationDao.getDispatchInformationById(carriername);
	}
	
	public List<String> getAllDispatchInformation(){
		return dispatchInformationDao.getAllDispatchInformation();
	}
}

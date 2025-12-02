package com.prog.service.inventory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.ReturnDetailsDao;
import com.prog.model.erp.ReturnDetails;

@Service
public class ReturnDetailsService {
	@Autowired
	private ReturnDetailsDao returnDetailsDao;
	
	public List<String> getAllReturnsDetailsData(){
		return returnDetailsDao.getAllReturnDetailsData();
	}
	public ReturnDetails getReturnDetailsById(String returnuid) {
		return returnDetailsDao.getReturnDetailsById(returnuid);
	}
}

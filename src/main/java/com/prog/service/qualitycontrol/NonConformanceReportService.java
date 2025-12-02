package com.prog.service.qualitycontrol;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.qualitycontrol.NonConformanceReportDao;
import com.prog.model.erp.Nonconformancereport;


@Service
public class NonConformanceReportService {
	
	
	@Autowired
    private NonConformanceReportDao nonConformanceReportDao;

    public void saveNcr(Nonconformancereport nonconformancereport) {
    	String uid = genratencruid();
    	nonconformancereport.setNonconformancereportuid(uid);
    	nonConformanceReportDao.addNcr(nonconformancereport);
    }
    public String genratencruid() {
    	int length=4;
    	 String characters = "0123456789";
    	Random random = new Random();
    	StringBuilder ncruid = new StringBuilder(length);
    	
    	for(int i = 0; i< length; i++) {
    		ncruid.append(characters.charAt(random.nextInt(characters.length())));
    	}
    	return"NCR" + ncruid.toString();
    	}
    	

    public List<Nonconformancereport> getAllNcr() {
        return nonConformanceReportDao.getAllNcr (); // Ensure it returns a non-null list
   }

    public void deleteById(Long id) {
        nonConformanceReportDao.deleteById(id);
    }
           
	 public int updateNcr(Nonconformancereport nonconformancereport ) {
		  return nonConformanceReportDao.updateNcr(nonconformancereport);
	 } 
	   

	 public Nonconformancereport getNcrByid(Long id) {
		    return nonConformanceReportDao.getNcrByid(id);
		}

	
		
		public List<Map<String, Object>> getProductDetailsByuid(String productuid){
	    	return nonConformanceReportDao.getProductDetailsByuid(productuid);
	    }
	    
	 
		 // Retrieve all product UIDs
	    public List<String> fetchProductUIds() {
	        return nonConformanceReportDao.fetchProductUIds();
	    }
          
	    public List <Map<String,Object>> showAllRecords() {
	        return nonConformanceReportDao.showFindAllrecords();
	    }
	}

	



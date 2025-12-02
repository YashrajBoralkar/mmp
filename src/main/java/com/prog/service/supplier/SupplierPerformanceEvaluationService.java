package com.prog.service.supplier;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.supplier.SupplierPerformanceEvaluationDao;
import com.prog.model.erp.SupplierPerformanceEvaluation;


@Service
public class SupplierPerformanceEvaluationService {
	
	@Autowired
	private SupplierPerformanceEvaluationDao supplierPerformanceEvaluationDao;
	
	public void saveSupplier(SupplierPerformanceEvaluation supplierPerformanceEvaluation) {
		    String evaluationuid = generateSupplierperformanceUid(); // Generate UID
		    supplierPerformanceEvaluation.setSupplierperformanceevaluationuid(evaluationuid); 
		    supplierPerformanceEvaluationDao.addSupplier(supplierPerformanceEvaluation);// Set UID to supplier object
		    
		}
          
	public String generateSupplierperformanceUid() {
	    int length = 4;
	    String characters = "0123456789";
	    Random random = new Random();
	    StringBuilder SupplierperformanceUid = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	    	SupplierperformanceUid.append(characters.charAt(random.nextInt(characters.length())));
	    }

	    return "SPE" + SupplierperformanceUid.toString();
	}

      
	
	

	public List<SupplierPerformanceEvaluation> getAllSupplier() {
        return supplierPerformanceEvaluationDao.getAllSupplier();
    }
	         
	 
	 public void deleteById(Long id) {
		 supplierPerformanceEvaluationDao.deleteById(id);
	 }

	 public int updatesupplierperformance(SupplierPerformanceEvaluation supplierPerformanceEvaluation) {
		    return supplierPerformanceEvaluationDao.updatesupplierperformance(supplierPerformanceEvaluation);
		}

	 public SupplierPerformanceEvaluation getSupplierById(Long id) {
	        return supplierPerformanceEvaluationDao.getSupplierById(id);
	 }
	    public List<Map<String, Object>> showFindAll() {
	        return supplierPerformanceEvaluationDao.showFindAll();
	    }
public int addevolution(SupplierPerformanceEvaluation e) {
	return supplierPerformanceEvaluationDao.addSupplier(e);
}
	}

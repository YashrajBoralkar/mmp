package com.prog.service.sales;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.sales.SalesCommissionDao;
import com.prog.model.erp.SalesCommission;


@Service
public class SalesCommissionService {

    @Autowired
    private SalesCommissionDao salesCommissionDao;

    public int saveSales(SalesCommission sales) {
        String salesuid = generateSalesCommissionuid();
        sales.setSalescommissionuid(salesuid);
        return salesCommissionDao.savesales(sales);
    }
    
    
    public String generateSalesCommissionuid() {
        int length = 4; 
        String characters = "0123456789"; // 
        Random random = new Random();
        StringBuilder salescommissionuid = new StringBuilder();

        for (int i = 0; i < length; i++) {
        	salescommissionuid.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "SC" + salescommissionuid.toString(); // e.g., SCL45823
    }

    
    public int updateSales(SalesCommission sales) {
    	return salesCommissionDao.updatesales(sales);
    }
   
    public List<Map<String, Object>>getAllSales() { // 
        return salesCommissionDao.getAllSales();
    }

    public SalesCommission getSalesById(Long id) {
        return salesCommissionDao.getSalesCommissionById(id);
    }

    public void deleteSalesById(Long id) {
        salesCommissionDao.deleteSalesId(id);
    }
  
}

package com.prog.service.inventory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.inventory.SupplierDao;
import com.prog.model.erp.Supplier;

@Service
public class SupplierService 
{
	@Autowired
	private SupplierDao supplierdao;
	
	public List<String> getAllSupplierUids() {
	     return supplierdao.getAllSupplierUids();
	 }
 
	public Supplier getSupplierDetailsBySupuid(String supplieruid) {
     return supplierdao.getSupplierDetailsBySupuid(supplieruid);
	}
	

}

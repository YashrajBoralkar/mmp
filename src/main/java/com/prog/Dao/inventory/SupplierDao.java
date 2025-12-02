package com.prog.Dao.inventory;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Supplier;


@Repository
public class SupplierDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 public List<String> getAllSupplierUids() {
	   	 
	        String sql = "SELECT supplieruid FROM supplier"; // Replace with your actual table and column names
	        return jdbcTemplate.queryForList(sql, String.class);
	    
	    }
	 
	 @SuppressWarnings("deprecation")
	public Supplier getSupplierDetailsBySupuid(String supplieruid) {
	        String sql = "SELECT suppliername FROM supplier WHERE supplieruid = ?";

			
	        return jdbcTemplate.queryForObject(sql, new Object[]{supplieruid}, (rs, rowNum) -> {
	        	Supplier sup = new Supplier();
	        	sup.setSuppliername(rs.getString("suppliername"));
	           	            return sup;
	        }); 
	 	}
	 

}

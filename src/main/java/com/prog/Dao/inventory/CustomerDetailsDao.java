package com.prog.Dao.inventory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.CustomerDetails;


@Repository
public class CustomerDetailsDao { 
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<String> getAllCustomerDetails(){
		String sql="Select customeruid from customerdetails ";
		return jdbcTemplate.queryForList( sql,String.class);
	}
    public CustomerDetails getCustomerDetailsByid(String customeruid) {
    	String sql = " SELECT contactnumber , contactperson , deliveryaddress FROM customerdetails WHERE customeruid = ?";
    	
    	return jdbcTemplate.queryForObject(sql, new Object[] {customeruid}, (rs, rowNum) -> {
    		CustomerDetails cd = new CustomerDetails();
    		cd.setContactNumber(rs.getString("contactnumber"));
    		cd.setContactPerson(rs.getString("contactperson"));
    		cd.setDeliveryAddress(rs.getString("deliveryaddress"));
    		return cd;
    	});
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////    
    //Vinay(Sales Order Form)
    
//    public List<String>showallcustomerdeatils()
//	{
//		String sql="select customeruid  from customerdetails";
//		return jdbcTemplate.queryForList(sql,String.class);
//
//	}
//=========================================================================================================================================

	
	
//=========================================================================================================================================

	//FETCHING IN RMA REQUEST SERVICE
	
	
	public List<String> getAllCustomerDetailsById(){
		String sql="select customeruid from customerdetails";
		return jdbcTemplate.queryForList(sql,String.class);
	}

	public CustomerDetails getCustomerDetailsById(String customeruid) {
		String sql="select customername,emailaddress,contactnumber,ordernumber from customerdetails where customeruid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {customeruid}, (rs, rowNum) -> {
			CustomerDetails cd= new CustomerDetails();
			cd.setCustomername(rs.getString("customername"));
			cd.setEmailaddress(rs.getString("emailaddress"));
			cd.setContactNumber(rs.getString("contactnumber"));
			cd.setOrdernumber(rs.getString("ordernumber"));
			return cd;			
		});
	}
	
//=========================================================================================================================================

		//FETCHING IN DELIVERY ORDER
	
	 public List<String>getCustomeruInDeliveryOrder(){
	    	String sql="select customeruid From customerdetails";
	    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customeruid"));
	    }
	     public List<Map<String, Object>> getCustomerByuidInDeliveryOrder(String customeruid){
	    	   String sql= "SELECT customername FROM customerdetails WHERE customeruid=?";
	    	   return jdbcTemplate.queryForList(sql, customeruid);
	     }
		

	
}

package com.prog.Dao.wholesellerandretailer;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.CustomerDetails;
import com.prog.model.erp.CustomerRegistration;
import com.prog.model.erp.Productinfo;
import com.prog.model.erp.RetailOrder;
import com.prog.model.erp.CustomerRegistration;


@Repository
public class CustomerRegistrationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	class CustomerRegistrationRowMapper implements RowMapper<CustomerRegistration> {
	    @Override
	    public CustomerRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
	        CustomerRegistration wr = new CustomerRegistration();
	        wr.setId(rs.getLong("id"));
	        wr.setCustomeruid(rs.getString("customeruid"));
	        wr.setCustomertype(rs.getString("customertype"));
	        wr.setAddress(rs.getString("address"));
	        wr.setCompanyname(rs.getString("companyname"));
	        wr.setEmail(rs.getString("email"));
	        wr.setPhonenumber(rs.getString("phonenumber"));
	        wr.setContactperson(rs.getString("contactperson"));
	        wr.setGstnumber(rs.getString("gstnumber"));
	        wr.setPannumber(rs.getString("pannumber"));
	        wr.setAccholder(rs.getString("accholder"));
	        wr.setBankaccountnum(rs.getString("bankaccountnum"));
	        wr.setIfsccode(rs.getString("ifsccode"));
	        wr.setCustomerdoc(rs.getBytes("customerdoc"));
	        return wr;
	    }
	}

	public int addRegistration(CustomerRegistration wrData) {
		String sql = "INSERT into customerregistration  (customertype, accholder, address, bankaccountnum, companyname, contactperson, email, gstnumber, ifsccode, pannumber, phonenumber, customeruid, customerdoc,insertdate,updatedate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
	    
		return jdbcTemplate.update(sql, 
				
				wrData.getCustomertype(), 
				wrData.getAccholder(),
				wrData.getAddress(),
				wrData.getBankaccountnum(),
				wrData.getCompanyname(), 
				wrData.getContactperson(), 
				wrData.getEmail(), 
				wrData.getGstnumber(),
				wrData.getIfsccode(),
				wrData.getPannumber(), 
				wrData.getPhonenumber(), 
				wrData.getCustomeruid(), 
				wrData.getCustomerdoc(),
				formattedTimestamp,
				formattedTimestamp
				
				);
	}

	public List<Map<String, Object>> getAllCustomers() {
	    String sql = "SELECT id, customeruid, customertype, accholder, address, bankaccountnum, companyname, contactperson, email, gstnumber, ifsccode, pannumber, phonenumber FROM customerregistration";
	    return jdbcTemplate.queryForList(sql);
	}

	public CustomerRegistration getWrDataById(Long id) {
	    String sql = "SELECT id, customertype, accholder, address, bankaccountnum, companyname, contactperson, email, gstnumber, ifsccode, pannumber, phonenumber, customeruid, customerdoc FROM customerregistration WHERE id = ?";
	    return jdbcTemplate.queryForObject(sql, new CustomerRegistrationRowMapper(), id); // ✅ correct
	}



	public void deletewrData(Long id) {
		String sql = "DELETE FROM customerregistration  WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	public int updateWr(CustomerRegistration wrData) {
	    String sql = "UPDATE customerregistration  SET " +
	            "customertype = ?, companyname = ?, contactperson = ?, address = ?, email = ?, gstnumber = ?, pannumber = ?, " +
	            "phonenumber = ?, accholder = ?, bankaccountnum = ?, ifsccode = ?, customerdoc = ?, updatedate = ? " +
	            "WHERE id = ?";
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);	        
	    
	    return jdbcTemplate.update(sql,
	            wrData.getCustomertype(),
	            wrData.getCompanyname(),
	            wrData.getContactperson(),
	            wrData.getAddress(),
	            wrData.getEmail(),
	            wrData.getGstnumber(),
	            wrData.getPannumber(),
	            wrData.getPhonenumber(),
	            wrData.getAccholder(),
	            wrData.getBankaccountnum(),
	            wrData.getIfsccode(),
	            wrData.getCustomerdoc(),
	            formattedTimestamp,
	            wrData.getId());
	}

	
	//FETCHING PURPOSE
	public List<String> getAllRetailerUids() {
		String sql = "SELECT customeruid FROM customerregistration  WHERE customertype = 'Retailer';";
        return jdbcTemplate.queryForList(sql, String.class);
    }
	
	//FETCHING WHOLESELLER UID
	public List<String> getAllWholesellerUids() {
		String sql = "SELECT customeruid FROM customerregistration  WHERE customertype = 'Wholeseller';";
        return jdbcTemplate.queryForList(sql, String.class);
    }
	
	 public  CustomerRegistration getSellerDetailsByRetailerUid(String selleruid) {
	        String sql = "SELECT companyname,contactperson FROM customerregistration  WHERE customeruid = ?";
	        try {
	            return jdbcTemplate.queryForObject(sql, new Object[]{selleruid}, (rs, rowNum) -> {
	            	CustomerRegistration sr = new CustomerRegistration();
	                sr.setCompanyname(rs.getString("companyname"));
	                sr.setContactperson(rs.getString("contactperson"));
	                return sr;
	            });
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
	
	 //SALES ORDER FETCHING
	 
	 public CustomerRegistration getdeatilscus(String customeruid )
		{
			 String sql = "SELECT companyname,email,phonenumber FROM customerregistration WHERE customeruid = ?";
		        return jdbcTemplate.queryForObject(sql, new Object[] {customeruid}, (rs, rowNum) ->
		        {
		        	CustomerRegistration cu = new CustomerRegistration();
		        	cu.setCompanyname(rs.getString("companyname"));
		        	cu.setEmail(rs.getString("email"));
		        	cu.setPhonenumber(rs.getString("phonenumber"));
		        	return cu;
		        });

	}
	 
	 public List<String>showallcustomerdeatils()
		{
			String sql="select customeruid  from customerdetails";
			return jdbcTemplate.queryForList(sql,String.class);

		}
	 
	
}

package com.prog.Dao.sales;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.customerquotation;


@Repository
	public class CustomerQuotationDAO {

	    @Autowired
	    private JdbcTemplate jdbcTemplate;


	    private static final String INSERT_SQL = "INSERT INTO customerquotation (customerquotationuid, customeruid, quotationdate, validuntil, productuid, quantityoffered,  quotationamount,totalamount, discountoffered, quotationstatus, salesrepresentative, insertdate, updatedate) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    public int addCustomerQuotation(customerquotation quotation) {
	    	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
		         return jdbcTemplate.update(INSERT_SQL,
	                quotation.getCustomerquotationuid(),
	                quotation.getCustomeruid(),
	                quotation.getQuotationdate(),
	                quotation.getValiduntil(),
	                quotation.getProductuid(),
	                quotation.getQuantityoffered(),
	                quotation.getQuotationamount(),
	                quotation.getTotalamount(),
	                quotation.getDiscountoffered(),
	                quotation.getQuotationstatus(),
	                quotation.getSalesrepresentative(),
	                formattedTimestamp,
	                formattedTimestamp
	        );
	    }

	    
	    
	    
	    
	    
	    
	    
	    // Retrieve all items
	    public  List<Map<String, Object>> getAllQuotations() {        
	    	String sql = "SELECT q.id, q.quotationdate, q.validuntil,  q.quotationstatus,  q.quantityoffered,  q.quotationamount, q.totalamount,  q.discountoffered,  q.salesrepresentative, p.productname,  c.customername,  p.productuid, q.customerquotationuid,  c.customeruid \r\n"
	    			+ "FROM customerquotation q \r\n"
	    			+ " join productinfo p ON p.productuid = q.productuid \r\n"
	    			+ "JOIN customerdetails c ON c.customeruid = q.customeruid;\r\n"
	    			+ "";
       
	       
	        return jdbcTemplate.queryForList(sql);
	        
	    }
	    
	
	    


		 public List<String> fetchCustomerUIds() {
		        String sql = "SELECT customeruid FROM customerdetails";
		        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customeruid"));
		    }
		
		
		
		  // Retrieve product details by product UID
	    public List<Map<String, Object>> getDataByCustomerUid(String customeruid) {
	        String sql = "SELECT customername FROM customerdetails WHERE customeruid = ?";
	        return jdbcTemplate.queryForList(sql, customeruid);
	    }


	    // Retrieve product details by product UID
	    public List<Map<String, Object>> getDataByProductUid(String productuid) {
	        String sql = "SELECT productname,sellingprice FROM productinfo WHERE productuid = ?";
	        return jdbcTemplate.queryForList(sql, productuid);
	    }

	    // Retrieve all product UIDs
	    public List<String> fetchProductUIds() {
	        String sql = "SELECT productuid FROM productinfo";
	        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
	    }



	    public customerquotation getQuotationById(Long id) {
	        String sql = "SELECT * FROM customerquotation WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new CustomerQuotationRowMapper(), id);
	    }
	    
	    
	 // Update a customerquotation

	    public int updateCustomerQuotation(customerquotation quotation) {
		    String sql = "UPDATE customerquotation SET quotationdate = ?, validuntil = ?,  quantityoffered = ?,  quotationamount = ?, totalamount = ?, discountoffered = ?, quotationstatus = ?, salesrepresentative = ?,updatedate=? WHERE id = ?";
	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		       return jdbcTemplate.update(sql,
	                
	                quotation.getQuotationdate(),
	                quotation.getValiduntil(),	                
	                quotation.getQuantityoffered(),
	                
	                quotation.getQuotationamount(),
	                quotation.getTotalamount(),
	                quotation.getDiscountoffered(),
	                quotation.getQuotationstatus(),
	                quotation.getSalesrepresentative(),
	                formattedTimestamp,
	                
	                quotation.getId()
	        );
	    }
	    
	    


	    public int deleteQuotation(Long id) {
	        String sql = "DELETE FROM customerquotation WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	    
	 
	    public static class CustomerQuotationRowMapper implements RowMapper<customerquotation> {
	        @Override
	        public customerquotation mapRow(ResultSet rs, int rowNum) throws SQLException {
	            customerquotation quotation = new customerquotation();
	            quotation.setId(rs.getLong("id"));
	            quotation.setCustomerquotationuid(rs.getString("customerquotationuid"));
	            quotation.setCustomeruid(rs.getString("customeruid"));
	            quotation.setQuotationdate(rs.getString("quotationdate"));
	            quotation.setValiduntil(rs.getString("validuntil"));
	            quotation.setProductuid(rs.getString("productuid"));
	            quotation.setQuantityoffered(rs.getString("quantityoffered"));
	            quotation.setQuotationamount(rs.getString("quotationamount"));
	            quotation.setTotalamount(rs.getString("totalamount"));

	            quotation.setDiscountoffered(rs.getString("discountoffered"));
	            quotation.setQuotationstatus(rs.getString("quotationstatus"));
	            quotation.setSalesrepresentative(rs.getString("salesrepresentative"));
	            return quotation;
	        }
	    }
}
package com.prog.Dao.wholesellerandretailer;

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

import com.prog.model.erp.customerpaymentcollection;


@Repository
public class CustomerpaymentcollectionDAO {

	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	 public static class CustomerpaymentcollectionRowMapper implements RowMapper<customerpaymentcollection> {
	        @Override
	        public customerpaymentcollection mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	customerpaymentcollection payment = new customerpaymentcollection();
	        	payment.setId(rs.getLong("id"));
	        	payment.setcustomerpaymentcollectionuid(rs.getString("customerpaymentcollectionuid"));
	        	payment.setCustomerinvoiceuid(rs.getString("customerinvoiceuid"));
	        	payment.setCustomeruid(rs.getString("customeruid"));
	        	payment.setInvoiceamount(rs.getString("invoiceamount"));
	        	payment.setRemainingamount(rs.getString("remainingamount"));
	        	payment.setPaymentdate(rs.getString("paymentdate"));
	        	payment.setAmountpaid(rs.getString("amountpaid"));
	        	payment.setPaymentmode(rs.getString("paymentmode"));
	        	payment.setReceivedby(rs.getString("receivedby"));


	                  return payment;
	        }
	    }
	 
	private static final String INSERT_SQL = 
		    "INSERT INTO customerpaymentcollection (customerpaymentcollectionuid, customerinvoiceuid,customeruid,invoiceamount,remainingamount, paymentdate, amountpaid, paymentmode, receivedby, insertdate, updatedate) " +
		    "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

	
	public int addCustomerPaymentCollection(customerpaymentcollection payment) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);

	    return jdbcTemplate.update(INSERT_SQL,	        
	    	payment.getcustomerpaymentcollectionuid(),
	        payment.getCustomerinvoiceuid(),
	        payment.getCustomeruid(),
	        payment.getInvoiceamount(),
	        payment.getRemainingamount(),
	        payment.getPaymentdate(),
	        payment.getAmountpaid(),
	        payment.getPaymentmode(),
	        payment.getReceivedby(),
	        formattedTimestamp,
	        formattedTimestamp
	    );
	}

	 public List<Map<String, Object>> getAllCustomerPaymentCollection() {
	        String sql = "SELECT r.id, r.customerpaymentcollectionuid, r.paymentdate, r.amountpaid, " +
	                     "r.paymentmode, r.receivedby, i.customeruid, i.customerinvoiceuid, r.insertdate, r.updatedate " +
	                     "FROM customerpaymentcollection r " +
	                     "INNER JOIN customerinvoice i ON r.customerinvoiceuid = i.customerinvoiceuid";

	        return jdbcTemplate.queryForList(sql);
	    }
	 
	 
	 public customerpaymentcollection getCustomerPaymentCollectionById(Long id) {
	        String sql = "SELECT * FROM customerpaymentcollection WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new CustomerpaymentcollectionRowMapper(), id);
	    } 
	 
	 public int updateCustomerPaymentCollection(customerpaymentcollection payment) {
		    String sql = "UPDATE customerpaymentcollection SET  paymentdate = ?, amountpaid = ?, paymentmode = ?,  receivedby = ?, updatedate = ? WHERE id = ?";
	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		       return jdbcTemplate.update(sql,
		    		   payment.getPaymentdate(),	                
		    		   payment.getAmountpaid(),
		    		   payment.getPaymentmode(),
		    		   payment.getReceivedby(),
		    		   formattedTimestamp,
		    		   payment.getId()
	        );
	    }
	  
	 public int deleteCustomerPaymentCollection(Long id) {
	        String sql = "DELETE FROM customerpaymentcollection WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	 
   
    
}

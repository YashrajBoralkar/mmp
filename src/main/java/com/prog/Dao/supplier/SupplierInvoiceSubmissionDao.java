package com.prog.Dao.supplier;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.SupplierInvoiceSubmission;


@Repository
public class SupplierInvoiceSubmissionDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class InvoiceSubmissionRowMapper implements RowMapper<SupplierInvoiceSubmission>{

		@Override
		public SupplierInvoiceSubmission mapRow(ResultSet rs, int rowNum) throws SQLException {
			SupplierInvoiceSubmission sis=new SupplierInvoiceSubmission();
			sis.setId(rs.getLong("id"));
			sis.setSupplierinvoiceuid(rs.getString("supplierinvoiceuid"));
			sis.setInvoicedate(rs.getString("invoicedate"));
			sis.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
			sis.setPaymentterms(rs.getString("paymentterms"));
			sis.setBankaccountnumber(rs.getString("bankaccountnumber"));
			sis.setTotalamount(rs.getDouble("totalamount"));
			sis.setPaymentduedate(rs.getString("paymentduedate"));
			sis.setInvoiceapprovalstatus(rs.getString("invoiceapprovalstatus"));
			sis.setApprovalauthority(rs.getString("approvalauthority"));
			return sis;
		}	
	}
	
	public int addSupplierInvoiceSubmission(SupplierInvoiceSubmission sis) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
		String sql="insert into supplierinvoicesubmission(supplierinvoiceuid,invoicedate,rawmaterialpurchaseorderuid,paymentterms,bankaccountnumber,totalamount,paymentduedate,"
				+ "invoiceapprovalstatus,approvalauthority,insertdate,updatedate)values(?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
			
				sis.getSupplierinvoiceuid(),
				sis.getInvoicedate(),
				sis.getRawmaterialpurchaseorderuid(),
				sis.getPaymentterms(),
				sis.getBankaccountnumber(),
				sis.getTotalamount(),
				sis.getPaymentduedate(),
				sis.getInvoiceapprovalstatus(),
				sis.getApprovalauthority(),
				formattedTimestamp,
				formattedTimestamp
				);
	}
	
	public SupplierInvoiceSubmission getSupplierInvoiceSubmissionById(Long id) {
		String sql="select * from supplierinvoicesubmission where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(SupplierInvoiceSubmission.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	public List<Map<String,Object>> fetchSupplierInvoiceSubmissionData() {
	    String sql = "SELECT sis.id, sis.supplierinvoiceuid, sis.invoicedate, " +
	                 "rpo.suppliername, rpo.totalvalue, sis.paymentduedate, " +
	                 "sis.invoiceapprovalstatus, sis.approvalauthority " +
	                 "FROM supplierinvoicesubmission sis " +
	                 "JOIN rawmaterialpurchaseorder rpo ON sis.rawmaterialpurchaseorderuid = rpo.rawmaterialpurchaseorderuid";
	    return jdbcTemplate.queryForList(sql);
	}

	
	
	
	public Map<String, Object> getRawMaterialPOData(String rawmaterialpurchaseorderuid) {
	    String sql = "SELECT suppliername, totalvalue, materialnames " +
	                 "FROM rawmaterialpurchaseorder " +
	                 "WHERE rawmaterialpurchaseorderuid = ?";
	    try {
	        return jdbcTemplate.queryForMap(sql, rawmaterialpurchaseorderuid);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}
	

	public List<String> getAllPurchaseOrderDetailsDataINSupplier() {
	    String sql = "SELECT rawmaterialpurchaseorderuid FROM rawmaterialpurchaseorder";
	    return jdbcTemplate.queryForList(sql, String.class);
	}
	



	
	public int updateSupplierInvoiceSubmission(SupplierInvoiceSubmission sis) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
	    String formattedTimestamp = LocalDateTime.now().format(formatter);

	    String sql = "UPDATE supplierinvoicesubmission SET invoicedate=?, rawmaterialpurchaseorderuid=?, paymentterms=?, bankaccountnumber=?, totalamount=?, paymentduedate=?, invoiceapprovalstatus=?, approvalauthority=?, updatedate=? WHERE id=?";
	    
	    return jdbcTemplate.update(sql,
	        sis.getInvoicedate(),
	        sis.getRawmaterialpurchaseorderuid(),
	        sis.getPaymentterms(),
	        sis.getBankaccountnumber(),
	        sis.getTotalamount(),
	        sis.getPaymentduedate(),
	        sis.getInvoiceapprovalstatus(),
	        sis.getApprovalauthority(),
	        formattedTimestamp,
	        sis.getId()
	    );
	}

	public void deleteSupplierInvoiceSubmission(Long id) {
		String sql="delete from supplierinvoicesubmission where id=?";
		jdbcTemplate.update(sql,id);
	}
	
	public List<Map<String, String>> getApprovalAuthorities() {
	    String sql = "SELECT employeeuid, CONCAT(first_name, ' ', last_name) AS name FROM employee";
	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        Map<String, String> map = new HashMap<>();
	        map.put("code", rs.getString("employeeuid"));
	        map.put("name", rs.getString("name"));
	        return map;
	    });
	}

	
	
}

package com.prog.Dao.sales;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.SalesInvoiceForm;


@Repository
public class SalesInvoiceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class SalesInvoiceRowMapper implements RowMapper<SalesInvoiceForm>{

		@Override
		public SalesInvoiceForm mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			SalesInvoiceForm si = new SalesInvoiceForm();
			si.setId(rs.getLong("id"));
			si.setSalesinvoiceuid(rs.getString("salesinvoiceuid"));
			si.setSalesorderuid(rs.getString("salesorderuid"));
			si.setCustomeruid(rs.getString("customeruid"));
			si.setProductuid(rs.getString("productuid"));
			si.setDiscount(rs.getString("discount"));
			si.setTaxamount(rs.getString("taxamount"));
			si.setTotalinvoiceamount(rs.getString("totalinvoiceamount"));
			si.setInvoicedate(rs.getString("invoicedate"));
			si.setDuedate(rs.getString("duedate"));
			si.setPaymentstatus(rs.getString("paymentstatus"));
			si.setPaymentmethods(rs.getString("paymentmethods"));
			
			return si;
		}
		
	}
	
	public int addSalesInvoiceData(SalesInvoiceForm si) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        String sql = "INSERT INTO salesinvoice(id, salesinvoiceuid, salesorderuid, customeruid, productuid, " +
                "discount, taxamount, totalinvoiceamount, invoicedate, duedate, paymentstatus, paymentmethods, insertdate, updatedate) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return jdbcTemplate.update(sql,
				si.getId(),
				si.getSalesinvoiceuid(),
				si.getSalesorderuid(),
				si.getCustomeruid(),
				si.getProductuid(),
				si.getDiscount(),   // ✅ new
				si.getTaxamount(),
				si.getTotalinvoiceamount(),
				si.getInvoicedate(),
				si.getDuedate(),
				si.getPaymentstatus(),
				si.getPaymentmethods(),
				formattedTimestamp,
                formattedTimestamp
				);
	}
	
	public SalesInvoiceForm getSalesInvoiceDataById(Long id) {
		String sql="Select * from salesinvoice where id=?";
		 try {
		    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(SalesInvoiceForm.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
	}
	
	public List<Map<String,Object>> getAllSalesInvoiseData(){
		String sql="SELECT si.id,si.salesinvoiceuid, si.salesorderuid, si.invoicedate,si.paymentmethods,si.paymentstatus,si.taxamount,si.totalinvoiceamount,\r\n"
				+ "	pd.unitofmeasure, pd.productname, cd.companyname,si.duedate                   \r\n"
				+ "	FROM salesinvoice si\r\n"
				+ "	 JOIN customerregistration cd ON si.customeruid = cd.customeruid\r\n"
				+ "	 JOIN productinfo pd ON si.productuid = pd.productuid;";
		return jdbcTemplate.queryForList(sql);
	} 
	public int updateSaleInvoice(SalesInvoiceForm si) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
        String sql = "UPDATE salesinvoice SET invoicedate=?, paymentmethods=?, paymentstatus=?, discount=?, taxamount=?, " +
                "totalinvoiceamount=?, duedate=?, updatedate=? WHERE id=?";
		return jdbcTemplate.update(sql,
				si.getInvoicedate(),
				si.getPaymentmethods(),
				si.getPaymentstatus(),
				si.getDiscount(),
				si.getTaxamount(),
				si.getTotalinvoiceamount(),
				si.getDuedate(),
				formattedTimestamp,
				si.getId()
				);
	}
	
	public void deleteSalesInvoice(Long id) {
		String sql="delete from salesinvoice where id=?";
		jdbcTemplate.update(sql,id);
	}
	//Data fetch from Sales info table by using customer details and prosuctuid  details in sales invoice form
	public List<String>getSalesOrderUId(){
    	String sql="select salesorderuid,orderquantity From salesorder";
    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("salesorderuid"));
    }
    
	public List<Map<String, Object>> getSalesOrderDetailsByuid(String salesorderuid) {
	    String sql = "SELECT si.productuid, cd.companyname, cd.customeruid, " +
	                 "p.productname, si.orderquantity, si.totalamount " +
	                 "FROM salesorder si " +
	                 "JOIN productinfo p ON p.productuid = si.productuid " +
	                 "JOIN customerregistration cd ON cd.customeruid = si.customeruid " +
	                 "WHERE si.salesorderuid = ?";
	    return jdbcTemplate.queryForList(sql, salesorderuid);
	}

}

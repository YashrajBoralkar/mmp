package com.prog.Dao.wholesellerandretailer;

import java.sql.ResultSet;



import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.customerinvoice;



@Repository
public class CustomerInvoiceDAO {

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	 private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    // ✅ Add invoice
	    public int addInvoice(customerinvoice invoice) {
	        String sql = "INSERT INTO customerinvoice " +
	                "(customerinvoiceuid, customerorderuid, customeruid, customertype, companyname, productuid, productname, unitprice, quantitysold, tax, applydiscount, discountapplied, totalammount, paymentmode, insertdate, updatedate, invoicedate) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
	    
	        return jdbcTemplate.update(sql,
	                invoice.getCustomerinvoiceuid(),
	                invoice.getCustomerorderuid(),
	                invoice.getCustomeruid(),
	                invoice.getCustomertype(),
	                invoice.getCompanyname(),
	                invoice.getProductuid(),
	                invoice.getProductname(),
	                invoice.getUnitprice(),
	                invoice.getQuantitysold(),
	                invoice.getTax(),
	                invoice.getApplydiscount(),
	                invoice.getDiscountapplied(),
	                invoice.getTotalammount(),
	                invoice.getPaymentmode(),
	                formattedTimestamp,
	                formattedTimestamp,
	                invoice.getInvoicedate()
	        );
	    }
    private class CustomerInvoiceRowMapper implements RowMapper<customerinvoice> {
        @Override
        public customerinvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            customerinvoice invoice = new customerinvoice();
            invoice.setId(rs.getLong("id"));
            invoice.setCustomerinvoiceuid(rs.getString("customerinvoiceuid"));
            invoice.setCustomerorderuid(rs.getString("customerorderuid"));
            invoice.setCustomeruid(rs.getString("customeruid"));
            invoice.setCustomertype(rs.getString("customertype"));
            invoice.setCompanyname(rs.getString("companyname"));
            invoice.setProductuid(rs.getString("productuid"));
            invoice.setProductname(rs.getString("productname"));
            invoice.setUnitprice(rs.getString("unitprice"));
            invoice.setQuantitysold(rs.getString("quantitysold"));
            invoice.setTax(rs.getDouble("tax"));
            invoice.setApplydiscount(rs.getString("applydiscount"));
            invoice.setDiscountapplied(rs.getString("discountapplied"));
            invoice.setTotalammount(rs.getDouble("totalammount"));
            invoice.setPaymentmode(rs.getString("paymentmode"));
            invoice.setInvoicedate(rs.getString("invoicedate"));
            return invoice;
        }
    }
    // ✅ Update invoice
    public void updateInvoice(customerinvoice invoice) {
        String sql = "UPDATE customerinvoice SET " +
                "customeruid=?, customertype=?, companyname=?, productuid=?, productname=?, unitprice=?, quantitysold=?, tax=?, applydiscount=?, discountapplied=?, totalammount=?, paymentmode=?, updatedate=?, invoicedate=? " +
                "WHERE id=?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);	        
    
        jdbcTemplate.update(sql,
                invoice.getCustomeruid(),
                invoice.getCustomertype(),
                invoice.getCompanyname(),
                invoice.getProductuid(),
                invoice.getProductname(),
                invoice.getUnitprice(),
                invoice.getQuantitysold(),
                invoice.getTax(),
                invoice.getApplydiscount(),
                invoice.getDiscountapplied(),
                invoice.getTotalammount(),
                invoice.getPaymentmode(),
                formattedTimestamp,         // updatedate
                invoice.getInvoicedate(),   // ✅ invoicedate added
                invoice.getId());           // ✅ last parameter matches WHERE id=?

    }
	
	public List<Map<String, Object>> getAllCustomerInvoice() {
		String sql = "SELECT ri.id, ri.customerinvoiceuid, ro.customerorderuid, ri.invoicedate, ri.paymentmode, " +
	             "ri.totalammount, ri.tax, ri.discountapplied, ri.applydiscount, ri.quantitysold, " +
	             "ro.productuid, po.productname, po.sellingprice, " +
	             "r.customeruid, r.companyname " +
	             "FROM customerinvoice ri " +
	             "JOIN customerregistration r ON ri.customeruid = r.customeruid " +
	             "JOIN customerorder ro ON ri.customerorderuid = ro.customerorderuid "+
	             "JOIN productinfo po ON ri.productuid = po.productuid";

	    return jdbcTemplate.queryForList(sql);
	}

	
	public customerinvoice getCustomerInvoicebyid(Long id) {
		String sql = "SELECT * FROM customerinvoice WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new CustomerInvoiceRowMapper(), id);
	}
	
	 // ✅ Delete invoice
    public int deleteInvoice(Long id) {
        String sql = "DELETE FROM customerinvoice WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

	
	
	//FETCHING PURPOSE COMBINE METHOD
	// Retrieve all product UIDs
    public List<String> fetchretailinvoiceUIds() {
        String sql = "SELECT customerinvoiceuid FROM customerinvoice";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customerinvoiceuid"));
    }
    
 // Retrieve product details by product UID
    public List<Map<String, Object>> getDataByretailinvoiceUid(String customerinvoiceuid) {
        String sql = "SELECT selleruid FROM customerinvoice WHERE customerinvoiceuid = ?";
        return jdbcTemplate.queryForList(sql, customerinvoiceuid);
    }


//-----------------------------------------------------------------------------Customer Registration and Order Table----------------------------------------------------------------------------------------
    
    
 // ✅ Get Customer UIDs by Type
    public List<String> getCustomerUidsByType(String customertype) {
        String sql = "SELECT DISTINCT customeruid FROM customerorder WHERE customertype = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{customertype}, String.class);
    }

    // ✅ Get company name by customer UID
    public String getCompanyName(String customeruid) {
        String sql = "SELECT companyname FROM customerregistration WHERE customeruid = ?";
        List<String> list = jdbcTemplate.query(sql, new Object[]{customeruid},
            (rs, rowNum) -> rs.getString("companyname"));
        return list.isEmpty() ? null : list.get(0);
    }

    // ✅ Get all customer order UIDs for a customer
    public List<Map<String, String>> getOrdersByCustomerUid(String customeruid) {
        String sql = "SELECT DISTINCT customerorderuid FROM customerorder WHERE customeruid = ?";
        return jdbcTemplate.query(sql, new Object[]{customeruid}, 
            (rs, rowNum) -> Map.of("customerorderuid", rs.getString("customerorderuid")));
    }

    
 // 4️⃣ Get Order Details (can be multiple products per order)
    public List<Map<String, Object>> getOrderDetailsByUid(String customerorderuid) {
        String sql = "SELECT co.productuid, po.productname, po.sellingprice " +
                     "FROM customerorder co "
                     + "JOIN productinfo po ON co.productuid = po.productuid "
                     + "WHERE customerorderuid = ?";
        return jdbcTemplate.query(sql, new Object[]{customerorderuid}, (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("productuid", rs.getString("productuid"));
            map.put("productname", rs.getString("productname"));
            map.put("sellingprice", rs.getBigDecimal("sellingprice"));
            return map;
        });
    }
    
    
//----------------------------------Customer Invoice Table Data Fetching---------------------------------------------
    
    
    public List<Map<String, Object>> getDataByCustomerInvoiceUid(String customerinvoiceuid) {
        String sql = "SELECT customeruid, totalammount AS invoiceamount " +
                     "FROM customerinvoice WHERE customerinvoiceuid = ?";
        return jdbcTemplate.queryForList(sql, customerinvoiceuid);
    }

    // Fetch all customer invoice UIDs for dropdown
    public List<String> fetchCustomerInvoiceUIds() {
        String sql = "SELECT customerinvoiceuid FROM customerinvoice";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customerinvoiceuid"));
    }
  

  
  
}

package com.prog.Dao.wholesellerandretailer;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.CustomerOrder;
import com.prog.model.erp.CustomerRegistration;




@Repository
public class CustomerorderDAO {
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    private static final String INSERT_SQL = 
	    	    "INSERT INTO customerorder (customerorderuid, customertype, customeruid, orderdate, productuid, quantity, discount, totalvalue, paymentstatus, insertdate, updatedate) " +
	    	    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    

	    public int addWholesaleOrder(CustomerOrder order) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

	        return jdbcTemplate.update(INSERT_SQL,
	            order.getCustomerorderuid(),
	            order.getCustomertype(),
	            order.getCustomeruid(),
	            order.getOrderdate(),
	            order.getProductuid(),
	            order.getQuantity(),
	            order.getDiscount(),
	            order.getTotalvalue(),
	            order.getPaymentstatus(),
	            formattedTimestamp,
	            formattedTimestamp
	        );
	    }
	    
	    public List<Map<String, Object>> getAllWholesaleOrder() {
	        String sql ="SELECT w.id, w.customerorderuid, w.customertype, w.customeruid, w.orderdate, w.quantity, w.paymentstatus, w.discount, w.totalvalue, p.productname, p.sellingprice, p.productuid, w.insertdate, w.updatedate,"
	        		+ "sr.companyname,sr.contactperson \r\n"
	        		+ "FROM customerorder w \r\n"
	        		+ "join productinfo p ON p.productuid = w.productuid \r\n"
	        		+ "JOIN customerregistration sr ON w.customeruid = sr.customeruid";
	    
	        return jdbcTemplate.queryForList(sql);
	    }
	    
	    
	    public int deleteWholesaleOrder(Long id) {
	        String sql = "DELETE FROM customerorder WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	    
	    public int updateWholesaleOrder(CustomerOrder order) {
		    String sql = "UPDATE customerorder SET orderdate = ?, quantity = ?,  discount = ?, totalvalue = ?, paymentstatus = ?, updatedate = ? WHERE id = ?";
	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		       return jdbcTemplate.update(sql,
		    		   order.getOrderdate(),	                
		    		   order.getQuantity(),
		    		   order.getDiscount(),
		    		   order.getTotalvalue(),
		    		   order.getPaymentstatus(),   
		    		   formattedTimestamp,
	                
	                order.getId()
	        );
	    }
	    
	    
	    public CustomerOrder getWholesaleOrderById(Long id) {
	        String sql = "SELECT * FROM customerorder WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new WholesaleOrderRowMapper(), id);
	    }
	    
	    public static class WholesaleOrderRowMapper implements RowMapper<CustomerOrder> {
	        @Override
	        public CustomerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	CustomerOrder order = new CustomerOrder();
	        	order.setId(rs.getLong("id"));
	        	order.setCustomerorderuid(rs.getString("customerorderuid"));
	        	order.setCustomertype(rs.getString("customertype"));
		        order.setCustomeruid(rs.getString("customeruid"));
	        	order.setOrderdate(rs.getString("orderdate"));
	        	order.setProductuid(rs.getString("productuid"));
	        	order.setQuantity(rs.getString("quantity"));
	        	order.setDiscount(rs.getString("discount"));
	        	order.setTotalvalue(rs.getString("totalvalue"));
	        	order.setPaymentstatus(rs.getString("paymentstatus"));

	                  return order;
	        }
	    }
	    
	 // 👉 Get Customer UIDs by Type (Wholesaler, Retailer, Other)
	    public List<String> getCustomerUidsByType(String type) {
	        String sql = "SELECT customeruid FROM customerregistration WHERE customertype = ?";
	        return jdbcTemplate.queryForList(sql, String.class, type);
	    }

	    // 👉 Get full customer details by UID
	    public CustomerRegistration getCustomerDetailsByUid(String uid) {
	        String sql = "SELECT * FROM customerregistration WHERE customeruid = ?";
	        return jdbcTemplate.queryForObject(sql,
	                new BeanPropertyRowMapper<>(CustomerRegistration.class),
	        uid);
	    }
	    
	    // Fetch customer order by ID
	    public CustomerOrder findById(Long id) {
	        String sql = "SELECT * FROM customerorder WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<CustomerOrder>() {
	            @Override
	            public CustomerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	CustomerOrder order = new CustomerOrder();
	                order.setId(rs.getLong("id"));
	                order.setCustomerorderuid(rs.getString("customerorderuid"));
	                order.setCustomertype(rs.getString("customertype"));
	                order.setCustomeruid(rs.getString("customeruid"));
	                order.setOrderdate(rs.getString("orderdate"));
	                order.setProductuid(rs.getString("productuid"));
	                order.setQuantity(rs.getString("quantity"));
	                order.setDiscount(rs.getString("discount"));
	                order.setTotalvalue(rs.getString("totalvalue"));
	                order.setPaymentstatus(rs.getString("paymentstatus"));
	                return order;
	            }
	        });
	    }

	    // Fetch customer registration by UID
	    public CustomerRegistration findByCustomerUid(String customeruid) {
	        String sql = "SELECT * FROM customerregistration WHERE customeruid = ?";
	        return jdbcTemplate.queryForObject(sql, new Object[]{customeruid}, new RowMapper<CustomerRegistration>() {
	            @Override
	            public CustomerRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
	                CustomerRegistration customer = new CustomerRegistration();
	                customer.setId(rs.getLong("id"));
	                customer.setCustomeruid(rs.getString("customeruid"));
	                customer.setCustomertype(rs.getString("customertype"));
	                customer.setCompanyname(rs.getString("companyname"));
	                customer.setContactperson(rs.getString("contactperson"));
	                customer.setPhonenumber(rs.getString("phonenumber"));
	                customer.setEmail(rs.getString("email"));
	                customer.setAddress(rs.getString("address"));
	                customer.setGstnumber(rs.getString("gstnumber"));
	                customer.setPannumber(rs.getString("pannumber"));
	                customer.setBankaccountnum(rs.getString("bankaccountnum"));
	                customer.setIfsccode(rs.getString("ifsccode"));
	                customer.setAccholder(rs.getString("accholder"));
	                return customer;
	            }
	        });
	        
	    }
	    
	    
	  //FETCHING PURPOSE COMBINE METHOD
	    public List<String> getCustomerOrderUId(){
	    	String sql="select customerorderuid From customerorder";
	    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("customerorderuid"));
	    }
	    
		
	  public List<Map<String, Object>> getCustomerOrderDetailsByuid(String customerorderuid){
	   String sql= "SELECT p.sellingprice, p.productuid,p.productname,r.customeruid,r.companyname\r\n"
	   		+ "FROM customerorder ro \r\n"
	   		+ "JOIN  productinfo p ON p.productuid = ro.productuid\r\n"
	   		+ "JOIN customerregistration r ON r.customeruid=ro.customeruid WHERE ro.customerorderuid = ?;";
	   return jdbcTemplate.queryForList(sql, customerorderuid);
		
	}

	    

	    
	

}

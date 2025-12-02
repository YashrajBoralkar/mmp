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

import com.prog.model.erp.CustomerReturn;


@Repository
public class CustomerReturnDao {

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    public int addCustomerReturn(CustomerReturn customerReturn) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String sql = "INSERT INTO customerreturn (customerreturnuid, salesorderuid, customeruid, returndate, productuid, " +
                     "quantityreturned, reasonforreturn, returnstatus, refundorreplacement, insertdate, updatedate) " +
                     "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        return jdbcTemplate.update(sql,
        		
        		customerReturn.getCustomerreturnuid(),	
            
            customerReturn.getSalesorderuid(),
            customerReturn.getCustomeruid(),
            customerReturn.getReturndate(),
            customerReturn.getProductuid(),
            customerReturn.getQuantityreturned(),
            customerReturn.getReasonforreturn(),
            customerReturn.getReturnstatus(),
            customerReturn.getRefundorreplacement(),
            formattedTimestamp,
            formattedTimestamp
        );
    }

    public List<CustomerReturn> findAll() {
        String sql = "SELECT * FROM customerreturn";
        return jdbcTemplate.query(sql, new CustomerReturnRowMapper());
    }

    public CustomerReturn findById(Long id) {
        String sql = "SELECT * FROM customerreturn WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerReturnRowMapper(), id);
    }

    private static class CustomerReturnRowMapper implements RowMapper<CustomerReturn> {
        @Override
        public CustomerReturn mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerReturn customerReturn = new CustomerReturn();
            customerReturn.setId(rs.getLong("id"));
            customerReturn.setCustomerreturnuid(rs.getString("customerreturnuid"));
            customerReturn.setSalesorderuid(rs.getString("salesorderuid"));
            customerReturn.setCustomeruid(rs.getString("customeruid"));
            customerReturn.setReturndate(rs.getString("returndate"));
            customerReturn.setProductuid(rs.getString("productuid"));
            customerReturn.setQuantityreturned(rs.getString("quantityreturned"));
            customerReturn.setReasonforreturn(rs.getString("reasonforreturn"));
            customerReturn.setReturnstatus(rs.getString("returnstatus"));
            customerReturn.setRefundorreplacement(rs.getString("refundorreplacement"));
            return customerReturn;
        }
    }
     
  //Data fetch from Sales info table by using customer details and prosuctuid  details in sales invoice form
  	public List<String>getSalesOrderUId(){
      	String sql="select salesorderuid From salesorder";
      	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("salesorderuid"));
      }
      
    public List<Map<String, Object>> getSalesOrderDetailsByuid(String salesorderuid){
     String sql= "SELECT cd.customername,cd.customeruid\r\n"
     		+ "FROM salesorder si  \r\n"
     			+ "JOIN customerdetails cd ON cd.customeruid=si.customeruid WHERE si.salesorderuid = ?;";
     return jdbcTemplate.queryForList(sql, salesorderuid);
  }
    public List<String>getProductOrderUId(){
      	String sql="select productuid From productinfo";
      	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
      }
       
    public List<Map<String, Object>> getproductDetailsByuid(String productuid){
     String sql= "SELECT productname FROM productinfo where productuid = ?";
     return jdbcTemplate.queryForList(sql, productuid);
  }

	
		

   

    public void deleteById(Long id) {
        String sql = "DELETE FROM customerreturn WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
       
    public CustomerReturn getCustomerReturnById(Long id) {
        String sql = "SELECT * FROM customerreturn WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerReturnRowMapper(), id);
    } 
    
    
    public int updateCustomerReturn(CustomerReturn customerReturn) {
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String formattedTimestamp = LocalDateTime.now().format(formatter);

    	String sql = "UPDATE customerreturn SET quantityreturned=?,  reasonforreturn=?, " +
                     "returndate=?,  returnstatus=?, refundorreplacement=?, updatedate = ? WHERE id=?";

        return jdbcTemplate.update(sql,
        	    customerReturn.getQuantityreturned(),
        	    customerReturn.getReasonforreturn(),  
        	    customerReturn.getReturndate(),       
        	    customerReturn.getReturnstatus(),     
        	    customerReturn.getRefundorreplacement(), 
        	    formattedTimestamp,
        	    customerReturn.getId()
        	);
    }
    public List<Map<String, Object>> showFindAll() {
        String sql = "SELECT \r\n"
        		+ "    rlu.id, \r\n"
        		+ "    rlu.customerreturnuid, \r\n"
        		+ "    rlu.salesorderuid, \r\n"
        		+ "    ci.customername,\r\n"
        		+ "    rlu.returndate, \r\n"
        		+ "    po.productname,\r\n"
        		+ "    rlu.quantityreturned,\r\n"
        		+ "    rlu.reasonforreturn, \r\n"
        		+ "    rlu.returnstatus,\r\n"
        		+ "    rlu.refundorreplacement\r\n"
        		+ "FROM \r\n"
        		+ "    customerreturn rlu\r\n"
        		+ "JOIN \r\n"
        		+ "    customerdetails ci ON rlu.customeruid = ci.customeruid\r\n"
        		+ "JOIN \r\n"
        		+ "    productinfo po ON rlu.productuid = po.productuid;\r\n";
        		
        return jdbcTemplate.queryForList(sql);
    }

	}



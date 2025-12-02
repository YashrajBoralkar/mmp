package com.prog.Dao.inventory;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.StockDispatch;


@Repository
public class StockDispatchDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class StockDispatchRowMapper implements RowMapper<StockDispatch>{

		@Override
		public StockDispatch mapRow(ResultSet rs, int rowNum) throws SQLException {
			StockDispatch sd=new StockDispatch();
			sd.setId(rs.getLong("id"));
			sd.setDispatchuid(rs.getString("dispatchuid"));
			sd.setDispatchtype(rs.getString("dispatchtype"));
			sd.setCustomeruid(rs.getString("customeruid"));
			sd.setBatchuid(rs.getString("batchuid"));
			sd.setProductuid(rs.getString("productuid"));
			sd.setCarriername(rs.getString("carriername"));
			sd.setDispatchedby(rs.getString("dispatchedby"));
			sd.setApprovedby(rs.getString("approvedby"));
			sd.setApprovaldate(rs.getString("approvaldate"));
			sd.setStatus(rs.getString("status"));
			sd.setRemarks(rs.getString("remarks"));
			
			return sd;
		}	
	}
	
	//Add new data in Stock Dispatch from table
	public int addStockDispatch(StockDispatch sd) {
		String sql="INSERT INTO stock_dispatch( id,approvaldate, approvedby, carriername, customeruid, dispatchedby, dispatchtype, dispatchuid,productuid, batchuid, remarks, status)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				sd.getId(),	
				sd.getApprovaldate(),
				sd.getApprovedby(),
				sd.getCarriername(),
				sd.getCustomeruid(),
				sd.getDispatchedby(),
				sd.getDispatchtype(),
				sd.getDispatchuid(),
				sd.getProductuid(),
				sd.getBatchuid(),
				sd.getRemarks(),
				sd.getStatus()
				);
	}
	
	public StockDispatch getStockDispatchById(Long id) {
	    String sql = "SELECT * FROM stock_dispatch WHERE id = ?";
	    try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(StockDispatch.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	public List<Map<String,Object>>fetchAllStockDispatch(){
		String sql="SELECT \r\n"
				+ "    sd.dispatchuid, sd.dispatchtype, sd.dispatchedby, sd.status, sd.carriername, sd.productuid,\r\n"
				+ "    sd.approvaldate, sd.id, sd.approvedby, sd.remarks,\r\n"
				+ "    di.dispatchcost, di.modeoftransport, di.transportdocumentnumber,\r\n"
				+ "    pd.unitofmeasure,\r\n"
				+ "    cd.contactnumber, cd.contactperson, cd.deliveryaddress\r\n"
				+ "FROM stock_dispatch sd\r\n"
				+ "JOIN customerdetails cd ON sd.customeruid = cd.customeruid\r\n"
				+ "JOIN productinfo pd ON sd.productuid = pd.productuid\r\n"
				+ "JOIN dispatchinformation di ON sd.carriername = di.carriername;\r\n"
				+ "\n";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateStockDispatch(StockDispatch sd) {
	    String sql = "UPDATE stock_dispatch SET dispatchtype=?, dispatchedby=?, approvedby=?, approvaldate=?,status=?,remarks=? WHERE id = ?";
	    return jdbcTemplate.update(sql,
	    		
	    		
	    		sd.getDispatchtype(),
	    		sd.getDispatchedby(),
	    		sd.getApprovedby(),
	    		sd.getApprovaldate(),
	    		sd.getStatus(),
	    		sd.getRemarks(),
	    		sd.getId()
	    		);
	}
	
	public void deleteStockDispatchById(Long id) {
		String sql="DELETE FROM stock_dispatch WHERE id = ?";
		 jdbcTemplate.update(sql,id);
	}
	
	public List<String>getBatchId(){
	    	String sql="select batchuid From batchinfo";
	    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("batchuid"));
	    }
	    
	   public List<String>getItemId(){
    	String sql="select productuid From productinfo";
    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }
	   public List<Map<String, Object>> getProductDetailsByuid(String productuid){
		   String sql= "SELECT unitofmeasure FROM productinfo WHERE productuid=?";
		   return jdbcTemplate.queryForList(sql, productuid);
	   }
	   
	  public List<Map<String, Object>> getbatchDetailsByuid(String batchuid){
	   String sql= "SELECT p.unitofmeasure, p.productuid FROM productinfo p JOIN batchinfo b ON p.productuid = b.productuid WHERE b.batchuid = ?";
	   return jdbcTemplate.queryForList(sql, batchuid);
   }
	
}

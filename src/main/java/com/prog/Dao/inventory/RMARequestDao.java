package com.prog.Dao.inventory;

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

import com.prog.model.erp.RMARequestForm;


@Repository
public class RMARequestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class RMARequestRowMapper implements RowMapper<RMARequestForm>{

		@Override
		public RMARequestForm mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			RMARequestForm rma=new RMARequestForm();
			rma.setId(rs.getLong("id"));
			rma.setReturnmerchandiseauthorizationuid(rs.getString("returnmerchandiseauthorizationuid"));
			rma.setCustomeruid(rs.getString("customeruid"));
			rma.setBatchuid(rs.getString("batchuid"));
			rma.setProductuid(rs.getString("productuid"));
			rma.setReturnuid(rs.getString("returnuid"));
			rma.setApprovalstatus(rs.getString("approvalstatus"));
			rma.setReturninstructions(rs.getString("returninstructions"));
			rma.setRestockingfee(rs.getString("restockingfee"));
			rma.setProcessedby(rs.getString("processedby"));
			rma.setDecisiondate(rs.getString("decisiondate"));
			rma.setReturntrakingnumber(rs.getString("returntrakingnumber"));
			rma.setReturnquantity(rs.getString("returnquantity"));
			return rma;
		}	
	}
	//Add new data in RMA Request Form 
	public int addRmaRequest(RMARequestForm rma) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
		String sql = "INSERT INTO returnmerchandiseauthorization(returnmerchandiseauthorizationuid, customeruid,batchuid, productuid, returnuid, "
				+ "approvalstatus, returninstructions, restockingfee, processedby, decisiondate, returntrakingnumber,returnquantity,insertdate,updatedate) "
				+ "VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

		return jdbcTemplate.update(sql,
		    rma.getReturnmerchandiseauthorizationuid(),
		    rma.getCustomeruid(),
		    rma.getBatchuid(),
		    rma.getProductuid(),
		    rma.getReturnuid(),
		    rma.getApprovalstatus(),
		    rma.getReturninstructions(),
		    rma.getRestockingfee(),
		    rma.getProcessedby(),
		    rma.getDecisiondate(),
		    rma.getReturntrakingnumber(),
		    rma.getReturnquantity(),
		    formattedTimestamp,
		    formattedTimestamp
		);

	}
	
	
	//View RMA Request data in list
	public RMARequestForm getRMARequestById(Long id) {
		String sql="select * from returnmerchandiseauthorization where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(RMARequestForm.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	public  List<Map<String,Object>> fetchALLRMARequest(){
		String sql="SELECT rma.id,rma.returnmerchandiseauthorizationuid,rma.approvalstatus, rma.returnquantity, rma.returntrakingnumber,\r\n"
				+ "	cd.customername,\r\n"
				+ "	pi.productname,\r\n"
				+ "	rd.dateofreturnrequest\r\n"
				+ "						FROM returnmerchandiseauthorization rma\r\n"
				+ "						 JOIN customerdetails cd ON rma.customeruid = cd.customeruid\r\n"
				+ "						 JOIN productinfo pi ON rma.productuid = pi.productuid\r\n"
				+ "						 JOIN returndetails rd ON rma.returnuid = rd.returnuid;";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateRMARequestData(RMARequestForm rma) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		String formattedTimestamp = LocalDateTime.now().format(formatter);                 
		
		String sql="UPDATE returnmerchandiseauthorization SET approvalstatus=?, returninstructions=?, restockingfee=?, processedby=?,"
				+ " decisiondate=?, returntrakingnumber=?,returnquantity = ?,updatedate=? WHERE id=?";


		return jdbcTemplate.update(sql,
				rma.getApprovalstatus(),
				rma.getReturninstructions(),
				rma.getRestockingfee(),
				rma.getProcessedby(),
				rma.getDecisiondate(),
				rma.getReturntrakingnumber(),
				rma.getReturnquantity(),
				formattedTimestamp,
				rma.getId()
				);
	}
	public void deleteRMARequestById(Long id) {
		String sql="DELETE FROM returnmerchandiseauthorization WHERE id=?";
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
   public List<Map<String, Object>> getItemDetailsByuid(String productuid){
	   String sql= "SELECT productname FROM productinfo WHERE productuid=?";
	   return jdbcTemplate.queryForList(sql, productuid);
   }
   
  public List<Map<String, Object>> getbatchDetailsByuid(String batchuid){
   String sql= "SELECT p.productname, p.productuid FROM productinfo p JOIN batchinfo b ON p.productuid = b.productuid WHERE b.batchuid = ?";
   return jdbcTemplate.queryForList(sql, batchuid);
}
}

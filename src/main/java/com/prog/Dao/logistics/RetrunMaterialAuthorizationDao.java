package com.prog.Dao.logistics;
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

import com.prog.model.erp.RetrunMaterialAuthorization;


@Repository
public class RetrunMaterialAuthorizationDao {
	
	@Autowired
	 private JdbcTemplate jdbctemplate;
	public int add(RetrunMaterialAuthorization rma) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
		   String sql = "insert into returnmaterialauthorization " 
		        + "( returnmaterialauthorizationuid,batchuid, quantityreturned, returndate, requestername, returnreason, productuid,returnapprovedby, suppliername, returnstatus, remarks, insertdate, updatedate) "
		        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    return jdbctemplate.update(sql,
		       
		        rma.getReturnmaterialauthorizationuid(),
		        rma.getBatchuid(),
		        rma.getQuantityreturned(),
		        rma.getReturndate(),
		        rma.getRequestername(),
		        rma.getReturnreason(),
		        rma.getProductuid(),
		        rma.getReturnapprovedby(),
		        rma.getSuppliername(),
		        rma.getReturnstatus(),
		        rma.getRemarks(),
		        formattedTimestamp,
		        formattedTimestamp
		    );
		}

	   
	   public List<RetrunMaterialAuthorization> show() {
		   String sql = "select * from returnmaterialauthorization ";
		return jdbctemplate.query(sql, new RMAentityRowMapper());
	   }
	   public List<Map<String,Object>> fetchAllData(){
		   String sql="SELECT rma.id,rma.returnmaterialauthorizationuid, rma.returnapprovedby,rma.returndate,rma.quantityreturned, rma.returnstatus,rma.remarks,rma.suppliername,pd.unitofmeasure,pd.productname,rma.requestername\r\n"
		   		+ "FROM returnmaterialauthorization rma\r\n"
		   		+ "	JOIN productinfo pd ON rma.productuid = pd.productuid;	";
		   return jdbctemplate.queryForList(sql);
	   }
	   public RetrunMaterialAuthorization getById(Long id) {
		   String sql = "SELECT * FROM returnmaterialauthorization WHERE id = ?";
	       return jdbctemplate.queryForObject(sql, new RMAentityRowMapper() , id);
	   }
	   
	  	   
	   public static class RMAentityRowMapper implements RowMapper<RetrunMaterialAuthorization>{
			 @Override
			 public RetrunMaterialAuthorization mapRow(ResultSet rs, int rowNum) throws SQLException {
				 RetrunMaterialAuthorization em = new RetrunMaterialAuthorization();
				 em.setId(rs.getLong("id"));
				 em.setReturnmaterialauthorizationuid(rs.getString("returnmaterialauthorizationuid"));
				 em.setBatchuid(rs.getString("batchuid"));
				 em.setQuantityreturned(rs.getString("quantityreturned"));
				 em.setReturndate(rs.getString("returndate"));
				 em.setRequestername(rs.getString("requestername"));
				 em.setReturnreason(rs.getString("returnreason"));
				 em.setProductuid(rs.getString("productuid"));
				 em.setSuppliername(rs.getString("suppliername"));
				 em.setReturnapprovedby(rs.getString("returnapprovedby"));
				 em.setReturnstatus(rs.getString("returnstatus"));
				 em.setRemarks(rs.getString("remarks"));
				 return em;
			 }
	   }


	   public List<String>getBatchId(){
		  	String sql="select batchuid From batchinfo";
		  	return jdbctemplate.query(sql, (rs, rowNum) -> rs.getString("batchuid"));
		  }
	   
	   		  
		 public List<String>getItemId(){
			String sql="select productuid From productinfo";
			return jdbctemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
		}
		 public List<Map<String, Object>> getProductDetailsByuid(String productuid){
			   String sql= "SELECT productname,quantity,unitofmeasure FROM productinfo WHERE productuid=?";
			   return jdbctemplate.queryForList(sql, productuid);
		 }
		 
		public List<Map<String, Object>> getbatchDetailsByuid(String batchuid){
		 String sql= "SELECT p.productname,p.unitofmeasure, p.productuid FROM productinfo p JOIN batchinfo b ON p.productuid = b.productuid WHERE b.batchuid = ?";
		 return jdbctemplate.queryForList(sql, batchuid);
		}
		public int deleteById(Long id) {
			       String sql = "DELETE FROM returnmaterialauthorization WHERE id = ?";
			       return jdbctemplate.update(sql,id);
			   }	
		public int updaterma(RetrunMaterialAuthorization rm) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	       
		    String sql = "UPDATE returnmaterialauthorization SET  returndate = ?,"
		            + " requestername = ?, returnreason = ?, "
		            + "  quantityreturned = ?,"
		            + " returnapprovedby=?, "
		            + "suppliername=?, returnstatus=?, remarks=?, updatedate = ? \r\n"
		            + "WHERE id = ?";
		    return jdbctemplate.update(sql,
		            
		            rm.getReturndate(),
		            rm.getRequestername(),
		            rm.getReturnreason(),
		            rm.getQuantityreturned(),
		            rm.getReturnapprovedby(),
		            rm.getSuppliername(),
		            rm.getReturnstatus(),
		            rm.getRemarks(),
		            formattedTimestamp,
		            rm.getId()); // Ensure the ID is included in the update
		}
		
} 


	   


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

import com.prog.model.erp.retailerfeedbackorcomplaint;
@Repository
public class RetailerFeedbackOrComplaintDao {
	
	@Autowired
	private  JdbcTemplate jdbctemplate;

	public int add(retailerfeedbackorcomplaint rf) {
			//insert
			   String sql = "insert into retailerfeedbackorcomplaint " 
			        + "( retailerfeedbackorcomplaintuid, selleruid,date,type, description ,priority,status,insertdate,updatedate) "
			      + "values (?, ?, ?, ?, ?,?,?,?,?)";
			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
			  
			    return jdbctemplate.update(sql,
			 
			    		rf.getRetailerfeedbackorcomplaintuid(),
			    		rf.getSelleruid(),
			    		rf.getDate(),
			    		rf.getType(),
			    		rf.getDescription(),
			    		rf.getPriority(),
			    		rf.getStatus(),
			    		formattedTimestamp,
		                formattedTimestamp
			    		
			    );
			}
	
			//list
		   public List<retailerfeedbackorcomplaint> show() {
			   String sql = "select * from retailerfeedbackorcomplaint ";
			return jdbctemplate.query(sql, new RFentityRowMapper());
		   }
		  
		   //rowmapper
		   public retailerfeedbackorcomplaint getById(Long id) {
			   String sql = "SELECT * FROM retailerfeedbackorcomplaint WHERE id = ?";
		       return jdbctemplate.queryForObject(sql, new RFentityRowMapper() , id);
		   }
		   
		  
		   
		   
		   public static class RFentityRowMapper implements RowMapper<retailerfeedbackorcomplaint>{
				 @Override
				 public retailerfeedbackorcomplaint mapRow(ResultSet rs, int rowNum) throws SQLException {
					 retailerfeedbackorcomplaint amc = new retailerfeedbackorcomplaint();
					 amc.setId(rs.getLong("id"));
					 amc.setRetailerfeedbackorcomplaintuid(rs.getString("retailerfeedbackorcomplaintuid"));
					 amc.setSelleruid(rs.getString("selleruid"));
					 amc.setDate(rs.getString("date"));
					 amc.setType(rs.getString("type"));
					 amc.setDescription(rs.getString("description"));
					 amc.setPriority(rs.getString("priority"));
					 amc.setStatus(rs.getString("status"));
					return amc;
				 }
		   }
		   //list
		   public List<Map<String,Object>> findAll() {
			   String sql = "select rfc.id, rfc.selleruid, rfc.date, rfc.type, rfc.status, sr.companyname from retailerfeedbackorcomplaint rfc "
			   		+ "JOIN sellerregistration sr ON sr.selleruid = rfc.selleruid ";
			return jdbctemplate.queryForList(sql);
		   }
		   
		   
		 //update 
		   public int updaterf(retailerfeedbackorcomplaint rf) {
		       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		       String formattedTimestamp = LocalDateTime.now().format(formatter);
		       String sql = "UPDATE retailerfeedbackorcomplaint SET date = ?, type = ?, description = ?, priority = ?, updatedate = ?, status = ? "
		               + "WHERE id = ?";
		       return jdbctemplate.update(sql,
		               rf.getDate(),
		               rf.getType(),
		               rf.getDescription(),
		               rf.getPriority(),
		               formattedTimestamp,  // updatedate
		               rf.getStatus(),      // status
		               rf.getId());        // id
		   }
	
		   //delete
			public int deleteById(Long id) {
				String sql = "DELETE FROM retailerfeedbackorcomplaint WHERE id = ?";
			     return jdbctemplate.update(sql,id);
		    }	
		
	}
		
		
	
			
	
	

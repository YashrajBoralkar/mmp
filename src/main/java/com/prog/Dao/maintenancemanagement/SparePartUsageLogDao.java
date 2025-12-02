package com.prog.Dao.maintenancemanagement;

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

import com.prog.model.erp.SparePartUsageLog;


@Repository
public class SparePartUsageLogDao {
	@Autowired
	 private JdbcTemplate jdbctemplate;

	 public static class SparePartUsageLogRowMapper implements RowMapper<SparePartUsageLog>{
		 @Override
		 public SparePartUsageLog mapRow(ResultSet rs, int rowNum) throws SQLException {
			 SparePartUsageLog spul = new SparePartUsageLog();
			 spul.setId(rs.getLong("id"));
			 spul.setSparepartusageuid(rs.getString("sparepartusageuid"));
			 spul.setSparepartsinventoryuid(rs.getString("sparepartsinventoryuid"));
			 spul.setDateofusage(rs.getString("dateofusage"));
			 spul.setUsedforequipment(rs.getString("usedforequipment"));
			 spul.setQuantityused(rs.getString("quantityused"));
			 spul.setUsedby(rs.getString("usedby"));
			 spul.setReasonforuse(rs.getString("reasonforuse"));
			
			return spul;
		 }
   }	
	 
	 public int addSparePartUsageLogData(SparePartUsageLog spul) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
		   String sql = "insert into sparepartusagelog (id,sparepartusageuid, sparepartsinventoryuid, dateofusage,usedforequipment,quantityused,"
		   		+ "usedby,reasonforuse,insertdate,updatedate) "
		      + "values (?,?,?,?,?,?,?,?,?,?)";
		  
		    return jdbctemplate.update(sql,
		    		spul.getId(),
		    		spul.getSparepartusageuid(),
		    		spul.getSparepartsinventoryuid(),
		    		spul.getDateofusage(),
		    		spul.getUsedforequipment(),
		    		spul.getQuantityused(),
		    		spul.getUsedby(),
		    		spul.getReasonforuse(),			    		
		    		formattedTimestamp,
		            formattedTimestamp
		    		
		    );
		}
	 
	//list
	   public List<Map<String,Object>> AllSparePartUsageLogList() {
		   String sql = "select id,sparepartusageuid, sparepartsinventoryuid, dateofusage,usedforequipment,\r\n"
		   		+ "quantityused,usedby,reasonforuse from sparepartusagelog";
		return jdbctemplate.queryForList(sql);
	   }
	 //row mapper
	   public SparePartUsageLog getSparePartUsageLogById(Long id) {
		   String sql = "SELECT * FROM sparepartusagelog WHERE id = ?";
	       return jdbctemplate.queryForObject(sql, new SparePartUsageLogRowMapper() , id);
	   }
	   
	   //delete
		public int deleteSparePartUsageLogById(long id) {
			       String sql = "DELETE FROM sparepartusagelog WHERE id = ?";
			       return jdbctemplate.update(sql,id);
	    }
		
		//update
		public int updateSparePartUsageLog(SparePartUsageLog spul) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	        String sql = "UPDATE sparepartusagelog SET   dateofusage=? ,usedforequipment=?,"
	        		+ "quantityused=?,usedby=?,reasonforuse=?,updatedate=?\r\n"
	            + "WHERE id = ?";
	    return jdbctemplate.update(sql,
	             spul.getDateofusage(),
	             spul.getUsedforequipment(),
	             spul.getQuantityused(),
	             spul.getUsedby(),
	             spul.getReasonforuse(),
	    		 formattedTimestamp,
	            spul.getId());
	}

	//fetch 
		public List<String> getSparepartDetailsByuid() {
			 String sql= "SELECT sparepartsinventoryuid FROM sparepartsinventory ";
			 return jdbctemplate.query(sql,(rs,rowNum)->rs.getString("sparepartsinventoryuid"));				
		}
}

package com.prog.Dao.maintenancemanagement;

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

import com.prog.model.erp.DowntimeAnalysis;
import com.prog.model.erp.EquipmentMaster;

@Repository
public class DowntimeAnalysisDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class MaintenanceHistoryLogRowMapper implements RowMapper<DowntimeAnalysis>{

		@Override
		public DowntimeAnalysis mapRow(ResultSet rs, int rowNum) throws SQLException {
			DowntimeAnalysis da=new DowntimeAnalysis();
			 	da.setId(rs.getLong("id"));
	            da.setDowntimeanalysisuid(rs.getString("downtimeanalysisuid"));
	            da.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
	            da.setTotaldowntime(rs.getString("totaldowntime"));
	            da.setNoofbreakdown(rs.getString("noofbreakdown"));
	            da.setFrequentissues(rs.getString("frequentissues"));
	            da.setPreventiveactionssuggested(rs.getString("preventiveactionssuggested"));
	            da.setRemarks(rs.getString("remarks"));
	           
			return da;
		}
		
	}

	 public int saveDowntimeanalysis(DowntimeAnalysis dtaf) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	       
	        String sql = "INSERT INTO downtimeanalysis (downtimeanalysisuid, equipmentmasteruid, totaldowntime, noofbreakdown, frequentissues, preventiveactionssuggested, remarks, insertdate, updatedate) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	return jdbcTemplate.update(sql,
	          
	           dtaf.getDowntimeanalysisuid(),
	           dtaf.getEquipmentmasteruid(),
	           dtaf.getTotaldowntime(),
	           dtaf.getNoofbreakdown(),
	           dtaf.getFrequentissues(),
	           dtaf.getPreventiveactionssuggested(),
	           dtaf.getRemarks(),
	           formattedTimestamp,
	           formattedTimestamp
	        );
	    }

	 public DowntimeAnalysis getDowntimeAnalysisById(Long id) {
			String sql= "select * from downtimeanalysis where id=?";
			try {
		    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(DowntimeAnalysis.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
		}
		
		public List<Map<String,Object>> getAlldowntimeAnalysisData(){
			String sql="select da.id,da.downtimeanalysisuid,em.equipmentmasteruid,em.equipmentname, da.noofbreakdown,da.totaldowntime,\r\n"
					+ "da.frequentissues,da.preventiveactionssuggested,da.remarks \r\n"
					+ "from downtimeanalysis da\r\n"
					+ "JOIN equipmentmaster em ON da.equipmentmasteruid = em.equipmentmasteruid;";
			return jdbcTemplate.queryForList(sql);
		}
	 
		public int deleteDowntimeAnalysisById(long id) {
	        String sql = "DELETE FROM downtimeanalysis WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
		
		  public int updateDowntimeAnalysis(DowntimeAnalysis dt) {
			  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			  String formattedTimestamp = LocalDateTime.now().format(formatter);
			      
		        String sql = "UPDATE downtimeanalysis SET frequentissues = ?, preventiveactionssuggested = ?, remarks = ?, updatedate = ? " +
		                     "WHERE id = ?";

		        return jdbcTemplate.update(sql,
		            dt.getFrequentissues(),
		            dt.getPreventiveactionssuggested(),
		            dt.getRemarks(),
		            formattedTimestamp,
		            dt.getId()
		        );
		    }
		
	public List<String> getEquipmentDetailsById(){
		String sql="select equipmentmasteruid from breakdownmaintenancereport";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("equipmentmasteruid"));
	}
	public EquipmentMaster getEquipmentDetails(String equipmentmasteruid){
		String sql="SELECT equipmentname FROM equipmentmaster where equipmentmasteruid=? \r\n";
				
    	return jdbcTemplate.queryForObject(sql, new Object[] {equipmentmasteruid}, (rs, rowNum) -> {
    		EquipmentMaster eu=new EquipmentMaster();
    		eu.setEquipmentname(rs.getString("equipmentname"));
    		return eu;
    	});
	}
	
	public String getdowntimeduration(String equipmentmasteruid) {
		String sql="SELECT SUM(downtimeduration) AS totaldowntime\r\n"
				+ "FROM  breakdownmaintenancereport\r\n"
				+ "WHERE equipmentmasteruid = ?\r\n"
				+ "GROUP BY equipmentmasteruid;\r\n";
				
		
        return jdbcTemplate.queryForObject(sql, new Object[]{equipmentmasteruid}, String.class);

	}




}

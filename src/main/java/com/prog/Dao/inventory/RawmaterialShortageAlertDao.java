package com.prog.Dao.inventory;

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

import com.prog.model.erp.rawmaterialshortagealert;



@Repository

public class RawmaterialShortageAlertDao {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	

	 private static final String INSERT_SQL = 
			    "INSERT INTO rawmaterialshortagealert " +
			    "(rawmaterialshortagealertuid, rawmaterialuid, rawmaterialname, minimumstocklevel,currentstocklevel, alertdate, generatedby, insertdate, updatedate) " +
			    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 public int addMaterialShortageAlert(rawmaterialshortagealert alert) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedTimestamp = LocalDateTime.now().format(formatter);

		    return jdbcTemplate.update(INSERT_SQL,
		        alert.getRawmaterialshortagealertuid(),
		        alert.getRawmaterialuid(),
		        alert.getRawmaterialname(),
		        alert.getMinimumstocklevel(),
		        alert.getCurrentstocklevel(),
		        alert.getAlertdate(),
		        alert.getGeneratedby(),
		        formattedTimestamp,
		        formattedTimestamp
		    );
		}
	 
	 public List<Map<String, Object>> getAllMaterialShortageAlerts() {
		 String sql = "SELECT * FROM rawmaterialshortagealert;";
		    return jdbcTemplate.queryForList(sql);
		}

	 public int deleteMaterialShortageAlerts(Long id) {
	        String sql = "DELETE FROM rawmaterialshortagealert WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }

	 public int updateMaterialShortageAlerts(rawmaterialshortagealert alert) {
		    String sql = "UPDATE rawmaterialshortagealert SET " +
		                 "minimumstocklevel = ?, " +
		                 "currentstocklevel = ?, " +
		                 "alertdate = ?, " +
		                 "generatedby = ?, " +
		                 "updatedate = ? " +
		                 "WHERE id = ?";

		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedTimestamp = LocalDateTime.now().format(formatter);

		    return jdbcTemplate.update(sql,
		        alert.getMinimumstocklevel(),
		        alert.getCurrentstocklevel(),
		        alert.getAlertdate(),
		        alert.getGeneratedby(),
		        formattedTimestamp,  // updatedate
		        alert.getId()        // WHERE id = ?
		    );
		}

	 
	 
	 
	 
	 public List<Map<String, Object>> getDataByrawmaterialUid(String rawmaterialuid) {
	        String sql = "SELECT rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid = ?";
	        return jdbcTemplate.queryForList(sql, rawmaterialuid);
	    }
	    

	    // Retrieve all product UIDs
	    public List<String> fetchrawmaterialUIds() {
	        String sql = "SELECT rawmaterialuid FROM rawmaterialinfo";
	        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("rawmaterialuid"));
	    }
	    
	    public rawmaterialshortagealert getMaterialShortageAlertsById(Long id) {
	        String sql = "SELECT * FROM rawmaterialshortagealert WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new MaterialShortageAlertsRowMapper(), id);
	    }
	    
	    class MaterialShortageAlertsRowMapper implements RowMapper<rawmaterialshortagealert> {
			@Override
			public rawmaterialshortagealert mapRow(ResultSet rs, int rowNum) throws SQLException {
				rawmaterialshortagealert alert = new rawmaterialshortagealert();
				alert.setId(rs.getLong("id"));
				alert.setRawmaterialshortagealertuid(rs.getString("rawmaterialshortagealertuid"));
				alert.setRawmaterialuid(rs.getString("rawmaterialuid"));
				alert.setRawmaterialname(rs.getString("rawmaterialname"));
				alert.setMinimumstocklevel(rs.getString("minimumstocklevel"));
				alert.setCurrentstocklevel(rs.getString("currentstocklevel"));
				alert.setAlertdate(rs.getString("alertdate"));
				alert.setGeneratedby(rs.getString("generatedby"));


				return alert;
			}

	    }
	    
	   
	    

}

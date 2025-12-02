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

import com.prog.model.erp.TechnicianMaster;

@Repository
public class TechnicianMasterDao {
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	 // ✅ RowMapper for Technicianmaster
	    public static class TechnicianMasterMapper implements RowMapper<TechnicianMaster> {
	        @Override
	        public TechnicianMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	TechnicianMaster technician = new TechnicianMaster();
	            technician.setId(rs.getLong("id"));
	            technician.setTechnicianmasteruid(rs.getString("technicianmasteruid"));
	            technician.setTechnicianname(rs.getString("technicianname"));
	            technician.setContactnumber(rs.getString("contactnumber"));
	            technician.setDepartment(rs.getString("department"));
	            technician.setSkillset(rs.getString("skillset"));
	            technician.setAvailabilitystatus(rs.getString("availabilitystatus"));
	            return technician;
	        }
	    }
	    
	 // ✅ Add Technician
	    public int addTechnician(TechnicianMaster technicianmaster) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

	        String sql = "INSERT INTO technicianmaster (technicianmasteruid, technicianname, contactnumber, department, skillset, availabilitystatus, insertdate, updatedate) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        return jdbcTemplate.update(sql,
	                technicianmaster.getTechnicianmasteruid(),
	                technicianmaster.getTechnicianname(),
	                technicianmaster.getContactnumber(),
	                technicianmaster.getDepartment(),
	                technicianmaster.getSkillset(),
	                technicianmaster.getAvailabilitystatus(),
	                formattedTimestamp,
	                formattedTimestamp
	        );
	    }

	    // ✅ Fetch Technician by ID
	    public TechnicianMaster getTechnicianMasterById(Long id) {
	        String sql = "SELECT * FROM technicianmaster WHERE id = ?";
	        try {
		    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(TechnicianMaster.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
	   }

	    
	    public List<Map<String,Object>> findAllTechnicianMasterList() {
	        String sql = "SELECT id,technicianmasteruid, technicianname, contactnumber, department, skillset, "
	        		+ "availabilitystatus FROM technicianmaster";  // No need for 'id' filtering to fetch all records
	        return jdbcTemplate.queryForList(sql);  // Use query() for multiple records
	    }

	    public int updateTechnicianMaster(TechnicianMaster technicianmaster) {
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
			 
	    	String sql = "UPDATE technicianmaster SET technicianname=?, contactnumber=?, department=?, skillset=?, "
	    			+ "availabilitystatus=?, updatedate=? WHERE id=?";
	    	return jdbcTemplate.update(sql,
	                        technicianmaster.getTechnicianname(),
	                        technicianmaster.getContactnumber(),
	                        technicianmaster.getDepartment(),
	                        technicianmaster.getSkillset(),
	                        technicianmaster.getAvailabilitystatus(),
	                        formattedTimestamp,
	                        technicianmaster.getId());
	               
	    }
	    
	 // ✅ Delete Technician by ID
	    public int deleteTechnicianMasterById(Long id) {
	        String sql = "DELETE FROM technicianmaster WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }

}

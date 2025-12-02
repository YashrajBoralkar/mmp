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

import com.prog.model.erp.SparePartsInventoryForm;

@Repository
public class SparePartsInventoryDao {
@Autowired JdbcTemplate jdbcTemplate;
	
	public static class SparePartsInventoryRowMapper implements RowMapper<SparePartsInventoryForm>{

		@Override
		public SparePartsInventoryForm mapRow(ResultSet rs, int rowNum) throws SQLException {
			SparePartsInventoryForm spif=new SparePartsInventoryForm();
			spif.setId(rs.getLong("id"));
			spif.setSparepartsinventoryuid(rs.getString("sparepartsinventoryuid"));
			spif.setSparepartsname(rs.getString("sparepartsname"));
			spif.setSparepartsdescription(rs.getString("sparepartsdescription"));
			spif.setCategory(rs.getString("category"));
			spif.setUnitofmeasure(rs.getString("unitofmeasure"));
			spif.setCurrentstocklevel(rs.getString("currentstocklevel"));
			spif.setMinimimstocklevel(rs.getString("minimimstocklevel"));
			spif.setLocation(rs.getString("location"));
			spif.setSuppliername(rs.getString("suppliername"));
			spif.setLastuseddate(rs.getString("lastuseddate"));
			
			return spif;
		}
	}
	
	public int addSparePartsInventoryData(SparePartsInventoryForm spid) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		String sql=" insert into sparepartsinventory(id,sparepartsinventoryuid,sparepartsname,sparepartsdescription,"
				+ "category,unitofmeasure,currentstocklevel,minimimstocklevel,location,suppliername,lastuseddate,"
				+ "insertdate,updatedate)values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		return jdbcTemplate.update(sql,
				spid.getId(),
				spid.getSparepartsinventoryuid(),
				spid.getSparepartsname(),
				spid.getSparepartsdescription(),
				spid.getCategory(),
				spid.getUnitofmeasure(),
				spid.getCurrentstocklevel(),
				spid.getMinimimstocklevel(),
				spid.getLocation(),
				spid.getSuppliername(),
				spid.getLastuseddate(),
				formattedTimestamp,
				formattedTimestamp
				);
	}
	 public SparePartsInventoryForm getSparePartsInventoryById(Long id) {
		 String sql="select * from sparepartsinventory where id=?";
		 try {
		    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(SparePartsInventoryForm.class));
		    } catch (EmptyResultDataAccessException e) {
		        return null; // Or throw a custom exception if you prefer
		    }
	 }
	
	 public List<Map<String,Object>> getAllSparePartsInventory(){
		 String sql="select id,sparepartsinventoryuid,sparepartsname,sparepartsdescription,"
		 		+ "category,unitofmeasure,currentstocklevel,minimimstocklevel,location,suppliername,lastuseddate from sparepartsinventory";
		 		return jdbcTemplate.queryForList(sql);
	 }
	 
	 public int updateSparePartsInventory(SparePartsInventoryForm spid) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
			
		 String sql="update sparepartsinventory set sparepartsname=?,sparepartsdescription=?,category=?,unitofmeasure=?,currentstocklevel=?,"
		 		+ "minimimstocklevel=?,location=?,suppliername=?,lastuseddate=?,  updatedate=? where id=?";
		 return jdbcTemplate.update(sql,
				 spid.getSparepartsname(),
				 spid.getSparepartsdescription(),
				 spid.getCategory(),
				 spid.getUnitofmeasure(),
				 spid.getCurrentstocklevel(),
				 spid.getMinimimstocklevel(),
				 spid.getLocation(),
				 spid.getSuppliername(),
				 spid.getLastuseddate(),
				 formattedTimestamp,
				 spid.getId()		 
				 );
	 }
	 
	 public void deleteSparePartsInventoryById(Long id) {
		 String sql="delete from sparepartsinventory where id=?";
		 jdbcTemplate.update(sql,id);
	 }

}

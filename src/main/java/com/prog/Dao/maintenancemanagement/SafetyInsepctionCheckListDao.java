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

import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.SafetyInsepctionCheckList;

@Repository
public class SafetyInsepctionCheckListDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 public static class SafetyInspectionRowMapper implements RowMapper<SafetyInsepctionCheckList> {
	        @Override
	        public SafetyInsepctionCheckList mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	SafetyInsepctionCheckList checklist = new SafetyInsepctionCheckList();
	        	checklist.setId(rs.getLong("id"));
	        	checklist.setSafetyinspectionchecklistuid(rs.getString("safetyinspectionchecklistuid"));
	        	checklist.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
	        	checklist.setInspectiondate(rs.getString("inspectiondate"));
	        	checklist.setChecklistitems(rs.getString("checklistitems"));
	        	checklist.setInspectedby(rs.getString("inspectedby"));
	        	checklist.setRemarks(rs.getString("remarks"));
	        	checklist.setStatus(rs.getString("status"));
	                  return checklist;
	        }
	    }
	
	 public int addSafetyInspection(SafetyInsepctionCheckList inspection) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

		     String sql = "INSERT INTO safetyinspectionchecklist (safetyinspectionchecklistuid, equipmentmasteruid, inspectiondate, checklistitems, inspectedby, remarks, status, insertdate, updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
	        return jdbcTemplate.update(sql,
	                inspection.getSafetyinspectionchecklistuid(),
	                inspection.getEquipmentmasteruid(),
	                inspection.getInspectiondate(),
	                inspection.getChecklistitems(),
	                inspection.getInspectedby(),
	                inspection.getRemarks(),
	                inspection.getStatus(),
	                formattedTimestamp,
	                formattedTimestamp
	        );
	    }
	    
	 public List<Map<String, Object>> getAllSafetyInspection() {
	        String sql = "SELECT s.id, s.safetyinspectionchecklistuid, s.inspectiondate, s.inspectedby, " +
	                     "s.checklistitems, s.remarks, em.equipmentname, em.equipmentmasteruid , s.status, s.insertdate, s.updatedate " +
	                     "FROM safetyinspectionchecklist s " +
	                     " JOIN equipmentmaster em ON s.equipmentmasteruid = em.equipmentmasteruid";

	        return jdbcTemplate.queryForList(sql);
	    }
	 
	 public SafetyInsepctionCheckList getSafetyInspectionById(Long id) {
	        String sql = "SELECT * FROM safetyinspectionchecklist WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new SafetyInspectionRowMapper(), id);
	    }
	 
	 public int updateSafetyInspection(SafetyInsepctionCheckList checklist) {
		    String sql = "UPDATE safetyinspectionchecklist SET  inspectiondate = ?, checklistitems = ?,  inspectedby = ?, remarks = ?, Status = ?, updatedate = ? WHERE id = ?";
	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		       return jdbcTemplate.update(sql,		    		                 
		    		   checklist.getInspectiondate(),
		    		   checklist.getChecklistitems(),
		    		   checklist.getInspectedby(),
		    		   checklist.getRemarks(),
		    		   checklist.getStatus(), 		    		    
		    		   formattedTimestamp,	                
		    		   checklist.getId()
	        );
	    }      
	    	    
	    public int deleteSafetyInspection(Long id) {
	        String sql = "DELETE FROM safetyinspectionchecklist WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	    	 
	public List<String> getEquipmentDetailsById(){
		String sql="select equipmentmasteruid from equipmentmaster";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("equipmentmasteruid"));
	}
	public EquipmentMaster getEquipmentDetails(String equipmentmasteruid){
		String sql="select equipmentname from equipmentmaster where equipmentmasteruid=? ";
    	return jdbcTemplate.queryForObject(sql, new Object[] {equipmentmasteruid}, (rs, rowNum) -> {
    		EquipmentMaster eu=new EquipmentMaster();
    		eu.setEquipmentname(rs.getString("equipmentname"));
    		return eu;
    	});
	}
}

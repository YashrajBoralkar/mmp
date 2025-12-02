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

import com.prog.model.erp.CalibrationRecord;
import com.prog.model.erp.EquipmentMaster;

@Repository
public class CalibrationRecordDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static class CalibRecordRowMapper implements RowMapper<CalibrationRecord> {

		@Override
		public CalibrationRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			CalibrationRecord record = new CalibrationRecord();
			record.setId(rs.getLong("id"));
			record.setCalibrationrecorduid(rs.getString("calibrationrecorduid"));
			record.setCalibrationdate(rs.getString("calibrationdate"));
			record.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
			record.setInserteddate(rs.getString("insertedDate"));
			record.setUpdateddate(rs.getString("updatedDate"));
			record.setNextduedate(rs.getString("nextduedate"));
			record.setPerformedby(rs.getString("performedby"));
			record.setSerialnumber(rs.getString("serialnumber"));
			record.setStatus(rs.getString("status"));
			return record;
		}
	}
	
	
	public int addCalibrationRecord(CalibrationRecord record) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        
		String sql = "INSERT INTO calibrationrecord (calibrationrecorduid, calibrationdate, equipmentmasteruid, nextduedate, performedby, serialnumber, status, updateddate,inserteddate) VALUES (?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,
				record.getCalibrationrecorduid(), 
				record.getCalibrationdate(),
				record.getEquipmentmasteruid(),
				record.getNextduedate(), 
				record.getPerformedby(),
				record.getSerialnumber(),
				record.getStatus(),
				formattedTimestamp,
        		formattedTimestamp);
	}

	public void deleteCalibrationRecord(Long id) {
		String sql = "DELETE FROM calibrationrecord WHERE id = ?";
		jdbcTemplate.update(sql, id);
		
	}

	public int updateCalibrationRecord(CalibrationRecord record) {
		String sql = "UPDATE calibrationrecord SET calibrationdate = ?, nextduedate = ?, performedby = ?, serialnumber = ?, status = ?, updateddate = ? WHERE id = ?";
		return jdbcTemplate.update(sql,
				record.getCalibrationdate(), 
				record.getNextduedate(),
				record.getPerformedby(), 
				record.getSerialnumber(), 
				record.getStatus(), 
				record.getUpdateddate(),
				record.getId()
				);
	
	}

	public CalibrationRecord getCalibrationRecordById(Long id) {
		String sql = "SELECT * from calibrationrecord WHERE id = ?";

		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(CalibrationRecord.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }	}

	public List<Map<String, Object>> getAllCalibrationRecord() {
		String sql = "select cr.id,cr.calibrationrecorduid,cr.calibrationdate, em.equipmentname, cr.nextduedate,\r\n"
				+ "cr.performedby, cr.serialnumber, cr.status, em.equipmentmasteruid  \r\n"
				+ "from calibrationrecord cr\r\n"
				+ "join  equipmentmaster em ON em.equipmentmasteruid = cr.equipmentmasteruid;";
		return jdbcTemplate.queryForList(sql);
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

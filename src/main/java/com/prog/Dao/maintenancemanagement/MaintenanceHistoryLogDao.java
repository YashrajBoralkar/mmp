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

import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.MaintenanceHistoryLogForm;


@Repository
public class MaintenanceHistoryLogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static class MaintenanceHistoryLogRowMapper implements RowMapper<MaintenanceHistoryLogForm>{

		@Override
		public MaintenanceHistoryLogForm mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaintenanceHistoryLogForm mhl=new MaintenanceHistoryLogForm();
			mhl.setId(rs.getLong("id"));
			mhl.setMaintenancehistoryloguid(rs.getString("maintenancehistoryloguid"));
			mhl.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
			mhl.setMaintenancetype(rs.getString("maintenancetype"));
			mhl.setServicedate(rs.getString("servicedate"));
			mhl.setServicedetails(rs.getString("servicedetails"));
			mhl.setTechnicianname(rs.getString("technicianname"));
			mhl.setPartsused(rs.getString("partsused"));
			mhl.setNextduedate(rs.getString("nextduedate"));
			return mhl;
		}	
	}
	
	public int addMaintenanceHistoryLogData(MaintenanceHistoryLogForm mhl) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        String sql="insert into maintenancehistorylog (id,maintenancehistoryloguid,equipmentmasteruid,maintenancetype,servicedate,servicedetails,"
        		+ "technicianname,partsused,nextduedate,insertdate,updatedate)values(?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
        		mhl.getId(),
        		mhl.getMaintenancehistoryloguid(),
        		mhl.getEquipmentmasteruid(),
        		mhl.getMaintenancetype(),
        		mhl.getServicedate(),
        		mhl.getServicedetails(),
        		mhl.getTechnicianname(),
        		mhl.getPartsused(),
        		mhl.getNextduedate(),
        		formattedTimestamp,
        		formattedTimestamp
        		);
	}
	
	public MaintenanceHistoryLogForm getMaintenanceHistoryLogById(Long id) {
		String sql= "select * from maintenancehistorylog where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(MaintenanceHistoryLogForm.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	public List<Map<String,Object>> getAllMaintenanceHistoryLogData(){
		String sql="select mhl.id, mhl.maintenancehistoryloguid,mhl.maintenancetype,mhl.nextduedate,mhl.partsused,mhl.servicedate,\r\n"
				+ "mhl.servicedetails,mhl.technicianname,em.equipmentname,em.equipmentmasteruid  \r\n"
				+ "from maintenancehistorylog mhl\r\n"
				+ "join equipmentmaster em ON em.equipmentmasteruid = mhl.equipmentmasteruid;\r\n";
		return jdbcTemplate.queryForList(sql);
	}
	
	public int updateMaintenanceHistoryLogData(MaintenanceHistoryLogForm mhl) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		 String sql="update maintenancehistorylog set maintenancetype=?,servicedate=?,servicedetails=?,"
		 		+ "technicianname=?,partsused=?,nextduedate=?,updatedate=? where id=?";
		 return jdbcTemplate.update(sql,
				 mhl.getMaintenancetype(),
				 mhl.getServicedate(),
				 mhl.getServicedetails(),
				 mhl.getTechnicianname(),
				 mhl.getPartsused(),
				 mhl.getNextduedate(),
				 formattedTimestamp,
				 mhl.getId()
				 );
	}
	
	public void deleteMaintenanceHistoryLogById(Long id) {
		String sql="delete from maintenancehistorylog where id=?";
		 jdbcTemplate.update(sql,id);
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

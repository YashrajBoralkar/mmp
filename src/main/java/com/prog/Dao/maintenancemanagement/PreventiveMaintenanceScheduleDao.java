package com.prog.Dao.maintenancemanagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.PreventiveMaintenanceSchedule;

@Repository
public class PreventiveMaintenanceScheduleDao {
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	private class PreventiveMaintenanceScheduleRowMapper implements RowMapper<PreventiveMaintenanceSchedule> {
		@Override
		public PreventiveMaintenanceSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			PreventiveMaintenanceSchedule pms =new PreventiveMaintenanceSchedule();
			pms.setId(rs.getLong("id"));
            pms.setPreventivemaintenancescheduleuid(rs.getString("preventivemaintenancescheduleuid"));
            pms.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
            pms.setMaintenancetask(rs.getString("maintenancetask"));
            pms.setFrequency(rs.getString("frequency"));
            pms.setNextduedate(rs.getString("nextduedate"));
            pms.setAssignedtechnician(rs.getString("assignedtechnician"));
            pms.setChecklistitems(rs.getString("checklistitems"));
            pms.setStatus(rs.getString("status"));
			return pms;
		}
		
	}
	

    public int savePreventivemaintenanceschedule(PreventiveMaintenanceSchedule pmsf) {
        LocalDateTime now = LocalDateTime.now();
        pmsf.setInsertdate(now);
        pmsf.setUpdatedate(now);

        String sql = "INSERT INTO preventivemaintenanceschedule (id,preventivemaintenancescheduleuid, equipmentmasteruid, maintenancetask, frequency, nextduedate, " +
                "assignedtechnician, checklistitems, status, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
        		pmsf.getId(),
                pmsf.getPreventivemaintenancescheduleuid(),
                pmsf.getEquipmentmasteruid(),
                pmsf.getMaintenancetask(),
                pmsf.getFrequency(),
                pmsf.getNextduedate(),
                pmsf.getAssignedtechnician(),
                pmsf.getChecklistitems(),
                pmsf.getStatus(),
                pmsf.getInsertdate(),
                pmsf.getUpdatedate()
        );
    }

 //  Get all preventive maintenance schedules
    public List<Map<String, Object>> getAllPreventiveSchedules() {
        String sql = "SELECT \r\n"
        		+ "  pms.id, pms.preventivemaintenancescheduleuid, em.equipmentmasteruid, em.equipmentname, pms.maintenancetask,\r\n"
        		+ "  pms.frequency, pms.nextduedate, pms.assignedtechnician, pms.checklistitems, pms.status FROM preventivemaintenanceschedule pms\r\n"
        		+ "JOIN equipmentmaster em \r\n"
        		+ "  ON em.equipmentmasteruid = pms.equipmentmasteruid;";
    return jdbcTemplate.queryForList(sql);
        }

    // ✅ Get by ID
    public PreventiveMaintenanceSchedule getPreventiveScheduleById(long id) {
        String sql = "SELECT * FROM preventivemaintenanceschedule WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new PreventiveMaintenanceScheduleRowMapper(), id);
    }

    // ✅ Update
    public int updatePreventiveSchedule(PreventiveMaintenanceSchedule pmsf) {
        pmsf.setUpdatedate(LocalDateTime.now());

        String sql = "UPDATE preventivemaintenanceschedule SET maintenancetask=?, frequency=?, " +
                "nextduedate=?, assignedtechnician=?, checklistitems=?, status=?, updatedate=? WHERE id=?";

        return jdbcTemplate.update(sql,
                pmsf.getMaintenancetask(),
                pmsf.getFrequency(),
                pmsf.getNextduedate(),
                pmsf.getAssignedtechnician(),
                pmsf.getChecklistitems(),
                pmsf.getStatus(),
                pmsf.getUpdatedate(),
                pmsf.getId()
        );
    }


    public int deletePreventiveSchedule(long id) {
        String sql = "DELETE FROM preventivemaintenanceschedule WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
	public List<String> getEquipmentMasteruid(){
    	String sql="select equipmentmasteruid from equipmentmaster ";
    	return  jdbcTemplate.query(sql,(rs,rowNum) -> rs.getString("equipmentmasteruid"));
    }
    
    
    public EquipmentMaster getEquipmentMasterDetails(String equipmentmasteruid) {
        String sql = "SELECT equipmentname FROM equipmentmaster WHERE equipmentmasteruid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{equipmentmasteruid}, (rs, rowNum) -> {
            EquipmentMaster em = new EquipmentMaster();
            em.setEquipmentname(rs.getString("equipmentname"));
            return em;
        });
    }
}

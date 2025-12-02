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
import com.prog.model.erp.MaintenanceCostTracking;


@Repository
public class MaintenanceCostTrackingDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public static class MaintenanceCostTrackingRowMapper implements RowMapper<MaintenanceCostTracking> {

		@Override
		public MaintenanceCostTracking mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaintenanceCostTracking cost = new MaintenanceCostTracking();
	        cost.setId(rs.getLong("id"));
	        cost.setMaintenancecosttrackinguid(rs.getString("maintenancecosttrackinguid"));
	        cost.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
	        cost.setDate(rs.getString("date"));
	        cost.setTypeofmaintenance(rs.getString("typeofmaintenance"));
	        cost.setLabourcost(rs.getString("labourcost"));
	        cost.setPartscost(rs.getString("partscost"));
	        cost.setOthercosts(rs.getString("othercosts"));
	        cost.setTotalcost(rs.getString("totalcost"));
	        return cost;
		}
		
    };
    
    //  Save a new maintenance cost record into the database
    public int saveMaintenanceCostTracking(MaintenanceCostTracking cost) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        String sql = "INSERT INTO maintenancecosttracking " +
                     "(id, maintenancecosttrackinguid,equipmentmasteruid, date, typeofmaintenance, labourcost, partscost, othercosts, totalcost, updatedate, insertdate) " +
                     "VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
        		cost.getId(),
        		cost.getMaintenancecosttrackinguid(),
            cost.getEquipmentmasteruid(),
            cost.getDate(),
            cost.getTypeofmaintenance(),
            cost.getLabourcost(),
            cost.getPartscost(),
            cost.getOthercosts(),
            cost.getTotalcost(),
            formattedTimestamp,
            formattedTimestamp
        );
    }
    
    public MaintenanceCostTracking getMaintenanceCostTrackingById(Long id) {
		String sql= "select * from maintenancecosttracking where id=?";
		try {
	    	return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(MaintenanceCostTracking.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Or throw a custom exception if you prefer
	    }
	}
	
	public List<Map<String,Object>> getAllMaintenanceCostTrackingData(){
		String sql="select mct.id, mct.maintenancecosttrackinguid,mct.date,mct.labourcost,mct.othercosts,mct.partscost,\r\n"
				+ "				mct.totalcost,mct.typeofmaintenance,em.equipmentname,em.equipmentmasteruid \r\n"
				+ "				from maintenancecosttracking mct\r\n"
				+ "				join equipmentmaster em ON em.equipmentmasteruid = mct.equipmentmasteruid;";
		return jdbcTemplate.queryForList(sql);
	}
    
//  Update an existing maintenance cost record in DB
    public int updateMaintenanceCostTracking(MaintenanceCostTracking cost) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
    	
        String query = "UPDATE maintenancecosttracking SET date=?, typeofmaintenance=?, labourcost=?, partscost=?, othercosts=?, totalcost=?, updatedate=? WHERE id=?";
        return jdbcTemplate.update(query,
            cost.getDate(),
            cost.getTypeofmaintenance(),
            cost.getLabourcost(),
            cost.getPartscost(), 
            cost.getOthercosts(),
            cost.getTotalcost(),
            formattedTimestamp,
            cost.getId());
    }

    //  Delete a maintenance cost record by its ID
    public void deleteMaintenanceCostTrackingById(Long id) {
        jdbcTemplate.update("DELETE FROM maintenancecosttracking WHERE id=?", id);
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

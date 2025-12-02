package com.prog.Dao.productionandoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.EquipmentMaster;
import com.prog.model.erp.EquipmentUtilization;
import com.prog.model.erp.productionplanning;

@Repository
public class EquipmentUtilizationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private class equipmentUtilizationRowMapper implements RowMapper<EquipmentUtilization>  {
		@Override
		public EquipmentUtilization mapRow(ResultSet rs, int rowNum) throws SQLException {
			
		EquipmentUtilization equipment = new EquipmentUtilization();
		equipment.setId(rs.getLong("id"));

		equipment.setEquipmentutilizationuid(rs.getString("equipmentutilizationuid"));
		equipment.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
		equipment.setUtilizationrate(rs.getDouble("utilizationrate"));
		equipment.setDowntimeoccurrence(rs.getInt("downtimeoccurrence"));
		equipment.setEnergyconsumption(rs.getString("energyconsumption"));
		return equipment;
	}
	}
	
	public int saveEquipmentUtilization(EquipmentUtilization equipment) {
		LocalDateTime now = LocalDateTime.now();
		String sql = "INSERT INTO  equipmentutilization (equipmentutilizationuid, equipmentmasteruid, utilizationrate, downtimeoccurrence, energyconsumption, insertdate, updatedate )"
				+ "VALUES (?, ?, ?, ?, ?, ?, ? )";

		return jdbcTemplate.update(sql, 
				equipment.getEquipmentutilizationuid(),
				equipment.getEquipmentmasteruid(),
				equipment.getUtilizationrate(), 
				equipment.getDowntimeoccurrence(),
				equipment.getEnergyconsumption(), 
				now.format(formatter),
				now.format(formatter)
				);
	}

	public List<Map<String, Object>> findallEquipmentUtilization() {
	    String sql = "SELECT eu.id, eu.equipmentutilizationuid, em.equipmentmasteruid, em.equipmentname,"
	    		+ "eu.utilizationrate,eu.downtimeoccurrence,eu.energyconsumption "+
	                 "FROM equipmentutilization eu " +
	                 "LEFT JOIN equipmentmaster em ON eu.equipmentmasteruid = em.equipmentmasteruid " ;

	    return jdbcTemplate.queryForList(sql);
	}
	
	public EquipmentUtilization getEquipmentUtilizationbyid(Long id) {
		String sql = "SELECT * FROM equipmentutilization WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new equipmentUtilizationRowMapper(), id);
	}
	
	public void deleteEquipmentUtilization(Long id) {
        String sql = "DELETE FROM equipmentutilization WHERE id = ?";
         jdbcTemplate.update(sql, id);
    }
	public int updateEquipmentUtilization(EquipmentUtilization equipment) {
	    String sql = "UPDATE equipmentutilization SET  utilizationrate = ?, downtimeoccurrence = ?, energyconsumption = ?, updatedate = ? WHERE id = ?";
	    LocalDateTime now = LocalDateTime.now();

	    return jdbcTemplate.update(sql,
	            equipment.getUtilizationrate(),
	            equipment.getDowntimeoccurrence(),
	            equipment.getEnergyconsumption(),
	            Timestamp.valueOf(now),
	            equipment.getId()  
	    );
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

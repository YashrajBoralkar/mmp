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
@Repository
public class EquipmentMasterDao {
	@Autowired JdbcTemplate jdbcTemplate;
	
	// RowMapper implementation for mapping ResultSet to ItemMaster entity
    public  class EquipmentmasterRowMapper implements RowMapper<EquipmentMaster> {
        @Override
        public EquipmentMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
        	EquipmentMaster equipment = new EquipmentMaster();
        	equipment.setId(rs.getLong("id"));
        	equipment.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));      
        	equipment.setEquipmentname(rs.getString("equipmentname"));
        	equipment.setCategory(rs.getString("category"));
        	equipment.setLocation(rs.getString("location"));
        	equipment.setPurchasedate(rs.getString("purchasedate"));
        	equipment.setWarrantystartdate(rs.getString("warrantystartdate")); 
        	equipment.setWarrantyenddate(rs.getString("warrantyenddate")); 
        	equipment.setManufacturername(rs.getString("manufacturername"));
        	equipment.setMaintenancefrequency(rs.getString("maintenancefrequency"));
        	equipment.setDepartmentname(rs.getString("departmentname"));
        	equipment.setStatus(rs.getString("status"));
            return equipment;
        }
    }
    
    public int addEquipmentmaster(EquipmentMaster equipment) {
    	String sql = "INSERT INTO equipmentmaster (equipmentmasteruid, equipmentname, category, location, purchasedate, warrantystartdate, warrantyenddate, manufacturername, maintenancefrequency,departmentname, status, insertdate, updatedate) " +
 			    "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 	
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);

	    return jdbcTemplate.update(sql,
	        equipment.getEquipmentmasteruid(),
	        equipment.getEquipmentname(),
	        equipment.getCategory(),
	        equipment.getLocation(),
	        equipment.getPurchasedate(),
	        equipment.getWarrantystartdate(),
	        equipment.getWarrantyenddate(),
	        equipment.getManufacturername(),
	        equipment.getMaintenancefrequency(),
	        equipment.getDepartmentname(),
	        equipment.getStatus(),
	        formattedTimestamp,
	        formattedTimestamp
	    );
	}
 // Retrieve all items
    public List<Map<String, Object>> getAllEquipmentmaster() {
        String sql = "SELECT em.id, em.equipmentmasteruid, em.equipmentname, em.category, em.location, em.purchasedate, em.warrantystartdate,\r\n"
        		+ " em.warrantyenddate, em.manufacturername, em.maintenancefrequency, em.status, dept.departmentname \r\n"
        		+ " FROM equipmentmaster em\r\n"
        		+ " join department dept ON dept.departmentname=em.departmentname ";
        return jdbcTemplate.queryForList(sql);
        
    }
 // Retrieve an item by ID
    public EquipmentMaster getEquipmentmasterById(Long id) {
        String sql = "SELECT * FROM equipmentmaster WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new EquipmentmasterRowMapper(), id);
    }
    
    public int deleteEquipmentmaster(Long id) {
        String sql = "DELETE FROM equipmentmaster WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    public int updateEquipmentmaster(EquipmentMaster em) {
	    String sql = "UPDATE equipmentmaster SET  equipmentname = ?, category = ?, location = ?,  purchasedate = ?, warrantystartdate = ?, warrantyenddate = ?, manufacturername = ?, maintenancefrequency = ?, Status = ?, updatedate = ? WHERE id = ?";
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);
	    return jdbcTemplate.update(sql,
	    		   em.getEquipmentname(),	
	    		   em.getCategory(),	
	    		   em.getLocation(),
	    		   em.getPurchasedate(),
	    		   em.getWarrantystartdate(),
	    		   em.getWarrantyenddate(),
	    		   em.getManufacturername(),
	    		   em.getMaintenancefrequency(),
	    		   em.getStatus(),   
	    		    
                formattedTimestamp,	                
                em.getId()
        );
    }
    
    public List<String> getDepartmentName(){
    	String sql="select departmentname from department";
    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("departmentname")); 
	}    
}

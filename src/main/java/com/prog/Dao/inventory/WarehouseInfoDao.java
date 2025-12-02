package com.prog.Dao.inventory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Warehouseinfo;




@Repository
public class WarehouseInfoDao 
{
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 
	  // RowMapper for Warehouse
	    private RowMapper<Warehouseinfo> rowMapper = (rs, rowNum) -> {
	    	Warehouseinfo warehouse = new Warehouseinfo();
	        warehouse.setId(rs.getLong("id"));
	        warehouse.setWarehouseuid(rs.getString("warehouseuid"));
	        warehouse.setWarehousename(rs.getString("warehousename"));
	        warehouse.setAddress(rs.getString("address"));
	        warehouse.setContactperson(rs.getString("contactperson"));
	        warehouse.setContactnumber(rs.getString("contactnumber"));
	        warehouse.setStoragecapacity(rs.getString("storagecapacity"));
	        warehouse.setStoragetype(rs.getString("storagetype"));
	        warehouse.setZonesandsections(rs.getString("zonesandsections"));
	        warehouse.setWarehousetype(rs.getString("warehousetype"));
	        warehouse.setOperationalhours(rs.getString("operationalhours"));
	        warehouse.setDockoperation(rs.getString("dockoperation"));
	        warehouse.setDockavailability(rs.getInt("dockavailability"));
	        warehouse.setFiresafetyequipment(rs.getString("firesafetyequipment"));
	        warehouse.setAssociatedinventorylocations(rs.getString("associatedinventorylocations"));
	        warehouse.setRemarks(rs.getString("remarks"));
	        warehouse.setStatus(rs.getString("status"));
	        warehouse.setSafetycertification(rs.getBytes("safetycertification"));
	        warehouse.setCityname(rs.getString("cityname"));
	        warehouse.setUnit(rs.getString("unit"));
	        return warehouse;
	    };

	    public void save(Warehouseinfo warehouse) {
	        if (warehouse.getId() != null) {
	            // Update existing warehouse
	            String sql = "UPDATE warehouseinfo SET " +
	                         "warehousename = ?, address = ?, contactperson = ?, contactnumber = ?, " +
	                         "storagecapacity = ?, storagetype = ?, zonesandsections = ?, warehousetype = ?, operationalhours = ?, " +
	                         "dockoperation = ?, dockavailability = ?, firesafetyequipment = ?, associatedinventorylocations = ?, " +
	                         "remarks = ?, status = ? , safetycertification = ?, cityname = ?, unit = ? " +
	                         "WHERE id = ?";
	            
	            jdbcTemplate.update(
	                sql,
	                warehouse.getWarehousename(),
	                warehouse.getAddress(),
	                warehouse.getContactperson(),
	                warehouse.getContactnumber(),
	               warehouse.getStoragecapacity(),
//	                warehouse.getCombinedStorageCapacity(),
	                warehouse.getStoragetype(),
	                warehouse.getZonesandsections(),
	                warehouse.getWarehousetype(),
	                warehouse.getOperationalhours(),
	                warehouse.getDockoperation(),
	                warehouse.getDockavailability(),
	                warehouse.getFiresafetyequipment(),
	                warehouse.getAssociatedinventorylocations(),
	                warehouse.getRemarks(),
	                warehouse.getStatus(),
	                warehouse.getSafetycertification(),
	                warehouse.getCityname(),
	                warehouse.getUnit(),
	                warehouse.getId() // Include the ID for the WHERE clause
	            );
	        } else {
	            // Insert new warehouse
	            String sql = "INSERT INTO warehouseinfo " +
	                         "(warehouseuid, warehousename, address, contactperson, contactnumber, " +
	                         "storagecapacity, storagetype, zonesandsections, warehousetype, operationalhours, " +
	                         "dockoperation, dockavailability, firesafetyequipment, associatedinventorylocations, " +
	                         "remarks, status, safetycertification,cityname,unit) " +
	                         "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
	            
	            jdbcTemplate.update(
	                sql,
	                warehouse.getWarehouseuid(),
	                warehouse.getWarehousename(),
	                warehouse.getAddress(),
	                warehouse.getContactperson(),
	                warehouse.getContactnumber(),
	                warehouse.getStoragecapacity(),
	                warehouse.getStoragetype(),
//	                warehouse.getCombinedStorageCapacity(),
	                warehouse.getZonesandsections(),
	                warehouse.getWarehousetype(),
	                warehouse.getOperationalhours(),
	                warehouse.getDockoperation(),
	                warehouse.getDockavailability(),
	                warehouse.getFiresafetyequipment(),
	                warehouse.getAssociatedinventorylocations(),
	                warehouse.getRemarks(),
	                warehouse.getStatus(),
	                warehouse.getSafetycertification(),
	                warehouse.getCityname(),
	                warehouse.getUnit()

	            );
	        }
	    } 

	    public List<Map<String, Object>>  findAll() {
	        String sql = "SELECT \r\n"
	        		+ "    id, \r\n"
	        		+ "    address, \r\n"
	        		+ "    associatedinventorylocations, \r\n"
	        		+ "    cityname, \r\n"
	        		+ "    contactnumber, \r\n"
	        		+ "    contactperson, \r\n"
	        		+ "    status, \r\n"
	        	    + "    warehousename, \r\n"
	        		+ "    warehousetype, \r\n"
	        		+ "    warehouseuid \r\n"
	        		+ "FROM warehouseinfo;\r\n"
	        		+ "";
	        return jdbcTemplate.queryForList(sql);
	    }

	    public void delete(Long id) {
	        String sql = "DELETE FROM Warehouseinfo WHERE id = ?";
	        jdbcTemplate.update(sql, id);
	    }

	    public Warehouseinfo findById(Long id) {
	        String sql = "SELECT * FROM Warehouseinfo WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
	    }


	    public List<String> getAllWarehouseUids() {
	   	 
	        String sql = "SELECT warehouseuid FROM warehouseinfo"; // Replace with your actual table and column names
	        return jdbcTemplate.queryForList(sql, String.class);
	    
	    }
	    
	    public Warehouseinfo getwarehouseDetailsByUid(String warehouseuid) {
	        String sql = "SELECT  address, cityname FROM warehouseinfo WHERE warehouseuid = ?";

			
	        return jdbcTemplate.queryForObject(sql, new Object[]{warehouseuid}, (rs, rowNum) -> {
	        	Warehouseinfo details = new Warehouseinfo();
	            details.setAddress(rs.getString("address"));
	            details.setCityname(rs.getString("cityname"));
	            
	            return details;
	        }); 


		}
	    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
//FETCHING IN GOODS RECEIPT FORM
	    public List<String> getAllWarehouseuidsIngoodsreceiptnote() {
		   	 
	        String sql = "SELECT warehouseuid FROM warehouseinfo"; // Replace with your actual table and column names
	        return jdbcTemplate.queryForList(sql, String.class);
	    
	    }
	 
	 @SuppressWarnings("deprecation")
	public Warehouseinfo getwarehouseDetailsByWarehouseuidIngoodsreceiptnote(String warehouseuid) {
	        String sql = "SELECT address, cityname FROM warehouseinfo WHERE warehouseuid = ?";

	        return jdbcTemplate.queryForObject(sql, new Object[]{warehouseuid}, (rs, rowNum) -> {
	        	Warehouseinfo war = new Warehouseinfo();
	        	war.setAddress(rs.getString("address"));
	        	war.setCityname(rs.getString("cityname"));
	            return war;
	        }); 
	 	}
	 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
//FETCHING IN TRANSPORTATION REQUEST FORM 
	 
//	 public List<String> getwarehouseuidInTransportationrequest() {
//	      String sql = "SELECT  warehouseuid FROM warehouseinfo";
//	      return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("warehouseuid"));
//	  }
//	 
//	 public List<String> getDestinationWarehouseUidsExcludingSource(String sourcewarehouseuid) {
//		    String sql = "SELECT warehouseuid FROM warehouseinfo WHERE warehouseuid != ?";
//		    return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("warehouseuid"), sourcewarehouseuid);
//		}
//
//	 public List<Map<String, Object>> getDataBywarehouseuidInTransportationrequest(String warehouseuid) {
//		    String sql = "SELECT address FROM warehouseinfo WHERE warehouseuid = ?";
//		    return jdbcTemplate.queryForList(sql, warehouseuid);
//	}
	  
	 
	 public List<Map<String, Object>> getDataBywarehouseuid(String warehouseuid) {
		    String sql = "SELECT address, warehousename FROM warehouseinfo WHERE warehouseuid = ?";
		    return jdbcTemplate.queryForList(sql, warehouseuid);
	}
	  	
	  public List<String> getwarehouseuid() {
	      String sql = "SELECT  warehouseuid FROM warehouseinfo";
	      return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("warehouseuid"));
	  }
	  
   
	 
	

}

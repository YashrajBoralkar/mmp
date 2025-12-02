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

import com.prog.model.erp.AnnualMaintenanceContractRegister;
import com.prog.model.erp.EquipmentMaster;
@Repository
public class AnnualMaintenanceContractRegisterDao {
	@Autowired
	 private  JdbcTemplate jdbctemplate;
	
	// This method is called for each row in the ResultSet
	   public static class AnnualMaintenanceContractRegisterRowMapper implements RowMapper<AnnualMaintenanceContractRegister>{

		@Override
		public AnnualMaintenanceContractRegister mapRow(ResultSet rs, int rowNum) throws SQLException {
			AnnualMaintenanceContractRegister amc = new AnnualMaintenanceContractRegister();
			 amc.setId(rs.getLong("id"));
			 amc.setAnnualmaintenancecontractuid(rs.getString("annualmaintenancecontractuid"));
			 amc.setVendorname(rs.getString("vendorname"));
			 amc.setAmcstartdate(rs.getString("amcstartdate"));
			 amc.setAmcenddate(rs.getString("amcenddate"));
			 amc.setEquipmentmasteruid(rs.getString("equipmentmasteruid"));
			 amc.setTermsandcondition(rs.getString("termsandcondition"));
			 amc.setContactperson(rs.getString("contactperson"));
			 return amc;
		}   
	   }
	   
	 //Its the query to insert the data into table 
		public int addAnnualMaintenanceContractRegisterData(AnnualMaintenanceContractRegister amc) {
			//insert
			   String sql = "insert into annualmaintenancecontract " 
			        + "( id,annualmaintenancecontractuid, vendorname,amcstartdate,amcenddate, equipmentmasteruid ,termsandcondition,contactperson,insertdate,updatedate) "
			      + "values (?,?, ?, ?, ?, ?,?,?,?,?)";
			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
			    return jdbctemplate.update(sql,
			    		amc.getId(),
			    		amc.getAnnualmaintenancecontractuid(),
			    		amc.getVendorname(),
			    		amc.getAmcstartdate(),
			    		amc.getAmcenddate(),
			    		amc.getEquipmentmasteruid(),
			    		amc.getTermsandcondition(),
			    		amc.getContactperson(),
			    		formattedTimestamp,
			            formattedTimestamp			    		
			    );
			}
		
		  // This method retrieves a single AMCentity record from the database based on the given ID
		   public AnnualMaintenanceContractRegister getAnnualMaintenanceContractRegisterById(Long id) {
			   String sql = "SELECT * FROM annualmaintenancecontract WHERE id = ?";
			   try {
			    	return jdbctemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(AnnualMaintenanceContractRegister.class));
			    } catch (EmptyResultDataAccessException e) {
			        return null; // Or throw a custom exception if you prefer
			    }
			   }
		    
		   public List<Map<String,Object>> getAnnualMaintenanceContractRegisterData(){
				String sql="select  amc.id,amc.annualmaintenancecontractuid,amcenddate,amc.amcstartdate,amc.contactperson,\r\n"
						+ "amc.termsandcondition,amc.vendorname,em.equipmentmasteruid, em.equipmentname\r\n"
						+ "from annualmaintenancecontract amc\r\n"
						+ "join  equipmentmaster em ON em.equipmentmasteruid = amc.equipmentmasteruid;";
				return jdbctemplate.queryForList(sql);
			}
			
		   
		 //Its the query for deleting the data from the database/table 
			public int deleteAnnualMaintenanceContractRegisterById(long id) {
				String sql = "DELETE FROM annualmaintenancecontract WHERE id = ?";
			     return jdbctemplate.update(sql,id);
		    }	

			
			// This Method is use to update a (annual maintenance contract) data in the database
			public int updategetAnnualMaintenanceContractRegister(AnnualMaintenanceContractRegister amc) {
				// Define the format for  date and time
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			        String formattedTimestamp = LocalDateTime.now().format(formatter);
			        // Define the SQL UPDATE query with placeholders
			    String sql = "UPDATE annualmaintenancecontract \r\n"
			    		+ "SET vendorname = ?, amcstartdate = ?, amcenddate = ?,termsandcondition=?,contactperson=?,updatedate=?\r\n"
			    		+ "WHERE id = ?\r\n";
		        // Execute the update query using JdbcTemplate and pass the values from the SWMentity object
			    return jdbctemplate.update(sql,
			            amc.getVendorname(),
			            amc.getAmcstartdate(),
			            amc.getAmcenddate(),
			            amc.getTermsandcondition(),
			    		 amc.getContactperson(),
			    		 formattedTimestamp,
			    		 
			            amc.getId()); // Ensure the ID is included in the update
			}

		
		public List<String> getEquipmentDetailsById(){
			String sql="select equipmentmasteruid from equipmentmaster";
			return jdbctemplate.query(sql, (rs, rowNum) -> rs.getString("equipmentmasteruid"));
		}
		public EquipmentMaster getEquipmentDetails(String equipmentmasteruid){
			String sql="select equipmentname from equipmentmaster where equipmentmasteruid=? ";
	    	return jdbctemplate.queryForObject(sql, new Object[] {equipmentmasteruid}, (rs, rowNum) -> {
	    		EquipmentMaster eu=new EquipmentMaster();
	    		eu.setEquipmentname(rs.getString("equipmentname"));
	    		return eu;
	    	});
		}

			
}

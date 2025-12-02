package com.prog.Dao.productionandoperation;

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

import com.prog.model.erp.Productinfo;
import com.prog.model.erp.ScrapandWasteManagement;

@Repository
public class ScrapandWasteManagementDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// This method is called for each row in the ResultSet
	   public static class ScrapandWasteManagemententityRowMapper implements RowMapper<ScrapandWasteManagement>{
			 @Override
			 public ScrapandWasteManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
				 ScrapandWasteManagement swm = new ScrapandWasteManagement();
				 swm.setId(rs.getLong("id"));
				 swm.setScrapuid(rs.getString("scrapuid"));
				 swm.setProductuid(rs.getString("productuid"));
				 swm.setScraptype(rs.getString("scraptype"));
				 swm.setQuantityscrapped(rs.getString("quantityscrapped"));
				 swm.setRecyclingmethod(rs.getString("recyclingmethod"));
				 swm.setWastedisposalcampany(rs.getString("wastedisposalcampany"));
				 swm.setEnvironmentalimpactassessment(rs.getString("environmentalimpactassessment"));
				 swm.setInsertdate(rs.getString("insertdate"));
				 swm.setUpdatedate(rs.getNString("updatedate"));

				 return swm;
			 }
	   }	
	 //Its the query to insert the data into table 
		public int addScrapandWasteManagement(ScrapandWasteManagement swm) {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
			   String sql = "insert into scarpandwastemanagement (id,scrapuid,productuid, scraptype,quantityscrapped,recyclingmethod,"
			   		+ " wastedisposalcampany ,environmentalimpactassessment,insertdate,updatedate) "
			      + "values (?,?, ?, ?, ?, ?,?,?,?,?)";
			  
			    return jdbcTemplate.update(sql,
			    		swm.getId(),
			    		swm.getScrapuid(),
			    		swm.getProductuid(),			    		
			    		
			    		swm.getScraptype(),
			    		swm.getQuantityscrapped(),
			    		swm.getRecyclingmethod(),
			    		swm.getWastedisposalcampany(),
			    		swm.getEnvironmentalimpactassessment(),
			    		formattedTimestamp,
			            formattedTimestamp
			    		
			    );
			}
		// This method retrieves a single SWMentity record from the database based on the given ID
		public ScrapandWasteManagement getScrapandWasteManagementById(Long id) {
			String sql = "SELECT * FROM scarpandwastemanagement WHERE id = ?";
		    return jdbcTemplate.queryForObject(sql, new ScrapandWasteManagemententityRowMapper() , id);
		}
		
		//Its the query to show the list for the table 
		public List<Map<String, Object>> findAllScrapandWasteManagementData() {
			String sql = "select swm.id,swm.scrapuid,swm.productuid,pi.productname,swm.scraptype,swm.quantityscrapped,"
					+ "swm.recyclingmethod,swm.wastedisposalcampany,swm.environmentalimpactassessment "
					+ "from scarpandwastemanagement swm "
					+ "LEFT JOIN productinfo pi ON swm.productuid = pi.productuid " ;
			return jdbcTemplate.queryForList(sql);
		 }

		//Its the query for deleting the data from the database/table 
		public void deleteScrapandWasteManagementById(long id) {
			String sql = "DELETE FROM scarpandwastemanagement WHERE id = ?";
			 jdbcTemplate.update(sql,id);
		}
		
		// This Method is use to update a (Scrap and Waste Management) data in the database
		public int updatecrapandWasteManagement(ScrapandWasteManagement swm) {
			// Define the format for  date and time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	        // Define the SQL UPDATE query with placeholders
	        String sql = "UPDATE scarpandwastemanagement SET  scraptype=?, quantityscrapped=? ,recyclingmethod=?,"
	        			  + "wastedisposalcampany=?,environmentalimpactassessment=?,updatedate=?\r\n"
	        			  + "WHERE id = ?";
	        // Execute the update query using JdbcTemplate and pass the values from the SWMentity object
	        return jdbcTemplate.update(sql,
	            swm.getScraptype(),
	            swm.getQuantityscrapped(),
	            swm.getRecyclingmethod(),
	            swm.getWastedisposalcampany(),
	            swm.getEnvironmentalimpactassessment(),	            
	    		 formattedTimestamp,
	            swm.getId()); 
			}			
		


		
		public List<String> getProductUID(){
			String sql="select productuid from productinfo";
			return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid")); 
		}
	
		public Productinfo getProductDeatils(String productuid) {
			String sql="select productname from productinfo where productuid=?";
			return jdbcTemplate.queryForObject(sql, new Object[] {productuid}, (rs, rowNum) -> {
				Productinfo pi=new Productinfo();
				pi.setProductname(rs.getString("productname"));
				return pi;
			});
		}
	
}

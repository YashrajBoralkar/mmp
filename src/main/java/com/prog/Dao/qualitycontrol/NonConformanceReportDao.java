package com.prog.Dao.qualitycontrol;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.Nonconformancereport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
	

	@Repository
	public class NonConformanceReportDao {


	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    // Save an NCR report
	    public int addNcr(Nonconformancereport nonconformancereport) {
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	         String formattedTimestamp = LocalDateTime.now().format(formatter);

	        String sql = "INSERT INTO nonconformancereport ( nonconformancereportuid, department, inspectiondate, productuid, " +
	                     "descriptionofdefect, rootcauseanalysis, defectseverity, immediateactiontaken, preventiveactionsuggested, approvalstatus, suppervisorapproval,insertdate,updatedate) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	        return jdbcTemplate.update(sql, 
	            nonconformancereport.getNonconformancereportuid(),
	            nonconformancereport.getDepartment(),
	            nonconformancereport.getInspectiondate(),
	            nonconformancereport.getProductuid(), 
	            nonconformancereport.getDescriptionofdefect(),
	            nonconformancereport.getRootcauseanalysis(),
	            nonconformancereport.getDefectseverity(),
	            nonconformancereport.getImmediateactiontaken(),
	            nonconformancereport.getPreventiveactionsuggested(),
	            nonconformancereport.getApprovalstatus(),
	            nonconformancereport.getSuppervisorapproval(),
	            formattedTimestamp,
                formattedTimestamp   
        );
	    }

	   
	    
	    
	    private class NcrRowMapper implements RowMapper<Nonconformancereport> {
	        @Override
	        public Nonconformancereport mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Nonconformancereport nr = new Nonconformancereport();
	            nr.setId(rs.getLong("id"));
	            nr.setNonconformancereportuid(rs.getString("nonconformancereportuid"));
	            nr.setDepartment(rs.getString("department"));
	            nr.setInspectiondate(rs.getString("inspectiondate"));
	            nr.setProductuid(rs.getString("productuid"));
	            nr.setDescriptionofdefect(rs.getString("descriptionofdefect"));
	            nr.setRootcauseanalysis(rs.getString("rootcauseanalysis"));
	            nr.setDefectseverity(rs.getString("defectseverity"));
	            nr.setImmediateactiontaken(rs.getString("immediateactiontaken"));
	            nr.setPreventiveactionsuggested(rs.getString("preventiveactionsuggested"));
	            nr.setApprovalstatus(rs.getString("approvalstatus"));
	            nr.setSuppervisorapproval(rs.getString("suppervisorapproval"));
	            nr.setProductname(rs.getString("productname"));
	            nr.setProductuid(rs.getString("productuid"));

	            
	            return nr;
	        }
	    
			/*
			 * public List<Nonconformancereport> getAllNcr() { String sql = "SELECT * FROM ncr"; return
			 * jdbcTemplate.query(sql,new NcrRowMapper()); }
			 */

//	    // Find a specific NCR report by ID
	        	  
	    }
	    
	    public Map<String, Object> findNcrById(Long id) {
	        String sql = "SELECT * FROM nonconformancereport WHERE id = ?";
	        return jdbcTemplate.queryForMap(sql, id);
	    }	
	    
		public List<Nonconformancereport> getAllNcr() {
			 String sql = "SELECT * FROM nonconformancereport";
		        return jdbcTemplate.query(sql,new NcrRowMapper());
		   
		}



		public int deleteById(Long id) {
			String sql="DELETE FROM nonconformancereport WHERE id = ?";
			return jdbcTemplate.update(sql, id);
		}



//		public Nonconformancereport getNcrByid(Long id) {
//	        String sql = "SELECT * FROM nonconformancereport WHERE id = ?";
//		        return jdbcTemplate.queryForObject(sql, new NcrRowMapper(), id);
//	    }
			
		public Nonconformancereport getNcrByid(Long id) {
		    String sql = "SELECT ncr.*, p.productname " +
		                 "FROM nonconformancereport ncr " +
		                 "LEFT JOIN productinfo p ON ncr.productuid = p.productuid " +
		                 "WHERE ncr.id = ?";
		    return jdbcTemplate.queryForObject(sql, new NcrRowMapper(), id);
		}



		public int updateNcr(Nonconformancereport nonconformancereport) {
		    String sql = "UPDATE nonconformancereport SET department=?, inspectiondate=?,productuid=?, " +
		                 "descriptionofdefect=?, rootcauseanalysis=?, defectseverity=?, immediateactiontaken=?, " +
		                 "preventiveactionsuggested=?, approvalstatus=?, suppervisorapproval=?, updatedate = ? WHERE id=?";
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	         String formattedTimestamp = LocalDateTime.now().format(formatter);


		    return jdbcTemplate.update(sql,
		        nonconformancereport.getDepartment(),
		        nonconformancereport.getInspectiondate(),
		        nonconformancereport.getProductuid(),
		        nonconformancereport.getDescriptionofdefect(),
		        nonconformancereport.getRootcauseanalysis(),
		        nonconformancereport.getDefectseverity(),
		        nonconformancereport.getImmediateactiontaken(),
		        nonconformancereport.getPreventiveactionsuggested(),
		        nonconformancereport.getApprovalstatus(),
		        nonconformancereport.getSuppervisorapproval(),
		        formattedTimestamp,
		        nonconformancereport.getId()
		    );
		}

		
		public List<Map<String, Object>> showFindAllrecords() {
		    String sql = "SELECT ncr.id, ncr.nonconformancereportuid, ncr.department, ncr.inspectiondate, " +
		                 "p.productname, ncr.descriptionofdefect, ncr.rootcauseanalysis, ncr.defectseverity, " +
		                 "ncr.immediateactiontaken, ncr.preventiveactionsuggested, ncr.approvalstatus, ncr.suppervisorapproval " +
		                 "FROM nonconformancereport ncr " +
		                 "JOIN productinfo p ON ncr.productuid = p.productuid";
		    return jdbcTemplate.queryForList(sql);
		}

		  
		  
		  //FETCHING
	    
		 // Retrieve all product UIDs
	    public List<String> fetchProductUIds() {
	        String sql = "SELECT productuid FROM productinfo";
	        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
	    }
	   public List<Map<String, Object>> getProductDetailsByuid(String productuid){
		   String sql= "SELECT productname FROM productinfo WHERE productuid=?";
		   return jdbcTemplate.queryForList(sql, productuid);
	   }
	   
	 
     }

		
	


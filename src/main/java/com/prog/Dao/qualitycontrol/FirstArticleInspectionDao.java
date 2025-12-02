package com.prog.Dao.qualitycontrol;

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

import com.prog.model.erp.Firstarticleinspection;


@Repository
public class FirstArticleInspectionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private final String insertSql = "INSERT INTO firstarticleinspection " +
		    "(firstarticleinspectionuid, productuid, defectiveunits, totalinspectedunits, measurementverification, " +
		    "materialverification, functionaltesting, defectcount,approvedcount, approvalstatus, productionplanninguid, inspectorname, " +
		    "productname, insertdate, updatedate, plannedquantity, workorderuid, productionorderuid) " +  // added here
		    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";


	public int saveFai(Firstarticleinspection firstarticleinspection) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedTimestamp = LocalDateTime.now().format(formatter);

	    return jdbcTemplate.update(insertSql,
	    	    firstarticleinspection.getFirstarticleinspectionuid(), 
	    	    firstarticleinspection.getProductuid(),                
	    	    firstarticleinspection.getDefectiveunits(),            
	    	    firstarticleinspection.getTotalinspectedunits(),       
	    	    firstarticleinspection.getMeasurementverification(),   
	    	    firstarticleinspection.getMaterialverification(),      
	    	    firstarticleinspection.getFunctionaltesting(),         
	    	    firstarticleinspection.getDefectcount(),
	    	    firstarticleinspection.getApprovedcount(),
	    	    firstarticleinspection.getApprovalstatus(),            
	    	    firstarticleinspection.getProductionplanninguid(),     
	    	    firstarticleinspection.getInspectorname(),             
	    	    firstarticleinspection.getProductname(),               
	    	    formattedTimestamp,                                    
	    	    formattedTimestamp,                                    
	    	    firstarticleinspection.getPlannedquantity(),           
	    	    firstarticleinspection.getWorkorderuid(),              
	    	    firstarticleinspection.getProductionorderuid()          // added here
	    	);

	}

	// Fetch all product UIDs from productinfo table
	public List<String> getAllProductUIDs() {
	    String sql = "SELECT productuid FROM productinfo ORDER BY productuid DESC";
	    return jdbcTemplate.queryForList(sql, String.class);
	}

	// Fetch all planning UIDs from productionplanning table
	public List<String> getAllPlanningUIDs() {
	    String sql = "SELECT DISTINCT productionplanninguid FROM productionplanning ORDER BY productionplanninguid DESC";
	    return jdbcTemplate.queryForList(sql, String.class);
	}

	// Fetch all work order UIDs from workorder table
	public List<String> getAllWorkOrderUIDs() {
	    String sql = "SELECT workorderuid FROM workorder ORDER BY workorderuid DESC";
	    return jdbcTemplate.queryForList(sql, String.class);
	}

	// Fetch all production order UIDs from productionorder table
	public List<String> getAllProductionOrderUIDs() {
	    String sql = "SELECT productionorderuid FROM productionorder ORDER BY productionorderuid DESC";
	    return jdbcTemplate.queryForList(sql, String.class);
	}

	
	public String fetchProductName(String productuid) {
		String sql = "SELECT productname FROM productinfo WHERE productuid = ?";
	    return jdbcTemplate.queryForObject(sql, new Object[]{productuid}, String.class);
	}
	 public List<String> fetchProductionPlanningUIDs(String productuid) {
	        String sql = "SELECT DISTINCT productionplanninguid FROM productionplanning WHERE productuid = ?";
	        return jdbcTemplate.queryForList(sql, new Object[]{productuid}, String.class);
	    }

	    public List<String> fetchWorkOrderUIDs(String productionplanninguid) {
	        String sql = "SELECT workorderuid FROM workorder WHERE productionplanninguid = ?";
	        return jdbcTemplate.queryForList(sql, new Object[]{productionplanninguid}, String.class);
	    }

	    public List<String> fetchProductionOrderUIDs(String workorderuid) {
	        String sql = "SELECT productionorderuid FROM productionorder WHERE workorderuid = ?";
	        return jdbcTemplate.queryForList(sql, new Object[]{workorderuid}, String.class);
	    }

	    public Integer fetchPlannedQuantity(String productionorderuid) {
	    	String sql = "SELECT productionorderquantity AS plannedquantity FROM productionorder WHERE productionorderuid = ?";
	        return jdbcTemplate.queryForObject(sql, new Object[]{productionorderuid}, Integer.class);
	    }

    //Fetching Uids
    public List<Map<String, Object>> getWorkorderDetailsByUid(String workorderuid) 
    {
        String sql = "SELECT " +
                     "w.workorderuid,w.productuid, " +
                     "p.productname, " +
                     "w.workorderquantity AS plannedquantity, " +
                     "w.productionplanninguid " +
                     "FROM workorder w " +
                     "JOIN productinfo p ON w.productuid = p.productuid " +
                     "WHERE w.workorderuid = ?";
        
        return jdbcTemplate.queryForList(sql, workorderuid);
    }
    
    
    public List<String> fetchAllWorkOrderUids() {
        String sql = "SELECT workorderuid FROM workorder ORDER BY workorderuid DESC";
        return jdbcTemplate.queryForList(sql, String.class);
    }



    
    public List<Map<String, Object>> fetchAllFirstarticleinspection() {
        String sql = "SELECT fai.id, fai.firstarticleinspectionuid, fai.totalinspectedunits, fai.defectiveunits, fai.defectcount, fai.inspectorname, fai.approvalstatus, pi.productname\r\n"
        		+ "FROM firstarticleinspection fai\r\n"
        		+ "JOIN productinfo pi ON fai.productuid = pi.productuid;";
        return jdbcTemplate.queryForList(sql);
    }

    public void deleteFaiById(Long id) {
        String sql = "DELETE FROM firstarticleinspection WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No Firstarticleinspection found with id " + id);
        }
    }

    class FaiRowMapper implements RowMapper<Firstarticleinspection> {
        @Override
        public Firstarticleinspection mapRow(ResultSet rs, int rowNum) throws SQLException {
            Firstarticleinspection fai = new Firstarticleinspection();
            fai.setId(rs.getLong("id"));
            fai.setFirstarticleinspectionuid(rs.getString("firstarticleinspectionuid"));
            fai.setProductuid(rs.getString("productuid"));
            fai.setProductname(rs.getString("productname"));
            fai.setProductionplanninguid(rs.getString("productionplanninguid"));
            fai.setWorkorderuid(rs.getString("workorderuid"));
            fai.setProductionorderuid(rs.getString("productionorderuid"));
            fai.setPlannedquantity(rs.getString("plannedquantity"));
            fai.setTotalinspectedunits(rs.getString("totalinspectedunits"));
            fai.setMeasurementverification(rs.getString("measurementverification"));
            fai.setMaterialverification(rs.getString("materialverification"));
            fai.setFunctionaltesting(rs.getString("functionaltesting"));
            fai.setDefectcount(rs.getString("defectcount"));
            fai.setApprovedcount(rs.getString("approvedcount"));
            fai.setDefectiveunits(rs.getString("defectiveunits"));
            fai.setApprovalstatus(rs.getString("approvalstatus"));
            fai.setInspectorname(rs.getString("inspectorname"));
            return fai;
        }
    }


    public Firstarticleinspection getFaiById(Long id) {
        String sql = "SELECT * FROM firstarticleinspection WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new FaiRowMapper(), id);
    }


    public void updateFai(Firstarticleinspection firstarticleinspection) {
        String sql = "UPDATE firstarticleinspection SET  measurementverification = ?, " +
                "totalinspectedunits = ?, defectiveunits = ?,materialverification = ?, functionaltesting = ?, defectcount = ?,approvedcount=?, approvalstatus = ?,  updatedate=? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
 		
        jdbcTemplate.update(sql,
                firstarticleinspection.getMeasurementverification(),
                firstarticleinspection.getTotalinspectedunits(),
                firstarticleinspection.getDefectiveunits(),
                firstarticleinspection.getMaterialverification(),
                firstarticleinspection.getFunctionaltesting(),
                firstarticleinspection.getDefectcount(),
                firstarticleinspection.getApprovedcount(),
                firstarticleinspection.getApprovalstatus(),
                formattedTimestamp,
                firstarticleinspection.getId()
        );
    }

    
    //FETCHING 
    
    public List<String>getBatchId(){
      	String sql="select batchuid From batchinfo";
      	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("batchuid"));
      }
      
     public List<String>getItemId(){
    	String sql="select productuid From productinfo";
    	return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }
     public List<Map<String, Object>> getProductDetailsByuid(String productuid){
    	   String sql= "SELECT productname,quantity,unitofmeasure FROM productinfo WHERE productuid=?";
    	   return jdbcTemplate.queryForList(sql, productuid);
     }
     
    public List<Map<String, Object>> getbatchDetailsByuid(String batchuid){
     String sql= "SELECT p.productname,p.unitofmeasure, p.productuid FROM productinfo p JOIN batchinfo b ON p.productuid = b.productuid WHERE b.batchuid = ?";
     return jdbcTemplate.queryForList(sql, batchuid);
    }
    
    
}
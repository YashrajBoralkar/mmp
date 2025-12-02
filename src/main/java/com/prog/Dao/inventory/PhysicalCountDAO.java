package com.prog.Dao.inventory;



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

import com.prog.model.erp.physicalcount;




	@Repository
	public class PhysicalCountDAO {

	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    // Insert Physical Count into the database
	    private static final String INSERT_SQL = "INSERT INTO physicalcount (physicalcountuid, productuid, productname,physicalcount, difference, adjustmentreason, supporting_document,countedby, countdate, approvalstatus, processedby, insertdate, updatedate) VALUES ( ?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    public int addPhysicalCount(physicalcount physicalcount) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	        return jdbcTemplate.update(INSERT_SQL,
	                physicalcount.getPhysicalcountuid(),
	                physicalcount.getProductuid(),
	              //  physicalcount.getStockmanageruid(),
	                physicalcount.getProductname(),
	                physicalcount.getPhysicalcount(),
	                physicalcount.getDifference(),
	                physicalcount.getAdjustmentreason(),
	                physicalcount.getSupportingDocument(),
	                physicalcount.getCountedby(),
	                physicalcount.getCountdate(),
	                physicalcount.getApprovalstatus(),
	                physicalcount.getProcessedby(),
	                formattedTimestamp,
	                formattedTimestamp
	        );
	    }

	    // Retrieve all Physical Counts
//	    public List<Map<String, Object>> getAllPhysicalCounts() {
//	        String sql = "SELECT pc.id,pc.physicalcountuid, pc.productuid, pc.physicalcount, pc.difference, pc.adjustmentreason, pc.countedby, pc.countdate, pc.approvalstatus, pc.processedby, pi.productname, pi.productdescription,sm.currentsystemstock \r\n"
//	        		+ "FROM physicalcount pc JOIN productinfo pi ON pi.productuid = pc.productuid";
//	       
//	    	
//	    	
//	    	return jdbcTemplate.queryForList(sql);
//	    }
	    
	 // Retrieve all Physical Counts
//	    public List<Map<String, Object>> getAllPhysicalCounts() {
//	        String sql = "SELECT pc.id, pc.physicalcountuid, pc.productuid, pc.physicalcount, pc.productname, pc.difference, " +
//	                     "pc.adjustmentreason, pc.countedby, pc.countdate, pc.approvalstatus, pc.processedby, " +
//	                     "pi.productname " +
//	                     "FROM physicalcount pc " +
//	                     "JOIN productinfo pi ON pi.productuid = pc.productuid;";
//	        return jdbcTemplate.queryForList(sql);
//	    }
	    
	    public List<Map<String, Object>> getAllPhysicalCounts() {
	        String sql = "SELECT id, physicalcountuid, productuid, productname, physicalcount, difference, " +
	                     "adjustmentreason, countedby, countdate, approvalstatus, processedby " +
	                     "FROM physicalcount";
	        return jdbcTemplate.queryForList(sql);
	    }



	    // Retrieve a Physical Count by ID
	    public physicalcount getPhysicalCountById(Long id) {
	        String sql = "SELECT * FROM physicalcount WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new PhysicalCountRowMapper(), id);
	    }

	    // Update a Physical Count
	    private static final String UPDATE_SQL = 
	    		   "UPDATE physicalcount SET physicalcountuid = ?, productuid = ?, productname = ?, physicalcount = ?, difference = ?, adjustmentreason = ?, supporting_document = ?, countedby = ?, countdate = ?, approvalstatus = ?, processedby = ?, updatedate = ? WHERE id = ?";
	    
	    public int updatePhysicalCount(physicalcount physicalcount) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
	        return jdbcTemplate.update(UPDATE_SQL,
	                physicalcount.getPhysicalcountuid(),
	                physicalcount.getProductuid(),     // ✅ दुसऱ्या क्रमांकावर productuid
	                physicalcount.getProductname(),    // ✅ तिसऱ्या क्रमांकावर productname
	                physicalcount.getPhysicalcount(),
	                physicalcount.getDifference(),
	                physicalcount.getAdjustmentreason(),
	                physicalcount.getSupportingDocument(),
	                physicalcount.getCountedby(),
	                physicalcount.getCountdate(),
	                physicalcount.getApprovalstatus(),
	                physicalcount.getProcessedby(),
	                formattedTimestamp,
	                physicalcount.getId()
	        );
	    }

	    // Delete a Physical Count by ID
	    public int deletePhysicalCount(Long id) {
	        String sql = "DELETE FROM physicalcount WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }

	    // RowMapper for mapping ResultSet to physicalcount entity
	    public static class PhysicalCountRowMapper implements RowMapper<physicalcount> {
	        @Override
	        public physicalcount mapRow(ResultSet rs, int rowNum) throws SQLException {
	            physicalcount physicalcount = new physicalcount();
	            
	            physicalcount.setPhysicalcountuid(rs.getString("physicalcountuid"));
	            physicalcount.setProductuid(rs.getString("productuid"));
	            //physicalcount.setStockmanageruid(rs.getString("stockmanageruid"));
	            physicalcount.setProductname(rs.getString("productname"));
	            physicalcount.setPhysicalcount(rs.getString("physicalcount"));
	            physicalcount.setDifference(rs.getString("difference"));
	            physicalcount.setAdjustmentreason(rs.getString("adjustmentreason"));
	            physicalcount.setCountedby(rs.getString("countedby"));
	            physicalcount.setCountdate(rs.getString("countdate"));
	            physicalcount.setSupportingDocument(rs.getBytes("supporting_document"));
	            physicalcount.setApprovalstatus(rs.getString("approvalstatus"));
	            physicalcount.setProcessedby(rs.getString("processedby"));
	            physicalcount.setId(rs.getLong("id"));
	            return physicalcount;
	        }
	    }

	    // Retrieve product details by product UID
	    public List<Map<String, Object>> getDataByProductUid(String productuid) {
	        String sql = "SELECT productname FROM productinfo WHERE productuid = ?";
	        return jdbcTemplate.queryForList(sql, productuid);
	    }

	    // Retrieve all product UIDs
	    public List<String> fetchProductUIds() {
	        String sql = "SELECT productuid FROM productinfo";
	        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
	    }
	    
//	    public List<String> fetchStockUIds() {
//	        String sql = "SELECT stockmanageruid FROM stockmanager";
//	        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("stockmanageruid"));
//	    }
	
//	    // Retrieve product details by product UID
//	    public List<Map<String, Object>> getDataByStockUid(String stockmanageruid) {
//	        String sql = "SELECT currentsystemstock FROM stockmanager WHERE stockmanageruid = ?";
//	        return jdbcTemplate.queryForList(sql, stockmanageruid);
//	    }

}

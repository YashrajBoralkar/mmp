//package com.prog.Dao.inventory;
//
//import java.sql.ResultSet;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import com.prog.model.inventory.ExpiryTracking;
//
//
//@Repository
//public class ExpiryDAO {
//	
//	@Autowired
//	private  JdbcTemplate jdbcTemplate;
//	
//	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	public int addExpiry(ExpiryTracking expirytracking) {
//		  String sql = "INSERT INTO expirytracking (expiryuid, productuid,batchnumber,stockquantity,warehouseuid, alertthreshold, recommendedaction, approvalrequired, actiontaken, processedby, actiondate, insertdate, updatedate) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
//	        
//			LocalDateTime now = LocalDateTime.now();
//
//	        return jdbcTemplate.update(sql,
//	        		expirytracking.getExpiryuid(),
//	        		expirytracking.getProductuid(),
//	        		expirytracking.getBatchnumber(),
//	        		expirytracking.getStockquantity(),
//	        		expirytracking.getWarehouseuid(),
//	        		expirytracking.getAlertthreshold(),
//	        		expirytracking.getRecommendedaction(),
//	        		expirytracking.getApprovalrequired(),
//	        		expirytracking.getActiontaken(),
//	        		expirytracking.getProcessedby(),
//	        		expirytracking.getActiondate(),
//	        		now.format(formatter),
//					now.format(formatter)
//	        		);
//	             }  
//	 private class ExpiryTrackingRowmapper implements RowMapper<ExpiryTracking>
//		{
//			@Override
//			public ExpiryTracking mapRow(ResultSet rs ,int rowNum) throws SQLException {
//				ExpiryTracking exp=new ExpiryTracking();
//				
//				exp.setId(rs.getLong("id"));
//				exp.setExpiryuid(rs.getString("expiryuid"));
//			    exp.setProductuid(rs.getString("productuid"));
//			    exp.setBatchnumber(rs.getString("batchnumber"));
//			    exp.setStockquantity(rs.getString("stockquantity"));
//                exp.setWarehouseuid(rs.getString("warehouseuid"));    
//			    exp.setAlertthreshold(rs.getString("alertthreshold"));
//			    exp.setRecommendedaction(rs.getString("recommendedaction"));
//			    exp.setApprovalrequired(rs.getString("approvalrequired"));			
//			    exp.setActiontaken(rs.getString("actiontaken"));
//			    exp.setProcessedby(rs.getString("processedby"));
//			    exp.setActiondate(rs.getString("actiondate"));
//			    exp.setInsertdate(LocalDateTime.parse(rs.getString("insertdate"), formatter));
//			    exp.setUpdatedate(LocalDateTime.parse(rs.getString("updatedate"), formatter));
//			    return exp;
//			}
//		}
//	  
//	  public List<ExpiryTracking> getExpiryTracking() {
//			  String sql = "SELECT * FROM expirytracking"; 
//		        return jdbcTemplate.query(sql, new ExpiryTrackingRowmapper());
//		}	
//	  
//	  public List<Map<String, Object>> showthefindall() {
//		    String sql = "SELECT ex.id, p.productname, p.manufacturingdate, p.expirydate, " +
//		                 "w.warehouselocation, ex.expiryuid, ex.productuid, ex.batchnumber, " +
//		                 "ex.stockquantity, ex.warehouseuid, ex.alertthreshold, ex.recommendedaction, " +
//		                 "ex.approvalrequired, ex.actiontaken, ex.processedby, ex.insertdate, " +
//		                 "ex.updatedate, ex.actiondate " +
//		                 "FROM expirytracking ex " +
//		                 "JOIN productinfo p ON ex.productuid = p.productuid " +
//		                 "JOIN warehouse w ON ex.warehouseuid = w.warehouseuid";
//		    
//		    return jdbcTemplate.queryForList(sql);
//		}
//
//	  
//	  public List <Map<String,Object>> getproductdetailsbyproductuid(String productuid) {
//	  String sql="select productname,expirydate,manufacturingdate from productinfo where productuid=?";
//	  return jdbcTemplate.queryForList(sql, productuid);
//			}
//
//	  
//	  public int deleteexpiry(Long id) {
//	        String sql = "DELETE FROM expirytracking WHERE id = ?";
//	        return jdbcTemplate.update(sql, id);
//	    }
//	  
//	  public ExpiryTracking getExpiryTrackingbyid(Long id) {
//			String sql="select * from expirytracking  where id=?";
//			return jdbcTemplate.queryForObject(sql, new ExpiryTrackingRowmapper(),id);
//			}
//	  
//	  public void updateExpiryTracking(ExpiryTracking expirytracking) {
//	        String sql = "UPDATE expirytracking  SET  batchnumber=?,  stockquantity=?, recommendedaction=?, approvalrequired=?, actiontaken=?, processedby=?, actiondate=?,updatedate=?  WHERE id =?";
//			LocalDateTime now = LocalDateTime.now();
//
//	        jdbcTemplate.update(sql,
//	        		expirytracking.getBatchnumber(),
//	        		expirytracking.getStockquantity(),
//	        		expirytracking.getRecommendedaction(),
//	        		expirytracking.getApprovalrequired(),
//	        		expirytracking.getActiontaken(),
//	        		expirytracking.getProcessedby(),
//	        		expirytracking.getActiondate(),
//					now.format(formatter),
//	        		expirytracking.getId()
//	        		);
//	    }
//	  
//}

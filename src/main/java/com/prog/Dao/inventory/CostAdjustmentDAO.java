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

import com.prog.model.erp.CostAdjustment;


@Repository
public class CostAdjustmentDAO {

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public int addCost(CostAdjustment costadjustment) {
		  String sql = "INSERT INTO cost_adjustment (costadjustmentuid,stockuid,unitcost,unitquantity, adjustmentvalue, newcost, reason, adjustmentdate, adjustedby, approvalstatus,approvedby,approvaldate,comments,insertdate,updatedate) VALUES (  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
			
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
			     
	        return jdbcTemplate.update(sql,
	        		costadjustment.getCostadjustmentuid(),
	        		costadjustment.getStockuid(),
	        		costadjustment.getUnitcost(),
	        		costadjustment.getUnitquantity(),
	        		costadjustment.getAdjustmentvalue(),
	        		costadjustment.getNewcost(),
	        		costadjustment.getReason(),
	        		costadjustment.getAdjustmentdate(),
	        		costadjustment.getAdjustedby(),
	        		costadjustment.getApprovalstatus(),
	        		costadjustment.getApprovedby(),
	        		costadjustment.getApprovaldate(),
	        		costadjustment.getComments(),
	        		formattedTimestamp,
	        		formattedTimestamp
	        		);
	             }
	
	 private class CostAdjustmentRowmapper implements RowMapper<CostAdjustment>
		{
			@Override
			public CostAdjustment mapRow(ResultSet rs ,int rowNum) throws SQLException {
				CostAdjustment cos=new CostAdjustment();
				
				cos.setId(rs.getLong("id"));
				cos.setCostadjustmentuid(rs.getString("costadjustmentuid"));
				cos.setStockuid(rs.getString("stockuid"));
				cos.setUnitcost(rs.getString("unitcost"));    
				cos.setUnitquantity(rs.getString("unitquantity"));
				cos.setAdjustmentvalue(rs.getString("adjustmentvalue"));
				cos.setNewcost(rs.getString("newcost"));
				cos.setReason(rs.getString("reason"));			
				cos.setAdjustmentdate(rs.getString("adjustmentdate"));
				cos.setAdjustedby(rs.getString("adjustedby"));
				cos.setApprovalstatus(rs.getString("approvalstatus"));
				cos.setApprovedby(rs.getString("approvedby"));
				cos.setApprovaldate(rs.getString("approvaldate"));
				cos.setComments(rs.getString("comments"));
			
			    return cos;
			}
		}
	  
	  public List<CostAdjustment> getCostAdjustment() {
			  String sql = "SELECT * FROM cost_adjustment"; 
		        return jdbcTemplate.query(sql, new CostAdjustmentRowmapper());
		}	
	  
	  public CostAdjustment getCostAdjustmentbyid(Long id) {
			String sql="select * from cost_adjustment  where id=?";
			return jdbcTemplate.queryForObject(sql, new CostAdjustmentRowmapper(),id);
			}
	  
	  public int deleteCost(Long id) {
	        String sql = "DELETE FROM cost_adjustment WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	  
	  public int updateCostAdjustment(CostAdjustment costadjustment) {
		    String sql = "UPDATE cost_adjustment SET unitcost=?, unitquantity=?, adjustmentvalue=?, newcost=?, reason=?, adjustmentdate=?, adjustedby=?, approvalstatus=?, approvedby=?, approvaldate=?, comments=?, updatedate=? WHERE id =?";

		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);
		
	        return jdbcTemplate.update(sql,
		        costadjustment.getUnitcost(),
		        costadjustment.getUnitquantity(),
		        costadjustment.getAdjustmentvalue(),
		        costadjustment.getNewcost(),
		        costadjustment.getReason(),
		        costadjustment.getAdjustmentdate(),
		        costadjustment.getAdjustedby(),
		        costadjustment.getApprovalstatus(),
		        costadjustment.getApprovedby(),
		        costadjustment.getApprovaldate(),
		        costadjustment.getComments(),
		        formattedTimestamp,
		        costadjustment.getId()
		    );
		}

	 
  	 
	  
	  public List<Map<String, Object>> showthefindall() {
	    	String sql = "SELECT co.id,s.stockname,s.stockcategory, co.costadjustmentuid, co.stockuid, co.unitcost, co.unitquantity,"
	    			+ "co.adjustmentvalue, co.newcost, co.reason,co.approvedby, co.approvaldate,co.comments, co.adjustmentdate, co.adjustedby, co.approvalstatus,"
	    			+ "co.insertdate, co.updatedate FROM cost_adjustment co "
	    			+ "JOIN stockinfo s ON co.stockuid = s.stockuid"
	    			+ "";
			return jdbcTemplate.queryForList(sql);
	       
	    } 
	  
	  
	  public List<Map<String, Object>> getDataByStockUid(String stockuid) {
		    String sql = "SELECT s.stockname FROM stock_valuation sv "
		               + "JOIN stockinfo s ON sv.stockuid = s.stockuid "
		               + "WHERE sv.stockuid = ?";
		    
		    return jdbcTemplate.queryForList(sql, stockuid);
		}
    
	   /**
	     * Fetch only Product UIDs of type GOODS and APPROVED status.
	     */
	    public List<String> getApprovedGoodsProductUids() {
	        String sql = "SELECT productuid FROM productinfo";
	        //String sql = "SELECT productuid FROM product_master WHERE producttype = 'GOODS' AND status = 'APPROVED'";

	        return jdbcTemplate.queryForList(sql, String.class);
	    }
	    
	    /**
	     * Fetch Unit Price and Available Quantity for given Product UID.
	     */
	    public Map<String, Object> getProductDetails(String productuid) {
	        String sql = "SELECT sellingprice as unitcost FROM productinfo WHERE productuid = ? "; 
	                    
	        return jdbcTemplate.queryForMap(sql, productuid);
	    }

	    public Map<String, Object> getstockDetails(String productuid) {
	        String sql = "SELECT totalstockquantity FROM stockinfo WHERE productuid = ? "; 
	                    
	        return jdbcTemplate.queryForMap(sql, productuid);
	    }

}
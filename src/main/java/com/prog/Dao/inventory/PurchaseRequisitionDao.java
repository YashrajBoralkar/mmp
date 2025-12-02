package com.prog.Dao.inventory;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.PurchaseRequisition;


@Repository
public class PurchaseRequisitionDao 
{
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
//============================================================================================================================	 

	 private static final String sql = "INSERT INTO purchase_requisition( " +
			    "approvaldate, approvalstatus, approvedby, department, insertdate, " +
			    "productuid, prioritylevel, quantity, reasonforrequest, remarks, " +
			    "requestdate, requestedby, requisitionuid, unitofmeasure, updatedate) " +
			    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			public int savePurchaseRequisitionForm(PurchaseRequisition pr) {
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			    String formattedTimestamp = LocalDateTime.now().format(formatter);
			    
			    return jdbcTemplate.update(sql,
			        pr.getApprovaldate(),          // approvaldate
			        pr.getApprovalstatus(),        // approvalstatus
			        pr.getApprovedby(),            // approvedby
			        pr.getDepartment(),            // department
			        formattedTimestamp,            // insertdate
			        pr.getProductuid(),               // itemuid
			        pr.getPrioritylevel(),         // prioritylevel
			        pr.getQuantity(),              // quantity
			        pr.getReasonforrequest(),      // reasonforrequest
			        pr.getRemarks(),               // remarks
			        pr.getRequestdate(),           // requestdate
			        pr.getRequestedby(),           // requestedby
			        pr.getRequisitionuid(),        // requisitionuid
			        pr.getUnitofmeasure(),         // unitofmeasure
			        formattedTimestamp             // updatedate
			    );
			}
//========================================================================================================================

    public List<PurchaseRequisition>getallpurchaserequest( ){
		String sql="select * from purchase_requisition  ";
    	return jdbcTemplate.query(sql,new PurchasereqRowMapper());
    	
    }
    
 //=======================================================================================================================
 
	 
    
    public PurchaseRequisition getPrbyRequisitionUid(Long id) {
        String sql = "SELECT * FROM purchase_requisition WHERE id = ?";
		return jdbcTemplate.queryForObject(sql,new PurchasereqRowMapper(),id);
        
    }
  
    public List<Map<String, Object>> getPurchasedLevelWithiteemhouse() {
        String sql = "select pr.id, pi.productname, pr.approvaldate, pr.approvalstatus,  pr.productuid, pr.prioritylevel, pr.quantity, pr.requestdate,\r\n"
        		+ "        		pr.requestedby, pr.requisitionuid, pr.unitofmeasure \r\n"
        		+ "        		from purchase_requisition pr join productinfo pi \r\n"
        		+ "        		on pr.productuid=pi.productuid";
            
        return jdbcTemplate.queryForList(sql);
        }

    
//========================================================================================================================
    
    
    public void deletepurchased(Long id) {
        String sql = "delete from purchase_requisition where id=?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No stock level found with id " + id);
        }
    }
    
//=========================================================================================================================
   
    private static class PurchasereqRowMapper implements RowMapper<PurchaseRequisition>
    {
    	public PurchaseRequisition mapRow(ResultSet rs,int rowNum) throws SQLException {
    		PurchaseRequisition pr=new PurchaseRequisition();
    		pr.setId(rs.getLong("id"));
    		pr.setApprovaldate(rs.getString("approvaldate"));
    		pr.setApprovalstatus(rs.getString("approvalstatus"));
    		pr.setApprovedby(rs.getString("approvedby"));
    		pr.setDepartment(rs.getString("department"));
    		pr.setProductuid(rs.getString("productuid"));
    		pr.setPrioritylevel(rs.getString("prioritylevel"));
    		pr.setQuantity(rs.getString("quantity"));
    		pr.setReasonforrequest(rs.getString("reasonforrequest"));
    		pr.setRemarks(rs.getString("remarks"));
    		pr.setRequestdate(rs.getString("requestdate"));
    		pr.setRequestedby(rs.getString("requestedby"));
    		pr.setRequisitionuid(rs.getString("requisitionuid"));
    		pr.setUnitofmeasure(rs.getString("unitofmeasure"));
    		
    		return pr;
    		
    	}
    	
    }
//==================================================================================================================
	
   
    	public void updatePrByUid(PurchaseRequisition pr) {
    	    String sql = "UPDATE purchase_requisition SET approvaldate = ?, approvalstatus = ?, approvedby = ?, department = ?, " +
    	                 "prioritylevel = ?, quantity = ?, reasonforrequest = ?, remarks = ?, requestdate = ?, " +
    	                 "requestedby = ?, unitofmeasure = ? WHERE id = ?";
    	     jdbcTemplate.update(sql,
    	            pr.getApprovaldate(),
    	            pr.getApprovalstatus(),
    	            pr.getApprovedby(),
    	            pr.getDepartment(),
    	            pr.getPrioritylevel(),
    	            pr.getQuantity(),
    	            pr.getReasonforrequest(),
    	            pr.getRemarks(),
    	            pr.getRequestdate(),
    	            pr.getRequestedby(),
    	            pr.getUnitofmeasure(),
    	            pr.getId()
    	    );
   }
//==================================================================================================================
}
	 


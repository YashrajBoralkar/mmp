package com.prog.Dao.purchase;

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

import com.prog.model.erp.noncompetitivepurchaserequest;


@Repository
public class NonCompetitivePurchaseRequestDAO {

	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;


	    private static final String INSERT_SQL = "INSERT INTO noncompetitivepurchaserequest (noncompetitivepurchaserequestuid , requestdate, requestingdepartment, requestedby, supplieruid, productuid, quantity, totalcost, expecteddeliverydate, deliverylocation, reasonforsolesourcepurchase, marketresearchconducted, uniquefeatures, riskofnotprocuring, budgetcode, availablebudget, fundingsource, financeapprovalstatus, financeapprover, financeapprovaldate, procurementapprovalstatus, procurementapprovername, procurementapprovaldate, comments, insertdate, updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    public int addPurchaseRequest(noncompetitivepurchaserequest request) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

	        return jdbcTemplate.update(INSERT_SQL,
	                request.getNoncompetitivepurchaserequestuid(),
	                request.getRequestdate(),
	                request.getRequestingdepartment(),
	                request.getRequestedby(),
	                request.getSupplieruid(),
	                request.getProductuid(),
	                request.getQuantity(),
	                request.getTotalcost(),
	                request.getExpecteddeliverydate(),
	                request.getDeliverylocation(),
	                request.getReasonforsolesourcepurchase(),
	                request.getMarketresearchconducted(),
	                request.getUniquefeatures(),
	                request.getRiskofnotprocuring(),
	                request.getBudgetcode(),
	                request.getAvailablebudget(),
	                request.getFundingsource(),
	                request.getFinanceapprovalstatus(),
	                request.getFinanceapprover(),
	                request.getFinanceapprovaldate(),
	                request.getProcurementapprovalstatus(),
	                request.getProcurementapprovername(),
	                request.getProcurementapprovaldate(),
	                request.getComments(),
	                formattedTimestamp,
	                formattedTimestamp
	        );
	    }

	           
		    // Retrieve all items
		    public  List<Map<String, Object>> getAllPurchaseRequest() {        

		    	String sql="SELECT \r\n"
		    			+ "    r.id, r.noncompetitivepurchaserequestuid , r.requestdate, r.requestingdepartment,  r.requestedby, s.suppliername,s.contactperson,s.contactnumber,s.email, r.quantity, \r\n"
		    			+ "    r.totalcost, r.reasonforsolesourcepurchase, r.marketresearchconducted, r.uniquefeatures,r.riskofnotprocuring,  \r\n"
		    			+ "    r.budgetcode,r.expecteddeliverydate, r.deliverylocation,  r.availablebudget, r.fundingsource, r.financeapprovalstatus, r.financeapprover, r.financeapprovaldate,  r.procurementapprovalstatus,  \r\n"
		    			+ "    r.procurementapprovername,r.procurementapprovaldate,r.comments, p.productname, p.sellingprice,r.expecteddeliverydate,r.deliverylocation\r\n"
		    			+ "    FROM noncompetitivepurchaserequest r \r\n"
		    			+ "JOIN productinfo p ON p.productuid = r.productuid  \r\n"
		    			+ "JOIN supplierregistration s ON s.supplieruid = r.supplieruid  \r\n";
	       
		       
		        return jdbcTemplate.queryForList(sql);
		        
		    }
	    	
		public noncompetitivepurchaserequest getPurchaseRequestById(Long id) {
			String sql = "SELECT * FROM noncompetitivepurchaserequest WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new PurchaseRequestRowMapper(), id);
	    }
		
		// Update a customerquotation

	    public int updatePurchaseRequest(noncompetitivepurchaserequest request) {
		    String sql = "UPDATE noncompetitivepurchaserequest SET requestdate = ?, requestingdepartment = ?, requestedby = ?, quantity = ?,  totalcost = ?, reasonforsolesourcepurchase = ?, marketresearchconducted = ?, uniquefeatures = ?, riskofnotprocuring = ?, budgetcode = ?, availablebudget = ?,fundingsource = ?,  financeapprovalstatus = ?,financeapprover = ?, financeapprovaldate = ?,  procurementapprovalstatus = ?, procurementapprovername = ?, procurementapprovaldate = ?, comments = ?,deliverylocation=?,deliverylocation = ?,updatedate = ? WHERE id = ?";
	    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedTimestamp = LocalDateTime.now().format(formatter);
		       return jdbcTemplate.update(sql,
	                
		    		   request.getRequestdate(),
		    		   request.getRequestingdepartment(),	                
		    		   request.getRequestedby(),
		    		   request.getQuantity(),
		    		   request.getTotalcost(),
		    		   request.getReasonforsolesourcepurchase(),
		    		   request.getMarketresearchconducted(),    		   
		    		   request.getUniquefeatures(),
		    		   request.getRiskofnotprocuring(),
		    		   request.getBudgetcode(),
		    		   request.getAvailablebudget(),
		    		   request.getFundingsource(),
		    		   request.getFinanceapprovalstatus(),
		    		   request.getFinanceapprover(),
		    		   request.getFinanceapprovaldate(),
		    		   request.getProcurementapprovalstatus(),
		    		   request.getProcurementapprovername(),
		    		   request.getProcurementapprovaldate(),
		    		   request.getComments(),
		    		   request.getDeliverylocation(),
		    		   request.getExpecteddeliverydate(),
		    		   formattedTimestamp,
	                   request.getId()
	        );
	    }
		
		
		
		 public int deletePurchaseRequest(Long id) {
		        String sql = "DELETE FROM noncompetitivepurchaserequest WHERE id = ?";
		        return jdbcTemplate.update(sql, id);
		    }
		
		 
		 public static class PurchaseRequestRowMapper implements RowMapper<noncompetitivepurchaserequest> {
		        @Override
		        public noncompetitivepurchaserequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	noncompetitivepurchaserequest PurchaseRequest = new noncompetitivepurchaserequest();
		            PurchaseRequest.setId(rs.getLong("id"));
		            PurchaseRequest.setNoncompetitivepurchaserequestuid(rs.getString("noncompetitivepurchaserequestuid"));
		            PurchaseRequest.setRequestdate(rs.getString("requestdate"));
		            PurchaseRequest.setRequestingdepartment(rs.getString("requestingdepartment"));
		            PurchaseRequest.setRequestedby(rs.getString("requestedby"));
		            PurchaseRequest.setProductuid(rs.getString("productuid"));
		            PurchaseRequest.setSupplieruid(rs.getString("supplieruid"));
		            PurchaseRequest.setQuantity(rs.getString("quantity"));
		            PurchaseRequest.setTotalcost(rs.getString("totalcost"));
		            PurchaseRequest.setExpecteddeliverydate(rs.getString("expecteddeliverydate"));		           
		            PurchaseRequest.setDeliverylocation(rs.getString("deliverylocation"));
		            PurchaseRequest.setReasonforsolesourcepurchase(rs.getString("reasonforsolesourcepurchase"));
		            PurchaseRequest.setMarketresearchconducted(rs.getString("marketresearchconducted"));
		            PurchaseRequest.setUniquefeatures(rs.getString("uniquefeatures"));
		            PurchaseRequest.setRiskofnotprocuring(rs.getString("riskofnotprocuring"));		            
		            PurchaseRequest.setBudgetcode(rs.getString("budgetcode"));
		            PurchaseRequest.setAvailablebudget(rs.getString("availablebudget"));
		            PurchaseRequest.setFundingsource(rs.getString("fundingsource"));
		            PurchaseRequest.setFinanceapprovalstatus(rs.getString("financeapprovalstatus"));
		            PurchaseRequest.setFinanceapprover(rs.getString("financeapprover"));
		            PurchaseRequest.setFinanceapprovaldate(rs.getString("financeapprovaldate"));
		            PurchaseRequest.setProcurementapprovalstatus(rs.getString("procurementapprovalstatus"));
		            PurchaseRequest.setProcurementapprovername(rs.getString("procurementapprovername"));
		            PurchaseRequest.setProcurementapprovaldate(rs.getString("procurementapprovaldate"));
		            PurchaseRequest.setComments(rs.getString("comments"));

		            return PurchaseRequest;
		        
		        }
		 }
		 
		 
//		 // Retrieve product details by product UID
//		    public List<Map<String, Object>> getDataBySupplieruid(String supplieruid) {
//		        String sql = "SELECT suppliername,suppliercontactperson,supplieremail,supplierphonenumber FROM supplier_info WHERE supplieruid = ?";
//		        return jdbcTemplate.queryForList(sql, supplieruid);
//		    }
//
//		    // Retrieve all product UIDs
//		    public List<String> fetchSupplierUIds() {
//		        String sql = "SELECT supplieruid FROM supplier_info";
//		        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("supplieruid"));
//		    }
//
//		    // Retrieve product details by product UID
//		    public List<Map<String, Object>> getDataByProductUid(String productuid) {
//		        String sql = "SELECT productname,productdescription,unitprice FROM product_info WHERE productuid = ?";
//		        return jdbcTemplate.queryForList(sql, productuid);
//		    }
//
//		    // Retrieve all product UIDs
//		    public List<String> fetchProductUIds() {
//		        String sql = "SELECT productuid FROM product_info";
//		        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
//		    }
//		    
//		    
//		    // Retrieve product details by product UID
//		    public List<Map<String, Object>> getDataByPLogisticsUid(String logisticsuid) {
//		        String sql = "SELECT expecteddeliverydate,destination FROM logistics_request WHERE logisticsuid = ?";
//		        return jdbcTemplate.queryForList(sql, logisticsuid);
//		    }
//
//		    // Retrieve all product UIDs
//		    public List<String> fetchLogisticsUIds() {
//		        String sql = "SELECT logisticsuid FROM logistics_request";
//		        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("logisticsuid"));
//		    }
//

		

		
	
}

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

import com.prog.model.erp.PurchaseOrder;


@Repository
public class PurchaseOrderDAO {

	@Autowired
	private  JdbcTemplate jdbcTemplate;
	
	  public int addPurchaseOrder(PurchaseOrder purchaseorder) {
		  String sql = "INSERT INTO purchase_order (pouid, podate,supplieruid,productuid,quantityordered, unitofmeasure, taxrate, unitprice, totalprice, deliverydate, deliverylocation, paymentterms, currency, totalamount, approvalstatus, authorizedby, approvaldate,insertdate,updatedate ) VALUES ( ?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);

	        return jdbcTemplate.update(sql,
	        		purchaseorder.getPouid(),
	        		purchaseorder.getPodate(),
	        		purchaseorder.getSupplieruid(),
	        		purchaseorder.getProductuid(),
	        		purchaseorder.getQuantityordered(),
	        		purchaseorder.getUnitofmeasure(),
	        		purchaseorder.getTaxrate(),
	        		purchaseorder.getUnitprice(),
	        		purchaseorder.getTotalprice(),
	        		purchaseorder.getDeliverydate(),
	        		purchaseorder.getDeliverylocation(),
	        		purchaseorder.getPaymentterms(),
	        		purchaseorder.getCurrency(),
	        		purchaseorder.getTotalamount(),
	        		purchaseorder.getApprovalstatus(),
	        		purchaseorder.getAuthorizedby(),
	        		purchaseorder.getApprovaldate(),
	                formattedTimestamp, // Insert date
                   formattedTimestamp); // Update date
           
	             }  
	  
	  private class PurchaseOrderRowmapper implements RowMapper<PurchaseOrder>
		{
			@Override
			public PurchaseOrder mapRow(ResultSet rs ,int rowNum) throws SQLException {
				PurchaseOrder ord=new PurchaseOrder();
				
			    ord.setId(rs.getLong("id"));
			    ord.setPouid(rs.getString("pouid"));
			    ord.setPodate(rs.getString("podate"));
			    ord.setSupplieruid(rs.getString("supplieruid"));
			    ord.setProductuid(rs.getString("productuid"));
			    ord.setQuantityordered(rs.getInt("quantityordered"));    
			    ord.setUnitofmeasure(rs.getString("unitofmeasure"));
			    ord.setTaxrate(rs.getDouble("taxrate"));
				ord.setCurrency(rs.getString("currency"));
			    ord.setUnitprice(rs.getBigDecimal("unitprice"));			
			    ord.setTotalprice(rs.getBigDecimal("totalprice"));
			    ord.setDeliverydate(rs.getString("deliverydate"));
			    ord.setDeliverylocation(rs.getString("deliverylocation"));
			    ord.setPaymentterms(rs.getString("paymentterms"));
			    ord.setTotalamount(rs.getDouble("totalamount"));
			    ord.setApprovalstatus(rs.getString("approvalstatus"));
			    ord.setAuthorizedby(rs.getString("authorizedby"));
			    ord.setApprovaldate(rs.getString("approvaldate"));
			    return ord;
			}
		}
	  
	  public List<PurchaseOrder> getPurchaseOrder() {
			  String sql = "SELECT * FROM purchase_order"; 
		        return jdbcTemplate.query(sql, new PurchaseOrderRowmapper());
		}

	  public int deleteorders(Long id) {
	        String sql = "DELETE FROM purchase_order WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }
	           

	  public PurchaseOrder getPurchaseOrderbyid(Long id) {
			String sql="select * from purchase_order where id=?";
			return jdbcTemplate.queryForObject(sql, new PurchaseOrderRowmapper(),id);
			}
		
	  public void updatePurchaseOrder(PurchaseOrder purchaseorder) {
	        String sql = "UPDATE purchase_order  SET  podate=?,  quantityordered=?, unitofmeasure=?, taxrate=?, unitprice=?, totalprice=?, deliverydate=?, deliverylocation=?, paymentterms=?, currency=?, totalamount=?, approvalstatus=?, authorizedby=?, approvaldate=?, updatedate = ? WHERE id =?";
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 	        String formattedTimestamp = LocalDateTime.now().format(formatter);

	        jdbcTemplate.update(sql,
	        		purchaseorder.getPodate(),
	        		purchaseorder.getQuantityordered(),
	        		purchaseorder.getUnitofmeasure(),
	        		purchaseorder.getTaxrate(),
	        		purchaseorder.getUnitprice(),
	        		purchaseorder.getTotalprice(),
	        		purchaseorder.getDeliverydate(),
	        		purchaseorder.getDeliverylocation(),
	        		purchaseorder.getPaymentterms(),
	        		purchaseorder.getCurrency(),
	        		purchaseorder.getTotalamount(),
	        		purchaseorder.getApprovalstatus(),
	        		purchaseorder.getAuthorizedby(),
	        		purchaseorder.getApprovaldate(),
	                formattedTimestamp, // Updated timestamp
	                purchaseorder.getId());
	    }
	  
	 
	
	 
	 public List <Map<String,Object>>showthefindall() {
	    	String sql = "SELECT po.id,s.suppliername,i.productname,po.approvaldate, po.approvalstatus, po.authorizedby, po.deliverydate, po.deliverylocation, \r\n"
	    			+ "	    			po.productuid, po.podate, po.pouid, po.supplieruid, \r\n"
	    			+ "	    			po.taxrate, po.totalamount, po.totalprice, po.unitofmeasure, po.unitprice  FROM purchase_order po \r\n"
	    			+ "	    			JOIN supplier s ON po.supplieruid = s.supplieruid \r\n"
	    			+ "	    			JOIN productinfo i ON po.productuid = i.productuid; \r\n"
	    			+ "";
			return jdbcTemplate.queryForList(sql);
	       
	    } 
	 
	 //FETCHING IN SUPPLIER INVOICE SUBMISSION FORM
	 public List<String> getAllSupplierPurchaseOrderDetailsData(){
			String sql="select pouid from purchase_order";
			return jdbcTemplate.queryForList(sql,String.class);
		}
		public PurchaseOrder getSupplierPurchaseOrderDetailsById(String pouid) {
			String sql="select totalamount from purchase_order where pouid=?";
			return jdbcTemplate.queryForObject(sql, new Object[]{pouid}, (rs, rowNum) -> {
				PurchaseOrder spod= new PurchaseOrder();
				spod.setTotalamount(rs.getDouble("totalamount"));
				return spod;
			});
		}
		
		
 //FETCHING IN POACKNOWLEDGE FORM

		 public List<String> fetchPoUIds() {
		        String sql = "SELECT pouid FROM purchase_order";
		        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("pouid"));
		    }

		    // Retrieve product details by product UID
		    public List<Map<String, Object>> getDataByPoUid(String pouid) {
		        String sql = "SELECT podate FROM purchase_order WHERE pouid = ?";
		        return jdbcTemplate.queryForList(sql, pouid);
		    }
		    
		    
//FETCHING IN GOODS RECEPIT NOTE FORM
		    
		    public List<String> getAllPurchaseUidsIngoodsReceiptnote() {
			   	 
		        String sql = "SELECT pouid FROM purchase_order";
		        return jdbcTemplate.queryForList(sql, String.class);
		    
		    }



 }


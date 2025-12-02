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

import com.prog.model.erp.CycleCountPhysical;


@Repository
public class CycleCountPhysicalDAO {
	
	@Autowired
	private JdbcTemplate jdbctemplete;
	
	
	public int AddPhysicalCycleCount(CycleCountPhysical pcc) {
		String sql = "INSERT INTO cyclecountphysical (id, cyclecountuid, category, scheduleddate, cyclecounttype, countinitiatedby,warehouseuid,shelfnumber,productuid,batchuid,unitofmeasure,systemstock,physicalstock,stockdifference,reasonofdiscrepancy,remark,correctiveactiontaken,resolvedby,resolutiondate,approvalstatus,verifiedby,reviewedby,finalapproval,insertdate,updatedate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	       
		return jdbctemplete.update(sql, 
				pcc.getId(),
				pcc.getCyclecountuid(),
				pcc.getCategory(),
				pcc.getScheduleddate(),
				pcc.getCyclecounttype(),
				pcc.getCountinitiatedby(),
				pcc.getWarehouseuid(),
				pcc.getShelfnumber(),
				pcc.getProductuid(),
				pcc.getBatchuid(),
				pcc.getUnitofmeasure(),
				pcc.getSystemstock(),
				pcc.getPhysicalstock(),
				pcc.getStockdifference(),
				pcc.getReasonofdiscrepancy(),
				pcc.getRemark(),
				pcc.getCorrectiveactiontaken(),
				pcc.getResolvedby(),
				pcc.getResolutiondate(),
				pcc.getApprovalstatus(),
				pcc.getVerifiedby(),
				pcc.getReviewedby(),
				pcc.getFinalapproval(),
				formattedTimestamp,
				formattedTimestamp
				);				
	}
	
	
		private static class PhysicalCycleCountRowMapper implements RowMapper<CycleCountPhysical>{
				
				@Override
				public CycleCountPhysical mapRow(ResultSet rs,int rowNum) throws SQLException{
					CycleCountPhysical pcc = new CycleCountPhysical();
					pcc.setId(rs.getLong("id"));
					pcc.setCyclecountuid(rs.getString("cyclecountuid"));
					pcc.setCategory(rs.getString("category"));
					pcc.setScheduleddate(rs.getString("scheduleddate"));
					pcc.setCyclecounttype(rs.getString("cyclecounttype"));
					pcc.setCountinitiatedby(rs.getString("countinitiatedby"));
					pcc.setWarehouseuid(rs.getString("warehouseuid"));
					pcc.setShelfnumber(rs.getString("shelfnumber"));
					pcc.setProductuid(rs.getString("productuid"));
					pcc.setBatchuid(rs.getString("batchuid"));
					pcc.setUnitofmeasure(rs.getString("unitofmeasure"));
					pcc.setSystemstock(rs.getString("systemstock"));
					pcc.setPhysicalstock(rs.getString("physicalstock"));
					pcc.setStockdifference(rs.getString("stockdifference"));
					pcc.setReasonofdiscrepancy(rs.getString("reasonofdiscrepancy"));
					pcc.setRemark(rs.getString("remark"));
					pcc.setCorrectiveactiontaken(rs.getString("correctiveactiontaken"));
					pcc.setResolvedby(rs.getString("resolvedby"));
					pcc.setResolutiondate(rs.getString("resolutiondate"));
					pcc.setApprovalstatus(rs.getString("approvalstatus"));
					pcc.setVerifiedby(rs.getString("verifiedby"));
					pcc.setReviewedby(rs.getString("reviewedby"));
					pcc.setFinalapproval(rs.getString("finalapproval"));
					
					return pcc;
				}
			}
		
		
		public List<Map<String, Object>> getPhysicalCycleCount(){
			String sql = "SELECT cc.id,cc.cyclecountuid,cc.productuid,p.productname,cc.warehouseuid,w.warehousename,cc.systemstock,cc.physicalstock,cc.stockdifference,cc.scheduleddate,cc.approvalstatus FROM cyclecountphysical cc "
					+ " JOIN productinfo p ON p.productuid = cc.productuid"
					+ " JOIN warehouseinfo w ON w.warehouseuid = cc.warehouseuid;";
			return jdbctemplete.queryForList(sql);
		}
		
		
		
		public int DeletePhysicalCycleCount(Long id) {
			String sql = "DELETE FROM cyclecountphysical WHERE id = ?";
			return jdbctemplete.update(sql, id);	
		}
		
		
		public CycleCountPhysical GetPhysicalCycleCountByid(Long id) {
			String sql = "SELECT * FROM cyclecountphysical WHERE id = ?";
				return jdbctemplete.queryForObject(sql, new PhysicalCycleCountRowMapper(), id);
		}
		
		
		
		
		public int UpdatePhysicalCycleCount(CycleCountPhysical pcc) {
			String sql = "UPDATE cyclecountphysical SET " +
				    "cyclecountuid = ?, category = ?, scheduleddate = ?, cyclecounttype = ?, countinitiatedby = ?, " +
				    "warehouseuid = ?, productuid = ?, shelfnumber = ?, unitofmeasure = ?, systemstock = ?, physicalstock = ?, " +
				    "stockdifference = ?, reasonofdiscrepancy = ?, remark = ?, correctiveactiontaken = ?, resolvedby = ?, " +
				    "resolutiondate = ?, approvalstatus = ?, verifiedby = ?, reviewedby = ?, finalapproval = ?, updatedate=? " +
				    "WHERE id = ?";

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		     String formattedTimestamp = LocalDateTime.now().format(formatter);
		          
			return jdbctemplete.update(sql, 
					pcc.getCyclecountuid(),
					pcc.getCategory(),
					pcc.getScheduleddate(),
					pcc.getCyclecounttype(),
					pcc.getCountinitiatedby(),
					 pcc.getWarehouseuid(),    // ✅ added
					    pcc.getProductuid(),  
					pcc.getShelfnumber(),
					pcc.getUnitofmeasure(),
					pcc.getSystemstock(),
					pcc.getPhysicalstock(),
					pcc.getStockdifference(),
					pcc.getReasonofdiscrepancy(),
					pcc.getRemark(),
					pcc.getCorrectiveactiontaken(),
					pcc.getResolvedby(),
					pcc.getResolutiondate(),
					pcc.getApprovalstatus(),
					pcc.getVerifiedby(),
					pcc.getReviewedby(),
					pcc.getFinalapproval(),
					formattedTimestamp,
					pcc.getId());
		}
	

		public List<String> fetchBatchIds() {
		    String sql = "SELECT batchuid FROM batchinfo";
		    return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("batchuid"));
		}
		
		
		public List<Map<String, Object>> getdataByBatchuid(String batchuid) {
		    String sql = "SELECT p.productname, p.productuid, w.warehouseuid, w.warehousename FROM productinfo p "
		               + "JOIN batchinfo b ON p.productuid = b.productuid "
		               + "JOIN warehouseinfo w ON w.warehouseuid = b.warehouseuid "
		               + "WHERE b.batchuid = ?";
		    
		    return jdbctemplete.queryForList(sql,batchuid);
		}
				
		//Fetch Product Name and Warehouse UID using Product UID
		public Map<String, Object> getProductInfoByProductUid(String productUid) {
		 String sql = "SELECT p.productname, w.warehouseuid " +
		              "FROM productinfo p " +
		              "JOIN inventoryentry i ON i.productuid = p.productuid " +
		              "JOIN warehouseinfo w ON w.warehouseuid = i.warehouseuid " +
		              "WHERE p.productuid = ?";
		 try {
		     return jdbctemplete.queryForMap(sql, productUid);
		 } catch (Exception e) {
		     return null; // Invalid Product UID
		 }
		}
		
		
		//Fetch Warehouse Name using Warehouse UID
		public String getWarehouseNameByUid(String warehouseUid) {
		 String sql = "SELECT warehousename FROM warehouseinfo WHERE warehouseuid = ?";
		 try {
		     return jdbctemplete.queryForObject(sql, String.class, warehouseUid);
		 } catch (Exception e) {
		     return null; // Invalid Warehouse UID
		 }
		}
		//Fetch Global Quantity from Realtimeupdate by Product UID
		public Double getGlobalQuantityByProductUid(String productUid) {
		 String sql = "SELECT globalrealtimequantity FROM realtimeupdate WHERE productuid = ? ORDER BY id DESC LIMIT 1";
		 try {
		     return jdbctemplete.queryForObject(sql, Double.class, productUid);
		 } catch (Exception e) {
		     return 0.0;
		 }
		}
		// Fetch Product Name & All Linked WarehouseUIDs from realtimeupdate
		
			// Fetch Product Name + All WarehouseUIDs linked with ProductUID
			public Map<String, Object> getProductNameAndWarehouses(String productUid) {
			    try {
			        // Step 1: Product Name
			        String sqlProduct = "SELECT productname FROM productinfo WHERE productuid = ?";
			        String productName = jdbctemplete.queryForObject(sqlProduct, String.class, productUid);

			        // Step 2: All linked Warehouses
			        String sqlWarehouses = "SELECT DISTINCT warehouseuid FROM realtimeupdate WHERE productuid = ?";
			        List<String> warehouses = jdbctemplete.query(sqlWarehouses,
			                (rs, rowNum) -> rs.getString("warehouseuid"),
			                productUid);

			      
			        Map<String, Object> result = new java.util.HashMap<>();
			        result.put("productname", productName);
			        result.put("warehouses", warehouses);

			        return result;

			    } catch (Exception e) {
			        return null;
			    }
			}


			// Fetch product name and warehouse UID from realtimeupdate by product UID
			public Map<String, Object> getRealtimeProductAndWarehouse(String productUid) {
			    String sql = "SELECT r.productuid, p.productname, r.warehouseuid " +
			                 "FROM realtimeupdate r " +
			                 "JOIN productinfo p ON r.productuid = p.productuid " +
			                 "WHERE r.productuid = ? " +
			                 "ORDER BY r.id DESC LIMIT 1";
			    try {
			        return jdbctemplete.queryForMap(sql, productUid);
			    } catch (Exception e) {
			        return null; 
			    }
			}
			// ✅ Fetch latest realtime quantity from realtimeupdate table by warehouseuid
			public Double getLatestRealtimeQuantityByWarehouse(String warehouseUid) {
			    String sql = "SELECT realtimequantity FROM realtimeupdate WHERE warehouseuid = ? ORDER BY id DESC LIMIT 1";
			    try {
			        return jdbctemplete.queryForObject(sql, Double.class, warehouseUid);
			    } catch (Exception e) {
			        return 0.0;
			    }
			}
			
			public List<CycleCountPhysical> getAllEntities() {
			    String sql = "SELECT * FROM cyclecountphysical";
			    return jdbctemplete.query(sql, new PhysicalCycleCountRowMapper());
			}


}

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

import com.prog.model.erp.Batchinfo;


@Repository
public class BatchInfoDao 

{
	@Autowired
	private JdbcTemplate jdbctemplete;
	
	public int AddBatchInfo(Batchinfo bi) {
		String sql = "INSERT INTO batchinfo (id, batchuid, lotuids, productuid, lotquantity,batchprice,manufacturingdate,expirydate,insertdate,updatedate) VALUES(?,?,?,?,?,?,?,?,?,?)";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbctemplete.update(sql, 
				bi.getId(),
				bi.getBatchuid(),
				bi.getLotuids(),
				bi.getProductuid(),
				bi.getLotquantity(),
				bi.getBatchprice(),
				bi.getManufacturingdate(),
				bi.getExpirydate(),
				formattedTimestamp,
				formattedTimestamp);

	}
	
	
	
	private static class BatchInfoRowMapper implements RowMapper<Batchinfo>{
		
		@Override
		public Batchinfo mapRow(ResultSet rs,int rowNum) throws SQLException{
			Batchinfo bi = new Batchinfo();
			bi.setId(rs.getLong("id"));
			bi.setProductuid(rs.getString("productuid"));
			bi.setBatchuid(rs.getString("batchuid"));
			bi.setLotuids(rs.getString("lotuids"));
			bi.setLotquantity(rs.getLong("lotquantity"));
			bi.setBatchprice(rs.getString("batchprice"));
			bi.setManufacturingdate(rs.getString("manufacturingdate"));
			bi.setExpirydate(rs.getString("expirydate"));


			
			return bi;
		}
	}
	
	
	public List<Map<String, Object>> getBatchInfo() {
	    String sql = "SELECT bi.id, bi.batchuid, bi.lotquantity, bi.lotuids, " +
	                 "po.productname, po.productuid, po.productcategory,po.manufacturingdate, po.expirydate " +
	                 "FROM batchinfo bi " +
	                 "JOIN productinfo po ON po.productuid = bi.productuid";
	    return jdbctemplete.queryForList(sql);
	}
	
	
	
	public int DeleteBatchInfo(Long id) {
		String sql = "DELETE FROM batchinfo WHERE id = ?";
		return jdbctemplete.update(sql, id);
		
	}
	
	
	public Batchinfo GetBatchInfoByid(Long id) {
		String sql = "SELECT * FROM batchinfo WHERE id = ?";
			return jdbctemplete.queryForObject(sql, new BatchInfoRowMapper(), id);
	}
	
	
	public int UpdateBatchInfo(Batchinfo bi) {
		String sql = "UPDATE batchinfo SET " +
                " lotquantity=?,lotuids=?,updatedate=? WHERE id = ?";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbctemplete.update(sql, 
				
				bi.getLotquantity(),
				bi.getLotuids(),
				formattedTimestamp,
				bi.getId());
	}
	
	
	
	public List<Map<String, Object>> getLotIds(String productuid) {
	    String sql = "SELECT lotuid, lotprice FROM lot_info WHERE productuid = ?";
	    return jdbctemplete.queryForList(sql, productuid);
	}

	
	
	
	public List<String> getProductIds() {
        String sql = "SELECT productuid FROM productinfo";
        return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }
	
	
	
	public List<Map<String, Object>> getDataByProductId(String productuid) {
	    String sql = "SELECT productname, productcategory,manufacturingdate,expirydate FROM productinfo WHERE productuid = ?";
	    return jdbctemplete.queryForList(sql, productuid);
	}
	
//	public List<String> getwarehouseIds() {
//        String sql = "SELECT warehouseuid FROM warehouseinfo";
//        return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("warehouseuid"));
//    }
//	
//	
//	public List<Map<String, Object>> getDataByWaerhouseUid(String warehouseuid) {
//	    String sql = "SELECT warehousename FROM warehouseinfo WHERE warehouseuid = ?";
//	    return jdbctemplete.queryForList(sql, warehouseuid);
//	}

	
   



	//fetch
	
	public List<String> getAllBatchUids() {
	   	 
        String sql = "SELECT batchuid FROM batchinfo"; // Replace with your actual table and column names
        return jdbctemplete.queryForList(sql, String.class);
      
    }
	public Batchinfo getbatchDetailsByUid(String batchuid) {
        String sql = "SELECT  id FROM batchinfo WHERE batchuid = ?";
        return jdbctemplete.queryForObject(sql, new Object[]{batchuid}, (rs, rowNum) -> {
        	Batchinfo details = new Batchinfo();
            details.setBatchuid(rs.getString("batchuid"));
            return details;
        }); 	}

}

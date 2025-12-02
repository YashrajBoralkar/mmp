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

import com.prog.model.erp.LotInfo;


@Repository
public class LotInfoDAO {
	
	@Autowired
	private JdbcTemplate jdbctemplete;
	
	
	public int AddLotInfo(LotInfo li) {
		String sql = "INSERT INTO lot_info (id, lotuid, productuid, productquantity,  lotprice,manufacturingdate,expirydate,insertedate) VALUES(?,?,?,?,?,?,?,?)";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
		return jdbctemplete.update(sql, 
				li.getId(),
				li.getLotuid(),
				li.getProductuid(),
				li.getProductquantity(),
				li.getLotprice(),
				li.getManufacturingdate(),
				li.getExpirydate(),
				formattedTimestamp);
	}
	
	
	
	private static class LotInfoRowMapper implements RowMapper<LotInfo>{
		
		@Override
		public LotInfo mapRow(ResultSet rs,int rowNum) throws SQLException{
			LotInfo li = new LotInfo();
			li.setId(rs.getLong("id"));
			li.setProductuid(rs.getString("productuid"));
			li.setLotuid(rs.getString("lotuid"));
			li.setProductquantity(rs.getLong("productquantity"));
			li.setLotprice(rs.getString("lotprice"));
			li.setManufacturingdate(rs.getString("manufacturingdate"));
			li.setExpirydate(rs.getString("expirydate"));


			
			return li;
		}
	}
	
	
	public List<Map<String, Object>> getLotInfo(){
		String sql = "SELECT l.id,l.lotuid, l.productquantity, p.productname,p.productcategory,p.productsubcategory,p.expirydate, p.sellingprice, p.manufacturingdate, p.productuid  FROM productinfo p "
	               + "JOIN lot_info l ON p.productuid = l.productuid ";
	    return jdbctemplete.queryForList(sql);
	}
	
	
	
	public int DeleteLotInfo(Long id) {
		String sql = "DELETE FROM lot_info WHERE id = ?";
		return jdbctemplete.update(sql, id);
		
	}
	
	
	public LotInfo GetLotInfoByid(Long id) {
		String sql = "SELECT * FROM lot_info WHERE id = ?";
			return jdbctemplete.queryForObject(sql, new LotInfoRowMapper(), id);
	}
	
	
	public int UpdateLotInfo(LotInfo li) {
		String sql = "UPDATE lot_info SET " +
                "productuid=?, productquantity=?, updateddate=? WHERE id = ?";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbctemplete.update(sql, 
				li.getProductuid(),
				li.getProductquantity(),
				formattedTimestamp,
				li.getId());
	}
	
	
	public List<String> getProductIds() {
        String sql = "SELECT productuid FROM productinfo";
        return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }
	
	
	public List<Map<String, Object>> getDataByProductId(String productuid) {
	    String sql = "SELECT productname, productcategory, productsubcategory,manufacturingdate, expirydate,sellingprice FROM productinfo WHERE productuid = ?";
	    return jdbctemplete.queryForList(sql, productuid);
	}
	
//	public List<String> getwarehouseIds() {
//        String sql = "SELECT warehouseuid FROM warehouseinfo";
//        return jdbctemplete.query(sql, (rs, rowNum) -> rs.getString("warehouseuid"));
//    }
//	
	
//	public List<Map<String, Object>> getDataByWaerhouseUid(String warehouseuid) {
//	    String sql = "SELECT warehousename FROM warehouseinfo WHERE warehouseuid = ?";
//	    return jdbctemplete.queryForList(sql, warehouseuid);
//	}


}

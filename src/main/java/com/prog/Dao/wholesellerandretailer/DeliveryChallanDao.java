package com.prog.Dao.wholesellerandretailer;

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

import com.prog.model.erp.Deliverychallan;
import com.prog.model.erp.Rawmaterialdeliverychallan;


@Repository
public class DeliveryChallanDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	class DeliveryChallanRowMapper implements RowMapper<Deliverychallan> {
		@Override
		public Deliverychallan mapRow(ResultSet rs, int rowNum) throws SQLException {
			Deliverychallan dc = new Deliverychallan();
			dc.setId(rs.getLong("id"));
			dc.setDeliverychallanuid(rs.getString("deliverychallanuid"));
			dc.setDrivername(rs.getString("drivername"));
			dc.setDispatchdate(rs.getString("dispatchdate"));
			dc.setOrderuid(rs.getString("orderuid"));
			dc.setSignature(rs.getString("signature"));
			dc.setVehiclenumber(rs.getString("vehiclenumber"));
			dc.setReceivername(rs.getString("receivername"));
			return dc;
		}
	}
	
	public int postChallan(Deliverychallan challan) {
		String sql ="INSERT into deliverychallan (deliverychallanuid, dispatchdate, drivername, orderuid, receivername, signature, vehiclenumber, insertdate, updatedate) VALUES (?,?,?,?,?,?,?,?,?,?)";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbcTemplate.update(sql,
				challan.getDeliverychallanuid(), 
				challan.getDispatchdate(), 
				challan.getDrivername(), 
				challan.getOrderuid(), 
				challan.getReceivername(), 
				challan.getSignature(),  
				challan.getVehiclenumber(),
				formattedTimestamp,
				formattedTimestamp
				);
	}

	public int deleteChallan(Long id) {
		String sql = "DELETE from deliverychallan where id = ?";
		return jdbcTemplate.update(sql,id);
	}

	public List<Map<String, Object>> getAllChallan() {
		String sql = "select d.dcid, d.challannumber, d.dispatchdate, d.drivername, d.receivername, d.signature, d.vehiclenumber ,r.productname, r.quantityordered from deliverychallan d Join retailorder r on d.orderuid = r.orderid";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getChallanById(Long id) {
		String sql = "SELECT d.dcid, d.challannumber, d.dispatchdate, d.drivername, d.orderuid ,d.receivername, d.signature, d.vehiclenumber,r.productname, r.quantityordered from deliverychallan d Join retailorder r on d.orderuid = r.orderid WHERE id = ?";
		return jdbcTemplate.queryForList(sql, id);
	}

	public int updateChallan(Deliverychallan challan) {
		String sql = "UPDATE deliverychallan SET dispatchdate = ?, drivername = ?, receivername = ?, signature = ?, vehiclenumber = ?, orderuid = ?, updatedate = ? WHERE id = ?";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedTimestamp = LocalDateTime.now().format(formatter);	
		return jdbcTemplate.update(sql, 
				challan.getDispatchdate(), 
				challan.getDrivername(),
				challan.getReceivername(), 
				challan.getSignature(),
				challan.getVehiclenumber(), 
				challan.getOrderuid(),
				formattedTimestamp,
				challan.getId());
	}
	
}

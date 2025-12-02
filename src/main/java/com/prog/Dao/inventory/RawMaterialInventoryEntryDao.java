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

import com.prog.model.erp.RawMaterialInventoryEntry;


@Repository
public class RawMaterialInventoryEntryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public class RawMaterialInventoryEntryRowMapper implements RowMapper<RawMaterialInventoryEntry> {
		@Override
		public RawMaterialInventoryEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
			RawMaterialInventoryEntry entry = new RawMaterialInventoryEntry();
			entry.setId(rs.getLong("id"));
			entry.setRawmaterialinventoryentryuid(rs.getString("rawmaterialinventoryentryuid"));
			entry.setRawmaterialreceiptnoteuid(rs.getString("rawmaterialreceiptnoteuid"));
			entry.setRawmaterialuid(rs.getString("rawmaterialuid"));
			entry.setRawmaterialname(rs.getString("rawmaterialname"));			
			entry.setActualquantity(rs.getInt("actualquantity"));
			entry.setStoragelocation(rs.getString("storagelocation"));
			entry.setEntrydate(rs.getString("entrydate"));
			entry.setEmployeeuid(rs.getString("employeeuid"));
			return entry;
		}
	}

	public void save(RawMaterialInventoryEntry entry) {
		String sql = "INSERT INTO rawmaterialinventoryentry (rawmaterialinventoryentryuid, rawmaterialreceiptnoteuid,rawmaterialuid,rawmaterialname,actualquantity, storagelocation, entrydate, employeeuid,insertdate, updatedate) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	    
		jdbcTemplate.update(sql, 
				entry.getRawmaterialinventoryentryuid(), 
				entry.getRawmaterialreceiptnoteuid(),
				entry.getRawmaterialuid(),
				entry.getRawmaterialname(),
				entry.getActualquantity(), 
				entry.getStoragelocation(), 
				entry.getEntrydate(), 
				entry.getEmployeeuid(),
				formattedTimestamp,
				formattedTimestamp
				);
	}

	public List<Map<String, Object>> findAll() {
		String sql = "SELECT rmie.id, rmie.rawmaterialinventoryentryuid, rmie.rawmaterialreceiptnoteuid, rmie.rawmaterialuid, rmie.rawmaterialname, rmie.actualquantity, e.employeeUID, CONCAT(first_name, ' ', last_name) AS fullName \n"
				+ "FROM rawmaterialinventoryentry rmie\n"
				+ "JOIN employee e ON rmie.employeeuid=e.employeeuid;";
		return jdbcTemplate.queryForList(sql);
	}

	public RawMaterialInventoryEntry findById(int id) {
		String sql = "SELECT * FROM rawmaterialinventoryentry WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new RawMaterialInventoryEntryRowMapper(), id);
	}

	public int update(RawMaterialInventoryEntry entry) {
		String sql = "UPDATE rawmaterialinventoryentry SET actualquantity=?, storagelocation=?, entrydate=?, updatedate=? WHERE id=?";
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	    
		return jdbcTemplate.update(sql, 
				entry.getActualquantity(), 
				entry.getStoragelocation(), 
				entry.getEntrydate(),
				formattedTimestamp,
				entry.getId());
	}

	public void delete(int id) {
		String sql = "DELETE FROM rawmaterialinventoryentry WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	public List<Map<String, Object>> findAllWithJoin() {
		String sql = " SELECT rn.rawmaterialreceiptnoteuid, rn.rawmaterialname, rn.rawmaterialuid, rn.orderedquantity FROM rawmaterialreceiptnote rn WHERE rawmaterialstatus = 'ACCEPTED'";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> findAllWithJoinForInventoryList() {
		String sql = """
				    SELECT inv.*, rn.rawmaterialname, rn.rawmaterialuid, rn.orderedquantity
				    FROM rawmaterialinventoryentry inv
				    JOIN rawmaterialgoodsreceiptnote rn
				    ON inv.rawmaterialreceiptnoteuid = rn.rawmaterialreceiptnoteuid
				""";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> findAllWarehouses() {
		String sql = "SELECT warehouseuid, warehousename FROM warehouseinfo WHERE status = 'Active'";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getAllEmployeeUid() {
		String sql = "SELECT CONCAT(first_name, ' ', last_name) AS fullname, employeeuid FROM employee";
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getmaterialDetails(String rawmaterialreceiptnoteuid) {
		String sql = "SELECT rawmaterialuid, rawmaterialname, orderedquantity FROM rawmaterialreceiptnote Where rawmaterialreceiptnoteuid = ? and rawmaterialstatus = 'ACCEPTED'";
		return jdbcTemplate.queryForList(sql,rawmaterialreceiptnoteuid);
	}

}
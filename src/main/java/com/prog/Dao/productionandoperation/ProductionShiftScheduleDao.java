package com.prog.Dao.productionandoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.ProductionShiftSchedule;

@Repository
public class ProductionShiftScheduleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int addShiftSchedule(ProductionShiftSchedule pss) {
		String SQL = "INSERT INTO productionshiftscheduling "
				+ "(id, supervisoruid, emplyeenames, employeeuid, empbatchuid,productiondepartment, batchname, shiftdate, shiftstarttime, shiftendtime, totalshiftduration, "
				+ "productionplanninguid, productuid, workstation, breaktiming, breakstarttime, breakendtime, totalshiftcountperday, "
				+ "supervisorname, productionscheduleuid, insertdate, updatedate) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbcTemplate.update(SQL,
				pss.getId(), 
				pss.getSupervisoruid(), 
				pss.getEmplyeenames(),
				pss.getEmployeeuid(), 
				pss.getEmpbatchuid(), 
				pss.getProductiondepartment(), 
				pss.getBatchname(),
				pss.getShiftdate(), 
				pss.getShiftstarttime(), 
				pss.getShiftendtime(), 
				pss.getTotalshiftduration(),
				pss.getProductionplanninguid(), 
				pss.getProductuid(), 
				pss.getWorkstation(), 
				pss.getBreaktiming(),
				pss.getBreakstarttime(), 
				pss.getBreakendtime(), 
				pss.getTotalshiftcountperday(), 
				pss.getSupervisorname(),
				pss.getProductionscheduleuid(), 
				formattedTimestamp, 
				formattedTimestamp);
	}

	// Retrieve all shift schedules with employee details
	public List<Map<String, Object>> getAllShiftSchedules() {
		// Step 1: Fetch all shift schedules (with comma-separated employeeuids)
		String sql = "SELECT * FROM productionshiftscheduling ";
				
		return jdbcTemplate.queryForList(sql);

	}

	public int deleteShiftSchedule(Long id) {
		String sql = "DELETE FROM productionshiftscheduling WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	public ProductionShiftSchedule getShiftScheduleById(Long id) {
		String sql = "SELECT * FROM productionshiftscheduling WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new ShiftScheduleRowMapper(), id);
	}

	public int updateShiftSchedule(ProductionShiftSchedule pss) {
		String sql = "UPDATE productionshiftscheduling SET "
				+ "productiondepartment = ?, shiftdate = ?, shiftstarttime = ?, shiftendtime = ?, "
				+ "totalshiftduration = ?, workstation = ?, breaktiming = ?, breakstarttime = ?, breakendtime = ?, "
				+ "totalshiftcountperday = ?, supervisorname = ?, updatedate = ? WHERE id = ?";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbcTemplate.update(sql, pss.getProductiondepartment(), pss.getShiftdate(), pss.getShiftstarttime(),
				pss.getShiftendtime(), pss.getTotalshiftduration(), pss.getWorkstation(), pss.getBreaktiming(),
				pss.getBreakstarttime(), pss.getBreakendtime(), pss.getTotalshiftcountperday(), pss.getSupervisorname(),
				formattedTimestamp, pss.getId());
	}

	public static class ShiftScheduleRowMapper implements RowMapper<ProductionShiftSchedule> {
		@Override
		public ProductionShiftSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductionShiftSchedule ShiftSchedule = new ProductionShiftSchedule();
			ShiftSchedule.setId(rs.getLong("id"));
			ShiftSchedule.setProductionscheduleuid(rs.getString("productionscheduleuid"));
			ShiftSchedule.setProductiondepartment(rs.getString("productiondepartment"));
			ShiftSchedule.setShiftdate(rs.getString("shiftdate"));
			ShiftSchedule.setShiftstarttime(rs.getString("shiftstarttime"));
			ShiftSchedule.setShiftendtime(rs.getString("shiftendtime"));
			ShiftSchedule.setTotalshiftduration(rs.getString("totalshiftduration"));
			ShiftSchedule.setEmpbatchuid(rs.getString("empbatchuid"));
			ShiftSchedule.setEmployeeuid(rs.getString("employeeuid"));
			ShiftSchedule.setEmplyeenames(rs.getString("emplyeenames"));

			ShiftSchedule.setBatchname(rs.getString("batchname"));

			ShiftSchedule.setProductionplanninguid(rs.getString("productionplanninguid"));
			ShiftSchedule.setProductuid(rs.getString("productuid"));
			ShiftSchedule.setWorkstation(rs.getString("workstation"));
			ShiftSchedule.setBreaktiming(rs.getString("breaktiming"));
			ShiftSchedule.setBreakstarttime(rs.getString("breakstarttime"));
			ShiftSchedule.setBreakendtime(rs.getString("breakendtime"));
			ShiftSchedule.setTotalshiftcountperday(rs.getString("totalshiftcountperday"));
			ShiftSchedule.setSupervisoruid(rs.getString("supervisoruid"));
			ShiftSchedule.setSupervisorname(rs.getString("supervisorname"));
			return ShiftSchedule;
		}
	}

	public List<Map<String, Object>> getDataByEmployeeUid(List<String> employeeUIDs) {
		if (employeeUIDs == null || employeeUIDs.isEmpty()) {
			return new ArrayList<>();
		}

		// Create placeholders (?, ?, ?) based on list size
		String placeholders = String.join(",", employeeUIDs.stream().map(e -> "?").toArray(String[]::new));
		String sql = "SELECT employeeuid, first_name, last_name FROM employee WHERE employeeuid IN (" + placeholders
				+ ")";

		return jdbcTemplate.queryForList(sql, employeeUIDs.toArray());
	}

	// Retrieve all product UIDs
	public List<String> fetchEmployeeBatchUIds() {
		String sql = "SELECT empbatchuid FROM employeebatchcreation";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("empbatchuid"));
	}

	public List<String> fetchBatchNameByEmpBatchUid(String empbatchuid) {
		String sql = "SELECT batchname FROM employeebatchcreation WHERE empbatchuid = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("batchname"), empbatchuid);
	}

	public List<String> getProductionPlanningUID() {
		String sql = "select productionplanninguid from productionplanning";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productionplanninguid"));
	}

	public List<Map<String, Object>> getProductDetalisByProductionPlanningUID(String productionplanninguid) {
		String sql = "SELECT pp.productionplanninguid, pp.productuid, pp.productname\r\n"
				+ "FROM productionplanning pp\r\n" + "WHERE pp.productionplanninguid = ?";
		return jdbcTemplate.queryForList(sql, productionplanninguid);
	}

	public List<Map<String, Object>> fetchEmployeeUIds() {
		String sql = "SELECT employeeuid, CONCAT(first_name , ' ',last_name) AS fullname FROM employee";
		return jdbcTemplate.queryForList(sql);
	}

	// ✅ In ProductionShiftScheduleDao.java
	public List<Map<String, Object>> getFullNameByEmployeeUid(String employeeuid) {
		String sql = "SELECT CONCAT(first_name , ' ',last_name) AS fullname " + "FROM employee WHERE employeeuid = ?";
		return jdbcTemplate.queryForList(sql, employeeuid);
	}

}

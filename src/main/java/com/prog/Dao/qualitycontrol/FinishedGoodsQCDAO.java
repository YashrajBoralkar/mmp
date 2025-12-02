package com.prog.Dao.qualitycontrol;

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

import com.prog.model.erp.finishedgoodsqc;

@Repository
public class FinishedGoodsQCDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_SQL = "INSERT INTO finishedgoodsqc ("
			+ "finishgoodsqcuid, productuid, productname, inspectiondate, dimensionsandweight, packagingcondition, "
			+ "functionalitytest, visualinspection, samplesize, defectiveitemscount, defectrate, "
			+ "finalapprovalstatus, actiontaken, finishedgoodsqcapprovedquantity, inprocessqualitycontroluid "
			+ ", inprocessqcapprovedqty, workorderuid, firstarticleinspectionuid, approvedby, employeeuid, "
			+ "productionorderuid, productionorderplannedqty, insertdate, updatedate"
			+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public int addFinishedGoodsQC(finishedgoodsqc finishedgoodsqc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbcTemplate.update(INSERT_SQL, finishedgoodsqc.getFinishgoodsqcuid(), finishedgoodsqc.getProductuid(),
				finishedgoodsqc.getProductname(), finishedgoodsqc.getInspectiondate(),
				finishedgoodsqc.getDimensionsandweight(), finishedgoodsqc.getPackagingcondition(),
				finishedgoodsqc.getFunctionalitytest(), finishedgoodsqc.getVisualinspection(),
				finishedgoodsqc.getSamplesize(), finishedgoodsqc.getDefectiveitemscount(),
				finishedgoodsqc.getDefectrate(), finishedgoodsqc.getFinalapprovalstatus(),
				finishedgoodsqc.getActiontaken(), finishedgoodsqc.getFinishedgoodsqcapprovedquantity(),
				finishedgoodsqc.getInprocessqualitycontroluid(), finishedgoodsqc.getInprocessqcapprovedqty(),
				finishedgoodsqc.getWorkorderuid(), finishedgoodsqc.getFirstarticleinspectionuid(),
				finishedgoodsqc.getApprovedby(), finishedgoodsqc.getEmployeeuid(),
				finishedgoodsqc.getProductionorderuid(), finishedgoodsqc.getProductionorderplannedqty(),
				formattedTimestamp,
				formattedTimestamp

		);
	}

	// Retrieve all finishedgoodsqc
	public List<Map<String, Object>> getAllFinishedGoodsQC() {
		String sql = "SELECT * FROM finishedgoodsqc";
		return jdbcTemplate.queryForList(sql);
	}

	// Retrieve a Physical Count by ID
	public List<Map<String, Object>> getFinishedGoodsQCById(Long id) {
		String sql = "SELECT * FROM finishedgoodsqc where id = ?";
		return jdbcTemplate.queryForList(sql, id);
	}

//	    // Update a Physical Count
	private static final String UPDATE_SQL = "UPDATE finishedgoodsqc SET finishedgoodsqcapprovedquantity=?,  inspectiondate = ?, dimensionsandweight = ?, packagingcondition = ?, functionalitytest =?, visualinspection = ?, samplesize = ?, defectiveitemscount = ?, defectrate = ?, finalapprovalstatus = ? , actiontaken = ?, updatedate = ? WHERE id = ?";

	public int updateFinishedGoodsQC(finishedgoodsqc finishedgoodsqc) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = LocalDateTime.now().format(formatter);

		return jdbcTemplate.update(UPDATE_SQL,

				finishedgoodsqc.getFinishedgoodsqcapprovedquantity(), finishedgoodsqc.getInspectiondate(),
				finishedgoodsqc.getDimensionsandweight(), finishedgoodsqc.getPackagingcondition(),
				finishedgoodsqc.getFunctionalitytest(), finishedgoodsqc.getVisualinspection(),
				finishedgoodsqc.getSamplesize(), finishedgoodsqc.getDefectiveitemscount(),
				finishedgoodsqc.getDefectrate(), finishedgoodsqc.getFinalapprovalstatus(),
				finishedgoodsqc.getActiontaken(), formattedTimestamp, finishedgoodsqc.getId());
	}

	// Delete a Physical Count by ID
	public int deleteFinishedGoodsQC(Long id) {
		String sql = "DELETE FROM finishedgoodsqc WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	// RowMapper for mapping ResultSet to physicalcount entity
	public static class FinishedGoodsQCRowMapper implements RowMapper<finishedgoodsqc> {
		@Override
		public finishedgoodsqc mapRow(ResultSet rs, int rowNum) throws SQLException {
			finishedgoodsqc finishedgoodsqc = new finishedgoodsqc();

			finishedgoodsqc.setFinishgoodsqcuid(rs.getString("finishgoodsqcuid"));
			finishedgoodsqc.setProductuid(rs.getString("productuid"));
			finishedgoodsqc.setInspectiondate(rs.getString("inspectiondate"));
			finishedgoodsqc.setDimensionsandweight(rs.getString("dimensionsandweight"));
			finishedgoodsqc.setPackagingcondition(rs.getString("packagingcondition"));
			finishedgoodsqc.setFunctionalitytest(rs.getString("functionalitytest"));
			finishedgoodsqc.setVisualinspection(rs.getString("visualinspection"));
			finishedgoodsqc.setSamplesize(rs.getString("samplesize"));
			finishedgoodsqc.setDefectiveitemscount(rs.getString("defectiveitemscount"));
			finishedgoodsqc.setDefectrate(rs.getString("defectrate"));
			finishedgoodsqc.setFinalapprovalstatus(rs.getString("finalapprovalstatus"));
			finishedgoodsqc.setActiontaken(rs.getString("actiontaken"));
			finishedgoodsqc.setFinishedgoodsqcapprovedquantity(rs.getString("finishedgoodsqcapprovedquantity"));
			finishedgoodsqc.setId(rs.getLong("id"));
			finishedgoodsqc.setInprocessqualitycontroluid(rs.getString("inprocessqualitycontroluid"));
			finishedgoodsqc.setWorkorderuid(rs.getString("workorderuid"));
			finishedgoodsqc.setFirstarticleinspectionuid(rs.getString("firstarticleinspectionuid"));
			finishedgoodsqc.setProductionorderuid(rs.getString("productionorderuid"));
			finishedgoodsqc.setInprocessqcapprovedqty(rs.getString("inprocessqcapprovedqty"));
			finishedgoodsqc.setApprovedby(rs.getString("approvedby"));
			finishedgoodsqc.setEmployeeuid(rs.getString("employeeuid"));
			finishedgoodsqc.setProductname(rs.getString("productname"));
			finishedgoodsqc.setInprocessqcapprovedqty(rs.getString("inprocessqcapprovedqty"));
			finishedgoodsqc.setProductionorderplannedqty(rs.getString("productionorderplannedqty"));
			return finishedgoodsqc;
		}
	}

	// FETCHING

	public List<String> getBatchIds() {
		String sql = "SELECT batchuid FROM batchinfo";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("batchuid"));

	}

	// Retrieve product details by product UID
	public List<Map<String, Object>> getDataByProductUid(String productuid) {
		String sql = " SELECT p.productname, ip.workorderuid, ip.productionorderuid, ip.firstarticleinspectionuid, ip.inprocessqualitycontroluid, ip.approvedquantity, po.productionorderquantity FROM inprocessqualitycontrol ip Join productinfo p ON ip.productuid = p.productuid Join productionorder po ON ip.productuid = po.productuid\r\n"
				+ " WHERE (ip.productuid = ? and supervisorapproval = 'Approved');";
		return jdbcTemplate.queryForList(sql, productuid);
	}

	// used in finished goods inspection.
	public List<String> fetchProductUIds() {
		String sql = "SELECT productuid FROM inprocessqualitycontrol where supervisorapproval = 'approved';";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
	}

	// Retrieve all product UIDs
	public List<Map<String, Object>> getdataByBatchuid(String batchuid) {
		String sql = "SELECT p.productname, p.productuid FROM productinfo p "
				+ "JOIN batchinfo b ON p.productuid = b.productuid " + "WHERE b.batchuid = ?";

		return jdbcTemplate.queryForList(sql, batchuid);
	}

	public List<Map<String, Object>> getdataByEmpuid(String employeeuid) {
		String sql = "SELECT CONCAT(first_name, ' ', last_name) AS fullname FROM employee WHERE employeeuid = ?";
		return jdbcTemplate.queryForList(sql, employeeuid);
	}

	public List<Map<String, Object>> getEmpuid() {
		String sql = "SELECT CONCAT(first_name, ' ', last_name) AS fullname , employeeuid FROM employee";
		return jdbcTemplate.queryForList(sql);
	}

}

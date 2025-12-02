package com.prog.Dao.inventory;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.ReturnStockInspection;


@Repository
public class ReturnStockInspectionDao {

    private final JdbcTemplate jdbcTemplate;

    public ReturnStockInspectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(ReturnStockInspection inspection) {
        String sql = "INSERT INTO returnstockinspection " +
                "(returnstockuid,productuid, quantityreturned,  stockcondition,inspectionnotes, " +
                "restockingdecision, updatestockstatus, approvalworkflow, " +
                "inspectedby, inspectiondate, decisionapprovedby, document) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

        jdbcTemplate.update(sql,
        		inspection.getReturnstockuid(),
        		inspection.getProductuid(),
                inspection.getQuantityreturned(),
                inspection.getStockcondition(),
                inspection.getInspectionnotes(),
                inspection.getRestockingdecision(),
                inspection.getUpdatestockstatus(),
                inspection.getApprovalworkflow(),
                inspection.getInspectedby(),
                inspection.getInspectiondate(),
                inspection.getDecisionapprovedby(),
                inspection.getDocument());
    }
    
    public void update(ReturnStockInspection inspection) {
        String sql = "UPDATE returnstockinspection SET " +
                " quantityreturned = ?, stockcondition = ?,inspectionnotes = ?, " +
                "restockingdecision = ?, updatestockstatus = ?, approvalworkflow = ?, " +
                "inspectedby = ?, inspectiondate = ?, decisionapprovedby = ?, document = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql,
                inspection.getQuantityreturned(),
                inspection.getStockcondition(),
                inspection.getInspectionnotes(),
                inspection.getRestockingdecision(),
                inspection.getUpdatestockstatus(),
                inspection.getApprovalworkflow(),
                inspection.getInspectedby(),
                inspection.getInspectiondate(),
                inspection.getDecisionapprovedby(),
                inspection.getDocument(),
                inspection.getId());
    }
    public List<Map<String, Object>> findAllReturnStockInfo() {
        String sql = "SELECT rsi.id,rsi.returnstockuid,rsi.quantityreturned, rsi.returnstockuid, rsi.stockcondition, rsi.inspectedby, rsi.inspectiondate,\r\n"
        		+ "				pi.productname \r\n"
        		+ "                FROM returnstockinspection rsi\r\n"
        		+ "				JOIN productinfo pi ON rsi.productuid = pi.productuid\r\n"
        		+ "";
        return jdbcTemplate.queryForList(sql);
    }

    public ReturnStockInspection findById(Long id) {
        String sql = "SELECT * FROM returnstockinspection WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ReturnStockInspectionRowMapper());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM returnstockinspection WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // RowMapper for mapping ResultSet to ReturnStockInspection
    private static class ReturnStockInspectionRowMapper implements RowMapper<ReturnStockInspection> {
        @Override
        public ReturnStockInspection mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReturnStockInspection inspection = new ReturnStockInspection();
            inspection.setId(rs.getLong("id"));
            inspection.setReturnstockuid(rs.getString("returnstockuid"));
            inspection.setProductuid(rs.getString("productuid"));
            inspection.setQuantityreturned(rs.getString("quantityreturned"));
            inspection.setStockcondition(rs.getString("stockcondition"));
            inspection.setInspectionnotes(rs.getString("inspectionnotes"));
            inspection.setRestockingdecision(rs.getString("restockingdecision"));
            inspection.setUpdatestockstatus(rs.getString("updatestockstatus"));
            inspection.setApprovalworkflow(rs.getString("approvalworkflow"));
            inspection.setInspectedby(rs.getString("inspectedby"));
            inspection.setInspectiondate(rs.getString("inspectiondate"));
            inspection.setDecisionapprovedby(rs.getString("decisionapprovedby"));
            inspection.setDocument(rs.getBytes("document"));
            return inspection;
        }
    }
    

  public List<String> getproductuid() {
	    String sql = "SELECT productuid FROM productinfo";
	    return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
	}

	public List<Map<String, Object>> getDataByproductuid(String productuid) {
	    String sql = "SELECT productname FROM productinfo WHERE productuid = ?";
	    return jdbcTemplate.queryForList(sql, productuid);
	}
}
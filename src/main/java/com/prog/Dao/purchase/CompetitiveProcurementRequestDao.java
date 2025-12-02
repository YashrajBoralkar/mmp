package com.prog.Dao.purchase;

import com.prog.model.erp.CompetitiveProcurementRequest;

import com.prog.model.erp.RawmaterialInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Repository
public class CompetitiveProcurementRequestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ----------------------------------------------------------------------
    // 🔹 RowMapper for CompetitiveProcurementRequest
    // ----------------------------------------------------------------------
    public static class CprRowMapper implements RowMapper<CompetitiveProcurementRequest> {
        @Override
        public CompetitiveProcurementRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            CompetitiveProcurementRequest cpr = new CompetitiveProcurementRequest();
            cpr.setId(rs.getLong("id"));
            cpr.setCompetitiveprocurementrequestuid(rs.getString("competitiveprocurementrequestuid"));
            cpr.setSupplierdetails(rs.getString("supplierdetails"));
            cpr.setRequestdate(rs.getString("requestdate"));
            cpr.setApprovedby(rs.getString("approvedby"));
            cpr.setRemark(rs.getString("remark"));
            cpr.setEmployeeuid(rs.getString("employeeuid"));
            cpr.setRequestforquotationuid(rs.getString("requestforquotationuid"));
            cpr.setRawmaterialuid(rs.getString("rawmaterialuid"));
            return cpr;
        }
    }

    // ----------------------------------------------------------------------
    // 🔹 INSERT
    // ----------------------------------------------------------------------
    public int add(CompetitiveProcurementRequest cpr) {
        String sql = """
        		INSERT INTO competitiveprocurementrequest(competitiveprocurementrequestuid, supplierdetails, requestdate, approvedby, remark, insertdate, updatedate, employeeuid, requestforquotationuid, rawmaterialuid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) """;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);

        try {
            return jdbcTemplate.update(sql,
                    cpr.getCompetitiveprocurementrequestuid(),
                    cpr.getSupplierdetails(),
                    cpr.getRequestdate(),
                    cpr.getApprovedby(),
                    cpr.getRemark(),
                    now,
                    now,
                    cpr.getEmployeeuid(),
                    cpr.getRequestforquotationuid(),
                    cpr.getRawmaterialuid()
            );
        } catch (Exception e) {
            e.printStackTrace(); // ⚠️ log DB error
            return 0;
        }
    }

    // ----------------------------------------------------------------------
    // 🔹 UPDATE
    // ----------------------------------------------------------------------
    public int update(Long id, String remark, String requestdate) {
        String sql = """
                UPDATE competitiveprocurementrequest
                SET remark = ?, requestdate = ?, updatedate = NOW()
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, remark, requestdate, id);
    }

    // ----------------------------------------------------------------------
    // 🔹 DELETE
    // ----------------------------------------------------------------------
    public int deleteById(Long id) {
        String sql = "DELETE FROM competitiveprocurementrequest WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // ----------------------------------------------------------------------
    // 🔹 FETCH CPRs
    // ----------------------------------------------------------------------

    /** Get all CPRs (with employee & material links) */
    public List<Map<String, Object>> findAllcpr() {
        String sql = """
                SELECT DISTINCT cpr.id,
                       cpr.competitiveprocurementrequestuid,
                       rm.rawmaterialuid,
                       rm.rawmaterialname,
                       rfq.requestforquotationuid,
                       cpr.requestdate,
                       cpr.approvedby,
                       cpr.remark,
                       cpr.supplierdetails
                 FROM competitiveprocurementrequest cpr
                 JOIN rawmaterialinfo rm ON cpr.rawmaterialuid = rm.rawmaterialuid
                 JOIN requestforquotation rfq ON cpr.requestforquotationuid = rfq.requestforquotationuid
                """;
        return jdbcTemplate.queryForList(sql);
    }

    /** Get all CPRs (simpler version, ordered) */
    public List<Map<String, Object>> getAllWithLinks() {
        String sql = """
                SELECT DISTINCT cpr.id,
                       cpr.requestdate,
                       cpr.rawmaterialuid,
                       rm.rawmaterialname,
                       cpr.requestforquotationuid,
                       cpr.remark,
                       cpr.supplierdetails,
                       emp.employeeuid AS employeeUid,
                       CONCAT(emp.first_name, ' ', emp.last_name) AS employeeName
                FROM competitiveprocurementrequest cpr
                LEFT JOIN employee emp ON cpr.employeeuid = emp.employeeuid
                LEFT JOIN rawmaterialinfo rm ON cpr.rawmaterialuid = rm.rawmaterialuid
                ORDER BY cpr.id DESC
                """;
        return jdbcTemplate.queryForList(sql);
    }

    /** Get CPR by ID */
    public Map<String, Object> findById(Long id) {
        String sql = """
                SELECT cpr.*,
                       emp.employeeuid AS "employeeUid",
                       CONCAT(emp.first_name, ' ', emp.last_name) AS "employeeName"
                FROM competitiveprocurementrequest cpr
                LEFT JOIN employee emp ON cpr.employeeuid = emp.employeeuid
                WHERE cpr.id = ?
                """;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    /** Get CPRs by RFQ UID */
    public List<Map<String, Object>> findByRfqUidWithLinks(String rfqUid) {
        String sql = """
                SELECT DISTINCT cpr.id,
                       cpr.competitiveprocurementrequestuid,
                       rm.rawmaterialuid,
                       rm.rawmaterialname,
                       rfq.requestforquotationuid,
                       cpr.requestdate,
                       cpr.approvedby,
                       cpr.remark,
                       cpr.supplierdetails,
                       emp.employeeuid AS "employeeUid",
                       CONCAT(emp.first_name, ' ', emp.last_name) AS "employeeName"
                FROM competitiveprocurementrequest cpr
                LEFT JOIN rawmaterialinfo rm ON cpr.rawmaterialuid = rm.rawmaterialuid
                LEFT JOIN requestforquotation rfq ON cpr.requestforquotationuid = rfq.requestforquotationuid
                LEFT JOIN employee emp ON cpr.employeeuid = emp.employeeuid
                WHERE cpr.requestforquotationuid = ?
                """;
        return jdbcTemplate.queryForList(sql, rfqUid);
    }

    // ----------------------------------------------------------------------
    // 🔹 FETCH EMPLOYEES / MATERIALS / RFQs
    // ----------------------------------------------------------------------

    /** All Employees */
    public List<Map<String, Object>> findAllEmployees() {
        String sql = "SELECT employeeuid, CONCAT(first_name, ' ', last_name) AS employeename FROM employee";
        return jdbcTemplate.queryForList(sql);
    }

    /** All Raw Materials (map) */
    public List<Map<String, Object>> findAllRawMaterials() {
        String sql = "SELECT rawmaterialuid, rawmaterialname FROM rawmaterialinfo ORDER BY rawmaterialname";
        return jdbcTemplate.queryForList(sql);
    }

    /** All Raw Materials (entity) */
    public List<RawmaterialInfo> findAll() {
        String sql = "SELECT * FROM rawmaterialinfo ORDER BY rawmaterialname";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RawmaterialInfo.class));
    }

    /** RFQs by Raw Material Name */
    /** RFQs by Raw Material Name */
    public List<Map<String, Object>> findRfqsByRawMaterial(String rawMaterialName) {
        String sql = """
                SELECT rfq.requestforquotationuid,
                       rfq.rfqdate,
                       rfq.issuedby,
                       rfq.rawmaterialuid,
                       rm.rawmaterialname
                FROM requestforquotation rfq
                LEFT JOIN rawmaterialinfo rm
                       ON rfq.rawmaterialuid = rm.rawmaterialuid
                WHERE rm.rawmaterialname = ?
                """;
        return jdbcTemplate.queryForList(sql, rawMaterialName);
    }

    /** RFQ by UID */
    public List<Map<String, Object>> findByUid(String uid) {
        String sql = "SELECT * FROM requestforquotation WHERE requestforquotationuid = ?";
        return jdbcTemplate.queryForList(sql, uid);
    }

    /** All RFQs with Raw Material Info */
    public List<Map<String, Object>> findAllWithRawMaterial() {
        String sql = """
                SELECT rfq.id,
                       rfq.requestforquotationuid,
                       rfq.rfqdate,
                       rfq.issuedby,
                       rfq.rawmaterialuid,
                       rm.rawmaterialname,
                       rfq.expecteddeliverydate,
                       rfq.supplierdetails
                FROM requestforquotation rfq
                LEFT JOIN rawmaterialinfo rm ON rfq.rawmaterialuid = rm.rawmaterialuid
                """;
        return jdbcTemplate.queryForList(sql);
    }
}

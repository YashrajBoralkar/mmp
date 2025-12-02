package com.prog.Dao.supplier;

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

import com.prog.model.erp.SupplierPerformanceEvaluation;


@Repository
public class SupplierPerformanceEvaluationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ✅ Add SupplierPerformanceEvaluation
    public int addSupplier(SupplierPerformanceEvaluation supplierPerformanceEvaluation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String sql = "INSERT INTO supplierperformanceevaluation (supplierperformanceevaluationuid, rawmaterialsupplieruid, evaluationperiod, "
                + "ontimedeliveryrate, qualityrating, orderaccuracy, responsivenessscore, compliancescore, "
                + "overallscore, performancestatus, actionrequired, reviewedby, insertdate, updatedate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        return jdbcTemplate.update(sql,
                supplierPerformanceEvaluation.getSupplierperformanceevaluationuid(),
                supplierPerformanceEvaluation.getRawmaterialsupplieruid(),
                supplierPerformanceEvaluation.getEvaluationperiod(),
                supplierPerformanceEvaluation.getOntimedeliveryrate(),
                supplierPerformanceEvaluation.getQualityrating(),
                supplierPerformanceEvaluation.getOrderaccuracy(),
                supplierPerformanceEvaluation.getResponsivenessscore(),
                supplierPerformanceEvaluation.getCompliancescore(),
                supplierPerformanceEvaluation.getOverallscore(),
                supplierPerformanceEvaluation.getPerformancestatus(),
                supplierPerformanceEvaluation.getActionrequired(),
                supplierPerformanceEvaluation.getReviewedby(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    // ✅ Fetch SupplierPerformanceEvaluation by ID
    public SupplierPerformanceEvaluation getSupplierById(Long id) {
        String sql = "SELECT * FROM supplierperformanceevaluation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new SupplierPerformanceMapper(), id);
    }

    public int updatesupplierperformance(SupplierPerformanceEvaluation supplierPerformanceEvaluation) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String sql = "UPDATE supplierperformanceevaluation SET evaluationperiod=?, ontimedeliveryrate=?, qualityrating=?, "
                + " orderaccuracy=?, responsivenessscore=?, compliancescore=?, overallscore=?, "
                + "performancestatus=?, actionrequired=?, reviewedby=?, updatedate=? WHERE id=?";


        try {
            return jdbcTemplate.update(sql,
                    supplierPerformanceEvaluation.getEvaluationperiod(),
                    supplierPerformanceEvaluation.getOntimedeliveryrate(),
                    supplierPerformanceEvaluation.getQualityrating(),
                    supplierPerformanceEvaluation.getOrderaccuracy(),
                    supplierPerformanceEvaluation.getResponsivenessscore(),
                    supplierPerformanceEvaluation.getCompliancescore(),
                    supplierPerformanceEvaluation.getOverallscore(),
                    supplierPerformanceEvaluation.getPerformancestatus(),
                    supplierPerformanceEvaluation.getActionrequired(),
                    supplierPerformanceEvaluation.getReviewedby(),

                    formattedTimestamp,
                    supplierPerformanceEvaluation.getId()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    // ✅ Delete SupplierPerformanceEvaluation by ID
    public int deleteById(Long id) {
        String sql = "DELETE FROM supplierperformanceevaluation WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // ✅ Get all suppliers
    public List<SupplierPerformanceEvaluation> getAllSupplier() {
        String sql = "SELECT * FROM supplierperformanceevaluation";
        return jdbcTemplate.query(sql, new SupplierPerformanceMapper());
    }

    // ✅ Get all supplier performance with JOIN
    public List<Map<String, Object>> showFindAll() {
        String sql = "SELECT rlu.id, rlu.supplierperformanceevaluationuid, rlu.rawmaterialsupplieruid, sp.suppliername, " +
                "rlu.evaluationperiod, rlu.ontimedeliveryrate, rlu.qualityrating, rlu.orderaccuracy, " +
                "rlu.responsivenessscore, rlu.compliancescore, rlu.overallscore, rlu.performancestatus, " +
                "rlu.actionrequired, rlu.reviewedby " +
                "FROM supplierperformanceevaluation rlu " +
                "JOIN rawmaterialsupplier sp ON rlu.rawmaterialsupplieruid = sp.rawmaterialsupplieruid";

        return jdbcTemplate.queryForList(sql);
    }

    // ✅ RowMapper for SupplierPerformanceEvaluation
    public class SupplierPerformanceMapper implements RowMapper<SupplierPerformanceEvaluation> {
        @Override
        public SupplierPerformanceEvaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
            SupplierPerformanceEvaluation sp = new SupplierPerformanceEvaluation();
            sp.setId(rs.getLong("id"));
            sp.setSupplierperformanceevaluationuid(rs.getString("supplierperformanceevaluationuid"));
            sp.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            sp.setEvaluationperiod(rs.getString("evaluationperiod"));
            sp.setOntimedeliveryrate(rs.getString("ontimedeliveryrate"));
            sp.setQualityrating(rs.getString("qualityrating"));
            sp.setOrderaccuracy(rs.getString("orderaccuracy"));
            sp.setResponsivenessscore(rs.getString("responsivenessscore"));
            sp.setCompliancescore(rs.getString("compliancescore"));
            sp.setOverallscore(rs.getString("overallscore"));
            sp.setPerformancestatus(rs.getString("performancestatus"));
            sp.setActionrequired(rs.getString("actionrequired"));
            sp.setReviewedby(rs.getString("reviewedby"));
            return sp;
        }
    }

	
}

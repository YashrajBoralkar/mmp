package com.prog.Dao.qualitycontrol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.InprocessQualityControl;

@Repository
public class InprocessQualityControlDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // SQL query for inserting a new quality control record
    private final String sql = "INSERT INTO inprocessqualitycontrol (inprocessqualitycontroluid, departmentname, productuid, productname, workorderuid,plannedquantity, productionorderuid,firstarticleinspectionuid, " +
            " stageofproduction, inspectiondatetime, testingmethod, tolerancelimits, defecttype, approvedquantity, samplesize, defectivecount, defectrate, " +
            "inspectionstatus, correctiveaction, inspectorname, supervisorapproval,insertdate,updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

    /**
     * Inserts a new record into the inprocessqualitycontrol table.
     * @param control The InprocessQualityControl object containing form data.
     * @return Number of rows affected.
     */
    public int saveProcessQualityControlForm(InprocessQualityControl control) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        return jdbcTemplate.update(sql,
                control.getInprocessqualitycontroluid(),
                control.getDepartmentname(),
                control.getProductuid(),
                control.getProductname(),
                control.getWorkorderuid(),
                control.getPlannedquantity(),
                control.getProductionorderuid(),
                control.getFirstarticleinspectionuid(),
                control.getStageofproduction(),
                control.getInspectiondatetime(),
                control.getTestingmethod(),
                control.getTolerancelimits(),
                control.getDefecttype(),
                control.getApprovedquantity(),
                control.getSamplesize(),
                control.getDefectivecount(),
                control.getDefectrate(),
                control.getInspectionstatus(),
                control.getCorrectiveaction(),
                control.getInspectorname(),
                control.getSupervisorapproval(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    /**
     * Fetches all in-process quality control records joined with product info.
     * @return List of maps representing each record.
     */
    public List<Map<String, Object>> fetchAllQualityControl() {
        String sql = "SELECT inp.id, inp.inprocessqualitycontroluid,  inp.departmentname,inp.inspectiondatetime,inp.inspectorname, inp.inspectionstatus,inp.supervisorapproval,inp.productionorderuid, pi.productuid,pi.productname " +
                     "FROM inprocessqualitycontrol inp " +
                     "JOIN productinfo pi ON pi.productuid = inp.productuid";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Deletes a record from inprocessqualitycontrol by ID.
     * @param id The record ID to delete.
     */
    public void deleteQualityControlbyId(Long id) {
        String sql = "DELETE FROM inprocessqualitycontrol WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No InprocessQualityControl found with id " + id);
        }
    }

    /**
     * RowMapper for mapping SQL result set to InprocessQualityControl object.
     */
    class QualityControlRowmapper implements RowMapper<InprocessQualityControl> {
        @Override
        public InprocessQualityControl mapRow(ResultSet rs, int rowNum) throws SQLException {
            InprocessQualityControl control = new InprocessQualityControl();
            control.setId(rs.getLong("id"));
            control.setInprocessqualitycontroluid(rs.getString("inprocessqualitycontroluid"));
            control.setDepartmentname(rs.getString("departmentname"));
            control.setProductuid(rs.getString("productuid"));
            control.setProductname(rs.getString("productname"));
            control.setWorkorderuid(rs.getString("workorderuid"));
            control.setPlannedquantity(rs.getString("plannedquantity"));
            control.setProductionorderuid(rs.getString("productionorderuid"));
            control.setFirstarticleinspectionuid(rs.getString("firstarticleinspectionuid"));
            control.setStageofproduction(rs.getString("stageofproduction"));
            control.setInspectiondatetime(rs.getString("inspectiondatetime"));
            control.setTestingmethod(rs.getString("testingmethod"));
            control.setTolerancelimits(rs.getString("tolerancelimits"));
            control.setDefecttype(rs.getString("defecttype"));
            control.setApprovedquantity(rs.getString("approvedquantity"));
            control.setSamplesize(rs.getString("samplesize"));
            control.setDefectivecount(rs.getString("defectivecount"));
            control.setDefectrate(rs.getString("defectrate"));
            control.setInspectionstatus(rs.getString("inspectionstatus"));
            control.setCorrectiveaction(rs.getString("correctiveaction"));
            control.setInspectorname(rs.getString("inspectorname"));
            control.setSupervisorapproval(rs.getString("supervisorapproval"));
            return control;
        }
    }

    /**
     * Fetches a single quality control record by ID.
     * @param id The ID of the record.
     * @return InprocessQualityControl object.
     */
    public InprocessQualityControl getQualityControlbyId(Long id) {
        String sql = "SELECT * FROM inprocessqualitycontrol WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new QualityControlRowmapper(), id);
    }

    /**
     * Updates an existing quality control record by ID.
     * @param control The InprocessQualityControl object with updated values.
     */
    public void updateQualityControl(InprocessQualityControl control) {
        String sql = "UPDATE inprocessqualitycontrol SET departmentname = ?, stageofproduction = ?, inspectiondatetime = ?, testingmethod = ?, tolerancelimits = ?, defecttype = ?, " +
                     "approvedquantity = ?, samplesize = ?, defectivecount = ?, defectrate = ?, inspectionstatus = ?, correctiveaction = ?, inspectorname = ?, supervisorapproval = ?, updatedate = ? WHERE id = ?";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        jdbcTemplate.update(sql,
                control.getDepartmentname(),
                control.getStageofproduction(),
                control.getInspectiondatetime(),
                control.getTestingmethod(),
                control.getTolerancelimits(),
                control.getDefecttype(),
                control.getApprovedquantity(),
                control.getSamplesize(),
                control.getDefectivecount(),
                control.getDefectrate(),
                control.getInspectionstatus(),
                control.getCorrectiveaction(),
                control.getInspectorname(),
                control.getSupervisorapproval(),
                formattedTimestamp,
                control.getId()
        );
    }

    // ======================
    // SUPPORTING QUERIES
    // ======================

    /**
     * Fetches all product UIDs from the productinfo table.
     * @return List of product UIDs.
     */
    public List<String> getItemId() {
        String sql = "SELECT productuid FROM productinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }

    /**
     * Retrieves product name by its UID.
     * @param productuid The UID of the product.
     * @return Product name as String.
     */
    public String getProductNameByUid(String productuid) {
        String sql = "SELECT productname FROM productinfo WHERE productuid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{productuid}, String.class);
    }

    /**
     * Retrieves all approved FAI UIDs for a given product.
     * @param productuid The product UID.
     * @return List of approved FAI UIDs.
     */
    public List<String> getApprovedFAIUIDsByProductUid(String productuid) {
        String sql = "SELECT firstarticleinspectionuid FROM firstarticleinspection WHERE productuid = ? AND approvalstatus = 'APPROVED'";
        return jdbcTemplate.queryForList(sql, new Object[]{productuid}, String.class);
    }

    /**
     * Retrieves work order UID, production order UID, and planned quantity based on FAI UID.
     * @param firstarticleinspectionuid The FAI UID.
     * @return Map containing order details.
     */
    public Map<String, String> getOrderDetailsByFAIUid(String firstarticleinspectionuid) {
        String sql = "SELECT workorderuid, productionorderuid, approvedcount FROM firstarticleinspection WHERE firstarticleinspectionuid = ? AND approvalstatus = 'APPROVED'";
        return jdbcTemplate.queryForObject(sql, new Object[]{firstarticleinspectionuid}, (rs, rowNum) -> {
            Map<String, String> result = new HashMap<>();
            result.put("workorderuid", rs.getString("workorderuid"));
            result.put("productionorderuid", rs.getString("productionorderuid"));
            result.put("plannedquantity", rs.getString("approvedcount"));
            return result;
        });
    }
}

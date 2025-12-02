package com.prog.Dao.qualitycontrol;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.RawmaterialQualityinspection;
import com.prog.model.erp.Rawmaterialpurchaseorder;


@Repository
public class RawMaterialQualityInspectionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ✅ Insert
    public void addInspection(RawmaterialQualityinspection inspection) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String sql = "INSERT INTO rawmaterialqualityinspection (" +
                "rawmaterialqualityinspectionuid, rawmaterialpurchaseorderuid, rawmaterialuid, rawmaterialname, rawmaterialdetails," +
                "inspectiondate, checkedby, qualityparameters, inspectionresult, remarks, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                inspection.getRawmaterialqualityinspectionuid(),
                inspection.getRawmaterialpurchaseorderuid(),
                inspection.getRawmaterialuid(),
                inspection.getRawmaterialname(),
                inspection.getRawmaterialdetails(),

                inspection.getInspectiondate(),
                inspection.getCheckedby(),
                inspection.getQualityparameters(),
                inspection.getInspectionresult(),
                inspection.getRemarks(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    // ✅ Fetch all
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM rawmaterialqualityinspection";
        return jdbcTemplate.queryForList(sql);
    }

    // ✅ RowMapper
    private static class RawmaterialQualityinspectionMapper implements RowMapper<RawmaterialQualityinspection> {
        @Override
        public RawmaterialQualityinspection mapRow(ResultSet rs, int rowNum) throws SQLException {
            RawmaterialQualityinspection inspection = new RawmaterialQualityinspection();
            inspection.setId(rs.getLong("id"));
            inspection.setRawmaterialqualityinspectionuid(rs.getString("rawmaterialqualityinspectionuid"));
            inspection.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            inspection.setRawmaterialuid(rs.getString("rawmaterialuid"));
            inspection.setRawmaterialname(rs.getString("rawmaterialname"));
            inspection.setRawmaterialdetails(rs.getString("rawmaterialdetails"));
            inspection.setInspectiondate(rs.getString("inspectiondate"));
            inspection.setCheckedby(rs.getString("checkedby"));
            inspection.setQualityparameters(rs.getString("qualityparameters"));
            inspection.setInspectionresult(rs.getString("inspectionresult"));
            inspection.setRemarks(rs.getString("remarks"));
            return inspection;
        }
    }

    // ✅ Delete
    public void deleteById(Long id) {
        String sql = "DELETE FROM rawmaterialqualityinspection WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // ✅ Get by ID
    public RawmaterialQualityinspection getById(Long id) {
        String sql = "SELECT * FROM rawmaterialqualityinspection WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(RawmaterialQualityinspection.class), id);
    }

    // ✅ Update
    public void update(RawmaterialQualityinspection inspection) {
        String sql = "UPDATE rawmaterialqualityinspection SET " +
                "checkedby = ?, inspectionresult = ?, qualityparameters = ?, inspectiondate = ?, remarks = ?, updatedate = NOW() " +
                "WHERE id = ?";

        jdbcTemplate.update(sql,
                inspection.getCheckedby(),
                inspection.getInspectionresult(),
                inspection.getQualityparameters(),
                inspection.getInspectiondate(),
                inspection.getRemarks(),
                inspection.getId()
        );
    }

    // ✅ Get employee names
    public List<Map<String, String>> getEmployeeNames() {
        String sql = "SELECT employeeuid, CONCAT(first_name, ' ', last_name) AS fullName FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> map = new HashMap<>();
            map.put("uid", rs.getString("employeeuid"));
            map.put("name", rs.getString("fullName"));
            return map;
        });
    }

    // ✅ Fetch raw material name + quantity for selected PO UID (corrected to support multiple)
    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUid(String rawmaterialpurchaseorderuid) {
        String sql = "SELECT rawmaterialuid, materialnames, materialdetails " +
                     "FROM rawmaterialpurchaseorder WHERE rawmaterialpurchaseorderuid = ?";

        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, rawmaterialpurchaseorderuid);

        for (Map<String, Object> row : rows) {
            String rawmaterialuid = (String) row.get("rawmaterialuid");       // e.g., "RMU5578,RMU7925"
            String materialnames = (String) row.get("materialnames");         // e.g., "Steel Rod,Plastic"
            String materialDetailsJson = (String) row.get("materialdetails"); // e.g., {"Steel Rod":{"quantity":10},"Plastic":{"quantity":50}}

            try {
                Map<String, Map<String, Object>> materialDetailsMap =
                        objectMapper.readValue(materialDetailsJson, Map.class);

                String[] materialNamesArray = materialnames.split(",");
                String[] rawMaterialUidsArray = rawmaterialuid.split(",");

                // ✅ Match name and UID by index
                if (materialNamesArray.length == rawMaterialUidsArray.length) {
                    for (int i = 0; i < materialNamesArray.length; i++) {
                        String name = materialNamesArray[i].trim();
                        String uid = rawMaterialUidsArray[i].trim();

                        if (materialDetailsMap.containsKey(name)) {
                            Map<String, Object> entryDetails = materialDetailsMap.get(name);

                            Map<String, Object> materialInfo = new HashMap<>();
                            materialInfo.put("rawmaterialuid", uid); // ✅ matched UID
                            materialInfo.put("rawmaterialname", name);
                            materialInfo.put("materialQuantity", entryDetails.get("quantity"));
                            materialInfo.put("price", entryDetails.get("price"));
                            result.add(materialInfo);
                        }
                    }
                } else {
                    System.err.println("Mismatch in number of UIDs and material names for PO UID: " + rawmaterialpurchaseorderuid);
                }

            } catch (Exception e) {
                e.printStackTrace(); // Handle JSON parsing error
            }
        }

        return result;
    }


    // ✅ Fetch distinct PO UIDs for dropdown
    public List<String> getAllPurchaseOrderUids() {
        String sql = "SELECT DISTINCT rawmaterialpurchaseorderuid FROM rawmaterialpurchaseorder";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}

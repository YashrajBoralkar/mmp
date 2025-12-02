package com.prog.Dao.productionandoperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.productionplanning;


@Repository
public class ProductionPlanningDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Formatter for datetime fields (used in insert/update)
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ✅ Save a single production planning record (including raw material details)
    public int addPlans(productionplanning productionplanning) {
        String sql = "INSERT INTO productionplanning (" +
                "productionplanninguid, productuid, productname, rawmaterialuid, " +
                "productiontype, productionstartdate, productionenddate, responsiblemanager, " +
                "actual, expected, diff, " +
                "productioncostestimation, leadtimedays, productiondependencies, " +
                "approvalstatus, approvedby, remarks, productionplanningcompletionstatus, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
      
        return jdbcTemplate.update(sql,
                productionplanning.getProductionplanninguid(),
                productionplanning.getProductuid(),
                productionplanning.getProductname(),
                productionplanning.getRawmaterialuid(),
                productionplanning.getProductiontype(),
                productionplanning.getProductionstartdate(),
                productionplanning.getProductionenddate(),
                productionplanning.getResponsiblemanager(),
                productionplanning.getActual(),
                productionplanning.getExpected(),
                productionplanning.getDiff(),
                productionplanning.getProductioncostestimation(),
                productionplanning.getLeadtimedays(),
                productionplanning.getProductiondependencies(),
                productionplanning.getApprovalstatus(),
                productionplanning.getApprovedby(),
                productionplanning.getRemarks(),
                productionplanning.getProductionplanningcompletionstatus(),
                formattedTimestamp,
                formattedTimestamp
        );
    }

    // ✅ RowMapper to convert ResultSet rows to ProductionPlanning Java objects
    private class ProductionPlanningRowMapper implements RowMapper<productionplanning> {
        @Override
        public productionplanning mapRow(ResultSet rs, int rowNum) throws SQLException {
        	productionplanning proplan = new productionplanning();

            proplan.setId(rs.getLong("id"));
            proplan.setProductionplanninguid(rs.getString("productionplanninguid"));
            proplan.setProductuid(rs.getString("productuid"));
            proplan.setProductname(rs.getString("productname"));
            proplan.setRawmaterialuid(rs.getString("rawmaterialuid"));
            proplan.setProductiontype(rs.getString("productiontype"));
            proplan.setProductionstartdate(rs.getString("productionstartdate"));
            proplan.setProductionenddate(rs.getString("productionenddate"));
            proplan.setResponsiblemanager(rs.getString("responsiblemanager"));
            proplan.setProductioncostestimation(rs.getDouble("productioncostestimation"));
            proplan.setLeadtimedays(rs.getString("leadtimedays"));
            proplan.setProductiondependencies(rs.getString("productiondependencies"));
            proplan.setApprovalstatus(rs.getString("approvalstatus"));
            proplan.setApprovedby(rs.getString("approvedby"));
            proplan.setRemarks(rs.getString("remarks"));
            proplan.setProductionplanningcompletionstatus(rs.getString("productionplanningcompletionstatus"));
           
            return proplan;
        }
    }

    // ✅ Get all production planning records (raw rows)
    public List<productionplanning> getplanlist() {
        String sql = "SELECT * FROM productionplanning";
        return jdbcTemplate.query(sql, new ProductionPlanningRowMapper());
    }

    // ✅ Get production planning rows by UID — used for update/edit mode
    public List<Map<String, Object>> getProductionPlanningbyuid(String productionplanninguid) {
        String sql = "SELECT * FROM productionplanning WHERE productionplanninguid = ?";
        return jdbcTemplate.queryForList(sql, productionplanninguid); // Returns multiple rows (one per material)
    }

    // ✅ Delete all records for a given production planning UID
    public int deleteplan(String productionplanninguid) {
        String sql = "DELETE FROM productionplanning WHERE productionplanninguid = ?";
        return jdbcTemplate.update(sql, productionplanninguid);
    }

    // ✅ Update only the main production planning info (not material details)
    public void updateProductionPlanning(productionplanning productionplanning) {
        String sql = "UPDATE productionplanning SET " +
                "productiontype=?, productionstartdate=?, productionenddate=?, " +
                "responsiblemanager=?, productioncostestimation=?, leadtimedays=?, " +
                "productiondependencies=?, approvalstatus=?, approvedby=?, remarks=?, productionplanningcompletionstatus=?, updatedate=? " +
                "WHERE productionplanninguid = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        jdbcTemplate.update(sql,
                productionplanning.getProductiontype(),
                productionplanning.getProductionstartdate(),
                productionplanning.getProductionenddate(),
                productionplanning.getResponsiblemanager(),
                productionplanning.getProductioncostestimation(),
                productionplanning.getLeadtimedays(),
                productionplanning.getProductiondependencies(),
                productionplanning.getApprovalstatus(),
                productionplanning.getApprovedby(),
                productionplanning.getRemarks(),
                productionplanning.getProductionplanningcompletionstatus(),
                formattedTimestamp,
                productionplanning.getProductionplanninguid());
        
    }

    // ✅ Used in list view: join and group materials for a production UID
    public List<Map<String, Object>> findallplan() {
        String sql = "SELECT " +
                "pl.productionplanninguid, " +
                "pl.productuid, " +
                "p.productname, " +
                "pl.productiontype, " +
                "pl.productionstartdate, " +
                "pl.productionenddate, " +
                "pl.responsiblemanager, " +
                "pl.productioncostestimation, " +
                "pl.leadtimedays, " +
                "pl.productiondependencies, " +
                "pl.approvalstatus, " +
                "pl.approvedby, " +
                "pl.remarks, " +
                "pl.insertdate, " +
                "pl.updatedate, " +
                "GROUP_CONCAT( DISTINCT CONCAT(r.rawmaterialname, ': Actual=', pl.actual, ', Expected=', pl.expected, ', Diff=', pl.diff) SEPARATOR '<br>') AS materialDetails " +
                "FROM productionplanning pl " +
                "LEFT JOIN rawmaterialinfo r ON pl.rawmaterialuid = r.rawmaterialuid " +
                "LEFT JOIN productinfo p ON pl.productuid = p.productuid " +
                "GROUP BY " +
                "pl.productionplanninguid, pl.productuid, p.productname, pl.productiontype, pl.productionstartdate, pl.productionenddate, " +
                "pl.responsiblemanager, pl.productioncostestimation, pl.leadtimedays, pl.productiondependencies, pl.approvalstatus, " +
                "pl.approvedby, pl.remarks, pl.insertdate, pl.updatedate";

        return jdbcTemplate.queryForList(sql);
    }

    // ✅ Get unique product UIDs (used in dropdown)
    public List<String> getProductUId() {
        String sql = "SELECT DISTINCT productuid FROM productinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productuid"));
    }

    // ✅ Get product name for a given product UID (used when selected from dropdown)
    public List<String> getProductNamesByProductUid(String productuid) {
        String sql = "SELECT DISTINCT productname FROM productinfo WHERE productuid = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("productname"), productuid);
    }

    // ✅ Get raw materials linked to a product name (used to generate material rows)
    public List<Map<String, Object>> getMaterialNamesByProductName(String productname) {
        String sql = "SELECT r.rawmaterialuid, r.rawmaterialname " +
                     "FROM rawmaterialinfo r " +
                     "JOIN productinfo p     ON FIND_IN_SET(r.rawmaterialuid, p.rawmaterialuid) > 0\n"
                      +
                     "WHERE p.productname = ?";
        return jdbcTemplate.queryForList(sql, productname);
    }
    
//    public List<String>getrawmaterialnamebyuid(String productname){
//    String sql="SELECT  rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid = ?;";   	
//    	return jdbcTemplate.queryForList(sql, String.class);
//    };

    // ✅ Get total available quantity of a material (used to fill 'actual' column)
//    public double getAvailableQuantityByMaterialName(String rawmaterialname) {
//        String sql = """
//            SELECT COALESCE(SUM(actualquantity), 0)
//            FROM rawmaterialinventoryentry
//            WHERE productuid = (
//                SELECT rawmaterialuid 
//                FROM rawmaterialinfo 
//                WHERE rawmaterialname = ?
//            )
//        """;
//        
//        return jdbcTemplate.queryForObject(sql, Double.class, rawmaterialname);
//    }
    
 // ✅ Get total available quantity of a material (used to fill 'actual' column)
//    public double getAvailableQuantityByMaterialName(String rawmaterialname) {
//        String sql = "SELECT COALESCE(SUM(actualquantity), 0) " +
//                     "FROM rawmaterialinventoryentry WHERE productname = 'RMU'; ";
//        return jdbcTemplate.queryForObject(sql, Double.class, rawmaterialname);
//    }


    
    public double getAvailableQuantityByMaterialuid(String rawmaterialuid) {
    	String sql = """
    	        SELECT ru.globalrealtimequantity
    	        FROM realtimeupdate ru
    	        WHERE ru.productuid = ?
    	          AND ru.producttype = 'rawmaterial'
    	        ORDER BY ru.insertdate DESC
    	        LIMIT 1
    	    """;


        return jdbcTemplate.queryForObject(sql, Double.class, rawmaterialuid);
    }


    // ✅ Get all employee names and UIDs (used in dropdowns for manager/approver)
    public List<Map<String, String>> getEmployeeNames() {
        String sql = "SELECT employeeUID, CONCAT(first_name, ' ', last_name) AS fullName FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> map = new HashMap<>();
            map.put("uid", rs.getString("employeeUID"));
            map.put("name", rs.getString("fullName"));
            return map;
        });
    }
}

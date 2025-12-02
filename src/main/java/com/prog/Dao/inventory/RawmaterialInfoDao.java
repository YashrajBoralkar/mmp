package com.prog.Dao.inventory;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.RawmaterialInfo;

@Repository
public class RawmaterialInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Insert a new raw material record
     */
    public void saveRawmaterialRegistration(RawmaterialInfo raw) {

        String sql = "INSERT INTO rawmaterialinfo (rawmaterialuid, rawmaterialname, materialdescription, category, unitofmeasure,standardpurchaseprice,leadtime, minimumstocklevel, hsncode,storageconditions, activestatus, insertdate, updatedate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
       
        jdbcTemplate.update(sql,
            raw.getRawmaterialuid(),
            raw.getRawmaterialname(),
            raw.getMaterialdescription(),
            raw.getCategory(),
            raw.getUnitofmeasure(),
            raw.getStandardpurchaseprice(),           
            raw.getLeadtime(),
            raw.getMinimumstocklevel(),
            raw.getHsncode(),
            raw.getStorageconditions(),
            raw.getActivestatus(),
            formattedTimestamp,
            formattedTimestamp
        );
    }

    /**
     * Retrieve all raw materials
     */
    public List<Map<String , Object>> findAll() {
        String sql = "SELECT * FROM rawmaterialinfo";
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * Retrieve raw material by ID
     */
    public RawmaterialInfo getById(Long id) {
        String sql = "SELECT * FROM rawmaterialinfo WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RawmaterialRegistrationRowMapper(), id);
    }

    /**
     * Update an existing raw material record
     */
    public void update(RawmaterialInfo raw) {
        String sql = "UPDATE rawmaterialinfo SET rawmaterialname = ?, materialdescription = ?, category = ?, unitofmeasure = ?, standardpurchaseprice = ?, leadtime = ?, minimumstocklevel = ?,hsncode = ?, storageconditions = ?, activestatus = ?, updatedate = ? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	     String formattedTimestamp = LocalDateTime.now().format(formatter);
	    
        jdbcTemplate.update(sql,
            raw.getRawmaterialname(),
            raw.getMaterialdescription(),
            raw.getCategory(),
            raw.getUnitofmeasure(),
            raw.getStandardpurchaseprice(),          
            raw.getLeadtime(),
            raw.getMinimumstocklevel(),
            raw.getHsncode(),
            raw.getStorageconditions(),
            raw.getActivestatus(),
            formattedTimestamp,
            raw.getId()
        );
    }

    /**
     * Delete a raw material by ID
     */
    public void deleteById(Long id) {
        String sql = "DELETE FROM rawmaterialinfo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * RowMapper for RawmaterialInfo
     */
    public static class RawmaterialRegistrationRowMapper implements RowMapper<RawmaterialInfo> {
        @Override
        public RawmaterialInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            RawmaterialInfo raw = new RawmaterialInfo();
            raw.setId(rs.getLong("id"));
            raw.setRawmaterialuid(rs.getString("rawmaterialuid"));
            raw.setRawmaterialname(rs.getString("rawmaterialname")); // ✅ missing before
            raw.setMaterialdescription(rs.getString("materialdescription"));
            raw.setCategory(rs.getString("category"));
            raw.setUnitofmeasure(rs.getString("unitofmeasure"));
            raw.setStandardpurchaseprice(rs.getString("standardpurchaseprice"));
            raw.setLeadtime(rs.getString("leadtime"));
            raw.setMinimumstocklevel(rs.getString("minimumstocklevel"));
            raw.setHsncode(rs.getString("hsncode"));
            raw.setStorageconditions(rs.getString("storageconditions"));
            raw.setActivestatus(rs.getString("activestatus"));
            return raw;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
   
    // fetching in request for quotation
    public List<Map<String, Object>> getDataByrawmaterialUid(String rawmaterialuid) {
        String sql = "SELECT rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid = ?";
        return jdbcTemplate.queryForList(sql, rawmaterialuid);
    }

    // Retrieve all product UIDs
    public List<String> fetchrawmaterialUIds() {
        String sql = "SELECT rawmaterialuid FROM rawmaterialinfo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("rawmaterialuid"));
    }
    
    
 // Join rawmaterialinfo + rawmaterialsupplier
    public List<Map<String, Object>> getSuppliersByRawMaterialUid(String rawmaterialuid) {
        String sql = """
            SELECT 
                s.rawmaterialsupplieruid,
                s.suppliername,
                s.mobilenumber
            FROM rawmaterialsupplier s
            WHERE FIND_IN_SET(?, s.rawmaterialuid)
        """;

        return jdbcTemplate.queryForList(sql, rawmaterialuid);
    }


}

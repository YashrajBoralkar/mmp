package com.prog.Dao.purchase;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.RawMaterialReceiptNote;
import com.prog.model.erp.Rawmaterialpurchaseorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class RawMaterialReceiptNoteDao {

    @Autowired
    private JdbcTemplate jdbctemplate;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ✅ RowMapper
    public class RawMaterialGoodsReceiptNotesRowMapper implements RowMapper<RawMaterialReceiptNote> {
        @Override
        public RawMaterialReceiptNote mapRow(ResultSet rs, int rowNum) throws SQLException {
            RawMaterialReceiptNote grn = new RawMaterialReceiptNote();
            grn.setId(rs.getLong("id"));
            grn.setRawmaterialreceiptnoteuid(rs.getString("rawmaterialreceiptnoteuid"));
            grn.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            grn.setRawmaterialstatus(rs.getString("rawmaterialstatus"));
            grn.setReceivername(rs.getString("receivername"));
            return grn;
        }
    }

    // ✅ Save
    public void save(RawMaterialReceiptNote rawmgrn) {
        String timestamp = LocalDateTime.now().format(formatter);
        String sql = "INSERT INTO rawmaterialreceiptnote " +
                "(rawmaterialreceiptnoteuid, rawmaterialpurchaseorderuid, rawmaterialdetails, receivername, rawmaterialstatus, insertdate, updatedate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbctemplate.update(sql,
                rawmgrn.getRawmaterialreceiptnoteuid(),
                rawmgrn.getRawmaterialpurchaseorderuid(),
                rawmgrn.getRawmaterialdetails(),
                rawmgrn.getReceivername(),
                rawmgrn.getRawmaterialstatus(),
                timestamp,
                timestamp
        );
    }

    // ✅ Get By Id
    public RawMaterialReceiptNote getById(Long id) {
        String sql = "SELECT * FROM rawmaterialreceiptnote WHERE id = ?";
        return jdbctemplate.queryForObject(sql, new Object[]{id}, new RawMaterialGoodsReceiptNotesRowMapper());
    }

    // ✅ Update
    public int update(RawMaterialReceiptNote rawmgrn) {
        String timestamp = LocalDateTime.now().format(formatter);
        String sql = "UPDATE rawmaterialreceiptnote SET receivername=?, rawmaterialstatus=?, updatedate=? WHERE id=?";
        return jdbctemplate.update(sql,
                rawmgrn.getReceivername(),
                rawmgrn.getRawmaterialstatus(),
                timestamp,
                rawmgrn.getId()
        );
    }

    // ✅ Delete
    public void delete(Long id) {
        String sql = "DELETE FROM rawmaterialreceiptnote WHERE id=?";
        jdbctemplate.update(sql, id);
    }

    // ✅ Dropdown values - only PASS
    public List<Rawmaterialpurchaseorder> getPurchaseOrdersWithPassedQuality() {
        String sql = "SELECT DISTINCT p.rawmaterialpurchaseorderuid, p.rawmaterialuid, p.materialdetails, p.suppliername " +
                     "FROM rawmaterialpurchaseorder p " +
                     "JOIN rawmaterialqualityinspection q ON p.rawmaterialpurchaseorderuid = q.rawmaterialpurchaseorderuid " +
                     "WHERE q.inspectionresult = 'PASS'";

        return jdbctemplate.query(sql, (rs, rowNum) -> {
            Rawmaterialpurchaseorder r = new Rawmaterialpurchaseorder();
            r.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            r.setRawmaterialuid(rs.getString("rawmaterialuid"));
            r.setMaterialdetails(rs.getString("materialdetails")); // ✅ new field instead of materialnames
            r.setSuppliername(rs.getString("suppliername"));
            return r;
        });
    }

    // ✅ For List Page
    public List<Map<String, Object>> getAllWithJoin() {
        String sql = "SELECT * FROM rawmaterialreceiptnote;" ;
        return jdbctemplate.queryForList(sql);
    }


    

    // ✅ Fetch PO details
    public Map<String, Object> getDetailsByUid(String poUid) {
        String sql = "SELECT rawmaterialuid, materialdetails " +
                     "FROM rawmaterialpurchaseorder WHERE rawmaterialpurchaseorderuid = ?";
        return jdbctemplate.queryForMap(sql, poUid);
    }

    
    
    public List<Map<String, Object>> findAllWithEmployeeNames() {
        String sql = "SELECT r.id, r.rawmaterialrnuid, r.rawmaterialpurchaseorderuid, " +
                     "e.first_Name, e.middle_Name, e.last_Name, r.receivername, r.insertdate, r.updatedate " +
                     "FROM rawmaterialreceiptnote r " +
                     "JOIN employee e ON r.receivername = e.employeeUID";
        return jdbctemplate.queryForList(sql);
    }
    
    
    public List<Map<String, Object>> getRawMaterialsByPurchaseOrderUid(String rawmaterialpurchaseorderuid) {
        String sql = "SELECT rawmaterialuid, materialdetails " +
                     "FROM rawmaterialpurchaseorder WHERE rawmaterialpurchaseorderuid = ?";

        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> rows = jdbctemplate.queryForList(sql, rawmaterialpurchaseorderuid);

        for (Map<String, Object> row : rows) {
            String rawmaterialuid = (String) row.get("rawmaterialuid");   // e.g. "MAT001" or "MAT001,MAT002"
            String materialDetails = (String) row.get("materialdetails"); // JSON string

            String[] rawMaterialUidsArray = rawmaterialuid.split(",");

            try {
                // Parse materialdetails JSON
                Map<String, Map<String, Object>> materialDetailsMap =
                        objectMapper.readValue(materialDetails, Map.class);

                int index = 0;
                for (Map.Entry<String, Map<String, Object>> entry : materialDetailsMap.entrySet()) {
                    Map<String, Object> materialInfo = new HashMap<>();
                    materialInfo.put("rawmaterialuid", rawMaterialUidsArray[index].trim());
                    materialInfo.put("rawmaterialname", entry.getKey()); // "Steel Rods"
                    materialInfo.put("orderedquantity", entry.getValue().get("quantity"));
                    materialInfo.put("price", entry.getValue().get("price"));
                    result.add(materialInfo);
                    index++;
                }
            } catch (Exception e) {
                System.err.println("⚠️ Failed to parse materialDetails JSON for PO: " + rawmaterialpurchaseorderuid);
            }
        }
        return result;
    }



}

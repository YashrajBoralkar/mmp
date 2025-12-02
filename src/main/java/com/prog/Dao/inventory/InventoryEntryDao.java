package com.prog.Dao.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prog.model.erp.InventoryEntry;


@Repository
public class InventoryEntryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /* ---------------- RowMapper ---------------- */
    public class InventoryEntryRowMapper implements RowMapper<InventoryEntry> {
        @Override
        public InventoryEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
            InventoryEntry entry = new InventoryEntry();
            entry.setId(rs.getLong("id"));
            entry.setInventoryentryuid(rs.getString("inventoryentryuid"));
            entry.setProducttype(rs.getString("producttype"));
            entry.setProductuid(rs.getString("productuid"));
            entry.setReferenceuid(rs.getString("referenceuid"));   // ✅ always available
            entry.setActualquantity(rs.getInt("actualquantity"));
            entry.setWarehouseuid(rs.getString("warehouseuid"));
            entry.setEntrydate(rs.getString("entrydate"));
            entry.setEmployeeuid(rs.getString("employeeuid"));

            // derived (from joins)
            try { entry.setProductname(rs.getString("productname")); } catch (SQLException ignored) {}
            try { entry.setApprovedquantity(rs.getBigDecimal("approvedquantity")); } catch (SQLException ignored) {}
            try { entry.setWarehousename(rs.getString("warehousename")); } catch (SQLException ignored) {}

            return entry;
        }
    }

    /* ---------------- SAVE ---------------- */
    public void save(InventoryEntry entry) {

        String sql = """
            INSERT INTO inventoryentry
            (inventoryentryuid, producttype, productuid, referenceuid,
               actualquantity, warehouseuid, entrydate, employeeuid, insertdate,updatedate, productname, approvedquantity)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)
        """;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
      
        jdbcTemplate.update(sql,
            entry.getInventoryentryuid(),
            entry.getProducttype(),
            entry.getProductuid(),
            entry.getReferenceuid(),    // unified field
            entry.getActualquantity(),
            entry.getWarehouseuid(),
            entry.getEntrydate(),
            entry.getEmployeeuid(),
            formattedTimestamp,
            formattedTimestamp,
            entry.getProductname(),
            entry.getApprovedquantity()
        );
    }

    /* ---------------- UPDATE ---------------- */
    public int update(InventoryEntry entry) {

        String sql = """
            UPDATE inventoryentry
            SET actualquantity=?, warehouseuid=?, entrydate=?, updatedate=?, referenceuid=?
            WHERE id=?
        """;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
      
        return jdbcTemplate.update(sql,
            entry.getActualquantity(),
            entry.getWarehouseuid(),
            entry.getEntrydate(),
            formattedTimestamp,
            entry.getReferenceuid(),
            entry.getId()
        );
    }

    /* ---------------- DELETE ---------------- */
    public void delete(Long id) {
        String sql = "DELETE FROM inventoryentry WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /* ---------------- LIST & FIND ---------------- */
    public List<InventoryEntry> findAll() {
        String sql = "SELECT * FROM inventoryentry";
        return jdbcTemplate.query(sql, new InventoryEntryRowMapper());
    }

    public InventoryEntry findById(int id) {
//        String sql = """
//            SELECT inv.id,
//                   inv.inventoryentryuid,
//                   inv.producttype,
//                   inv.productuid,
//                   inv.referenceuid,
//                   inv.actualquantity,
//                   inv.warehouseuid,
//                   inv.entrydate,
//                   inv.employeeuid,
//                   COALESCE(r.rawmaterialname, fg.productname) AS productname,
//                   COALESCE(r.orderedquantity, CAST(fg.finishedgoodsqcapprovedquantity AS UNSIGNED)) AS approvedquantity,
//                   w.warehousename AS warehousename
//            FROM inventoryentry inv
//            LEFT JOIN rawmaterialreceiptnote r
//                   ON inv.producttype = 'RAW_MATERIAL'
//                  AND inv.referenceuid = r.rawmaterialreceiptnoteuid
//                  AND UPPER(r.rawmaterialstatus) IN ('PASS','APPROVED')
//            LEFT JOIN finishedgoodsqc fg
//                   ON inv.producttype = 'GOODS'
//                  AND inv.referenceuid = fg.finishgoodsqcuid
//                  AND UPPER(fg.finalapprovalstatus) = 'APPROVED'
//            LEFT JOIN warehouseinfo w
//                   ON inv.warehouseuid = w.warehouseuid
//            WHERE inv.id = ?
//        """;
    String sql= 	"""
        SELECT inv.id,
               inv.inventoryentryuid,
               inv.producttype,
               inv.productuid,
               inv.referenceuid,
               inv.actualquantity,
               inv.warehouseuid,
               inv.entrydate,
               inv.employeeuid,
               inv.productname,
               inv.approvedquantity,
               w.warehousename AS warehousename
        FROM inventoryentry inv
        LEFT JOIN rawmaterialreceiptnote r
               ON inv.producttype = 'RAW MATERIAL'
              AND inv.referenceuid = r.rawmaterialreceiptnoteuid
              AND UPPER(r.rawmaterialstatus) IN ('PASS','APPROVED')
        LEFT JOIN finishedgoodsqc fg
               ON inv.producttype = 'GOODS'
              AND inv.referenceuid = fg.finishgoodsqcuid
              AND UPPER(fg.finalapprovalstatus) = 'APPROVED'
        LEFT JOIN warehouseinfo w
               ON inv.warehouseuid = w.warehouseuid
        WHERE inv.id = ?
    """;
        return jdbcTemplate.queryForObject(sql, new InventoryEntryRowMapper(), id);
    }

    public List<Map<String, Object>> findAllForList() {
        String sql = """
            SELECT inv.id,
                   inv.inventoryentryuid,
                   inv.producttype,
                   inv.productuid,
                   inv.referenceuid,
                   inv.actualquantity,
                   inv.warehouseuid,
                   inv.entrydate,
                   inv.employeeuid,
                   inv.productname,
                   inv.actualquantity,
                   w.warehousename AS warehousename
            FROM inventoryentry inv
            LEFT JOIN rawmaterialreceiptnote r
                   ON inv.producttype = 'RAW MATERIAL'
                  AND inv.referenceuid = r.rawmaterialreceiptnoteuid
                  AND UPPER(r.rawmaterialstatus) IN ('PASS','APPROVED')
            LEFT JOIN finishedgoodsqc fg
                   ON inv.producttype = 'GOODS'
                  AND inv.referenceuid = fg.finishgoodsqcuid
                  AND UPPER(fg.finalapprovalstatus) = 'APPROVED'
            LEFT JOIN warehouseinfo w
                   ON inv.warehouseuid = w.warehouseuid
            ORDER BY inv.id DESC
        """;
        return jdbcTemplate.queryForList(sql);
    }
//    public List<Map<String, Object>> findAllForList() {
//        String sql = """
//            SELECT inv.id,
//                   inv.inventoryentryuid,
//                   inv.producttype,
//                   inv.productuid,
//                   inv.referenceuid,
//                   inv.actualquantity,
//                   inv.warehouseuid,
//                   inv.entrydate,
//                   inv.employeeuid,
//                COALESCE(fg.productname) AS productname,
//                   COALESCE(CAST(fg.finishedgoodsqcapprovedquantity AS UNSIGNED)) AS approvedquantity,
//                   w.warehousename AS warehousename
//            FROM inventoryentry inv
//            LEFT JOIN rawmaterialreceiptnote r
//                   ON inv.producttype = 'RAW_MATERIAL'
//                  AND inv.referenceuid = r.rawmaterialreceiptnoteuid
//                  AND UPPER(r.rawmaterialstatus) IN ('PASS','APPROVED')
//            LEFT JOIN finishedgoodsqc fg
//                   ON inv.producttype = 'GOODS'
//                  AND inv.referenceuid = fg.finishgoodsqcuid
//                  AND UPPER(fg.finalapprovalstatus) = 'APPROVED'
//            LEFT JOIN warehouseinfo w
//                   ON inv.warehouseuid = w.warehouseuid
//            ORDER BY inv.id DESC
//        """;
//        return jdbcTemplate.queryForList(sql);
//    }

    /* ---------------- UID lists ---------------- */
 // Fetch all Raw Material UIDs from rawmaterialinfo
    public List<Map<String, Object>> findAllRawMaterialUids() {
        String sql = "SELECT rawmaterialuid AS uid FROM rawmaterialinfo";
        return jdbcTemplate.queryForList(sql);
    }

    // Fetch all Goods Product UIDs from productinfo
    public List<Map<String, Object>> findAllGoodsUids() {
        String sql = "SELECT productuid AS uid FROM productinfo";
        return jdbcTemplate.queryForList(sql);
    }


 // Fetch all Finish Goods QC UIDs for a product
    public List<Map<String, Object>> findAllFinishGoodsQcUidsByProduct(String productUid) {
        String sql = "SELECT finishgoodsqcuid AS uid FROM finishedgoodsqc WHERE productuid = ? and  finalapprovalstatus = 'APPROVED' ORDER BY id DESC";
        return jdbcTemplate.queryForList(sql, productUid);
    }

//    // Fetch all Raw Material Receipt Note UIDs for a raw material
//    public List<Map<String, Object>> findAllRawMaterialReceiptNoteUidsByRawMaterial(String rawMaterialUid) {
//        String sql = "SELECT rawmaterialreceiptnoteuid AS uid FROM rawmaterialreceiptnote WHERE rawmaterialuid = ? and  rawmaterialstatus = 'Accepted' ORDER BY id DESC";
//        return jdbcTemplate.queryForList(sql, rawMaterialUid);
//    }


    /* ---------------- Product / Warehouse details ---------------- */
 // RAW MATERIAL
    public Map<String, Object> findRawMaterialDetailsByUid(String rawMaterialUid, String receiptNoteUid) {
        // 1️⃣ Fetch raw material name from rawmaterialinfo
        String nameSql = "SELECT rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid = ?";
        String rawMaterialName = jdbcTemplate.queryForObject(nameSql, new Object[]{rawMaterialUid}, String.class);

        // 2️⃣ Fetch ordered quantity from rawmaterialreceiptnote (remove status filter)
        String qtySql = """
            SELECT CAST(orderedquantity AS UNSIGNED) AS approvedquantity
            FROM rawmaterialreceiptnote
            WHERE rawmaterialuid = ?
              AND rawmaterialreceiptnoteuid = ?
            ORDER BY id DESC
            LIMIT 1
            """;
        Integer approvedQuantity = null;
        try {
            approvedQuantity = jdbcTemplate.queryForObject(qtySql, new Object[]{rawMaterialUid, receiptNoteUid}, Integer.class);
        } catch (Exception e) {
            approvedQuantity = 0; // fallback if no row found
        }

        // 3️⃣ Prepare result map
        Map<String, Object> map = new HashMap<>();
        map.put("productname", rawMaterialName);
        map.put("approvedquantity", approvedQuantity);
        return map;
    }

    // GOODS
    public Map<String, Object> findGoodsDetailsByUid(String productUid, String referenceUid) {
        // 1️⃣ Fetch product name from productinfo
        String nameSql = "SELECT productname FROM productinfo WHERE productuid = ?";
        String productName = jdbcTemplate.queryForObject(nameSql, new Object[]{productUid}, String.class);

        // 2️⃣ Fetch finished goods quantity from finishedgoodsqc (remove status filter)
        String qtySql = """
            SELECT CAST(finishedgoodsqcapprovedquantity AS UNSIGNED) AS approvedquantity
            FROM finishedgoodsqc
            WHERE productuid = ?
              AND finishgoodsqcuid = ?
            ORDER BY id DESC
            LIMIT 1
            """;
        Integer approvedQuantity = null;
        try {
            approvedQuantity = jdbcTemplate.queryForObject(qtySql, new Object[]{productUid, referenceUid}, Integer.class);
        } catch (Exception e) {
            approvedQuantity = 0; // fallback if no row found
        }

        // 3️⃣ Prepare result map
        Map<String, Object> map = new HashMap<>();
        map.put("productname", productName);
        map.put("approvedquantity", approvedQuantity);
        return map;
    }





    public List<Map<String, Object>> findAllWarehouses() {
        String sql = "SELECT warehouseuid, warehousename FROM warehouseinfo";
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> findWarehouseByUid(String warehouseUid) {
        String sql = "SELECT warehousename FROM warehouseinfo WHERE warehouseuid = ? LIMIT 1";
        return jdbcTemplate.queryForMap(sql, warehouseUid);
    }

    public List<Map<String, Object>> getAllEmployeeUid() {
        String sql = "SELECT CONCAT(first_name, ' ', last_name) AS fullname, employeeuid FROM employee";
        return jdbcTemplate.queryForList(sql);
    }
    
    
//=============================================================================================================================
    
    //Separate fetching
    
 // ---------------- Fetch Product Name ----------------
    public String fetchProductName(String type, String productUid) {
        try {
            if ("RAW MATERIAL".equalsIgnoreCase(type)) {
                return jdbcTemplate.queryForObject(
                    "SELECT rawmaterialname FROM rawmaterialinfo WHERE rawmaterialuid = ?",
                    String.class, productUid);
            } else { // GOODS
                return jdbcTemplate.queryForObject(
                    "SELECT productname FROM productinfo WHERE productuid = ?",
                    String.class, productUid);
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // ---------------- Fetch Approved Quantity ----------------
    public Integer fetchApprovedQuantity(String type, String productUid, String referenceUid) {
        try {
            if ("RAW MATERIAL".equalsIgnoreCase(type)) {
                return jdbcTemplate.queryForObject(
                    "SELECT CAST(orderedquantity AS UNSIGNED) FROM rawmaterialreceiptnote WHERE rawmaterialuid=? AND rawmaterialreceiptnoteuid=? AND UPPER(qualitystatus)='APPROVED' LIMIT 1",
                    Integer.class, productUid, referenceUid);
            } else { // GOODS
                return jdbcTemplate.queryForObject(
                    "SELECT CAST(finishedgoodsqcapprovedquantity AS UNSIGNED) FROM finishedgoodsqc WHERE productuid=? AND finishgoodsqcuid=? AND UPPER(finalapprovalstatus)='APPROVED' LIMIT 1",
                    Integer.class, productUid, referenceUid);
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
 // ---------------- Fetch used Reference UIDs ----------------
    public List<String> getUsedReferenceUids(String type) {
        String sql = type.equals("GOODS")
            ? "SELECT referenceuid FROM inventoryentry WHERE producttype = 'GOODS'"
            : "SELECT referenceuid " +
              "FROM inventoryentry " +
              "WHERE LOWER(REPLACE(REPLACE(REPLACE(producttype, ' ', ''), '-', ''), '_', '')) = 'rawmaterial'";

        return jdbcTemplate.queryForList(sql, String.class);
    }

    // Fetch only warehouseUIDs linked with a specific productUID
//  public List<String> findWarehouseUidsByProductUid(String productUid) {
//      String sql = """
//          SELECT DISTINCT warehouseuid
//          FROM inventoryentry
//          WHERE productuid = ?
//            AND warehouseuid IS NOT NULL
//            AND warehouseuid <> ''
//      """;
//      return jdbcTemplate.queryForList(sql, String.class, productUid);
//  }
// Fetch warehouseUIDs linked to a productUID
  public List<String> findWarehouseUidsByProductUid(String productUid) {
      String sql = "SELECT DISTINCT warehouseuid FROM inventoryentry WHERE productuid = ?";
      return jdbcTemplate.queryForList(sql, String.class, productUid);
  }
// inventoryentry मधून warehouseuid वापरून warehousename fetch करा
//  public String findWarehousenameByWarehouseUid(String warehouseUid) {
//      String sql = "SELECT warehousename FROM inventoryentry WHERE warehouseuid = ? LIMIT 1";
//      try {
//          return jdbcTemplate.queryForObject(sql, String.class, warehouseUid);
//      } catch (EmptyResultDataAccessException e) {
//          return "";
//      }
//  }

}

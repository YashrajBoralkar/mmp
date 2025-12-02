package com.prog.Dao.purchase;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prog.model.erp.RawMaterialReceiptNote;
import com.prog.model.erp.Rawmaterialpurchaseinvoice;



@Repository
public class RawmaterialpurchaseinvoiceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Rawmaterialpurchaseinvoice invoice = new Rawmaterialpurchaseinvoice();

    // ✅ Save Invoice
    public int saveInvoice(Rawmaterialpurchaseinvoice invoice) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

        String sql = "INSERT INTO rawmaterialpurchaseinvoice ("
                + "invoicedate, paymentterms, rawmaterialpurchaseinvoiceuid, rawmaterialpurchaseorderuid, "
                + "taxamount, totalamount, rawmaterialreceiptnoteuid, suppliername, rawmaterialsupplieruid, "
                + "verifiedby, materialdetails, insertdate, updatedate"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                invoice.getInvoicedate(),
                invoice.getPaymentterms(),
                invoice.getRawmaterialpurchaseinvoiceuid(),
                invoice.getRawmaterialpurchaseorderuid(),
                invoice.getTaxamount(),
                invoice.getTotalamount(),
                invoice.getRawmaterialreceiptnoteuid(),
                invoice.getSuppliername(),
                invoice.getRawmaterialsupplieruid(),
                invoice.getVerifiedby(),
                invoice.getMaterialdetails(),  // JSON string of all materials
                formattedTimestamp,
                formattedTimestamp
        );
    }


    // ✅ Get All Invoices
    public List<Map<String, Object>> getAllInvoices() {
        String sql = "SELECT pi.* , rmrn.rawmaterialpurchaseorderuid FROM rawmaterialpurchaseinvoice pi"
        		+ " JOIN rawmaterialreceiptnote rmrn ON rmrn.rawmaterialreceiptnoteuid = pi.rawmaterialreceiptnoteuid;";
        return jdbcTemplate.queryForList(sql);
    }

    // ✅ Delete Invoice by ID
    public void deleteById(Long id) {
        String sql = "DELETE FROM rawmaterialpurchaseinvoice WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // ✅ Get Invoice by ID
    public Rawmaterialpurchaseinvoice getById(Long id) {
        String sql = "SELECT * FROM rawmaterialpurchaseinvoice WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Rawmaterialpurchaseinvoice.class), id);
    }

    // ✅ Update Invoice
    public int update(Rawmaterialpurchaseinvoice invoice) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);

    	String sql = "UPDATE rawmaterialpurchaseinvoice SET "
                + "invoicedate = ?,  "
                + " taxamount = ?, totalamount = ? ,paymentterms = ? , updatedate = ? "
                + "WHERE id = ?";

        return jdbcTemplate.update(sql,
                invoice.getInvoicedate(),               
                invoice.getTaxamount(),
                invoice.getTotalamount(),
                invoice.getPaymentterms(),
                formattedTimestamp,
                invoice.getId()); // WHERE condition
    }

    // ✅ Fetch related order details
    public Map<String, String> findOrderDetailsByUid(String uid) {
        try {
            String sql = "SELECT rawmaterialsupplieruid, suppliername, unitprice FROM rawmaterialpurchaseorder WHERE rawmaterialpurchaseorderuid = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Map<String, String> map = new HashMap<>();
                map.put("rawmaterialsupplieruid", rs.getString("rawmaterialsupplieruid"));
                map.put("suppliername", rs.getString("suppliername"));
                map.put("unitprice", rs.getString("unitprice"));
                return map;
            }, uid);
        } catch (Exception e) {
            System.out.println("⚠️ No record found for UID: " + uid);
            return null;
        }
    }


    // ✅ Get all purchase order UIDs
    public List<String> getAllRawmaterialpurchaseorderUid() {
        String sql = "SELECT rawmaterialpurchaseorderuid FROM rawmaterialpurchaseorder";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // ✅ RowMapper Inner Class
 // ✅ RowMapper Inner Class
    public class InvoiceRowMapper implements RowMapper<Rawmaterialpurchaseinvoice> {
        @Override
        public Rawmaterialpurchaseinvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rawmaterialpurchaseinvoice invoice = new Rawmaterialpurchaseinvoice();

            invoice.setId(rs.getLong("id"));
            invoice.setRawmaterialpurchaseinvoiceuid(rs.getString("rawmaterialpurchaseinvoiceuid"));
            invoice.setRawmaterialpurchaseorderuid(rs.getString("rawmaterialpurchaseorderuid"));
            invoice.setInvoicedate(rs.getString("invoicedate"));
            invoice.setTaxamount(rs.getString("taxamount"));
            invoice.setTotalamount(rs.getString("totalamount"));
            invoice.setPaymentterms(rs.getString("paymentterms"));
            invoice.setRawmaterialname(rs.getString("rawmaterialname"));
            invoice.setRawmaterialreceiptnoteuid(rs.getString("rawmaterialreceiptnoteuid"));
            invoice.setRawmaterialsupplieruid(rs.getString("rawmaterialsupplieruid"));
            invoice.setSuppliername(rs.getString("suppliername"));
            invoice.setMaterialdetails(rs.getString("materialdetails"));
            
            // ✅ ADD THIS LINE:

            return invoice;
        }
    }

        
     // ✅ Get All Invoices with JOIN (Invoice + Order Details)
        public void getAllInvoiceWithOrderDetails() {
            String sql = "SELECT " +
                         "rlu.id, " +
                         "rlu.rawmaterialpurchaseinvoiceuid, " +
                         "rlu.rawmaterialpurchaseorderuid, " +
                         "po.rawmaterialsupplieruid, " +
                         "po.suppliername,"+
                         "po.materiallist, " +
                         "po.acceptedquantity, " +
                         "po.unitprice, " +
                         "rlu.invoicedate, " +
                         "rlu.quantityreceived, " +
                         "rlu.taxamount, " +
                         "rlu.totalamount, " +
                         "rlu.paymentterms, " +
                         "rlu.verifiedby " +
                         "FROM rawmaterialpurchaseinvoice rlu " +
                         "JOIN rawmaterialpurchaseorder po " +
                         "ON rlu.rawmaterialpurchaseorderuid = po.rawmaterialpurchaseorderuid";
        }
    

    public String findPurchaseOrderUidByRawMaterialUid(String rawMaterialUid) {
        String sql = "SELECT rawmaterialpurchaseorderuid FROM rawmaterialpurchaseorder WHERE rawmaterialuid = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, rawMaterialUid);
        } catch (Exception e) {
            System.out.println("No Purchase Order UID found for Raw Material UID: " + rawMaterialUid);
            return null;
        }
    }
    
    
   

    // ✅ Fetch all materials for a given rawmaterialpurchaseorderuid
    public List<Map<String, Object>> fetchMaterialsByPurchaseOrderUid(String rawmaterialpurchaseorderuid) {
        String sql = "SELECT rawmaterialdetails, rawmaterialpurchaseorderuid " +
                     "FROM rawmaterialreceiptnote WHERE rawmaterialpurchaseorderuid = ?";

        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, rawmaterialpurchaseorderuid);

        for (Map<String, Object> row : rows) {
            String rawMaterialDetailsJson = (String) row.get("rawmaterialdetails"); // JSON column value

            try {
                // Parse the JSON into Map
                Map<String, Map<String, Object>> materialMap =
                        objectMapper.readValue(rawMaterialDetailsJson, Map.class);

                for (Map.Entry<String, Map<String, Object>> entry : materialMap.entrySet()) {
                    String materialName = entry.getKey(); // e.g. "Steel Rod"
                    Map<String, Object> details = entry.getValue();

                    Map<String, Object> materialInfo = new HashMap<>();
                    materialInfo.put("rawmaterialname", materialName);
                    materialInfo.put("rawmaterialuid", details.get("rawmaterialuid"));
                    materialInfo.put("receivedquantity", details.get("quantity"));
                    materialInfo.put("rawmaterialpurchaseorderuid", rawmaterialpurchaseorderuid);

                    result.add(materialInfo);
                }
            } catch (Exception e) {
                System.err.println("⚠️ Failed to parse JSON for PO UID: " + rawmaterialpurchaseorderuid);
                e.printStackTrace();
            }
        }

        return result;
    }

 // 1. Just GRN UID list
   public List<String> getAllRawmaterialgrnUids() {
	    String sql = "SELECT DISTINCT rawmaterialreceiptnoteuid FROM rawmaterialreceiptnote";
	    return jdbcTemplate.queryForList(sql, String.class);
	}

    // 2. Material list by selected UID
   public List<Map<String, Object>> getRawMaterialsByGrnUid(String uid) {
	    String sql = 
	        "SELECT " +
	        "g.rawmaterialname, " +
	        "p.rawmaterialsupplieruid, " +
	        "p.suppliername, " +
	        "g.rawmaterialpurchaseorderuid " +
	        "FROM rawmaterialreceiptnote g " +
	        "JOIN rawmaterialpurchaseorder p " +
	        "ON g.rawmaterialpurchaseorderuid = p.rawmaterialpurchaseorderuid " + // ✅ added space
	        "WHERE g.rawmaterialreceiptnoteuid = ?";

	    return jdbcTemplate.queryForList(sql, uid);
	}



    public List<Map<String, String>> getAllEmployees() {
        String sql = "SELECT employeeuid, CONCAT(first_name, ' ', last_name) AS full_name FROM employee;\r\n"
        		+ "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, String> map = new HashMap<>();
            map.put("employeeuid", rs.getString("employeeuid"));
            map.put("full_name", rs.getString("full_name"));
            return map;
        });
    }
    
    public List<Map<String, Object>> getInvoiceDetailsByJoin() {
        String sql = "SELECT " +
                     "g.rawmaterialname, " +
                    // "g.orderedquantity AS receivedquantity, " +
                     "p.unitprice, " +
                     "p.rawmaterialsupplieruid, " +
                     "p.suppliername, " +
                     "g.rawmaterialpurchaseorderuid " +
                     "FROM rawmaterialreceiptnote g " +
                     "JOIN rawmaterialpurchaseorder p " +
                     "ON g.rawmaterialpurchaseorderuid = p.rawmaterialpurchaseorderuid";
        return jdbcTemplate.queryForList(sql);
    }


    public List<Map<String, Object>> getRawMaterialsByReceiptNoteUid(String rawmaterialreceiptnoteuid) {
        String sql = "SELECT rmpo.rawmaterialpurchaseorderuid, rmpo.rawmaterialsupplieruid, rmpo.suppliername, rmpo.materialdetails " +
                     "FROM rawmaterialreceiptnote rmrn " +
                     "JOIN rawmaterialpurchaseorder rmpo " +
                     "ON rmrn.rawmaterialpurchaseorderuid = rmpo.rawmaterialpurchaseorderuid " +
                     "WHERE rmrn.rawmaterialreceiptnoteuid = ?";

        List<Map<String, Object>> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, rawmaterialreceiptnoteuid);

        for (Map<String, Object> row : rows) {
            String purchaseOrderUid = (String) row.get("rawmaterialpurchaseorderuid"); // ✅ new
            String supplierUid = (String) row.get("rawmaterialsupplieruid");
            String supplierName = (String) row.get("suppliername");
            String rawMaterialDetailsJson = (String) row.get("materialdetails");

            try {
                Map<String, Map<String, Object>> materialMap = objectMapper.readValue(rawMaterialDetailsJson, Map.class);

                for (Map.Entry<String, Map<String, Object>> entry : materialMap.entrySet()) {
                    Map<String, Object> material = new HashMap<>();
                    material.put("rawmaterialpurchaseorderuid", purchaseOrderUid); // ✅ added
                    material.put("rawmaterialsupplieruid", supplierUid);
                    material.put("suppliername", supplierName);
                    material.put("rawmaterialname", entry.getKey());
                    material.put("receivedquantity", entry.getValue().get("quantity"));
                    material.put("price", entry.getValue().get("price"));
                    result.add(material);
                }
            } catch (Exception e) {
                System.err.println("❌ Error parsing JSON for receipt note: " + rawmaterialreceiptnoteuid);
                e.printStackTrace();
            }
        }

        return result;
    }


	
}
